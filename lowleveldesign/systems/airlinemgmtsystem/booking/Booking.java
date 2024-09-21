package lowleveldesign.systems.airlinemgmtsystem.booking;

import lowleveldesign.systems.airlinemgmtsystem.Flight;
import lowleveldesign.systems.airlinemgmtsystem.Passenger;
import lowleveldesign.systems.airlinemgmtsystem.Seat;
import lowleveldesign.systems.hotelmanagement.BookingStatus;

public class Booking {
    private String bookingNumber;
    private Passenger passenger;
    private Flight flight;
    private BookingStatus status;
    private Seat seat;
    private final double price;

    public Booking(String bookingNumber, Flight flight, Passenger passenger, Seat seat, double price) {
        this.bookingNumber = bookingNumber;
        this.flight = flight;
        this.passenger = passenger;
        this.seat = seat;
        this.price = price;
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        status = BookingStatus.CANCELLED;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

}
