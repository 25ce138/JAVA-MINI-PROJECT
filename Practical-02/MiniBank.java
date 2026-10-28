// Run this class to see the demonstration for this practical.
public class MiniBank {
    public static void main(String[] args) {
        Account[] accounts = {
            new Account("Diya", 5000),
            new Account("Arjun", 3000),
            new Account("Neel")
        };

        accounts[0].deposit(2000);
        System.out.println(accounts[0].getAccountNumber() + " after deposit(2000): " + accounts[0].getBalance());

        for (Account a : accounts) System.out.println(a.getAccountNumber() + " | " + a.getOwnerName() + " | " + a.getBalance());
        boolean ok1 = accounts[0].withdraw(3000);
        System.out.println("Withdraw 3000 (" + ok1 + ") -> Balance: " + accounts[0].getBalance());

        boolean ok2 = accounts[0].withdraw(10000);
        System.out.println("Withdraw 10000 (" + ok2 + ") -> Balance: " + accounts[0].getBalance());
    }
}
