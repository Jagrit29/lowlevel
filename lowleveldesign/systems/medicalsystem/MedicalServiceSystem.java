package lowleveldesign.systems.medicalsystem;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MedicalServiceSystem {
    private static MedicalServiceSystem medicalServiceSystemInstance;
    //  private Map<String, List<Doctor>> doctorBySpeciality;
    private Map<String, Doctor> doctors;
    private Map<String, Patient> patients;
    private Map<String, Appointment> appointments;

    private MedicalServiceSystem() {
        // String
        this.doctors = new ConcurrentHashMap<>();
        this.patients = new ConcurrentHashMap<>();
        this.appointments = new ConcurrentHashMap<>();
    }

    public synchronized static MedicalServiceSystem getInstance() {
        if(medicalServiceSystemInstance == null) {
            medicalServiceSystemInstance = new MedicalServiceSystem();
        }

        return medicalServiceSystemInstance;
    }

    // register a doctor
    public void registerDoctor(String id, String name, Speciality speciality) {
        doctors.put(id, new Doctor(id, name, speciality));
    }

    // register a patient
    public void registerPatient(String id, String name) {
        patients.put(id, new Patient(id, name));
    }

    // declare doctor's availability for a day for given slots;
    public void declareDoctorAvailability(String doctorId, List<TimeSlot> slots) {
        doctors.get(doctorId).initializeAvailability(slots);
    }

    // book an appointment for a doctor and patient for a given Slot
    public boolean bookAppointment(String patientId, String doctorId, TimeSlot slot) {
        Patient patient = patients.get(patientId);
        Doctor doctor = doctors.get(doctorId);

        // check if patient already has an appointment at the same time;
        List<Appointment> patientAppointments = patient.getBookedAppointments();
        // traverse this and check if the above appintment is already there;

        List<TimeSlot> availableSlots = doctor.getAvailableSlots();

        // Check if the timeSlot is available for the doctor;
        for (TimeSlot ts : availableSlots) {
            if (ts.equals(slot)) {
                // it is free to be booked;
                Appointment appointment = new Appointment("1", patientId, doctorId, ts);
                doctor.getAvailableSlots().remove(ts); // remove this slot;
                appointments.put("1", appointment);

                // TODO - add appointment to patient
                // TODO - add appointment to doctor
                return true;
            }
        }
        return false;

    }

    // search slots by speciality;
    public void searchSlotsBySpeciality(Speciality speciality) {
        for(Doctor doctor: doctors.values()) {
            if(doctor.getSpeciality() == speciality) {
                List<String> displaySlots = new ArrayList<>();
                for(TimeSlot ts: doctor.getAvailableSlots()) {
                    displaySlots.add(ts.toString());
                }
                System.out.println(doctor.getId() +" "+ displaySlots.toString());
            }
        }
    }
}


// SDE 2 - Not data heavy
// Add Comments on the go - DSA
// Code Structure -
// Handle Exceptions and Mock the exception
// catch and Log the exception


