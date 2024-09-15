package lowlevelcoding.snippets;

public class POC4Deadlock {
    public static void main(String args[]) throws InterruptedException {
        DeadLock deadLock = new DeadLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                deadLock.task1();

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                deadLock.task2();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Finished");

    }
}

class DeadLock {
    // it has two shared locks/resource;
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    // now I can create two methods
    // one will lock r1 first, the other will lock r2;

    public void task1() {
        synchronized (resource1) {
            System.out.println("T1: Locked R1");
            // then it did some work
            try { Thread.sleep(50); } catch (InterruptedException e) {}

            synchronized (resource2) {
                System.out.println("T1: Locked R2");
            }

        }
    }

    public void task2() {
        synchronized (resource2) {
            System.out.println("T2: Locked R2");
            // then it did some work
            try { Thread.sleep(50); } catch (InterruptedException e) {}

            synchronized (resource1) {
                System.out.println("T2: Locked R1");
            }

        }
    }
}
