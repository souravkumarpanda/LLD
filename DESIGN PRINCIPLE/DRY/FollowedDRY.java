package DRY;

class MessageFormatter {
    public static String format(String category, String userId, String detail) {
        String message = "[" + category + "] Hi " + userId + ", " + detail;
        return message.substring(0, 1).toUpperCase() + message.substring(1);
    }
}

class NotificationSender {
    public static void send(String userId, String message) {
        System.out.println("Connecting to notification API...");
        System.out.println("Sending to " + userId + ": " + message);
        System.out.println("Notification sent successfully.");
    }
}

class OrderServiceFollowed {
    public void notifyOrderConfirmation(String userId, String orderId) {
        String message = MessageFormatter.format(
                "Order", userId, "your order " + orderId + " has been confirmed.");
        NotificationSender.send(userId, message);
    }
}

class ShippingServiceFollowed {
    public void notifyShipmentUpdate(String userId, String trackingId) {
        String message = MessageFormatter.format(
                "Shipping", userId, "your shipment " + trackingId + " is on its way.");
        NotificationSender.send(userId, message);
    }
}

class SupportServiceFollowed {
    public void notifyTicketResolution(String userId, String ticketId) {
        String message = MessageFormatter.format(
                "Support", userId, "your ticket " + ticketId + " has been resolved.");
        NotificationSender.send(userId, message);
    }
}

public class FollowedDRY {
    public static void main(String[] args) {
        OrderServiceFollowed orderService = new OrderServiceFollowed();
        ShippingServiceFollowed shippingService = new ShippingServiceFollowed();
        SupportServiceFollowed supportService = new SupportServiceFollowed();
        orderService.notifyOrderConfirmation("Sourav", "ORD101");
        System.out.println();
        shippingService.notifyShipmentUpdate("Sourav", "TRK56789");
        System.out.println();
        supportService.notifyTicketResolution("Sourav", "TKT999");
    }
}
