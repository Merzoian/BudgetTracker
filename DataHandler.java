import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// This class handles reading and writing transactions to a file
public class DataHandler {
    private static final String FILE_NAME = "budget_data.txt"; // File to store data

    // Save all transactions to the file
    public static void saveToFile(List<Transaction> transactions) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t : transactions) {
                writer.printf("%s,%s,%s,%.2f,%s%n",
                        t.getDate(), t.isIncome(), t.getCategory(), t.getAmount(), t.getDescription());
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Load transactions from the file (if exists)
    public static List<Transaction> loadFromFile() {
        List<Transaction> loaded = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Format: date,isIncome,category,amount,description
                String[] parts = line.split(",", 5);
                LocalDate date = LocalDate.parse(parts[0]);
                boolean isIncome = Boolean.parseBoolean(parts[1]);
                String category = parts[2];
                double amount = Double.parseDouble(parts[3]);
                String description = parts[4];

                loaded.add(new Transaction(amount, description, category, isIncome, date));
            }

        } catch (IOException e) {
            System.out.println("No saved data found.");
        }

        return loaded;
    }
}
