// Run this class to see the demonstration for this practical.
package service;
import model.Account;
import util.*;
import java.nio.file.*;
import java.util.Arrays;

public class MiniBank {
    public static void main(String[] args) throws Exception {
        Path dataDir = Path.of("data");
        Files.createDirectories(dataDir);
        Path accFile = dataDir.resolve("accounts.dat");
        Path logFile = dataDir.resolve("tx_2026_10_21.log");
        Files.deleteIfExists(logFile);

        Account[] accounts = {
            new Account("AC0001", "Diya", 5500),
            new Account("AC0002", "Arjun", 2800)
        };
        StatePersister.save(accounts, accFile);
        Account[] reloaded = StatePersister.load(accFile);
        System.out.println("Reloaded: " + Arrays.toString(reloaded));

        TransactionLog.append(logFile, "DEPOSIT AC0001 500");
        TransactionLog.append(logFile, "WITHDRAW AC0002 200");
        System.out.print(ReportGenerator.generate(dataDir, dataDir.resolve("report.txt")));
    }
}
