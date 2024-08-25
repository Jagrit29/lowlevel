package lowleveldesign.ubersystemdesign;
import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private final String id;
    private String name;
    private String contact; // phone can be maded integer or number based on the requirement;
    private Location location;
    private List<Ride> rides;

    public Passenger(String id, String name, String contact, Location location) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.rides = new ArrayList<>();
        this.location = location;
        // now location can be either null when the user is added or it can be automatically fetched by the user.
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

    public List<Ride> getRides() {
        return rides;
    }

    public Location getLocation() {
        return location;
    }
}
