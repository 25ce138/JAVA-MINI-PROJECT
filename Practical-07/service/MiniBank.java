// Run this class to see the demonstration for this practical.
package service;
import model.SavingsAccount;
import util.AnnotationValidator;
import java.util.Arrays;

public class MiniBank {
    public static void main(String[] args) throws Exception {
        SavingsAccount invalidAcc = new SavingsAccount("AC0001", "Diya", -100, 500);
        SavingsAccount validAcc   = new SavingsAccount("AC0002", "Arjun", 5000, 500);

        System.out.println("Invalid account errors: " + Arrays.toString(AnnotationValidator.validate(invalidAcc)));
        System.out.println("Valid account errors:   " + Arrays.toString(AnnotationValidator.validate(validAcc)));
    }
}
