import java.time.LocalDate;

// This class represents a single transaction: either income or expense
public class Transaction {
    private double amount;           // The amount of money
    private String description;      // Description of the transaction
    private String category;         // Category like "Food", "Rent", etc.
    private boolean isIncome;        // True if income, false if expense
    private LocalDate date;          // The date the transaction was made

    // Constructor to create a transaction
    public Transaction(double amount, String description, String category, boolean isIncome, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.isIncome = isIncome;
        this.date = date;
    }

    // Getters for each private member
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public boolean isIncome() { return isIncome; }
    public LocalDate getDate() { return date; }

    // Format transaction details nicely when printed
    @Override
    public String toString() {
        return String.format("%s | %s | %s | $%.2f | %s",
                date.toString(),
                isIncome ? "Income" : "Expense",
                category,
                amount,
                description);
    }
}
