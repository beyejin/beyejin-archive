import AppKit
import Foundation

private struct UsageSnapshot {
    let usedPercent: Double
    let windowMinutes: Int
    let resetsAt: Date?
    let observedAt: Date?
    let sourceURL: URL

    var remainingPercent: Double {
        min(100, max(0, 100 - usedPercent))
    }
}

private enum UsageReader {
    private static let weeklyWindowMinutes = 7 * 24 * 60
    private static let tailByteCount: UInt64 = 2_000_000

    static func latestWeeklyUsage() -> UsageSnapshot? {
        let sessionsURL = FileManager.default.homeDirectoryForCurrentUser
            .appendingPathComponent(".codex/sessions", isDirectory: true)
        let keys: [URLResourceKey] = [.contentModificationDateKey, .isRegularFileKey]

        guard let enumerator = FileManager.default.enumerator(
            at: sessionsURL,
            includingPropertiesForKeys: keys,
            options: [.skipsHiddenFiles]
        ) else {
            return nil
        }

        var candidates: [(url: URL, modifiedAt: Date)] = []
        for case let url as URL in enumerator where url.pathExtension == "jsonl" {
            guard let values = try? url.resourceValues(forKeys: Set(keys)),
                  values.isRegularFile == true else {
                continue
            }
            candidates.append((url, values.contentModificationDate ?? .distantPast))
        }

        candidates.sort { $0.modifiedAt > $1.modifiedAt }

        var newest: UsageSnapshot?
        for candidate in candidates.prefix(80) {
            guard let snapshot = latestWeeklyUsage(in: candidate.url) else {
                continue
            }

            if newest == nil || snapshotDate(snapshot) > snapshotDate(newest!) {
                newest = snapshot
            }
        }
        return newest
    }

    private static func snapshotDate(_ snapshot: UsageSnapshot) -> Date {
        snapshot.observedAt
            ?? (try? snapshot.sourceURL.resourceValues(forKeys: [.contentModificationDateKey])
                .contentModificationDate)
            ?? .distantPast
    }

    private static func latestWeeklyUsage(in url: URL) -> UsageSnapshot? {
        guard let handle = try? FileHandle(forReadingFrom: url) else {
            return nil
        }
        defer { try? handle.close() }

        let size = (try? handle.seekToEnd()) ?? 0
        let offset = size > tailByteCount ? size - tailByteCount : 0
        do {
            try handle.seek(toOffset: offset)
        } catch {
            return nil
        }

        guard let data = try? handle.readToEnd(),
              var text = String(data: data, encoding: .utf8) else {
            return nil
        }

        if offset > 0, let firstNewline = text.firstIndex(of: "\n") {
            text = String(text[text.index(after: firstNewline)...])
        }

        for line in text.split(separator: "\n").reversed() {
            guard line.contains("\"rate_limits\""),
                  let data = String(line).data(using: .utf8),
                  let root = try? JSONSerialization.jsonObject(with: data) as? [String: Any],
                  let payload = root["payload"] as? [String: Any],
                  let rateLimits = payload["rate_limits"] as? [String: Any] else {
                continue
            }

            let limits = ["primary", "secondary", "individual_limit"]
                .compactMap { rateLimits[$0] as? [String: Any] }
                .compactMap(parseLimit)

            guard let weekly = limits
                .filter({ $0.windowMinutes >= weeklyWindowMinutes })
                .min(by: {
                    abs($0.windowMinutes - weeklyWindowMinutes)
                        < abs($1.windowMinutes - weeklyWindowMinutes)
                })
                ?? limits.max(by: { $0.windowMinutes < $1.windowMinutes }) else {
                continue
            }

            return UsageSnapshot(
                usedPercent: weekly.usedPercent,
                windowMinutes: weekly.windowMinutes,
                resetsAt: weekly.resetsAt,
                observedAt: parseTimestamp(root["timestamp"]),
                sourceURL: url
            )
        }
        return nil
    }

    private static func parseLimit(_ dictionary: [String: Any]) -> (
        usedPercent: Double,
        windowMinutes: Int,
        resetsAt: Date?
    )? {
        guard let usedPercent = (dictionary["used_percent"] as? NSNumber)?.doubleValue,
              let windowMinutes = (dictionary["window_minutes"] as? NSNumber)?.intValue else {
            return nil
        }

        let resetsAt = (dictionary["resets_at"] as? NSNumber)
            .map { Date(timeIntervalSince1970: $0.doubleValue) }
        return (usedPercent, windowMinutes, resetsAt)
    }

