@echo off
cd /d "%~dp0"
if not exist build mkdir build
javac -d build MiniBank.java
if errorlevel 1 exit /b 1
java -cp build MiniBank
