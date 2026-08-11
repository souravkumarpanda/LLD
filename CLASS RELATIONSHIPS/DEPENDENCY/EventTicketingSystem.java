class SeatValidator {
    public boolean isAvailable(String eventId, String seatNumber) {
        System.out.println("Checking seat " + seatNumber + " for event " + eventId);
        return true; // Simulated: a seat is available
    }
}

class PaymentProcessor {
    public boolean charge(String email, double amount) {
        System.out.println("Charging $" + amount + " to " + email);
        return true; // Simulated: payment succeeds
    }
}

class QRCodeGenerator {
    public String generate(String eventId, String seatNumber) {
        String qrCode = "QR-" + eventId + "-" + seatNumber;
        System.out.println("Generated QR code: " + qrCode);
        return qrCode;
    }
}

class EmailService {
    public void sendConfirmation(String email, String qrCode) {
        System.out.println("Sending confirmation to " + email + " with code " + qrCode);
    }
}

class TicketBookingService {
    public boolean bookTicket(String eventId, String seatNumber, String email,
                              double amount, SeatValidator validator,
                              PaymentProcessor payment, QRCodeGenerator qrGenerator,
                              EmailService emailService) {
        if (!validator.isAvailable(eventId, seatNumber)) {
            System.out.println("Seat not available.");
            return false;
        }

        if (!payment.charge(email, amount)) {
            System.out.println("Payment failed.");
            return false;
        }

        String qrCode = qrGenerator.generate(eventId, seatNumber);
        emailService.sendConfirmation(email, qrCode);

        System.out.println("Booking confirmed!");
        return true;
    }
}

public class EventTicketingSystem {
    public static void main(String[] args) {
        TicketBookingService bookingService = new TicketBookingService();

        // All dependencies are created externally and passed in
        SeatValidator validator = new SeatValidator();
        PaymentProcessor payment = new PaymentProcessor();
        QRCodeGenerator qrGenerator = new QRCodeGenerator();
        EmailService emailService = new EmailService();

        bookingService.bookTicket("CONF-2025", "A12", "alice@example.com",
            99.99, validator, payment, qrGenerator, emailService);
    }
}
