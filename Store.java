public class Store {
    private int id;
    private String name;
    private String address;

    public Store(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    // Setters and getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
