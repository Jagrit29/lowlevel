Here’s a formatted version of your notes on multithreading in Java with added Java code snippets. I've used Markdown format for better readability.

---

# MultiThreading in JAVA

## CHAPTER 1: Processes and Threads

### Process

All the applications we run on our laptop are managed by the operating system, which creates an instance for each application called a **Process**. Each process runs independently of others, and it is the CPU's job to manage these processes.

### Thread

Each process can have one or more threads. Threads within a process have their own stack and instruction pointer but share the process's memory and code. They are not completely independent within a process.

- **Shared Resources**: Threads share heap memory, code, and metadata.
- **Non-Shared Resources**: Threads do not share their stack and instruction pointer.

### Context Switching

When there are more threads than available cores, the OS performs context switching to manage multiple tasks. Context switching involves saving and restoring the state of threads, which can become costly if there are too many threads, leading to thrashing.

**Example of Context Switching:**
```java
public class ContextSwitchExample {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Task 1 - " + i);
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Task 2 - " + i);
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
```

### Thread Scheduling

The OS schedules threads by dividing time into epochs and assigning threads based on dynamic priority. This helps in managing interactions like UI actions and avoiding starvation of threads.

## MultiThread vs. MultiProcess

- **MultiThreading (MT)**: Preferred when a lot of data is shared between tasks. MT is faster with less context-switching overhead.
- **MultiProcessing (MP)**: Better for tasks requiring isolation or higher security. Processes are independent of each other.

### Why MultiThreading?

It helps achieve:
- **Concurrency**: Managing multiple tasks simultaneously.
- **Parallelism**: Executing multiple tasks at the same time.
- **Responsiveness and Performance**: Making applications more responsive and improving performance.

## CHAPTER 2: Practical Threads

In Java, thread-related properties and methods are encapsulated in the `Thread` class. You can create a thread by passing a `Runnable` object to a `Thread` object and then starting it.

**Creating and Starting a Thread:**
```java
public class SimpleThreadExample {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Running in a thread");

        Thread thread = new Thread(task);
        thread.start();
    }
}
```

### Thread Inheritance

You can also create a thread by extending the `Thread` class.

**Example of Thread Inheritance:**
```java
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in inherited thread");
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
```

## CHAPTER 3: Thread Termination

### Why and When

Threads may need to be terminated if they misbehave, their work is done, or they are consuming resources unnecessarily.

### How

Use `thread.interrupt()` to request termination if the thread supports interruption. Check for interruption using `Thread.interrupted()`.

**Example of Interrupting a Thread:**
```java
public class InterruptExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Thread work
                    System.out.println("Thread running...");
                    Thread.sleep(1000); // Simulating work
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        });

        thread.start();
        thread.interrupt(); // Request thread to stop
    }
}
```

### Daemon Threads

Daemon threads run in the background and do not prevent the application from exiting. Use `thread.setDaemon(true)` to mark a thread as a daemon.

**Example of Daemon Thread:**
```java
public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Handle interruption
                }
            }
        });

        daemonThread.setDaemon(true);
        daemonThread.start();

        // Main thread ends here, daemon thread will be terminated
    }
}
```

### Thread Coordination

To manage dependencies between threads, use `thread.join()` to wait for a thread to complete.

**Example of Thread Coordination:**
```java
public class ThreadCoordinationExample {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            System.out.println("Thread A is working");
        });

        Thread threadB = new Thread(() -> {
            try {
                threadA.join(); // Wait for thread A to finish
                System.out.println("Thread B is working");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
    }
}
```

## CHAPTER 4: Performance Optimization

### Performance Metrics

- **Latency**: Time to complete one task.
- **Throughput**: Amount of tasks completed in a unit time.

### Reducing Latency

Divide tasks into multiple independent subtasks to run them concurrently, improving performance and reducing latency.

### Hyperthreading

Hyperthreading allows a single core to handle two threads simultaneously by sharing resources.

### Inherent Costs

Breaking tasks into smaller threads has overheads such as task division, thread creation, and result combining. Small tasks may not benefit from multithreading.

### Image Processing

Multithreading is beneficial for processing images by dividing the image into pixels and processing each pixel concurrently.

### Handling I/O-bound Tasks

Multithreading is highly effective for I/O-bound tasks because it allows the CPU to perform other work while waiting for I/O operations to complete.

**Example of I/O-bound Multithreading:**
```java
public class IOBoundExample {
    public static void main(String[] args) {
        Runnable ioTask = () -> {
            try {
                // Simulating I/O operation
                Thread.sleep(2000);
                System.out.println("I/O operation completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread ioThread = new Thread(ioTask);
        ioThread.start();

        // CPU can perform other tasks while waiting for I/O operation
    }
}
```

---

Feel free to adjust the code snippets and explanations based on your specific needs or use cases.