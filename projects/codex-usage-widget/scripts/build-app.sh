#!/bin/zsh
set -euo pipefail

SCRIPT_DIR="${0:A:h}"
PROJECT_DIR="${SCRIPT_DIR:h}"
APP_PATH="${PROJECT_DIR}/dist/Codex Usage.app"
EXECUTABLE_PATH="${APP_PATH}/Contents/MacOS/CodexUsageWidget"

if [[ -d "${APP_PATH}" ]]; then
    rm -rf "${APP_PATH}"
fi

mkdir -p "${APP_PATH}/Contents/MacOS"
cp "${PROJECT_DIR}/Resources/Info.plist" "${APP_PATH}/Contents/Info.plist"
swiftc \
    -parse-as-library \
    -swift-version 5 \
    -O \
    -framework AppKit \
    -framework Foundation \
    "${PROJECT_DIR}/Sources/CodexUsageWidget.swift" \
    -o "${EXECUTABLE_PATH}"

xattr -cr "${APP_PATH}"
codesign --force --deep --sign - "${APP_PATH}"
codesign --verify --deep --strict "${APP_PATH}"
echo "${APP_PATH}"
