// Account stores the details and balance of one bank account.
package model;

public class Account {
    private final String accountNumber;
    private final String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public synchronized void deposit(long amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(long amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized long getBalance() { return balance; }
}
