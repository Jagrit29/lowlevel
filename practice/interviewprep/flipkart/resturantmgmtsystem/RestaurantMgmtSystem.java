package practice.interviewprep.flipkart.resturantmgmtsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RestaurantMgmtSystem {
    private static RestaurantMgmtSystem instance;
    private Map<String, Order> orders;
    private Map<String, Customer> customers;
    private Map<String, Table> tables;
    private Map<String, Menu> menus;

    public synchronized static RestaurantMgmtSystem getInstance() {
        if (instance == null) {
            instance = new RestaurantMgmtSystem();
        }
        return instance;
    }

    private RestaurantMgmtSystem() {
        orders = new ConcurrentHashMap<>();
        customers = new ConcurrentHashMap<>();
        tables = new ConcurrentHashMap<>();
        menus = new ConcurrentHashMap<>();
    }

    public void addTable(Table table, String adminId) {
        // TODO validate if the userId is of admin is valid;
        tables.put(table.getId(), table);
    }

    public void browseMenuByCategory(MenuItemCategory menuItemCategory) {
        List<String> selecteMenuItems = new ArrayList<>();
        for(Menu menu: menus.values()) {
            for(MenuItem menuItem: menu.getItems()) {
                if(menuItem.getMenuItemCategory().equals(menuItemCategory)) {
                    selecteMenuItems.add(menuItem.getName());
                }
            }
        }
        System.out.println(selecteMenuItems.toString());

    }






}
