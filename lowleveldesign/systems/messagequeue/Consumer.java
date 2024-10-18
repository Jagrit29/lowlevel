package lowleveldesign.systems.messagequeue;

class Consumer implements Runnable {
    private NotificationQueue queue;
    private String consumerId;

    public Consumer(NotificationQueue queue, String consumerId) {
        this.queue = queue;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        while (true) {
            Notification notification = queue.consume();
            if (notification != null) {
                NotificationStrategy sender = NotificationFactory.getStrategy(notification.getType());
                sender.sendNotification(notification);
                System.out.println(consumerId + " sent notification to " + notification.getUserName());
            }
        }
    }
}
