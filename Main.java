import java.time.LocalDate;
import java.util.Scanner;

// This class runs the actual program and provides the menu
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BudgetManager manager = new BudgetManager();

        // Load existing transactions from file if available
        manager.setTransactions(DataHandler.loadFromFile());

        boolean running = true;
        while (running) {
            // Display menu
            System.out.println("\n📋 Budget Tracker Menu");
            System.out.println("1. Add Transaction");
            System.out.println("2. View All Transactions");
            System.out.println("3. Delete Transaction");
            System.out.println("4. Show Summary");
            System.out.println("5. Summary by Category");
            System.out.println("6. Set Monthly Budget Limit");
            System.out.println("7. Save & Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine(); // Clear newline from input

            // Handle each menu option
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount: ");
                    double amount = input.nextDouble();
                    input.nextLine();
                    System.out.print("Enter description: ");
                    String desc = input.nextLine();
                    System.out.print("Enter category (e.g. Food, Rent): ");
                    String cat = input.nextLine();
                    System.out.print("Is this income? (true/false): ");
                    boolean income = input.nextBoolean();
                    input.nextLine();

                    // Add new transaction with today's date
                    Transaction t = new Transaction(amount, desc, cat, income, LocalDate.now());
                    manager.addTransaction(t);
                }
                case 2 -> manager.showAllTransactions();
                case 3 -> {
                    manager.showAllTransactions();
                    System.out.print("Enter index to delete: ");
                    int index = input.nextInt();
                    manager.deleteTransaction(index);
                }
                case 4 -> manager.showSummary();
                case 5 -> manager.showSummaryByCategory();
                case 6 -> {
                    System.out.print("Enter monthly budget limit: ");
                    double limit = input.nextDouble();
                    manager.setBudgetLimit(limit);
                }
                case 7 -> {
                    DataHandler.saveToFile(manager.getTransactions());
                    System.out.println("Data saved. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        input.close(); // Always close your scanner!
    }
}
