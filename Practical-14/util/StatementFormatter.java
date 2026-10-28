package util;
import model.Account;

public class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Account Statement ---\n")
          .append("Account : ").append(account.getAccountNumber()).append("\n")
          .append("Owner   : ").append(account.getOwnerName()).append("\n")
          .append("Balance : Rs. ").append(account.getBalance()).append("\n");
        return sb.toString();
    }
}
