# 내일배움캠프 Spring Security 학습 프로젝트

사용자가 제공한 다운로드 폴더의 `nbcam_addvanced_1` 소스 스냅샷입니다. Servlet Filter, JWT 인증과 역할별 인가, 사용자 소유권을 확인하는 Interceptor를 학습하는 예제입니다.

## 보관 기준

1. 보관일: 2026년 10월 6일
2. 원본: [DongHyunKIM-Hi/nbcam_addvanced_1](https://github.com/DongHyunKIM-Hi/nbcam_addvanced_1)
3. 로컬 기준 커밋: `6ccd39bb9dfca01f9dc6fac4f4d87081586d1107`
4. 로컬 브랜치: `master`. 당시 로컬의 원격 추적 브랜치보다 10개 커밋 뒤에 있었으며, 최신 코드로 갱신하지 않고 제공된 상태를 보관했습니다.

## 구성

| 위치 | 내용 |
| --- | --- |
| `common/config` | Spring Security와 MVC 설정 |
| `common/filter` | Servlet Filter와 JWT 인증 처리 |
| `common/interceptor` | 요청 사용자와 대상 사용자의 소유권 확인 |
| `user` | 로그인, 사용자 API, 서비스와 저장소 |
| `gradle`, `gradlew`, `build.gradle` | Gradle 실행 파일과 의존성 설정 |

`build.gradle`에서 확인한 환경은 Java 17, Spring Boot 3.5.6, Spring Security, Spring Data JPA, MySQL, JJWT 0.12.5입니다.

## 다시 실행할 때

MySQL의 `nbcam` 데이터베이스와 로컬 실행 환경을 준비하고 `DB_PASSWORD`, `JWT_SECRET_KEY` 환경변수를 설정한 뒤 프로젝트 폴더에서 실행합니다.

```sh
./gradlew bootRun
```

JWT 키는 코드의 HS256 설정에 맞는 Base64 문자열을 사용해야 합니다. DB 비밀번호와 JWT 키의 원래 값은 공개 보관본에 포함하지 않았습니다. `application.yml`의 나머지 설정은 원문대로 보관했으며, 이 작업에서는 애플리케이션을 실행하지 않았습니다.

## 원본과 차이

소스, Gradle 실행 파일과 `HELP.md`를 보관했습니다. Git 내부 자료, Gradle 캐시, 빌드 결과, IDE 설정과 Finder 메타데이터는 공개 스냅샷에서 제외했습니다. 다운로드 폴더 자체는 검증 후 복구 가능한 휴지통으로 이동합니다.

[출처와 보관 경계](SOURCE.md), [파일별 SHA-256](MANIFEST.json), [기존 참고 링크](HELP.md)를 함께 확인할 수 있습니다.
