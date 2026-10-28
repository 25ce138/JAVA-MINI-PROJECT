# MiniBank Java Practicals

A beginner-friendly Java OOP project based on the supplied MiniBank practical sample. Each practical is a separate program. Do not compile all practical folders together: several deliberately reuse the same class names.

## Start here on Windows
1. Install a JDK version 17 or newer, and check `java -version` and `javac -version`.
2. Extract this ZIP. Open a terminal in the chosen practical folder.
3. Run `run.bat`. On Linux or macOS run `bash run.sh`.
4. For the complete application use `Practical-14`. Enter account IDs such as AC0001, a name, a ten-digit demo mobile number starting with 6–9, and an opening balance.
5. Select 0 to save and exit. Run it again to reload your accounts.

Amounts are whole rupees in long variables. Use fictional data. This is a classroom demonstration, without login or production banking security. The saved object file is for your own local runs; do not load untrusted files.

## Folders
- `Practical-01` — Menu shell
- `Practical-02` — Customer and account encapsulation
- `Practical-03` — Object methods and nested class
- `Practical-04` — Validation and command parsing
- `Practical-05` — Inheritance and polymorphism
- `Practical-06` — Interfaces packages and runnable JAR
- `Practical-07` — Annotations and reflection
- `Practical-08` — Exception handling
- `Practical-09` — Threads and synchronization
- `Practical-10` — Thread pool and deadlock prevention
- `Practical-11` — File handling and serialization
- `Practical-12` — Collections and sorting
- `Practical-13` — Generic repository
- `Practical-14` — Complete MiniBank application

- `screenshots` — PNG images rendered from actual captured console output, not desktop screenshots.
- `outputs` — original captured text and test results for comparison.
- `docs` — Word report with aims, complete source listings and output images.

## Practical 6 runnable JAR
After running its build once, open a terminal in Practical-06:
```text
jar cfm minibank.jar MANIFEST.MF -C build .
java -jar minibank.jar
```
Use the same command in Practical-14 to package the final application.

## JUnit 5 tests
The seven tests are in Practical-14/tests/BankServiceTest.java. Download the JUnit Platform Console Standalone JAR from the official JUnit project and name it junit-platform-console-standalone.jar in Practical-14. Run run.bat once, then on Windows:
```text
javac -cp "build;junit-platform-console-standalone.jar" -d build tests/BankServiceTest.java
java -jar junit-platform-console-standalone.jar --class-path build --scan-class-path
```
On Linux/macOS use a colon instead of the semicolon in the compiler classpath. No test dependency is required to run the application. The report's tests ran with JUnit Jupiter 5.10.1.

## Beginner notes
Read Practical-01 first and proceed in order. Records, lambdas, annotations, reflection, generics and threads appear because the reference practicals require them. The final menu uses an ordinary switch statement. Comments explain the main steps.

## GitHub
See GITHUB_GUIDE.md. Repository: https://github.com/25ce138/JAVA-MINI-PROJECT. Review your identity and repository URL before committing. Generated class files and personal account data are ignored.
