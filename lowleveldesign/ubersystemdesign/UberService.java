package lowleveldesign.ubersystemdesign;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

// now this is just like my uber App;, this will follow single;
// now this will have list of passengers and drivers;
public class UberService {
    public static UberService uberService;
    private Map<String, Passenger>  passengers;
    private Map<String, Driver> drivers;
    private Map<String, Ride> rides;
    private Queue<Ride> requestedRides;

    private UberService() {
        passengers = new ConcurrentHashMap<>();
        drivers = new ConcurrentHashMap<>();
        rides = new ConcurrentHashMap<>();
        requestedRides = new ConcurrentLinkedQueue<>();
    }

    public static UberService getUberService() {
        if(uberService == null) {
            synchronized (UberService.class) {
                uberService = new UberService();
            }
        }

        return uberService;
    }

    public void registerPassenger(Passenger passenger) {
        passengers.put(passenger.getId(), passenger);
    }

    public void registerDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    // here we create a ride and put it into the queue and request the drivers to pick it up;
    public void requestRide(Passenger passenger, Location pickup, Location drop) {
        // first will creqte a request in pending state and let it flow to the driver;
        Ride ride = new Ride(generateRideId(), passenger, null, pickup, drop, RideStatus.REQUESTED, 0.0);
        // offer doens't through exception error;
        requestedRides.offer(ride); // we cre
//        notifyDrivers(ride);

    }

    // now someone might pick the request; which is accept ride;
    public void acceptRide(Driver driver, Ride ride) {
        if(ride.getStatus() == RideStatus.REQUESTED) {
            ride.setDriver(driver);
            ride.setStatus(RideStatus.ACCEPTED);
            driver.setStatus(DriverStatus.ONRIDE);
            // now once driver accetped ride, We need to inform the passnger;
           // notifyPassenger(ride);
        }
    }

    // now once the driver has accepted the ride might started;
    public void startRide(Ride ride) {
        // maybe we
        if(ride.getStatus() == RideStatus.ACCEPTED) {
            ride.setStatus(RideStatus.IN_PROGRESS);
//            notifyPassenger(ride); // once ride is in progres;
        }
    }

    public void completeRide(Ride ride) {
        if (ride.getStatus() == RideStatus.IN_PROGRESS) {
            // todo here we can add check if it has reached the end location as well;

            ride.setStatus(RideStatus.COMPLETED);
            ride.getDriver().setStatus(DriverStatus.ONLINE);

            // now once the ride is completed, We need to create a payment also for this ride;
            double fare = calculateFare(ride);
            ride.setFare(fare);
//            Payment payment = new Payment()
            // notifyPassengers
            // notifiyDriver;

        }
    }

    private double calculateFare(Ride ride) {
        double baseFare = 2.0;
        double perKm = 1.5;
        double perMin = 0.25;

        double distance = calculateDistance(ride.getPickup(), ride.getDrop());
        double duration = calculateDuration(ride.getPickup(), ride.getDrop(), distance);

        return baseFare + distance*perKm + duration*perMin;
    }

    private double calculateDistance(Location source, Location Destination) {
        // learn more about Haversine formula
        return Math.random()*20 + 1; // random distance from 1 to km for assumption;
    }

    private double calculateDuration(Location source, Location destination, double distance) {
        // speed = distance/time;
        // time = distance/speed;
        return distance * (double)(30/60); // 30 km per hour, so 30 km 60 per minute;
    }

    private String generateRideId() {
        return "RIDE" + (int) (System.currentTimeMillis() / 1000);
    }

}
