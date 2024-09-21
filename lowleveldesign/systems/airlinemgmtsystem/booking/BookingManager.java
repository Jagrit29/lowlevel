package lowleveldesign.systems.airlinemgmtsystem.booking;

import lowleveldesign.systems.airlinemgmtsystem.Flight;
import lowleveldesign.systems.airlinemgmtsystem.Passenger;
import lowleveldesign.systems.airlinemgmtsystem.Seat;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BookingManager {
    private static BookingManager instance;
    private Map<String, Booking> bookings;
    private final Object lock = new Object();

    private BookingManager() {
        bookings = new ConcurrentHashMap<>();
    }

    public static synchronized BookingManager getInstance() {
        if(instance == null) {
            instance = new BookingManager();
        }

        return instance;
    }

    // create booking for a aprticular flight;

    public Booking createBooking(Flight flight, Passenger passenger, Seat seat, double price) {
        Booking booking = new Booking("123", flight, passenger, seat, price);
        synchronized (lock) {
            bookings.put(booking.getBookingNumber(), booking);
        }

        return booking;
    }

    public void cancelBooking(String bookingNumber) {
        synchronized (lock) {
            Booking booking = bookings.get(bookingNumber);
            if (booking != null) {
                booking.cancel();
            }
        }
    }


}
