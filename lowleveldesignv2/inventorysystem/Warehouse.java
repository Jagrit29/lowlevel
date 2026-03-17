package lowleveldesignv2.inventorysystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Warehouse {
    private String id;
    private final HashMap<String, Integer> inventory;
    private final HashMap<String, List<AlertConfig>> alertConfigs; // for particular product, list of alert configs;


    public Warehouse(String id) {
        this.id = id;
        this.inventory = new HashMap<>();
        this.alertConfigs = new HashMap<>();
    }

    public String getId() {
        return id;
    }


    public void addStock(String productId, int quantity) {
        if(quantity<0) throw new IllegalArgumentException("Quantity must be postive");

        List<AlertToFire> alertsToFire = new ArrayList<>();

        // add an lock on this warehouse;
        synchronized (this) {
            int currentQty = inventory.getOrDefault(productId, 0);
            int newQty = currentQty + quantity;
            inventory.put(productId, newQty);

            // there could be multiple alerts for the same product;
            alertsToFire = getAlertsToFire(productId, currentQty, newQty);

        }

        if(!alertsToFire.isEmpty()) fireAlerts(alertsToFire);
    }

    public boolean removeStock(String productId, int quantity) {
       //  if(!inventory.containsKey(productId) || inventory.get(productId) < quantity) return false;
        List<AlertToFire> alertsToFire = new ArrayList<>();

        // add a lock on this warehouse;
        synchronized (this) {
            int currentQty = inventory.getOrDefault(productId, 0);
            int newQty = currentQty - quantity;
            inventory.put(productId, newQty);

            // there could be multiple alerts for the same product;
            alertsToFire = getAlertsToFire(productId, currentQty, newQty);

        }

        if(!alertsToFire.isEmpty()) fireAlerts(alertsToFire);

        return true;
    }

    public boolean checkAvailability(String productId, int quantity) {
        return inventory.containsKey(productId) && inventory.get(productId) >= quantity;
    }

    private List<AlertToFire> getAlertsToFire(String productId, int previousQty, int newQty) {
        List<AlertToFire> alerts = new ArrayList<>();
        List<AlertConfig> configs = alertConfigs.get(productId); // null checking;

        if(configs == null) return alerts;

        for(AlertConfig config: configs) {
            if(previousQty >= config.getThreshold() && newQty < config.getThreshold()) {
                alerts.add(new AlertToFire(config.getListener(), productId, newQty));
            }
        }

        return alerts;
    }

    private void fireAlerts(List<AlertToFire> alerts) {
        for(AlertToFire alert: alerts) {
            alert.listener.onLowStock(id, alert.productId, alert.quantity);
        }
    }

    private static class AlertToFire {
        private final AlertListener listener;
        private final String productId;
        private final int quantity;

        public AlertToFire(AlertListener listener, String productId, int quantity) {
            this.listener = listener;
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    public synchronized void setLowAlert(String productId, int threshold, AlertListener listener) {
        // error handling
        alertConfigs.computeIfAbsent(productId, k -> new ArrayList<>());
        alertConfigs.get(productId).add(new AlertConfig(threshold, listener));
    }
}
