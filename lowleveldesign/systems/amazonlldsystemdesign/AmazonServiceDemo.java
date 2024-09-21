package lowleveldesign.systems.amazonlldsystemdesign;

import lowleveldesign.systems.amazonlldsystemdesign.payment.CreditCardPayment;
import lowleveldesign.systems.amazonlldsystemdesign.payment.Payment;

import java.util.List;

public class AmazonServiceDemo {
    public static void main(String args[]) {
        // first we need get our aamazon app jsut like download the app;
        AmazonService amazonService = AmazonService.getAmazonService();

        // now you need some Users, Products firs tot be added to Amazon;
        User user1 = new User("u1", "Jagrit", "abc@gmail.com", "123");
        User user2 = new User("u2", "Jaagi", "ab2@gmail.com", "124");

        amazonService.registerUser(user1);
        amazonService.registerUser(user2);

        // Products;
        Product product1 = new Product("p1", "Iphone 13", "Apple Iphone 13", 10, 1000);
        Product product2 = new Product("p2", "Iphone 14", "Apple Iphone 14", 10, 1000);

        amazonService.addProduct(product1);
        amazonService.addProduct(product2);

        // now I have users and products.

        // now User 1 wants to add things to it's add

        System.out.println("Cart and Order Functionality::");
        ShoppingCart cart1 = new ShoppingCart();
        cart1.addUpdateItem(new OrderItem(product1, 1)); // buying iphone;
        cart1.addUpdateItem(new OrderItem(product2, 1));

        Payment payment1 = new CreditCardPayment();
        Order order1 = amazonService.placeOrder(user1, cart1, payment1);
        System.out.println("Order placed"+" "+order1.getId());

        // Test search functionality
        System.out.println("Search Functioanlity test");
        List<Product> searchResults = amazonService.searchProducts("iphone 14");
        for(Product p: searchResults) {
            System.out.println(p.getName()+" "+p.getQuantity());
        }

        System.out.println("View my orders functionality");
        for(Order order: user1.getOrders()) {
            System.out.println(order.getStatus());
        }


    }
}
