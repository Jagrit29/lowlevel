package lowleveldesign.systems.messagequeue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotificationQueue {
    private Queue<Notification> queue;
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
        consumeLock.lock();
        try {
            if(queue.isEmpty()) {
                return null;
            }
            return queue.poll();
        } finally {
            consumeLock.unlock();
        }
    }

}
