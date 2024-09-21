package lowleveldesign.hotelmanagement;

import java.time.LocalDate;

public class Booking {
    private final String id;
    private final Room room;
    private final Guest guest;
    private final LocalDate checkInDate;
    private final LocalDate  checkOutDate;
    private BookingStatus status;


    public Booking(String id, Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = BookingStatus.CONFIRMED; // by default we will assume and we created the status;
    }

    public synchronized void cancel() throws IllegalStateException{
        if(status == BookingStatus.CONFIRMED) {
            status = BookingStatus.CANCELLED;
        } else {
            throw new IllegalStateException("Booking not confirmed");
        }
    }

    public String getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

}
