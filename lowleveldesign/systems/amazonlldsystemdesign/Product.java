package lowleveldesign.systems.amazonlldsystemdesign;

public class Product {
    private final String id;
    private String name;
    private String description;
    private int quantity;
    private double price;

    public Product(String id, String name, String description, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public synchronized void updateQuantity(int quantity) {
        // now we want this to be only done by 1 thread of this object, otherwise
        // only 1 thread should update it once;
        this.quantity += quantity;
    }

    // now avl should also be shcnronised;
    // otherwise it will return avl for both the threads here threads can be think of two users ordering;
    public synchronized boolean isAvailable(int quantity) {
        return this.quantity >= quantity;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }


}
