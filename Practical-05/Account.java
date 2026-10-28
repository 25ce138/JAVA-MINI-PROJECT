// Account stores the details and balance of one bank account.
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

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
}
