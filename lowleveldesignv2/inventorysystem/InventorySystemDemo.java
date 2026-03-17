package lowleveldesignv2.inventorysystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventorySystemDemo {
    public static void main(String[] args) {

        // create two warehouses
        Warehouse hyderbadWarehouse = new Warehouse("h1");
        Warehouse bangaloreWarehouse = new Warehouse("b1");

        // add products in the warehouse;
        hyderbadWarehouse.addStock("Iphone", 10);
        bangaloreWarehouse.addStock("Iphone", 20);

        List<String> warehouseList = new ArrayList<>(Arrays.asList(bangaloreWarehouse.getId(), hyderbadWarehouse.getId()));

        InventoryManager blinketInventoryManager = new InventoryManager(warehouseList);

        // set alert for iphone
        blinketInventoryManager.setLowAlert("h1", "Iphone", 10, new EmailAlertListener());
        blinketInventoryManager.setLowAlert("b1", "Iphone", 7, new EmailAlertListener());

        blinketInventoryManager.removeStock("b1", "Iphone", 5);
    }
}
