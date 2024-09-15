package lowlevelcoding.snippets;

import java.util.concurrent.Semaphore;

/*
Key Points:
Thread B starts and waits for the semaphore, which is initially unavailable (0 permits).
Thread A completes its initialization, then releases the semaphore (signals Thread B).
Thread B proceeds only after the semaphore is released.

 */
public class POC6Semaphore {
    public static void main(String[] args) {
        SignalingExample example = new SignalingExample();

        Thread t1 = new Thread(example::threadA, "Thread A");
        Thread t2 = new Thread(example::threadB, "Thread B");

        t2.start();  // Start Thread B first to show it's waiting
        t1.start();  // Start Thread A after to simulate initialization
    }
}

class SignalingExample {
    private final Semaphore semaphore = new Semaphore(0);  // Start with 0 permits

    public void threadA() {
        System.out.println("Thread A: Doing initialization...");
        try {
            Thread.sleep(2000);  // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread A: Initialization complete. Releasing semaphore.");
        semaphore.release();  // Signal to Thread B that it can proceed
    }

    public void threadB() {
        System.out.println("Thread B: Waiting for initialization to complete...");
        try {
            semaphore.acquire();  // Wait until Thread A releases the semaphore
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread B: Proceeding after initialization.");
    }
}
