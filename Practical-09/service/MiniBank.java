// Run this class to see the demonstration for this practical.
package service;
import model.Account;

public class MiniBank {
    public static void main(String[] args) throws InterruptedException {
        Account acc = new Account("AC0001", "Diya", 0);
        Thread[] workers = new Thread[10];

        for (int i = 0; i < 10; i++) {
            workers[i] = new Thread(new AccountWorker(acc, 1000, 1), "Worker-" + (i + 1));
            workers[i].start();
        }
        System.out.println("Started threads: " + workers[0].getName() + " .. " + workers[9].getName());

        for (Thread t : workers) {
            t.join();
        }
        System.out.println("Final synchronized balance: " + acc.getBalance());
    }
}
