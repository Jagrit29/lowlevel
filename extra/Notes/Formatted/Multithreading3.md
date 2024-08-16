### **Chapter 5: Data Sharing Between Threads**

#### **1. Stack and Heap Memory Regions**

**Stack Memory Region:**
- **Belongs to a Particular Thread:** Each thread has its own stack memory.
- **Local References and Primitives:** Local variables and primitive types are stored here.
- **Stack Frames:** For each method call, a stack frame is created within the stack. When the method returns, the frame is removed, and the return value is pushed to the previous stack frame.
- **Unique to Thread:** The stack is unique to each thread, meaning that local variables are not shared between threads.

**Heap Memory Region:**
- **Shared Among Threads:** The heap is a shared memory area accessible by all threads in a process.
- **Object Storage:** Objects and data structures are stored in the heap.
- **Class Members and Static Variables:** These are stored in the heap, allowing multiple methods and threads to access them.
- **Garbage Collection:** The heap is managed by the garbage collector, which reclaims memory used by objects no longer in use.

**Example in Java:**
```java
public class MemoryExample {
    private static int staticVar = 10; // Stored in heap

    public static void main(String[] args) {
        int localVar = 5; // Stored in stack

        Thread thread1 = new Thread(() -> {
            int threadVar = 20; // Unique to this thread
            System.out.println("Thread 1: " + threadVar);
        });

        Thread thread2 = new Thread(() -> {
            int threadVar = 30; // Unique to this thread
            System.out.println("Thread 2: " + threadVar);
        });

        thread1.start();
        thread2.start();
    }
}
```

#### **2. Object and Reference**

- **Example:** 
  ```java
  List<Integer> list = new ArrayList<>();
  ```
  - Here, `list` is a reference stored in the stack, while the actual `ArrayList` object is stored in the heap.

#### **3. Why Resource Sharing Between Threads?**

- **Resource:** Represents data or state, such as variables, data structures, files, or any object.
- **Shared Resources:** Threads can share everything in the heap within a process. Common examples include:
  - **Kafka Queue or Bus:** Shared among consumers to achieve high performance.
  - **Database in Microservices:** Multiple microservices accessing the same database, where the database is the shared resource.

#### **4. Problems of Sharing Resources in Threads**

- **Example Scenario:** An e-commerce site with an `InventoryCounter` class that tracks the count of items. If multiple threads access this shared resource, the `item` variable could lead to inconsistent results due to non-atomic operations.

- **Atomic Operations:** An atomic operation is one that completes entirely or not at all, with no intermediate states. For example:
  - `item++` is a three-step operation: 
    1. Retrieve the value.
    2. Compute the new value.
    3. Store the new value.
  - If interrupted, this non-atomic operation can lead to race conditions.

**Example of Race Condition in Java:**
```java
public class InventoryCounter {
    private int items = 0;

    public void increment() {
        items++; // Not atomic
    }

    public void decrement() {
        items--; // Not atomic
    }

    public int getItems() {
        return items;
    }

    public static void main(String[] args) throws InterruptedException {
        InventoryCounter counter = new InventoryCounter();

        Thread thread1 = new Thread(counter::increment);
        Thread thread2 = new Thread(counter::decrement);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final item count: " + counter.getItems());
    }
}
```
- **Outcome:** Due to the non-atomic nature of `items++` and `items--`, the final count may not be as expected, leading to potential inconsistencies.

### **Summary**
- **Stack:** Unique to each thread, containing local variables and method arguments.
- **Heap:** Shared among threads, containing objects, class members, and static variables.
- **Resource Sharing:** Threads can share resources in the heap, but this can lead to issues like race conditions if not handled properly.
- **Atomic Operations:** Crucial for ensuring consistency when threads modify shared resources.

This chapter emphasizes the importance of understanding how data is shared between threads and the potential pitfalls that arise in multithreaded environments. Proper synchronization techniques are essential to avoid issues like race conditions.