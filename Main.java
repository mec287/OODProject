import java.util.List;
import java.util.Scanner;

/**
 * The Main class is the entry point of the inventory management system.
 * It provides a menu for user interactions and utilizes the InventoryManagementSystem and InventoryIO classes.
 */

public class Main {
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        InventoryIO io = new InventoryIO();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("********** Inventory Management System **********");
            System.out.println("1. Add Store");
            System.out.println("2. Add Product Stock");
            System.out.println("3. Perform Incoming Transaction");
            System.out.println("4. Perform Outgoing Transaction");
            System.out.println("5. View Available Items");
            System.out.println("6. View Entered Items");
            System.out.println("7. View Transactions");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Store store = io.readStore();
                    ims.addStore(store);
                    break;
                case 2:
                    ProductStock productStock = io.readProductStock();
                    ims.addProductStock(productStock);
                    break;
                case 3:
                    Transaction incomingTransaction = io.readTransaction();
                    List<ProductStock> incomingItems = io.readItemsForTransaction();
                    ims.performIncomingTransaction(incomingTransaction, incomingItems);
                    break;
                case 4:
                    Transaction outgoingTransaction = io.readTransaction();
                    List<ProductStock> outgoingItems = io.readItemsForTransaction();
                    System.out.print("Enter Store ID: ");
                    int storeId = Integer.parseInt(scanner.nextLine());
                    ims.performOutgoingTransaction(outgoingTransaction, storeId, outgoingItems);
                    break;
                case 5:
                    io.displayProductStocks(ims.getAvailableItems());
                    break;
                case 6:
                    io.displayProductStocks(ims.getEnteredItems());
                    break;
                case 7:
                    System.out.println("***** View Transactions *****");
                    System.out.println("1. View All Transactions");
                    System.out.println("2. View Transactions of a Product");
                    System.out.println("3. View Most Incoming Item");
                    System.out.println("4. View Most Outgoing Item");
                    System.out.println("0. Back");
                    System.out.print("Enter your choice: ");
                    int subChoice = Integer.parseInt(scanner.nextLine());

                    switch (subChoice) {
                        case 1:
                            io.displayTransactions(ims.getAllTransactions());
                            break;
                        case 2:
                            System.out.print("Enter Product ID: ");
                            int productId = Integer.parseInt(scanner.nextLine());
                            io.displayTransactions(ims.getTransactionsByProductId(productId));
                            break;
                        case 3:
                            ProductStock mostIncomingItem = ims.getMostIncomingItem();
                            System.out.println("Most Incoming Item:");
                            if (mostIncomingItem != null) {
                                System.out.println("Product ID: " + mostIncomingItem.getId());
                                System.out.println("Product Name: " + mostIncomingItem.getName());
                                System.out.println("Number of Items: " + mostIncomingItem.getNumberOfItems());
                            } else {
                                System.out.println("No incoming transactions found.");
                            }
                            break;
                        case 4:
                            ProductStock mostOutgoingItem = ims.getMostOutgoingItem();
                            System.out.println("Most Outgoing Item:");
                            if (mostOutgoingItem != null) {
                                System.out.println("Product ID: " + mostOutgoingItem.getId());
                                System.out.println("Product Name: " + mostOutgoingItem.getName());
                                System.out.println("Number of Items: " + mostOutgoingItem.getNumberOfItems());
                            } else {
                                System.out.println("No outgoing transactions found.");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
                case 0:
                    System.out.println("Exiting Inventory Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        } while (choice != 0);
        scanner.close();
    }
}