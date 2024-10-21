package practice.interviewprep.flipkart.resturantmgmtsystem;

public class Table {
    private String id;
    private TableCategory tableCategory;

    public Table(String id, TableCategory tableCategory) {
        this.id = id;
        this.tableCategory = tableCategory;
    }

    public String getId() {
        return id;
    }

    public TableCategory getTableCategory() {
        return tableCategory;
    }
}
