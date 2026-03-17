package lowleveldesignv2.inventorysystem;

public class EmailAlertListener implements AlertListener {
    @Override
    public void onLowStock(String warehouseId, String productId, int currentQuantity) {
        System.out.println("The stock for " +productId+ " has decreased to "+ currentQuantity);
    }
}
