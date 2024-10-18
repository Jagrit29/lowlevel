package lowleveldesign.systems.messagequeue;

import lowleveldesign.systems.messagequeue.NotificationQueue;

public class Producer implements Runnable {
    private NotificationQueue queue; // can change it to singleton
    private String userId;
    private String userName;
    private NotificationType notificationType;
    private String message;

    public Producer(NotificationQueue queue, String userId, String userName, NotificationType notificationType, String message) {
        this.queue = queue;
        this.userId = userId;
        this.userName = userName;
        this.notificationType = notificationType;
        this.message = message;
    }

    @Override
    public void run() {
        Notification notification = new Notification(userId, userName, "hello", notificationType);
        queue.publish(notification);
        System.out.println("Produced notification for " + userName);
    }
}