import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Room {
    private String number;
    private int floor;

    public Room(String number, int floor) {
        this.number = number;
        this.floor = floor;
    }

    public String getNumber() { return number; }
    public int getFloor() { return floor; }
}

class Appointment {
    private Doctor doctor;
    private Patient patient;
    private Room room;
    private String time;

    public Appointment(Doctor doctor, Patient patient, Room room, String time) {
        this.doctor = doctor;
        this.patient = patient;
        this.room = room;
        this.time = time;
        doctor.addAppointment(this);
        patient.addAppointment(this);
    }

    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public Room getRoom() { return room; }
    public String getTime() { return time; }
}

class Doctor {
    private String name;
    private String specialization;
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public void addAppointment(Appointment appt) {
        appointments.add(appt);
    }

    public List<Patient> getPatients() {
        return appointments.stream()
            .map(Appointment::getPatient)
            .distinct()
            .collect(Collectors.toList());
    }

    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public List<Appointment> getAppointments() { return appointments; }
}

class Patient {
    private String name;
    private List<Appointment> appointments = new ArrayList<>();

    public Patient(String name) {
        this.name = name;
    }

    public void addAppointment(Appointment appt) {
        appointments.add(appt);
    }

    public List<Doctor> getDoctors() {
        return appointments.stream()
            .map(Appointment::getDoctor)
            .distinct()
            .collect(Collectors.toList());
    }

    public String getName() { return name; }
    public List<Appointment> getAppointments() { return appointments; }
}

public class HospitalAppointmentSystem {
    public static void main(String[] args) {
        Doctor drSmith = new Doctor("Dr. Smith", "Cardiology");
        Doctor drPatel = new Doctor("Dr. Patel", "Neurology");

        Patient alice = new Patient("Alice");
        Patient bob = new Patient("Bob");

        Room room101 = new Room("101", 1);
        Room room205 = new Room("205", 2);

        new Appointment(drSmith, alice, room101, "9:00 AM");
        new Appointment(drSmith, bob, room101, "10:00 AM");
        new Appointment(drPatel, alice, room205, "2:00 PM");

        System.out.println(drSmith.getName() + "'s patients:");
        for (Patient p : drSmith.getPatients()) {
            System.out.println("  - " + p.getName());
        }

        System.out.println(alice.getName() + "'s doctors:");
        for (Doctor d : alice.getDoctors()) {
            System.out.println("  - " + d.getName() + " (" + d.getSpecialization() + ")");
        }

        System.out.println(drSmith.getName() + "'s schedule:");
        for (Appointment a : drSmith.getAppointments()) {
            System.out.println("  - " + a.getTime() + " with " + a.getPatient().getName()
                + " in Room " + a.getRoom().getNumber());
        }
    }
}
