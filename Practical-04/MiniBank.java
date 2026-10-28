// Run this class to see the demonstration for this practical.
public class MiniBank {
    public static void main(String[] args) {
        System.out.println("isValidMobile('9876543210'): " + Validator.isValidMobile("9876543210"));
        System.out.println("isValidMobile('12345'): " + Validator.isValidMobile("12345"));
        System.out.println("isValidPan('ABCDE1234F'): " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("isValidIfsc('SBIN0001234'): " + Validator.isValidIfsc("SBIN0001234"));

        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Parsed: " + cmd);
        System.out.print(StatementFormatter.buildStatement(new Account("AC0001", "Diya", 5500)));
        System.out.println("Email valid: " + Validator.isValidEmail("diya@example.com"));

        Account acc = new Account("AC0001", "Diya", 4500);
        System.out.print(StatementFormatter.buildStatement(acc));
    }
}