    private static func parseTimestamp(_ value: Any?) -> Date? {
        guard let value = value as? String else {
            return nil
        }

        let fractional = ISO8601DateFormatter()
        fractional.formatOptions = [.withInternetDateTime, .withFractionalSeconds]
        return fractional.date(from: value) ?? ISO8601DateFormatter().date(from: value)
    }
}

private final class UsageProgressView: NSView {
    var remainingPercent: Double = 0 {
        didSet { needsDisplay = true }
    }

    override var intrinsicContentSize: NSSize {
        NSSize(width: NSView.noIntrinsicMetric, height: 9)
    }

    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)

        let trackRect = bounds.insetBy(dx: 0, dy: 1)
        let radius = trackRect.height / 2
        NSColor.quaternaryLabelColor.setFill()
        NSBezierPath(roundedRect: trackRect, xRadius: radius, yRadius: radius).fill()

        let ratio = min(1, max(0, remainingPercent / 100))
        guard ratio > 0 else {
            return
        }

        var fillRect = trackRect
        fillRect.size.width *= ratio
        let color: NSColor
        switch remainingPercent {
        case 50...:
            color = .systemGreen
        case 20..<50:
            color = .systemOrange
        default:
            color = .systemRed
        }
        color.setFill()
        NSBezierPath(roundedRect: fillRect, xRadius: radius, yRadius: radius).fill()
    }
}

@MainActor
private final class UsagePopoverController: NSViewController {
    var onRefresh: (() -> Void)?
    var onQuit: (() -> Void)?

    private let titleLabel = NSTextField(labelWithString: "주간 사용량 확인 중…")
    private let progressView = UsageProgressView()
    private let detailLabel = NSTextField(labelWithString: "")
    private let resetLabel = NSTextField(labelWithString: "")
    private let updatedLabel = NSTextField(labelWithString: "")

    override func loadView() {
        view = NSView(frame: NSRect(x: 0, y: 0, width: 300, height: 178))

        titleLabel.font = .systemFont(ofSize: 15, weight: .semibold)
        titleLabel.maximumNumberOfLines = 1

        detailLabel.font = .monospacedDigitSystemFont(ofSize: 12, weight: .regular)
        detailLabel.textColor = .secondaryLabelColor
        resetLabel.font = .systemFont(ofSize: 12)
        resetLabel.textColor = .secondaryLabelColor
        updatedLabel.font = .systemFont(ofSize: 11)
        updatedLabel.textColor = .tertiaryLabelColor

        let refreshButton = NSButton(title: "새로고침", target: self, action: #selector(refresh))
        refreshButton.bezelStyle = .rounded
        refreshButton.controlSize = .small

        let quitButton = NSButton(title: "종료", target: self, action: #selector(quit))
        quitButton.bezelStyle = .rounded
        quitButton.controlSize = .small

        let spacer = NSView()
        let buttonRow = NSStackView(views: [refreshButton, spacer, quitButton])
        buttonRow.orientation = .horizontal
        buttonRow.alignment = .centerY

        let stack = NSStackView(views: [
            titleLabel,
            progressView,
            detailLabel,
            resetLabel,
            updatedLabel,
            buttonRow
        ])
        stack.orientation = .vertical
        stack.alignment = .leading
        stack.spacing = 8
        stack.translatesAutoresizingMaskIntoConstraints = false
        progressView.translatesAutoresizingMaskIntoConstraints = false
        buttonRow.translatesAutoresizingMaskIntoConstraints = false

        view.addSubview(stack)
        NSLayoutConstraint.activate([
            stack.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),
            stack.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            stack.topAnchor.constraint(equalTo: view.topAnchor, constant: 16),
            progressView.widthAnchor.constraint(equalTo: stack.widthAnchor),
            buttonRow.widthAnchor.constraint(equalTo: stack.widthAnchor)
        ])
    }

    func update(with snapshot: UsageSnapshot?) {
        guard let snapshot else {
            titleLabel.stringValue = "주간 사용량을 찾지 못했습니다"
            progressView.remainingPercent = 0
            detailLabel.stringValue = "Codex에서 요청을 한 번 보내면 갱신됩니다."
            resetLabel.stringValue = ""
            updatedLabel.stringValue = ""
            return
        }

        let remaining = Int(snapshot.remainingPercent.rounded())
        let used = Int(snapshot.usedPercent.rounded())
        titleLabel.stringValue = "주간 사용량 \(remaining)% 남음"
        progressView.remainingPercent = snapshot.remainingPercent
        detailLabel.stringValue = "\(used)% 사용 · \(remaining)% 남음"

        if let resetsAt = snapshot.resetsAt {
            resetLabel.stringValue = "초기화 \(Self.resetFormatter.string(from: resetsAt))"
        } else {
            resetLabel.stringValue = "초기화 시각 정보 없음"
        }

        if let observedAt = snapshot.observedAt {
            updatedLabel.stringValue = "Codex 로그 기준 · \(Self.updatedFormatter.string(from: observedAt)) 갱신"
        } else {
            updatedLabel.stringValue = "Codex 로그 기준"
        }
    }

    @objc private func refresh() {
        onRefresh?()
    }

    @objc private func quit() {
        onQuit?()
    }

    private static let resetFormatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.locale = Locale(identifier: "ko_KR")
        formatter.timeZone = .current
        formatter.dateFormat = "M월 d일 EEE a h:mm"
        return formatter
    }()

