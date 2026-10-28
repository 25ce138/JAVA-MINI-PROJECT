public class CommandParser {
    public static Command parse(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Expected 3 tokens: TYPE ACCOUNT AMOUNT");
        }
        return new Command(
            TransactionType.valueOf(parts[0].toUpperCase()),
            parts[1],
            Long.parseLong(parts[2])
        );
    }
}
