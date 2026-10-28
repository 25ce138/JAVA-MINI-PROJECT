package util;
import java.io.IOException;
import java.nio.file.*;

public class TransactionLog {
    public static void append(Path file, String line) throws IOException {
        Files.writeString(file, line + System.lineSeparator(),
            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
