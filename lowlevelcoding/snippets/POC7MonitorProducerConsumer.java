package lowlevelcoding.snippets;
import java.util.LinkedList;
import java.util.Queue;

public class POC7MonitorProducerConsumer {
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        // Producer thread
        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer thread
        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}


class ProducerConsumer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 5;

    // Produce data and add it to the buffer
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() == capacity) {
                    System.out.println("Buffer is full. Producer is waiting...");
                    wait();  // Wait until there's space in the buffer
                }

                System.out.println("Producing value: " + value);
                buffer.add(value++);
                notify();  // Notify the consumer that there’s data in the buffer
                Thread.sleep(1000);  // Simulate production delay
            }
        }
    }

    // Consume data from the buffer
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    System.out.println("Buffer is empty. Consumer is waiting...");
                    wait();  // Wait until there's data in the buffer
                }

                int value = buffer.poll();
                System.out.println("Consuming value: " + value);
                notify();  // Notify the producer that there's space in the buffer
                Thread.sleep(1000);  // Simulate consumption delay
            }
        }
    }
}
