package lowleveldesign.systems.airlinemgmtsystem;


import lowleveldesign.systems.airlinemgmtsystem.booking.Booking;
import lowleveldesign.systems.airlinemgmtsystem.booking.BookingManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AirlineMgmtSystem {
    private final List<Flight> flights;
    private final List<Airport> airports;
    private final BookingManager bookingManager;


    public AirlineMgmtSystem() {
        flights = new ArrayList<>();
        airports = new ArrayList<>();
        bookingManager = BookingManager.getInstance();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public List<Flight> searchFlights(String source, String destination, LocalDate date) {
        return flights.stream()
                .filter(flight -> flight.getSource().getName().equals(source)
                        && flight.getDestination().getName().equals(destination)
                        && flight.getDepartureTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public Booking bookFlight(Flight flight, Passenger passenger, Seat seat, double price) {
        return bookingManager.createBooking(flight, passenger, seat, price);
    }

    public void cancelBooking(String bookingNumber) {
        bookingManager.cancelBooking(bookingNumber);
    }

}
