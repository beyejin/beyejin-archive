#!/bin/zsh
set -euo pipefail

SCRIPT_DIR="${0:A:h}"
PROJECT_DIR="${SCRIPT_DIR:h}"
BUILT_APP="${PROJECT_DIR}/dist/Codex Usage.app"
INSTALLED_APP="${HOME}/Applications/Codex Usage.app"
LAUNCH_AGENT="${HOME}/Library/LaunchAgents/com.hanyejin.codex-usage-widget.plist"
LABEL="com.hanyejin.codex-usage-widget"

"${SCRIPT_DIR}/build-app.sh"
mkdir -p "${HOME}/Applications" "${HOME}/Library/LaunchAgents" "${HOME}/Library/Logs"
ditto "${BUILT_APP}" "${INSTALLED_APP}"
cp "${PROJECT_DIR}/Resources/LaunchAgent.plist" "${LAUNCH_AGENT}"
/usr/libexec/PlistBuddy -c \
    "Set :ProgramArguments:0 ${INSTALLED_APP}/Contents/MacOS/CodexUsageWidget" \
    "${LAUNCH_AGENT}"
/usr/libexec/PlistBuddy -c \
    "Set :StandardOutPath ${HOME}/Library/Logs/CodexUsageWidget.log" \
    "${LAUNCH_AGENT}"
/usr/libexec/PlistBuddy -c \
    "Set :StandardErrorPath ${HOME}/Library/Logs/CodexUsageWidget.error.log" \
    "${LAUNCH_AGENT}"

launchctl bootout "gui/$(id -u)/${LABEL}" 2>/dev/null || true
launchctl bootstrap "gui/$(id -u)" "${LAUNCH_AGENT}"
launchctl kickstart -k "gui/$(id -u)/${LABEL}"
echo "${INSTALLED_APP}"
