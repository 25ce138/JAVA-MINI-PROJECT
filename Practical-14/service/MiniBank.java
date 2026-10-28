// Run this class to see the demonstration for this practical.
package service;
import exception.BankException;
import java.nio.file.Path;
import java.util.Scanner;

public class MiniBank {
    private static String ask(Scanner s, String label) {
        System.out.print(label + ": ");
        if (!s.hasNextLine()) throw new java.util.NoSuchElementException();
        return s.nextLine().trim();
    }
    private static long amount(Scanner s, String label) {
        return Long.parseLong(ask(s, label));
    }
    public static void main(String[] args) {
        // BankService performs all banking operations.
        BankService bank = new BankService();
        Path file = Path.of("data", "accounts.dat");
        try { bank.load(file); }
        catch (Exception e) {
            System.out.println("Cannot load saved accounts. No data overwritten: " + e);
            return;
        }
        System.out.println("=== MiniBank Student Bank ===");
        System.out.println("Loaded accounts: " + bank.listAccounts().size());
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("1 Open  2 Deposit  3 Withdraw  4 Transfer");
                System.out.println("5 Statement  6 List accounts  0 Save and exit");
                try {
                    switch (ask(sc, "Choice")) {
                        case "1": {
                            bank.openAccount(ask(sc,"Account ID"), ask(sc,"Owner"),
                                ask(sc,"Mobile"), amount(sc,"Opening balance"));
                            System.out.println("Account opened.");
                            break;
                        }
                        case "2": { bank.deposit(ask(sc,"Account ID"), amount(sc,"Amount"));
                            System.out.println("Deposit successful."); break; }
                        case "3": { bank.withdraw(ask(sc,"Account ID"), amount(sc,"Amount"));
                            System.out.println("Withdrawal successful."); break; }
                        case "4": { bank.transfer(ask(sc,"From account"), ask(sc,"To account"),
                            amount(sc,"Amount")); System.out.println("Transfer successful."); break; }
                        case "5":
                            System.out.print(bank.statement(ask(sc,"Account ID")));
                            break;
                        case "6":
                            for (model.Account account : bank.listAccounts()) {
                                System.out.println(account);
                            }
                            break;
                        case "0": running = false; break;
                        default: System.out.println("Invalid menu option.");
                    }
                } catch (BankException | IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (java.util.NoSuchElementException e) { running = false; }
            }
        }
        try { bank.save(file); System.out.println("Accounts saved. Goodbye!"); }
        catch (Exception e) { System.out.println("Save failed: " + e.getMessage()); }
    }
}
