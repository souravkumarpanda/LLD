package LOD.lodfollowed;

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
    // Delegation method
    public String getDriverName() {
        return driver.getProfile().getFullName();
    }
    // Delegation method
    public String getVehiclePlate() {
        return driver.getVehicle()
                .getRegistration()
                .getLicensePlate();
    }
    // Delegation method
    public String getPassengerPhone() {
        return passenger.getContactInfo().getPhoneNumber();
    }
}

class NotificationService {
    public void sendRideUpdate(Ride ride) {
        // No train wreck here
        String driverName = ride.getDriverName();
        String plate = ride.getVehiclePlate();
        String phone = ride.getPassengerPhone();
        String message = String.format(
                "Your driver %s is arriving in a %s. Contact: %s",
                driverName,
                plate,
                phone
        );
        System.out.println("SMS to " + phone + ": " + message);
    }
}

public class LODFollowed {
    public static void main(String[] args) {
        // Create driver's profile
        Profile profile = new Profile("Rahul Kumar");
        // Create vehicle registration
        Registration registration = new Registration("OD-02-AB-1234");
        // Create vehicle
        Vehicle vehicle = new Vehicle(registration);
        // Create driver
        Driver driver = new Driver(profile, vehicle);
        // Create passenger contact information
        ContactInfo contactInfo = new ContactInfo("9876543210");
        // Create passenger
        Passenger passenger = new Passenger(contactInfo);
        // Create ride
        Ride ride = new Ride(driver, passenger);
        // Create notification service
        NotificationService notificationService = new NotificationService();
        // Send ride update
        notificationService.sendRideUpdate(ride);
    }
}
