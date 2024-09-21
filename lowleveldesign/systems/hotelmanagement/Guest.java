package lowleveldesign.systems.hotelmanagement;

public class Guest {
    private String id;
    private String name;


    public Guest(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
