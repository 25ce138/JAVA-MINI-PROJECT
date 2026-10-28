@echo off
cd /d "%~dp0"
if not exist build mkdir build
javac -d build exception\AccountNotFoundException.java exception\BankException.java exception\InsufficientFundsException.java exception\InvalidAmountException.java model\Account.java model\annotation\Audit.java model\annotation\Id.java service\BankService.java service\MiniBank.java service\Repository.java util\StatementFormatter.java util\Validator.java
if errorlevel 1 exit /b 1
java -cp build service.MiniBank
