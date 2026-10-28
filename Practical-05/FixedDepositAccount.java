public class FixedDepositAccount extends Account {
    public FixedDepositAccount(String accNo, String owner, long balance) {
        super(accNo, owner, balance);
    }

    @Override
    public double interestRate() { return 7.0; }

    @Override
    public boolean canWithdraw(long amount) { return false; }
}
