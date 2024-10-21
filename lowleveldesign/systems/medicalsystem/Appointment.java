package lowleveldesign.systems.medicalsystem;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private TimeSlot timeSlot;

    public Appointment(String appointmentId, String patientId, String doctorId, TimeSlot timeSlot) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.timeSlot = timeSlot;
    }
    // Getters and setters
}

