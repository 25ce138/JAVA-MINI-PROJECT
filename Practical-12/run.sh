#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
mkdir -p build
find . -name "*.java" ! -path "./tests/*" > sources.txt
javac -d build @sources.txt
java -cp build service.MiniBank
