package lowleveldesign.systems.hotelmanagement;

public class Room {
    private String id;
    private double price;
    private RoomType type;
    private RoomStatus status;

    public Room(String id, RoomType type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.status = RoomStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public synchronized RoomStatus getStatus() {
        return status;
    }

    public RoomType getType() {
        return type;
    }

    public synchronized void bookRoom() throws IllegalStateException {
        if(status == RoomStatus.AVAILABLE) {
            status = RoomStatus.BOOKED;
        } else {
            throw new IllegalStateException("Room not available");
        }
    }

    public synchronized void checkIn() throws IllegalStateException {
        if(status == RoomStatus.BOOKED) {
            status = RoomStatus.OCCUPIED;
        } else {
            throw new IllegalStateException("Room not booked");
        }
    }

    public synchronized void checkOut() throws IllegalStateException {
        if(status == RoomStatus.OCCUPIED) {
            status = RoomStatus.AVAILABLE;
        } else {
            throw new IllegalStateException("Room not occupied");
        }
    }

}
