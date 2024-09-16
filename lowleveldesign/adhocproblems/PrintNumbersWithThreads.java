package lowleveldesign.adhocproblems;

public class PrintNumbersWithThreads {
    public static void main(String[] args) {
        // Shared object for printing numbers
        SharedPrinter printer = new SharedPrinter();

        // Creating three threads
        Thread t1 = new Thread(() -> printer.printNumbers(1));
        Thread t2 = new Thread(() -> printer.printNumbers(2));
        Thread t3 = new Thread(() -> printer.printNumbers(3));

        // Starting the threads
        t1.start();
        t2.start();
        t3.start();
    }
}

// Class to handle the shared printing logic
class SharedPrinter {
    private int currentNumber = 1; // Start printing from 1
    private final int MAX = 100;    // Maximum number to print
    private int turn = 1;           // Thread 1 starts first

    // Method to print numbers for each thread
    public synchronized void printNumbers(int threadId) {
        while (currentNumber <= MAX) {
            // Wait if it's not the current thread's turn
            while (turn != threadId) {
                try {
                    wait();  // Waiting for the other thread to finish
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Print the number if still within the limit
            if (currentNumber <= MAX) {
                System.out.println("Thread " + threadId + ": " + currentNumber);
                currentNumber++;
            }

            // Change the turn to the next thread (1, 2, 3, then back to 1)
            turn = (turn % 3) + 1;

            // Notify all threads to wake up and check whose turn it is
            notifyAll();
        }
    }
}

