package lowleveldesign.systems.carRentalsystem;
import java.util.*;
// first object or entity that I can see in this system is a vechice which for us will be car only but yeah it can be scaled to other type later;
enum Status {
    ACTIVE,
    INACTIVE,
}

enum VehicleType {
    CAR,
    BIKE,
}

class Vehicle {
    int id;
    int seats;
    int cc;
    String licensePlate;
    String registrationDate;
    Status status;
    VehicleType type;

    // then here will be getter and setter to have encapsulation as we don't want the data to be changed;
}

class Location {
    int pin;
    String address;
    String city;
    String state;
}

class User {
    int id;
    String name;
}

// now each vehicle will be in a particular store;
// now reservation in a story will also be there;
// reservation have it's own status
// t
class Reservation {
    int id;
    User user;
    Vehicle vehicle;
    String bookingDetails; // this can be scaled
}


class Store {
    int id;
    Location location; // but this is a object so we need to put it ousite like location object
    List<Vehicle> vehicles; // now this can be put in another objecte called vehicleInventoryMgmt which can be scaled accordingly based on the vechicle type;

}




public class CarRentalSystem {
}
