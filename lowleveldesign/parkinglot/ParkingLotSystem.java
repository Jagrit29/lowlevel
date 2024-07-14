package lowleveldesign.parkinglot;
// Requirements
/*
1. Parking Lot should have levels
2. Parking Spot
 */

enum VehicleType {
    CAR, TRUCK, BIKE
}

class Vehicle {
    private VehicleType vehicleType;
    private String licensePlate;

    public Vehicle(String licensePlate, VehicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    String getLicensePlate() {
        return licensePlate;
    }

    VehicleType getVehicleType() {
        return vehicleType;
    }

}

class ParkingSpot {
    private int spotNumber;
    private VehicleType vehicleType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
    }

    // now I need to check if the spot is available and this is should be thread safe;
    public synchronized boolean isAvl() {
        return parkedVehicle == null;
    }

    // now park and unpark;
    public synchronized boolean park(Vehicle vehicle) {
        if(isAvl() && vehicleType == vehicle.getVehicleType()) {
            parkedVehicle = vehicle;
            return true;
        }

        return false;
    }

    public synchronized void unpark() {
        parkedVehicle = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public Vehicle getParkedVehicle(){
        return parkedVehicle;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}


public class ParkingLotSystem {
}
