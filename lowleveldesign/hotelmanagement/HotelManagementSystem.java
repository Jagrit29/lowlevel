package lowleveldesign.hotelmanagement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// We will assume this is our one hotel managesystem for 1 hotel
// Hotel wil have list of guest, rooms, and bookings
class HotelManagementSystem {
    private static HotelManagementSystem instance;
    private Map<String, Room> rooms;
    private Map<String, Guest> guests;
    private Map<String, Booking> bookings;


    private HotelManagementSystem() {
        rooms = new ConcurrentHashMap<>();
        guests = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
    }

    // singleton assuming there is only 1 instance for 1 hotel for now;
    public static synchronized HotelManagementSystem getInstance() {
        if(instance == null) {
            instance = new HotelManagementSystem();
        }

        return instance;
    }

    public void addGuest(Guest guest) {
        guests.put(guest.getId(), guest);
    }

    public void addRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    public synchronized Booking bookRoom(Room room, Guest guest, LocalDate checkIn, LocalDate checkOut) {
        // for a particular room, guest and for date and checkout book that room and give the booking to the usr;
        if(room.getStatus() == RoomStatus.AVAILABLE) {
            room.bookRoom();
            Booking booking = new Booking("uuid123", room, guest, checkIn, checkOut);
            bookings.put(booking.getId(), booking);

            return booking;
        }

        return null;
    }

    public synchronized void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);

        if(booking != null) {
            booking.cancel();
            bookings.remove(bookingId);
        }

    }

    // Now guest can come and checkIn too, Receptionist will ask for BookingId

    public synchronized void checkIn(String bookingId) {
        Booking booking = bookings.get(bookingId);

        if(booking != null && booking.getStatus() == BookingStatus.CONFIRMED) {
            booking.getRoom().checkIn();
        } else {
            throw new IllegalStateException("Invalid Booking Id or Booking not Confirmed");
        }
    }

    public synchronized void checkOut(String bookingId, Payment payment) {
        // here we can add Payment method too;
        Booking booking = bookings.get(bookingId);

        if(booking != null && booking.getStatus() == BookingStatus.CONFIRMED) {
            double dailyPrice = booking.getRoom().getPrice();
            long numberOfDays = ChronoUnit.DAYS.between( booking.getCheckInDate(), booking.getCheckOutDate());
            double totalPrice = dailyPrice * numberOfDays;
            if(payment.processPayment(totalPrice))  // make payment using the method given in the number;
            {
                booking.getRoom().checkOut();
                bookings.remove(bookingId);
            }
        }
    }




}
