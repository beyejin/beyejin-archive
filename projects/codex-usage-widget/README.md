# Codex Usage Widget

Codex 로컬 세션 로그에서 주간 사용량을 읽어 macOS 메뉴 막대에 표시하는 작은 앱입니다.

현재는 Codex와 Claude 여러 계정을 지원하는 [AI Usage Banner](https://github.com/beyejin/ai-usage-banner)로 대체되어 보관 중입니다.

## 기능

- Codex 주간 잔량을 메뉴 막대에 표시
- 사용률, 잔량, 초기화 시각을 팝오버로 확인
- 60초마다 자동 갱신
- 선택적으로 로그인 시 자동 실행

사용량은 `~/.codex/sessions`의 최근 JSONL 로그에서 읽으며 외부 서버로 전송하지 않습니다.

## 요구 사항

- macOS 13 이상
- Xcode Command Line Tools

## 빌드

```bash
./scripts/build-app.sh
```

빌드 결과는 `dist/Codex Usage.app`에 생성됩니다.

## 설치

```bash
./scripts/install.sh
```

앱은 `~/Applications/Codex Usage.app`에 설치되고 로그인 시 자동 실행됩니다.
