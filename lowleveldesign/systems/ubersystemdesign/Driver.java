package lowleveldesign.systems.ubersystemdesign;


// Now here there can be many optimisation or changes like Driver could have another class reference like Vechile type. or isntead os just licenseplace;
public class Driver {
    private final String id;
    private String name;
    private String contact;
    private String licensePlate;
    private Location location;
    private DriverStatus status;

    public Driver(String id, String name, String contact, String licensePlate, Location location, DriverStatus status) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.licensePlate = licensePlate;
        this.location = location;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Location getLocation() {
        return location;
    }

    public DriverStatus getStatus() {
        return status;
    }

    // no two threads should make the driver busy;
    public synchronized void setStatus(DriverStatus status) {
        this.status = status;
    }

    // will se if I need more setters;
}
