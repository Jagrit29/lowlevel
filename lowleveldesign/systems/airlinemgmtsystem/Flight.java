package lowleveldesign.systems.airlinemgmtsystem;

import java.time.LocalDateTime;

public class Flight {
    private String id;
    private String name;
    private Airport source;
    private Airport destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Flight(String id, String name, Airport source, Airport destination, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Airport getSource() {
        return source;
    }

    public Airport getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
}
