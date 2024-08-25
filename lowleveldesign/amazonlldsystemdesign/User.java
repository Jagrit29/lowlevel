package lowleveldesign.amazonlldsystemdesign;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String id;
    private String name;
    private String email;
    private String password;
    private List<Order> orders;

    // now I have my constructor to create a new user for my system;
    public User(String id, String name, String email, String password) {
        this.id = id; // assign UUID to the newly created user;
        this.name = name;
        this.email = email;
        this.password = password;
        this.orders = new ArrayList<>();
    }

    // now an user can place and order which means a order will be added to it's list;
    public void addOrder(Order order) {
        orders.add(order);
    }

    // I will need getter functions to get details like name, etc etc and a
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Order> getOrders() {
        return orders;
    }


}
