@echo off
cd /d "%~dp0"
if not exist build mkdir build
javac -d build model\Account.java model\Customer.java service\MiniBank.java util\ReportGenerator.java util\StatePersister.java util\TransactionLog.java
if errorlevel 1 exit /b 1
java -cp build service.MiniBank
