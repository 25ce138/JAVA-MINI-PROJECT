// Account stores the details and balance of one bank account.
package model;
import exception.*;

public abstract class Account {
    protected final String accountNumber;
    protected String ownerName;
    protected long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException(amount);
        balance += amount;
    }

    public void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException(amount);
        if (amount > balance) throw new InsufficientFundsException(amount - balance);
        balance -= amount;
    }

    public void transfer(Account to, long amount) throws BankException {
        try {
            this.withdraw(amount);
            to.deposit(amount);
        } catch (BankException e) {
            throw e;
        } finally {
            System.out.println("Transfer attempt logged for " + accountNumber);
        }
    }

    public String getAccountNumber() { return accountNumber; }
    public long getBalance() { return balance; }
}
