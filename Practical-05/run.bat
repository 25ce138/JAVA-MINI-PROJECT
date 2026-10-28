@echo off
cd /d "%~dp0"
if not exist build mkdir build
javac -d build Account.java CurrentAccount.java FixedDepositAccount.java MiniBank.java SavingsAccount.java
if errorlevel 1 exit /b 1
java -cp build MiniBank
