package DRY;

class OrderService {
    public void notifyOrderConfirmation(String userId, String orderId) {
        // Duplicated: message formatting
        String message = "[Order] Hi " + userId + ", your order "
                + orderId + " has been confirmed.";
        String formatted = message.toUpperCase().substring(0, 1)
                + message.substring(1);

        // Duplicated: sending logic
        System.out.println("Connecting to notification API...");
        System.out.println("Sending to " + userId + ": " + formatted);
        System.out.println("Notification sent successfully.");
    }
}

class ShippingService {
    public void notifyShipmentUpdate(String userId, String trackingId) {
        // Duplicated: message formatting
        String message = "[Shipping] Hi " + userId + ", your shipment "
                + trackingId + " is on its way.";
        String formatted = message.toUpperCase().substring(0, 1)
                + message.substring(1);

        // Duplicated: sending logic
        System.out.println("Connecting to notification API...");
        System.out.println("Sending to " + userId + ": " + formatted);
        System.out.println("Notification sent successfully.");
    }
}

class SupportService {
    public void notifyTicketResolution(String userId, String ticketId) {
        // Duplicated: message formatting
        String message = "[Support] Hi " + userId + ", your ticket "
                + ticketId + " has been resolved.";
        String formatted = message.toUpperCase().substring(0, 1)
                + message.substring(1);

        // Duplicated: sending logic
        System.out.println("Connecting to notification API...");
        System.out.println("Sending to " + userId + ": " + formatted);
        System.out.println("Notification sent successfully.");
    }
}
public class ViolatingDRY {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        ShippingService shippingService = new ShippingService();
        SupportService supportService = new SupportService();
        orderService.notifyOrderConfirmation("Sourav", "ORD101");
        System.out.println();
        shippingService.notifyShipmentUpdate("Sourav", "TRK56789");
        System.out.println();
        supportService.notifyTicketResolution("Sourav", "TKT999");
    }
}
