package lowleveldesign.systems.medicalsystem;

import java.util.List;

public class Doctor {
    private String id;
    private String name;
    private Speciality speciality;
    private List<TimeSlot> availableSlots;
    private List<Appointment> bookedAppointments;

    public Doctor(String id, String name, Speciality speciality) {
        this.id = id;
        this.name = name;
        this.speciality= speciality;
    }

    public void initializeAvailability(List<TimeSlot> slots) {
        this.availableSlots.addAll(slots);
    }

    public List<TimeSlot> getAvailableSlots() {
        return availableSlots;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public String getId() {
        return id;
    }
}
