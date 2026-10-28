package util;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {
    public static String generate(Path logsDir, Path reportFile) throws IOException {
        long totalDeposits = 0;
        long totalWithdrawals = 0;
        StringBuilder sb = new StringBuilder("=== End-of-Day Summary Report ===\n");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(logsDir, "*.log")) {
            for (Path logPath : stream) {
                BasicFileAttributes attr = Files.readAttributes(logPath, BasicFileAttributes.class);
                sb.append("File: ").append(logPath.getFileName())
                  .append(" (").append(attr.size()).append(" bytes)\n");
                try (BufferedReader reader = Files.newBufferedReader(logPath)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.trim().split("\\s+");
                        if (parts.length == 3) {
                            long amt = Long.parseLong(parts[2]);
                            if ("DEPOSIT".equalsIgnoreCase(parts[0])) totalDeposits += amt;
                            else if ("WITHDRAW".equalsIgnoreCase(parts[0])) totalWithdrawals += amt;
                        }
                    }
                }
            }
        }
        sb.append("Total Deposits    : Rs. ").append(totalDeposits).append("\n")
          .append("Total Withdrawals : Rs. ").append(totalWithdrawals).append("\n")
          .append("Net Change        : Rs. ").append(totalDeposits - totalWithdrawals).append("\n");
        Files.writeString(reportFile, sb.toString());
        return sb.toString();
    }
}
