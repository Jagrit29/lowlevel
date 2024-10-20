package lowleveldesign.systems.messagequeue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotificationQueue {
    private Queue<Notification> queue;
    // choose to avoid the flag on produce
    private Lock produceLock;
    private Lock consumeLock;

    public NotificationQueue() {
        queue = new LinkedList<>();
        produceLock = new ReentrantLock();
        consumeLock = new ReentrantLock();
    }

    // now one publish message into queue or consume message from queue;
    public void publish(Notification notification) {
        produceLock.lock();
        try {
            queue.add(notification);
        } finally {
            produceLock.unlock();
        }
    }

    public Notification consume() {
        consumeLock.lock(); // lock should have time out
        // flag the message is alternate - What if consumer dies after reading the lock?
        // Other way - Lock on the message, This is not at the top of queue, (DeadLetterQueue) (DLQ)
        // 1: Consumer read and just died
        // 2: Consumer read and stored but never processed
        try {
            if(queue.isEmpty()) {
                return null;
            }
            return queue.poll();
        } finally {
            consumeLock.unlock();
        }
    }

    // Consumer will cronjob

}
