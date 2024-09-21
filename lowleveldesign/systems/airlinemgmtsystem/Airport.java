package lowleveldesign.systems.airlinemgmtsystem;

import lowleveldesign.systems.ubersystemdesign.Location;

public class Airport {
    private String id;
    private String name;
    private Location location;

    public Airport(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }
}
