// Run this class to see the demonstration for this practical.
package service;
import exception.*;
import model.SavingsAccount;

class AuditResource implements AutoCloseable {
    public AuditResource() { System.out.println("[AuditResource] Opened"); }
    @Override
    public void close() { System.out.println("[AuditResource] Closed"); }
}

public class MiniBank {
    public static void main(String[] args) {
        SavingsAccount a1 = new SavingsAccount("AC0001", "Diya", 1000, 0);
        try (AuditResource res = new AuditResource()) {
            a1.withdraw(5000);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage() + " | Balance remains: " + a1.getBalance());
        } catch (BankException e) {
            System.out.println("Bank error: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed.");
        }
    }
}
