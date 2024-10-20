package lowleveldesign.systems.medicalsystem;

import java.util.List;

public class Patient {
    private String id;
    private String name;
    private List<Appointment> bookedAppointments;

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Appointment> getBookedAppointments() {
        return bookedAppointments;
    }

    // Methods for booking and viewing appointments
}

