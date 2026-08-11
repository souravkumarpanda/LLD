interface NotificationService {
    void send(String recipient, String message);
}

class EmailNotifier implements NotificationService {
    public void send(String recipient, String message) {
        System.out.println("[Email] To: " + recipient + " | " + message);
    }
}

class SlackNotifier implements NotificationService {
    public void send(String recipient, String message) {
        System.out.println("[Slack] Channel: " + recipient + " | " + message);
    }
}

class WebhookNotifier implements NotificationService {
    public void send(String recipient, String message) {
        System.out.println("[Webhook] URL: " + recipient + " | " + message);
    }
}

class AlertService {
    private NotificationService notifier;

    public AlertService(NotificationService notifier) {
        this.notifier = notifier;
    }

    public void triggerAlert(String recipient, String issue) {
        String alertMessage = "ALERT: " + issue;
        notifier.send(recipient, alertMessage);
    }
}

public class NotificationServiceSystem {
    public static void main(String[] args) {
        AlertService emailAlerts = new AlertService(new EmailNotifier());
        emailAlerts.triggerAlert("ops@company.com", "CPU usage at 95%");

        AlertService slackAlerts = new AlertService(new SlackNotifier());
        slackAlerts.triggerAlert("#incidents", "Database connection pool exhausted");

        AlertService webhookAlerts = new AlertService(new WebhookNotifier());
        webhookAlerts.triggerAlert("https://hooks.example.com/alerts", "Disk usage at 90%");
    }
}
