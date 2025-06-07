import java.util.*;

// This class manages all transactions and provides summary features
public class BudgetManager {
    private List<Transaction> transactions = new ArrayList<>(); // List to store transactions
    private double budgetLimit = Double.MAX_VALUE; // Default to a very high number (optional)

    // Add a new transaction
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    // Delete a transaction at a given index
    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
            System.out.println("Transaction deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    // Show all saved transactions
    public void showAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to show.");
            return;
        }

        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(i + ": " + transactions.get(i));
        }
    }

    // Show total income, expenses, and balance
    public void showSummary() {
        double income = transactions.stream()
                .filter(Transaction::isIncome)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double expenses = transactions.stream()
                .filter(t -> !t.isIncome())
                .mapToDouble(Transaction::getAmount)
                .sum();

        System.out.printf("Total Income: $%.2f%n", income);
        System.out.printf("Total Expenses: $%.2f%n", expenses);
        System.out.printf("Net Balance: $%.2f%n", income - expenses);

        if (expenses > budgetLimit) {
            System.out.println("⚠️ Warning: You have exceeded your monthly budget!");
        }
    }

    // Show a breakdown of expenses by category
    public void showSummaryByCategory() {
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Transaction t : transactions) {
            if (!t.isIncome()) {
                // Add up expenses per category
                categoryTotals.put(t.getCategory(),
                        categoryTotals.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
            }
        }

        System.out.println("Expenses by Category:");
        for (String cat : categoryTotals.keySet()) {
            System.out.printf("  %s: $%.2f%n", cat, categoryTotals.get(cat));
        }
    }

    // Set a new budget warning limit
    public void setBudgetLimit(double limit) {
        this.budgetLimit = limit;
        System.out.printf("Budget limit set to $%.2f%n", limit);
    }

    // Get and set methods for saving/loading
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
