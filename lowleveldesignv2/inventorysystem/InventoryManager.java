package lowleveldesignv2.inventorysystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class InventoryManager {
     private final HashMap<String, Warehouse> warehouses;

     public InventoryManager(List<Warehouse> warehouses) {
          this.warehouses = new HashMap<>();
          for(Warehouse wh: warehouses) {
               this.warehouses.put(wh.getId(), wh);
          }
     }

     public void addStock(String warehouseId, String productId, int quantity) {
          if(!warehouses.containsKey(warehouseId)) throw new IllegalArgumentException("Warehouse " + warehouseId + "not found");

          // get warehouse and add stock
          Warehouse  warehouse = warehouses.get(warehouseId);
          warehouse.addStock(productId, quantity);
     }

     public boolean removeStock(String warehouseId, String productId, int quantity) {
          if(!warehouses.containsKey(warehouseId)) return false;

          // get warehouse and remove stock
          Warehouse warehouse = warehouses.get(warehouseId);
          return warehouse.removeStock(productId, quantity);
     }

     public List<Warehouse> getWarehousesWithStock(String productId, int quantity) {
          List<Warehouse> available = new ArrayList<>();
          for(Warehouse warehouse: warehouses.values()) {
               if(warehouse.checkAvailability(productId, quantity)) {
                    available.add(warehouse);
               }
          }

          return available;
     }

     public boolean transfer(String fromWarehouseId, String toWarehouseId, String productId, int quantity) {
          if(quantity <= 0 || fromWarehouseId.equals(toWarehouseId)) return false; // input validation
          if(!warehouses.containsKey(fromWarehouseId) || !warehouses.containsKey(toWarehouseId)) return false;

          Warehouse fromWh = warehouses.get(fromWarehouseId);
          Warehouse toWh = warehouses.get(toWarehouseId);


          Warehouse first = fromWarehouseId.compareTo(toWarehouseId) < 0 ? fromWh : toWh;
          Warehouse second = fromWarehouseId.compareTo(toWarehouseId) < 0 ? toWh : fromWh;

          synchronized (first) {
               synchronized (second) {
                    if(!fromWh.removeStock(productId, quantity)) return false;

                    toWh.addStock(productId, quantity);
               }
          }

          return true;
     }

     public void setLowAlert(String warehouseId, String productId, int threshold, AlertListener listener) {
          // TODO: add error handling;
          Warehouse wh = warehouses.get(warehouseId);
          wh.setLowAlert(productId, threshold, listener);
     }
}
