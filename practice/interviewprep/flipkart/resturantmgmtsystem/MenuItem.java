package practice.interviewprep.flipkart.resturantmgmtsystem;

enum MenuItemType {
    VEG,
    NONVEG
}

enum MenuItemCategory {
    STARTER,
    MAIN_COURSE,
    DESSERT
}

public class MenuItem {
    private String id;
    private String name;
    private double price;
    private MenuItemType menuItemType;
    private MenuItemCategory menuItemCategory;
    private boolean isAvailable;

    public MenuItem(String id, String name, double price, MenuItemType menuItemType, MenuItemCategory menuItemCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuItemType = menuItemType;
        this.menuItemCategory = menuItemCategory;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailablity(boolean available) {
        isAvailable = available;
    }

    public MenuItemCategory getMenuItemCategory() {
        return menuItemCategory;
    }

    public String getName() {
        return name;
    }
}
