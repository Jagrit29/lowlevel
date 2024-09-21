package lowleveldesign.systems.ubersystemdesign;

// First user will request ride,
// driver will accept ride;
// ride will be in progress
// complete ride
// cancelld ride;
public enum RideStatus {
    REQUESTED,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
}
