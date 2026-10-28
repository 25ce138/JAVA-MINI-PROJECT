// Run this class to see the demonstration for this practical.
package service;
import model.Account;

public class MiniBank {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account("AC0001", "Diya", 5000);
        Account b = new Account("AC0002", "Arjun", 5000);

        TransactionProcessor processor = new TransactionProcessor();
        for (int i = 0; i < 5; i++) {
            processor.submit(() -> TransactionProcessor.transferOrdered(a, b, 200));
            processor.submit(() -> TransactionProcessor.transferOrdered(b, a, 100));
        }
        processor.stop();
        TransactionBuffer buffer = new TransactionBuffer();
        Thread consumer = new Thread(() -> {
            try { System.out.println("Consumed: " + buffer.take()); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        consumer.start();
        buffer.put("DEPOSIT AC0001 100");
        consumer.join();

        System.out.println("Ordered transfers finished without deadlock.");
        System.out.println("Account AC0001 Balance: " + a.getBalance());
        System.out.println("Account AC0002 Balance: " + b.getBalance());
    }
}
