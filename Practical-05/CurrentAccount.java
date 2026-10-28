public class CurrentAccount extends Account {
    private final long overdraftLimit;

    public CurrentAccount(String accNo, String owner, long balance, long overdraftLimit) {
        super(accNo, owner, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() { return 0.0; }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0 && (balance - amount) >= -overdraftLimit;
    }

    public long getOverdraftLimit() { return overdraftLimit; }
}
