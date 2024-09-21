package lowleveldesign.systems.airlinemgmtsystem;

enum SeatType {
    WINDOW,
    MIDDLE,
    AISLE
}

enum SeatStatus {
    OCCUPIED,
    FREE
}

public class Seat {
    private final String seatNumber;
    private final SeatType type;
    private SeatStatus status;

    public Seat(String seatNumber, SeatType type) {
        this.seatNumber = seatNumber;
        this.type = type;
        this.status = SeatStatus.FREE;
    }

    public void reserve() {
        status = SeatStatus.OCCUPIED;
    }

    public void release() {
        status = SeatStatus.FREE;
    }

}

