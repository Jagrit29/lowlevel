package lowleveldesignv2.inventorysystem;

public interface AlertListener {
    void onLowStock(String warehouseId, String productId, int currentQuantity);
}
