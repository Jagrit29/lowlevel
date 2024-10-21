package practice.interviewprep.flipkart.resturantmgmtsystem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Menu {
    private String id;
    private Map<String, MenuItem> items;

    public Menu(String id) {
        this.id = id;
        this.items = new ConcurrentHashMap<>();
    }

    public boolean addMenuItem(MenuItem item) {
        items.put(item.getId(), item);

        return true;
    }

    public boolean updateMenuItem(String itemId, boolean newAvailability) {
        items.get(itemId).setAvailablity(newAvailability);

        return true;
    }

    public List<MenuItem> getItems() {
        return items.values().stream().toList();
    }
}
