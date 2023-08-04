public class Transaction {
    private int id;
    private String type;
    private String date;

    public Transaction(int id, String type, String date) {
        this.id = id;
        this.type = type;
        this.date = date;
    }

    // Setters and getters
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
