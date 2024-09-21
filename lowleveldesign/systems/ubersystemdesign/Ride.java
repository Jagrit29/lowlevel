package lowleveldesign.systems.ubersystemdesign;

// now what does Ride, it has pickup and drop, it has status, it has driver and passenger;, payment would also be there
// price of the ride;
public class Ride {
    private final String id;
    private Passenger passenger;
    private Driver driver;
    private Location pickup;
    private Location drop;
    private RideStatus status;
    private double fare;

    public Ride(String id, Passenger passenger, Driver driver, Location pickup, Location drop, RideStatus status, double fare) {
        this.id = id;
        this.passenger = passenger;
        this.driver = driver;
        this.pickup = pickup;
        this.drop = drop;
        this.status = status;
        this.fare = fare;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public void setFare(double amount) {
        this.fare = amount;
    }

    public String getId() {
        return id;
    }

    public double getFare() {
        return fare;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getPickup() {
        return pickup;
    }

    public RideStatus getStatus() {
        return status;
    }

    public Location getDrop() {
        return drop;
    }

    // ride' location will be constatnly updated too;
}
