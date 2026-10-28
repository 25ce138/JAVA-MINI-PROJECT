// Run this class to see the demonstration for this practical.
package service;
import model.Account;

public class MiniBank {
    public static void main(String[] args) {
        AccountStore store = new AccountStore();
        store.add(new Account("AC0001", "Diya", 5000));
        store.add(new Account("AC0002", "Arjun", 2000));
        store.add(new Account("AC0003", "Neel", 9000));

        System.out.println("Lookup AC0002: " + store.get("AC0002"));
        System.out.println("Accounts sorted by balance (descending):");
        for (Account a : store.sortedByBalance()) {
            System.out.println("  " + a);
        }
    }
}
