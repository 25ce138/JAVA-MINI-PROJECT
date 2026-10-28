// Run this class to see the demonstration for this practical.
import java.util.Scanner;

record BankInfo(String name, String branch) {}

enum MenuOption {
    OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT
}

public class MiniBank {
    public static void main(String[] args) {
        BankInfo info = new BankInfo("MiniBank Student Bank", "Campus Branch");
        System.out.println("=== " + info.name() + " (" + info.branch() + ") ===");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. OPEN_ACCOUNT  2. DEPOSIT  3. WITHDRAW  4. TRANSFER  5. EXIT");
            System.out.print("Select option: ");
            if (!scanner.hasNextInt()) break;
            if (!scanner.hasNextLine()) break;
            int choice;
            try { choice = Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a number from 1 to 5."); continue; }

            if (choice < 1 || choice > MenuOption.values().length) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            MenuOption option = MenuOption.values()[choice - 1];
            String message = switch (option) {
                case OPEN_ACCOUNT -> "Open Account — to be implemented in a later lab";
                case DEPOSIT      -> "Deposit — to be implemented in a later lab";
                case WITHDRAW     -> "Withdraw — to be implemented in a later lab";
                case TRANSFER     -> "Transfer — to be implemented in a later lab";
                case EXIT -> {
                    running = false;
                    yield "Thank you for banking with MiniBank. Goodbye!";
                }
            };
            System.out.println(message);
        }
        scanner.close();
    }
}
