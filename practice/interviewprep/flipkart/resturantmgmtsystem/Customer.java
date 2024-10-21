package practice.interviewprep.flipkart.resturantmgmtsystem;

public class Customer {
    private String id;
    private String name;
    private String tableId;

    public Customer(String id, String name, String tableId) {
        this.id = id;
        this.name = name;
        this.tableId = tableId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTableId() {
        return tableId;
    }
}
