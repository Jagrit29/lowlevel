package lowleveldesign.designpatterns.creational.factory;


import com.sun.jdi.request.InvalidRequestStateException;

interface TransportService {
    void deliver();
}

class Car implements TransportService {
    @Override
    public void deliver() {
        System.out.println("Car Transport Service");
    }
}

class Bike implements TransportService {
    @Override
    public void deliver() {
        System.out.println("Bike Transport Service");
    }
}

class Truck implements TransportService {
    @Override
    public void deliver() {
        System.out.println("Bike Transport Service");
    }
}

class TransportFactory {
    public static TransportService createTransportService(String type)  {
        switch (type.toUpperCase()) {
            case "CAR":
                return new Car();
            case "TRUCK":
                return new Truck();
            case "BIKE":
                return new Bike();
            default:
                throw new IllegalArgumentException("Unsupported transport type");
        }
    }
}


public class FactoryPattern {
    public static void main(String[] args) {
        // Bruteforce way;
        TransportService carTransportService = new Car();
        TransportService truckTransportService = new Truck();

        // Factory pattern, Give the initialization object to another factory based on some variable which would be runtime;
        TransportService bikeTransportService = TransportFactory.createTransportService("BIKE"); // this can be runtime variable;
        bikeTransportService.deliver();
    }
}
