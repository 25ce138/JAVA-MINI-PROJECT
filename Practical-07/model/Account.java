// Account stores the details and balance of one bank account.
package model;
import model.annotation.*;
import java.util.Objects;

public abstract class Account {
    @Id
    protected final String accountNumber;

    @MaxLength(20)
    protected String ownerName;

    @Positive
    protected long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
