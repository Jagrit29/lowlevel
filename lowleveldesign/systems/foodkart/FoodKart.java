package lowleveldesign.systems.foodkart;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class User {
    // all these are private;
    String name;
    String gender; // optimisation to make it an enum
    String phoneNumber;
    String pincode;

    public User(String name, String gender, String phoneNumber, String pincode) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPincode() {
        return pincode;
    }
}

class Restaurant {
    String name;
    String dishName;
    int dishPrice;
    int dishQuantity;
    List<String> availablePincodes;

    public Restaurant(String name, String dishName, int dishPrice, int dishQuantity, List<String> pincodes) {
        this.name = name;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishQuantity = dishQuantity;
        this.availablePincodes = new ArrayList<>(pincodes);
    }

    public synchronized boolean isServiceable(String pincode) {
        for(String pin: availablePincodes) {
            if (pin.equals(pincode) && dishQuantity > 0) return true;
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public String getDishName() {
        return dishName;
    }
}


public class FoodKart {
    Map<String, User> users;
    Map<String, Restaurant> restaurants;
    String loggedInUser;
    public static FoodKart instance;

    private FoodKart() {
        loggedInUser = "";
        users = new ConcurrentHashMap<>();
        restaurants = new ConcurrentHashMap<>();
    }

    public synchronized static FoodKart getFoodKart() {
        if(instance == null) {
            instance = new FoodKart();
        }

        return instance;
    }

    public void registerUser(User user) {
        users.put(user.getPhoneNumber(), user);
    }

    public void registerRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getName(), restaurant);
    }

    public void loginUser(String userPhoneNumber) {
        loggedInUser = userPhoneNumber;
    }

    public List<String> showRestaurants(String query) {
        // use enum;
        if(query.equals("price")) {
            return showRestaurantsByPrice();
        } else if(query.equals("rating")) {
            // implement rating;
            return  showRestaurantsByPrice();
        }

        return new ArrayList<>();
    }

    public List<String> showRestaurantsByPrice() {
        // I have a loggin user;
        PriorityQueue<Restaurant> pq = new PriorityQueue<>(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return o1.dishPrice - o2.dishPrice;
            }
        });

        for(Restaurant restaurant: restaurants.values()) {
            String userPincode = users.get(loggedInUser).getPincode();
            if(restaurant.isServiceable(userPincode)) {
                pq.add(restaurant);
            }
        }
        System.out.println(pq.size() + " "+loggedInUser);

        List<String> result = new ArrayList<>();
        while(pq.size()>0) {
            Restaurant restaurant = pq.poll();
//            System.out.println(pq.size() + " "+loggedInUser);
            result.add(restaurant.getName() + " " + restaurant.getDishName());
        }

        return result;
    }
}

class FoodKartDemo {
    public static void main(String args[]) {

        FoodKart foodKart = FoodKart.getFoodKart();
        User user1 = new User("Pralove", "M", "phoneNumber-1", "HSR");
        User user2 = new User("Vatsal", "M", "phoneNumber-2", "BTM");

        foodKart.registerUser(user1);
        foodKart.registerUser(user2);

        Restaurant restaurant1 = new Restaurant("Food Court 1", "NI Thali", 100, 5, new ArrayList<>(Arrays.asList("BTM", "HSR")));
        Restaurant restaurant2 = new Restaurant("Food Court 2", "Burger", 120, 5, new ArrayList<>(Arrays.asList("BTM")));
        foodKart.registerRestaurant(restaurant1);
        foodKart.registerRestaurant(restaurant2);

        foodKart.loginUser(user1.getPhoneNumber());
        System.out.println(foodKart.showRestaurants("price"));

        foodKart.loginUser(user2.getPhoneNumber());
        System.out.println(foodKart.showRestaurants("price"));

    }
}
