package lowleveldesign.systems.messagequeue;

public class NotificationFactory {
    public static NotificationStrategy getStrategy(NotificationType notificationType) {
        switch (notificationType) {
            case NotificationType.EMAIL:
                return new EmailNotificationStrategy();
            default:
                return new SMSNotificationStrategy(); // default

        }
    }
}
