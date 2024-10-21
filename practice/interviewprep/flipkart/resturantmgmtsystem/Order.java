package practice.interviewprep.flipkart.resturantmgmtsystem;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private String tableId;
    private String customerId;
    private List<OrderItem>  orderItems;

    public Order(String id, String tableId, String customerId, List<OrderItem> items) {
        this.id = id;
        this.tableId = tableId;
        this.customerId = customerId;
        orderItems = new ArrayList<>(items);
    }

    public String getId() {
        return id;
    }

    public String getTableId() {
        return tableId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }
}
