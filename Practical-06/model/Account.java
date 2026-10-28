// Account stores the details and balance of one bank account.
package model;

public abstract class Account implements Transactable, InterestBearing {
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

    @Override
    public void deposit(long amount) {
        if (amount > 0) balance += amount;
    }

    @Override
    public boolean withdraw(long amount) {
        if (amount > 0 && canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    @Override
    public long getBalance() { return balance; }
}
