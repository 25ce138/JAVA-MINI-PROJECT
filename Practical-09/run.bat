@echo off
cd /d "%~dp0"
if not exist build mkdir build
javac -d build model\Account.java service\AccountWorker.java service\MiniBank.java
if errorlevel 1 exit /b 1
java -cp build service.MiniBank