    private static let updatedFormatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.locale = Locale(identifier: "ko_KR")
        formatter.timeZone = .current
        formatter.dateFormat = "M/d HH:mm"
        return formatter
    }()
}

@MainActor
private final class AppDelegate: NSObject, NSApplicationDelegate {
    private let statusItem = NSStatusBar.system.statusItem(withLength: NSStatusItem.variableLength)
    private let popover = NSPopover()
    private let popoverController = UsagePopoverController()
    private let readerQueue = DispatchQueue(label: "com.hanyejin.codex-usage-widget.reader", qos: .utility)
    private var timer: Timer?

    func applicationDidFinishLaunching(_ notification: Notification) {
        NSApplication.shared.setActivationPolicy(.accessory)

        if let button = statusItem.button {
            button.title = "주간 --% 남음"
            button.font = .monospacedDigitSystemFont(ofSize: 12, weight: .medium)
            button.target = self
            button.action = #selector(togglePopover)
            button.toolTip = "Codex 주간 사용량"
        }

        popover.behavior = .transient
        popover.contentSize = NSSize(width: 300, height: 178)
        popover.contentViewController = popoverController

        popoverController.onRefresh = { [weak self] in
            self?.refresh()
        }
        popoverController.onQuit = {
            NSApplication.shared.terminate(nil)
        }

        refresh()
        timer = Timer.scheduledTimer(
            timeInterval: 60,
            target: self,
            selector: #selector(refreshFromTimer),
            userInfo: nil,
            repeats: true
        )
    }

    func applicationWillTerminate(_ notification: Notification) {
        timer?.invalidate()
    }

    @objc private func togglePopover() {
        guard let button = statusItem.button else {
            return
        }

        if popover.isShown {
            popover.performClose(nil)
        } else {
            refresh()
            popover.show(relativeTo: button.bounds, of: button, preferredEdge: .minY)
            popover.contentViewController?.view.window?.makeKey()
        }
    }

    @objc private func refreshFromTimer() {
        refresh()
    }

    private func refresh() {
        statusItem.button?.toolTip = "Codex 주간 사용량 · 갱신 중"
        readerQueue.async { [weak self] in
            let snapshot = UsageReader.latestWeeklyUsage()
            DispatchQueue.main.async {
                guard let self else {
                    return
                }
                self.render(snapshot)
            }
        }
    }

    private func render(_ snapshot: UsageSnapshot?) {
        popoverController.update(with: snapshot)

        guard let snapshot else {
            statusItem.button?.title = "주간 --% 남음"
            statusItem.button?.toolTip = "Codex 주간 사용량을 찾지 못했습니다"
            return
        }

        if let resetsAt = snapshot.resetsAt, resetsAt < Date() {
            statusItem.button?.title = "주간 갱신 대기"
            statusItem.button?.toolTip = "Codex에서 새 요청 후 사용량이 갱신됩니다"
            return
        }

        let remaining = Int(snapshot.remainingPercent.rounded())
        statusItem.button?.title = "주간 \(remaining)% 남음"
        statusItem.button?.toolTip = "Codex 주간 사용량 \(remaining)% 남음"
    }
}

@main
private enum CodexUsageWidget {
    @MainActor
    static func main() {
        let application = NSApplication.shared
        let delegate = AppDelegate()
        application.delegate = delegate
        application.run()
        _ = delegate
    }
}
