package lowleveldesign.amazonlldsystemdesign;

import lowleveldesign.amazonlldsystemdesign.payment.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

// now this is my amazon, I will use singleton to create single instance of Amazon so that people don't see multiple Amazon's
public class AmazonService {
    public static AmazonService amazonService;
    private Map<String, User> users;
    private Map<String, Order> orders;
    private Map<String, Product> products;

    // make the constructor private, so no one can create their own AmazonService instance
    private AmazonService() {
        users = new ConcurrentHashMap<>(); // this is thread safe, meanign let's say two threads want to updat eht eproduct table so we need to make that thread safe;
        orders = new ConcurrentHashMap<>();
        products = new ConcurrentHashMap<>();
    }

    // now Amazon service might need things like Users list, Orders list and products at the Servic elevel;
    // so at amazon level, It will have it's user, orders and products;
    // now whenver our service is intialted, We will have ServiceInstance and then users, order and prodcuts. Initiially everything is empty;
    // this also has to be static;
    public static AmazonService getAmazonService() {
        // whoever wants to use this, they will use this isntance of AmazonService;
        if(amazonService == null) {
            synchronized (AmazonService.class) {
                amazonService = new AmazonService();
            }
        }
        return amazonService;
    }

    // now let's talk about what we can do on the users, We can add a user; // now okay you hav eregisters the user;
    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    // get userDetails from the app;
    public User getUser(String userId) {
        return users.get(userId);
    }

    // we can add items also to our products;
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    // now in my Amazon, I have it's instance, users, products, etc what elese?
    // now in my products, I want to add search functionality too;
    public List<Product> searchProducts(String keyword) {
        // first we need list of products
        // now our hashmap has all the products so it;s the value of hasmap;
        // products.values()
        // now we need to convert this into a stream, meaning it can be processed in functional manner. stream is a sequnce of elements;
        // procuts.values().stream()
        // now once I have got hte stream, I can apply filter on it.
        return products.values().stream().filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }

    // now you ahve the search also done. What else. someone can place order too righ?
    // how will that wikll, it will be like user have some prodcuts in its shopping cart and there will be user and it will require soem payment too;
    // so user, some shoppingcart item and payment mehtod;

    public synchronized Order placeOrder(User user, ShoppingCart cart, Payment payment)  {
        // now we have a user sho wants to palc eorder, first we need to calculat;
        List<OrderItem> orderItems = new ArrayList<>(); // there

        // first I need to get all the orderItems from the cart with there amount;
        for(OrderItem item: cart.getItems()) {
            // noe rsch item has prodcut and quantity;
            Product product = item.getProduct();
            int requiredQuantity = item.getProductQuantity();
            // now here I would like to check if the quantity is avail for the product or not;
            if(product.isAvailable(requiredQuantity)) {
                // this much quantity of the roduct is avl;
                product.updateQuantity(-requiredQuantity); // update its acc;
                orderItems.add(item); // this item is good to be orders;

            }
        }

        if(orderItems.isEmpty()) {
            // either the items expired or there were no items at all;
//            throw new IllegalAccessException("No avl prods");
            System.out.println("no prdocuts");
            return null;
        }

        // now we need to create a order for the above orderIterms
        // for which you will need a uuid too;
        String orderId = generateOrderId();
        Order order = new Order(orderId, user, orderItems);

        // now you order is generated;
        // now you will pu tthis orde rin orders map;
        orders.put(orderId, order);
        user.addOrder(order); // this is also added to the suer
        // and cart is cleared for that user;

        cart.clear();

        /// now what;
        // now amount also needs to be calculted for tha torder;
        // now our payment needs to process the orderAmount;
        if(payment.processPayment(order.getAmount())) {
            order.setOrderStatus(OrderStatus.PAYMENT_PENDING);
            // now here we could also say done if the successu;
        } else {
            order.setOrderStatus(OrderStatus.CANCELLED);
            // if it's cancelled;
            for(OrderItem orderItem: orderItems) {
                Product product = orderItem.getProduct();
                product.updateQuantity(orderItem.getProductQuantity());

            }
        }

        return order;

    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    private String generateOrderId() {
        return "ORDEr" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }


}
