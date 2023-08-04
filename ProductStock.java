public class ProductStock {
    private int id;
    private String name;
    private int numberOfItems;

    public ProductStock(int id, String name, int numberOfItems) {
        this.id = id;
        this.name = name;
        this.numberOfItems = numberOfItems;
    }

    // Setters and getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}

