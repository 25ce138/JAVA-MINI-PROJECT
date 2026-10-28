@echo off
cd /d "%~dp0"
if not exist build mkdir build
javac -d build model\Account.java model\SavingsAccount.java model\annotation\Id.java model\annotation\MaxLength.java model\annotation\Positive.java service\MiniBank.java util\AnnotationValidator.java
if errorlevel 1 exit /b 1
java -cp build service.MiniBank
