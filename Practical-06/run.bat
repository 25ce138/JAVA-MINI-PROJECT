@echo off
cd /d "%~dp0"
if not exist build mkdir build
javac -d build model\Account.java model\InterestBearing.java model\Premium.java model\SavingsAccount.java model\Transactable.java service\MiniBank.java service\WithdrawRule.java util\Validator.java
if errorlevel 1 exit /b 1
java -cp build service.MiniBank
