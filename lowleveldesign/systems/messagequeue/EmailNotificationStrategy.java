package lowleveldesign.systems.messagequeue;

public class EmailNotificationStrategy implements NotificationStrategy {
    public void sendNotification(Notification notification) {
        System.out.println("Sending notification via EMAIL");
    }
}
