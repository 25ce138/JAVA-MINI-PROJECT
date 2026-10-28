// Run this class to see the demonstration for this practical.
package service;
import model.*;
import static util.Validator.isValidMobile;

public class MiniBank {
    public static void main(String[] args) {
        SavingsAccount acc = new SavingsAccount("AC0001", "Diya", 10000, 1000);

        WithdrawRule anonRule = new WithdrawRule() {
            @Override
            public boolean allow(Account a, long amt) {
                return amt <= 5000 && a.canWithdraw(amt);
            }
        };

        WithdrawRule lambdaRule = (a, amt) -> amt <= 5000 && a.canWithdraw(amt);

        System.out.println("Anonymous Rule allow(3000): " + anonRule.allow(acc, 3000));
        System.out.println("Lambda Rule allow(3000):    " + lambdaRule.allow(acc, 3000));
        System.out.println("Yearly Interest: Rs. " + acc.yearlyInterest());
        System.out.println("Mobile valid check via static import: " + isValidMobile("9876543210"));
    }
}
