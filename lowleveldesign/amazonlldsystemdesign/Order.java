package lowleveldesign.amazonlldsystemdesign;
import java.util.List;

enum OrderStatus {
    CANCELLED,
    PAYMENT_PENDING,
    SHIPPED,
    DELIVERED,
}

// Each order will have 1 user
// 1 user can have multiple orders
public class Order {
    private final String id;
    private final User user;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems;
    private double amount;

    // To create a new order, We need a user who is creating that order and then orderItems he is ordering;
    public Order(String id, User user, List<OrderItem> orderItems) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
        this.amount = orderAmount();
    }

    // we need to calculate the amount for that particular order; for that each orderItem will have a product and it w
    // it will come with a price;
    // now this we will implement once we have product class;
    // -----> todo

    private double orderAmount() {
        // this will return the amount of all the items;
        return orderItems.stream().mapToDouble(orderItem -> orderItem.getProductQuantity()*orderItem.getProduct().getPrice()).sum();
    }


    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
    }

    public double getAmount() {
        // first make sure the amount is calculated;
        return amount;
    }
}
