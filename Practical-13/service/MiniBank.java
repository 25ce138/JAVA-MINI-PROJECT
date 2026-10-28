// Run this class to see the demonstration for this practical.
package service;
import model.*;

public class MiniBank {
    public static void main(String[] args) {
        Repository<Account> accRepo = new Repository<>();
        accRepo.saveAll(
            new Account("AC0001", "Diya", 5000),
            new Account("AC0002", "Arjun", 8000),
            new Account("AC0003", "Neel", 11000)
        );

        System.out.println("findById('AC0002'): " + accRepo.findById("AC0002"));
        System.out.println("All Accounts: " + accRepo.findAll());
        Repository<model.Customer> customers = new Repository<>();
        customers.save(new model.Customer("CUST101", "Diya"));
        System.out.println("Customer repository: " + customers.findById("CUST101"));
        System.out.println("Deleted AC0003: " + accRepo.delete("AC0003"));
        Long[] balances = {5000L, 8000L, 11000L};
        System.out.println("Average Balance: Rs. " + Repository.average(balances));

        Repository<Customer> custRepo = new Repository<>();
        custRepo.save(new Customer("CUST101", "Diya"));
        System.out.println("Customer findById('CUST101'): " + custRepo.findById("CUST101"));
    }
}
