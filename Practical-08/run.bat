@echo off
cd /d "%~dp0"
if not exist build mkdir build
javac -d build exception\AccountNotFoundException.java exception\BankException.java exception\InsufficientFundsException.java exception\InvalidAmountException.java model\Account.java model\SavingsAccount.java service\MiniBank.java
if errorlevel 1 exit /b 1
java -cp build service.MiniBank
