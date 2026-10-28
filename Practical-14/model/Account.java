// Account stores the details and balance of one bank account.
package model;
import exception.*;
import model.annotation.Id;
import java.io.Serializable;

public class Account implements Serializable, Comparable<Account> {
    private static final long serialVersionUID = 1L;

    @Id
    private final String accountNumber;
    private final String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException(amount);
        balance += amount;
    }

    public synchronized void depositSync(long amount) {
        if (amount > 0) balance += amount;
    }

    public synchronized void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException(amount);
        if (amount > balance) throw new InsufficientFundsException(amount - balance);
        balance -= amount;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public synchronized long getBalance() { return balance; }

    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }
}
