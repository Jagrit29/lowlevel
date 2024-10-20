package lowleveldesign.systems.medicalsystem;

public class TimeSlot {
    private String startTime;
    private String endTime;
    private boolean isAvailable;

    public TimeSlot(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = true;  // Default available
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookSlot() {
        this.isAvailable = false;
    }
}
