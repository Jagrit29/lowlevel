package lowleveldesign.systems.messagequeue;

import java.util.ArrayList;
import java.util.List;

class NotificationService {
    private NotificationQueue queue = new NotificationQueue();
    private List<Thread> consumers = new ArrayList<>();

    public void startConsumers(int numConsumers, String notificationType) {
        for (int i = 0; i < numConsumers; i++) {
            String consumerId = notificationType + " Consumer " + (i + 1);
            Thread consumerThread = new Thread(new Consumer(queue, consumerId));
            consumers.add(consumerThread);
            consumerThread.start();
        }
    }

    public void startProducer(String userId, String userName, String notificationType, String message) {
        Thread producerThread = new Thread(new Producer(queue, userId, userName, NotificationType.SMS, message));
        producerThread.start();
    }
}
