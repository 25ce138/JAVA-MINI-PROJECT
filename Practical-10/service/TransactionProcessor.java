package service;
import model.Account;
import java.util.concurrent.*;

public class TransactionProcessor {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);

    public void submit(Runnable task) {
        pool.execute(task);
    }

    public void stop() throws InterruptedException {
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void transferOrdered(Account from, Account to, long amount) {
        Account first = from.getAccountNumber().compareTo(to.getAccountNumber()) < 0 ? from : to;
        Account second = (first == from) ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                }
            }
        }
    }
}
