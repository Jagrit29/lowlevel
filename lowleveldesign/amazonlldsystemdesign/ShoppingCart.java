package lowleveldesign.amazonlldsystemdesign;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// now think of shopping cart as a user can have multiple orderItems and then can increase thir quantity;
// now each item can have multiple quanties so that will be like item to freuqnce mapping;
public class ShoppingCart {
//    private final User user;
    private HashMap<String, OrderItem> items;

    public ShoppingCart() {
        // this will be created for a particular user it's 1 to 1 mapping;
        this.items = new HashMap<>();
    }

    public void addUpdateItem(OrderItem item) {
        // we can think of naming it just item ;
        // now it might be possible that this is the new product or it can be same but quantity is increase;
        String productId = item.getProduct().getId();
        int quantity = item.getProductQuantity();
        if(items.containsKey(productId)) {
            int oldQ = items.get(productId).getProductQuantity();
            quantity += item.getProductQuantity();
        }

        items.put(productId, new OrderItem(item.getProduct(), quantity));
    }

    public void removeItem(OrderItem item) {
        items.remove(item.getProduct());
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public void clear() {
        items.clear();
    }


}
