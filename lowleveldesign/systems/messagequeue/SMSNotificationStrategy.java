package lowleveldesign.systems.messagequeue;

public class SMSNotificationStrategy implements NotificationStrategy {
    public void sendNotification(Notification notification) {
        System.out.println("Sending notification via SMS");
    }
}
