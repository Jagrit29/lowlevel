package lowlevelcoding.snippets;

public class UnSafeThread {
    public static void main(String args[]) throws InterruptedException {
        // first I need to create a process of threadCounter on which I will run the threads;
        ThreadUnsafeCounter countingProcess = new ThreadUnsafeCounter();

        // now I will run two threads the above object to do two similar things;'

        // now to make threads I need runnable
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++) {
                    countingProcess.increment();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++) {
                    countingProcess.decrement();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            }
        });

        // start the threads
        thread1.start();
        thread2.start();

        // wait for both the threads to finish
        thread1.join();
        thread2.join();

        countingProcess.displayCount();


    }
}

class ThreadUnsafeCounter {
    // shared variable for any thread running on the above class
    int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public void displayCount() {
        System.out.println(count);
    }
}
