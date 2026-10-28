// Run this class to see the demonstration for this practical.
public class MiniBank {
    public static void main(String[] args) {
        Account a1 = new Account("AC0001", "Diya", 4000);
        Account a2 = new Account("AC0001", "Diya", 4000);

        System.out.println(a1);
        System.out.println("a1.equals(a2): " + a1.equals(a2));
        System.out.println("a1 instanceof Account: " + (a1 instanceof Account));

        Customer c1 = new Customer("Diya", new Customer.Address("MG Road", "Anand", "388001"));
        Customer c2 = c1.clone();
        System.out.println("Cloned Customer: " + c2);
    }
}
