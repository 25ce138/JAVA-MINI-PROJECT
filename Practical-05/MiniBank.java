// Run this class to see the demonstration for this practical.
public class MiniBank {
    public static void main(String[] args) {
        Account[] accounts = {
            new SavingsAccount("AC0001", "Diya", 5000, 1000),
            new CurrentAccount("AC0002", "Arjun", 2000, 5000),
            new FixedDepositAccount("AC0003", "Neel", 50000)
        };

        for (Account acc : accounts) {
            System.out.println(acc.getAccountNumber() + " (" + acc.getClass().getSimpleName()
                + ") -> Interest Rate: " + acc.interestRate() + "%");
            if (acc instanceof CurrentAccount ca) {
                System.out.println("   Overdraft Limit: Rs. " + ca.getOverdraftLimit());
            }
        }
    }
}
