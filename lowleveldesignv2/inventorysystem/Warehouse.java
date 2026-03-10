package lowleveldesignv2.inventorysystem;

import java.util.HashMap;

public class Warehouse {
    private String id;
    private HashMap<String, Integer> inventory;

    public Warehouse(String id) {
        this.id = id;
        this.inventory = new HashMap<>();
    }

    public void addStock(String productId, int quantity) {
        inventory.put(productId, inventory.getOrDefault(productId, 0) + quantity);
    }

    public boolean removeStock(String productId, int quantity) {
        if(!inventory.containsKey(productId) || inventory.get(productId) < quantity) return false;
        inventory.put(productId, inventory.getOrDefault(productId, 0) - quantity);

        return true;
    }

    public boolean checkAvailability(String productId, int quantity) {
        return inventory.containsKey(productId) && inventory.get(productId) >= quantity;
    }
}
