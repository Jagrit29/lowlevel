package lowleveldesignv2.inventorysystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class InventoryManager {
     HashMap<String, Warehouse> warehouses;

     public InventoryManager(List<String> warehouseIds) {
          this.warehouses = new HashMap<>();
          for(String id: warehouseIds) {
               warehouses.putIfAbsent(id, new Warehouse(id));
          }
     }

     public void addStock(String warehouseId, String productId, int quantity) {
          if(!warehouses.containsKey(warehouseId)) return; // warehouse not found

          // get warehouse and add stock
          Warehouse  warehouse = warehouses.get(warehouseId);
          warehouse.addStock(productId, quantity);
     }

     public void removeStock(String warehouseId, String productId, int quantity) {
          if(!warehouses.containsKey(warehouseId)) return;

          // get warehouse and remove stock
          Warehouse warehouse = warehouses.get(warehouseId);
          warehouse.removeStock(productId, quantity);
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

     public void transfer(String fromWarehouseId, String toWarehouseId, String productId, int quantity) {
          if(quantity <= 0) return; // input validation
          if(!warehouses.containsKey(fromWarehouseId) || !warehouses.containsKey(toWarehouseId)) return;

          Warehouse fromWh = warehouses.get(fromWarehouseId);
          Warehouse toWh = warehouses.get(toWarehouseId);


          Warehouse first = fromWarehouseId.compareTo(toWarehouseId) < 0 ? fromWh : toWh;
          Warehouse second = fromWarehouseId.compareTo(toWarehouseId) < 0 ? toWh : fromWh;

          synchronized (first) {
               synchronized (second) {
                    if(!fromWh.removeStock(productId, quantity)) return;

                    toWh.addStock(productId, quantity);
               }
          }
     }
}
