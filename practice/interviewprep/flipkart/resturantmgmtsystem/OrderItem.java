package practice.interviewprep.flipkart.resturantmgmtsystem;

public class OrderItem {
    public String id;
    public String menuItemId;
    public int quantity;

    public OrderItem(String id, String menuItemId, int quantity) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
