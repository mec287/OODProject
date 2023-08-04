import java.util.ArrayList;
import java.util.List;

/**
 * The InventoryManagementSystem class represents the main inventory management system.
 * It holds all the details of stores, product stocks, and transactions in the inventory.
 */

public class InventoryManagementSystem {
    private List<Store> stores;
    private List<ProductStock> productStocks;
    private List<Transaction> transactions;

    /**
     * Constructs an InventoryManagementSystem with empty lists for stores, product stocks, and transactions.
     */
    public InventoryManagementSystem() {
        stores = new ArrayList<>();
        productStocks = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    // Methods to add new product stocks, perform incoming and outgoing transactions, view reports, etc.

    public void addStore(Store store) {
        stores.add(store);
    }

    public void addProductStock(ProductStock productStock) {
        productStocks.add(productStock);
    }

    public void performIncomingTransaction(Transaction transaction, List<ProductStock> items) {
        transactions.add(transaction);
        for (ProductStock item : items) {
            ProductStock existingItem = getProductStockById(item.getId());
            if (existingItem != null) {
                existingItem.setNumberOfItems(existingItem.getNumberOfItems() + item.getNumberOfItems());
            } else {
                productStocks.add(item);
            }
        }
    }

    public void performOutgoingTransaction(Transaction transaction, int storeId, List<ProductStock> items) {
        transactions.add(transaction);
        for (ProductStock item : items) {
            ProductStock existingItem = getProductStockById(item.getId());
            if (existingItem != null) {
                existingItem.setNumberOfItems(existingItem.getNumberOfItems() - item.getNumberOfItems());
            } else {
            }
        }
    }

    public List<ProductStock> getAvailableItems() {
        List<ProductStock> availableItems = new ArrayList<>();
        for (ProductStock item : productStocks) {
            if (item.getNumberOfItems() > 0) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    public List<ProductStock> getEnteredItems() {
        return productStocks;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }
    public List<Transaction> getTransactionsByProductId(int productId) {
        List<Transaction> transactionsByProductId = new ArrayList<>();
        for (Transaction transaction : transactions) {
            for (ProductStock item : getProductStocksByTransactionId(transaction.getId())) {
                if (item.getId() == productId) {
                    transactionsByProductId.add(transaction);
                    break;
                }
            }
        }
        return transactionsByProductId;
    }

    public ProductStock getMostIncomingItem() {
        ProductStock mostIncomingItem = null;
        int maxIncomingItems = -1;
        for (ProductStock item : productStocks) {
            int incomingItems = 0;
            for (Transaction transaction : transactions) {
                if (transaction.getType().equalsIgnoreCase("incoming")) {
                    for (ProductStock transactionItem : getProductStocksByTransactionId(transaction.getId())) {
                        if (transactionItem.getId() == item.getId()) {
                            incomingItems += transactionItem.getNumberOfItems();
                        }
                    }
                }
            }
            if (incomingItems > maxIncomingItems) {
                maxIncomingItems = incomingItems;
                mostIncomingItem = item;
            }
        }
        return mostIncomingItem;
    }

    public ProductStock getMostOutgoingItem() {
        ProductStock mostOutgoingItem = null;
        int maxOutgoingItems = -1;
        for (ProductStock item : productStocks) {
            int outgoingItems = 0;
            for (Transaction transaction : transactions) {
                if (transaction.getType().equalsIgnoreCase("outgoing")) {
                    if (transaction.getType().equalsIgnoreCase("incoming")) {
                        for (ProductStock transactionItem : getProductStocksByTransactionId(transaction.getId())) {
                            if (transactionItem.getId() == item.getId()) {
                                outgoingItems += transactionItem.getNumberOfItems();
                            }
                        }
                    }
                }
            }
            if (outgoingItems > maxOutgoingItems) {
                maxOutgoingItems = outgoingItems;
                mostOutgoingItem = item;
            }
        }
        return mostOutgoingItem;
    }

    // Helper methods to interact with the lists and manage the data

    private ProductStock getProductStockById(int productId) {
        for (ProductStock item : productStocks) {
            if (item.getId() == productId) {
                return item;
            }
        }
        return null;
    }

    private List<ProductStock> getProductStocksByTransactionId(int transactionId) {
        List<ProductStock> transactionItems = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getId() == transactionId) {
                for (ProductStock item : productStocks) {
                    if (item.getId() == transaction.getId()) {
                        transactionItems.add(item);
                    }
                }
                break;
            }
        }
        return transactionItems;
    }
}
