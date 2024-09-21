package lowleveldesign.systems.hotelmanagement;

import java.time.LocalDate;

public class HMSDemo {
    public static void main(String args[]) {
        // If the
        HotelManagementSystem jagritPlazza = HotelManagementSystem.getInstance();

        // I have need to add some rooms to the hotel;
        Room room1 = new Room("R1", RoomType.Single, 100);
        Room room2 = new Room("R2", RoomType.Suite, 500);

        // Add these room to hotel;

        jagritPlazza.addRoom(room1);
        jagritPlazza.addRoom(room2);

        // now guest
        Guest guest1 = new Guest("123", "Jordan");

        // now this guest wants to book roomt;
        LocalDate checkInDate = LocalDate.now();
        LocalDate checkOutDate = checkInDate.plusDays(3);

        Booking booking1 = jagritPlazza.bookRoom(room1, guest1, checkInDate, checkOutDate);

        // now I want to checking
        jagritPlazza.checkIn(booking1.getId());

        // now I want to checkout
        jagritPlazza.checkOut(booking1.getId(), new CreditCardPayment());



    }
}
