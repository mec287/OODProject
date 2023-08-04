import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The InventoryIO class handles input and output operations for the inventory management system.
 * It provides methods to read input from the user and display information to the console.
 */
public class InventoryIO {
    private static final Scanner scanner = new Scanner(System.in);

    public static Store readStore() {
        System.out.println("Enter Store Details:");
        System.out.print("Store ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Store Name: ");
        String name = scanner.nextLine();

        System.out.print("Store Address: ");
        String address = scanner.nextLine();

        return new Store(id, name, address);
    }

    public static ProductStock readProductStock() {
        System.out.println("Enter Product Details:");
        System.out.print("Product ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Number of Items: ");
        int numberOfItems = Integer.parseInt(scanner.nextLine());

        return new ProductStock(id, name, numberOfItems);
    }

    public static Transaction readTransaction() {
        System.out.println("Enter Transaction Details:");
        System.out.print("Transaction ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Transaction Type (incoming/outgoing): ");
        String type = scanner.nextLine();

        System.out.print("Transaction Date: ");
        String date = scanner.nextLine();

        return new Transaction(id, type, date);
    }

    public static List<ProductStock> readItemsForTransaction() {
        List<ProductStock> items = new ArrayList<>();
        System.out.print("Enter the number of items for this transaction: ");
        int itemCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < itemCount; i++) {
            System.out.println("Enter Product ID for item " + (i + 1) + ":");
            int productId = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter Number of Items for item " + (i + 1) + ":");
            int numberOfItems = Integer.parseInt(scanner.nextLine());

            items.add(new ProductStock(productId, "", numberOfItems));
        }
        return items;
    }

    public static void displayProductStocks(List<ProductStock> productStocks) {
        System.out.println("***** Product Stocks *****");
        for (ProductStock item : productStocks) {
            System.out.println("Product ID: " + item.getId());
            System.out.println("Product Name: " + item.getName());
            System.out.println("Number of Items: " + item.getNumberOfItems());
            System.out.println("*********************");
        }
    }

    /**
     * Displays the details of all transactions to the console.
     *
     * @param transactions The list of transactions to be displayed.
     */
    public static void displayTransactions(List<Transaction> transactions) {
        System.out.println("***** Transactions *****");
        for (Transaction transaction : transactions) {
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Transaction Type: " + transaction.getType());
            System.out.println("Transaction Date: " + transaction.getDate());
            System.out.println("*********************");
        }
    }
}


