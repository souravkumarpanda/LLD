package LOD.lodviolation;

class Profile {
    private String fullName;
    public Profile(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName() {
        return fullName;
    }
}

class Registration {
    private String licensePlate;
    public Registration(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
}

class Vehicle {
    private Registration registration;
    public Vehicle(Registration registration) {
        this.registration = registration;
    }
    public Registration getRegistration() {
        return registration;
    }
}

class Driver {
    private Profile profile;
    private Vehicle vehicle;
    public Driver(Profile profile, Vehicle vehicle) {
        this.profile = profile;
        this.vehicle = vehicle;
    }
    public Profile getProfile() {
        return profile;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
}

class ContactInfo {
    private String phoneNumber;
    public ContactInfo(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class Passenger {
    private ContactInfo contactInfo;
    public Passenger(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
    public ContactInfo getContactInfo() {
        return contactInfo;
    }
}

class Ride {
    private Driver driver;
    private Passenger passenger;
    public Ride(Driver driver, Passenger passenger) {
        this.driver = driver;
        this.passenger = passenger;
    }
    public Driver getDriver() {
        return driver;
    }
    public Passenger getPassenger() {
        return passenger;
    }
}

class NotificationService {
    public void sendRideUpdate(Ride ride) {
        // LoD violation - Train Wreck 1
        String driverName = ride.getDriver()
                .getProfile()
                .getFullName();
        // LoD violation - Train Wreck 2
        String plate = ride.getDriver()
                .getVehicle()
                .getRegistration()
                .getLicensePlate();
        // LoD violation - Train Wreck 3
        String phone = ride.getPassenger()
                .getContactInfo()
                .getPhoneNumber();
        String message = String.format(
                "Your driver %s is arriving in a %s. Contact: %s",
                driverName,
                plate,
                phone
        );
        System.out.println("SMS to " + phone + ": " + message);
    }
}

public class LODViolation {
    public static void main(String[] args) {
        // Driver profile
        Profile profile = new Profile("Rahul Kumar");
        // Vehicle registration
        Registration registration = new Registration("OD-02-AB-1234");
        // Vehicle
        Vehicle vehicle = new Vehicle(registration);
        // Driver
        Driver driver = new Driver(profile, vehicle);
        // Passenger contact information
        ContactInfo contactInfo = new ContactInfo("9876543210");
        // Passenger
        Passenger passenger = new Passenger(contactInfo);
        // Ride
        Ride ride = new Ride(driver, passenger);
        // Notification service
        NotificationService notificationService = new NotificationService();
        notificationService.sendRideUpdate(ride);
    }
}
