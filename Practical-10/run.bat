@echo off
cd /d "%~dp0"
if not exist build mkdir build
javac -d build model\Account.java service\MiniBank.java service\TransactionBuffer.java service\TransactionProcessor.java
if errorlevel 1 exit /b 1
java -cp build service.MiniBank
