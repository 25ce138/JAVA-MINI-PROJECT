package tests;
import service.BankService;
import exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {
    BankService bank() throws Exception {
        BankService b = new BankService();
        b.openAccount("AC0001", "Diya", "9876543210", 5000);
        b.openAccount("AC0002", "Arjun", "9876543211", 2000);
        return b;
    }
    @Test void depositIncreasesBalance() throws Exception {
        BankService b = bank(); b.deposit("AC0001",1500);
        assertEquals(6500,b.getRequired("AC0001").getBalance());
    }
    @Test void overLimitWithdrawalIsRejected() throws Exception {
        BankService b = bank();
        assertThrows(InsufficientFundsException.class, () -> b.withdraw("AC0001",6000));
        assertEquals(5000,b.getRequired("AC0001").getBalance());
    }
    @Test void transferPreservesTotal() throws Exception {
        BankService b = bank(); b.transfer("AC0001","AC0002",1000);
        assertEquals(4000,b.getRequired("AC0001").getBalance());
        assertEquals(3000,b.getRequired("AC0002").getBalance());
    }
    @Test void failedTransferDoesNotDebit() throws Exception {
        BankService b = bank();
        assertThrows(AccountNotFoundException.class, () -> b.transfer("AC0001","AC9999",500));
        assertEquals(5000,b.getRequired("AC0001").getBalance());
    }
    @Test void invalidInputsAreRejected() throws Exception {
        BankService b = bank();
        assertThrows(InvalidAmountException.class, () -> b.deposit("AC0001",0));
        assertThrows(InvalidAmountException.class, () -> b.withdraw("AC0001",-1));
        assertThrows(BankException.class, () -> b.openAccount("AC0001","Diya","9876543210",0));
        assertThrows(BankException.class, () -> b.openAccount("AC0003","Diya","123",0));
    }
    @Test void concurrentDepositsKeepExactBalance() throws Exception {
        BankService b = bank();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        java.util.List<Future<?>> jobs = new java.util.ArrayList<>();
        try {
            for (int i=0;i<10;i++) jobs.add(pool.submit(() -> {
                for (int j=0;j<100;j++) {
                    try { b.deposit("AC0001",10); }
                    catch (BankException e) { throw new RuntimeException(e); }
                }
            }));
            for (Future<?> job : jobs) job.get();
        } finally { pool.shutdownNow(); }
        assertEquals(15000,b.getRequired("AC0001").getBalance());
    }
    @Test void persistenceRoundTrip(@TempDir Path dir) throws Exception {
        BankService b = bank(); b.deposit("AC0001",500);
        Path file=dir.resolve("accounts.dat"); b.save(file);
        BankService restored=new BankService(); restored.load(file);
        assertEquals(5500,restored.getRequired("AC0001").getBalance());
        assertEquals(2,restored.listAccounts().size());
    }
}
