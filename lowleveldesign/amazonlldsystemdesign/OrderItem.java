package lowleveldesign.amazonlldsystemdesign;

// OrderITem can't really exist alone so order link is deabtled;
public class OrderItem {
//    private final String id;
   //  private final Order order;
    private final Product product;
    private int productQuantity;


    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.productQuantity = quantity;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public Product getProduct() {
        return product;
    }
}
