## Throughput Performance Metric

**Throughput** is a performance metric that measures the number of tasks completed in a given period. It's typically expressed as the number of tasks per second. This metric is crucial when you have a program handling a concurrent flow of tasks and want to maximize the number of tasks completed as quickly as possible.

### Improving Throughput

1. **Breaking Tasks into Subtasks**:
    - **Concept**: If a task takes time \( T \) to complete, breaking it into subtasks can potentially improve throughput. If you have \( N \) subtasks, and each subtask takes \( T/N \) time, you can theoretically achieve a throughput of \( N/T \).
    - **Example**: Suppose you need to process large files. Instead of processing each file sequentially, you can split each file into chunks and process them concurrently. This can significantly improve throughput, though in practice, the improvement may be less due to overhead costs.

    ```java
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    public class TaskSplitterExample {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(4); // 4 threads in the pool

            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Processing task " + taskId);
                    // Simulate task processing
                    try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                });
            }

            executor.shutdown();
        }
    }
    ```

2. **Scheduling Tasks on Separate Threads**:
    - **Concept**: Assign each task to a separate thread. This method can achieve a throughput close to \( 1/T \), assuming tasks are independent and do not need to be broken down further.
    - **Example**: If you have multiple independent tasks that can be executed in parallel, creating separate threads for each task can improve throughput.

    ```java
    public class ThreadSchedulingExample {
        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                new Thread(() -> {
                    System.out.println("Processing task " + taskId);
                    // Simulate task processing
                    try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                }).start();
            }
        }
    }
    ```

3. **Thread Pooling**:
    - **Concept**: Instead of creating and destroying threads frequently, use a pool of pre-created threads to handle tasks. This reduces the overhead of thread creation and destruction.
    - **Example**: Java's `ExecutorService` provides a convenient way to implement thread pooling using a `FixedThreadPool`. The `FixedThreadPool` maintains a constant number of threads and manages task scheduling through an internal queue.

    ```java
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    public class ThreadPoolExample {
        public static void main(String[] args) {
            Executor

Service threadPool = Executors.newFixedThreadPool(4); // 4 threads in the pool

            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                threadPool.submit(() -> {
                    System.out.println("Processing task " + taskId);
                    // Simulate task processing
                    try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                });
            }

            threadPool.shutdown();
        }
    }
    ```

## Summary

- **Throughput** measures how many tasks are completed per unit time.
- To improve throughput, consider breaking tasks into smaller subtasks or scheduling each task on a separate thread.
- **Thread Pooling** helps maintain a constant number of threads, reducing the overhead associated with thread creation and destruction.
- Using a fixed thread pool, you can achieve high throughput and efficient resource utilization.

In practice, applying these techniques can lead to significant performance improvements, which will be explored further in practical examples in future lectures.

---

---

# HTTP Server Application Guide

## Overview

This guide explains how to build an HTTP server that serves as a basic search engine. The server processes HTTP requests to count the occurrences of a word in a large book ("War and Peace" by Tolstoy). We’ll also use Apache JMeter to test the performance of the server.

## 1. Building the HTTP Server

### 1.1. Overview

The HTTP server will:
- Load a large book into memory.
- Handle incoming HTTP requests to search for specific words in the book.
- Respond with the count of occurrences of the word.

### 1.2. Code Implementation

**WordCountServer.java**
```java
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class WordCountServer {
    private static String text;

    public static void main(String[] args) throws Exception {
        // Load the book into a string
        text = loadBook("war_and_peace.txt");

        // Create a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // Create the HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Create a context for handling requests
        server.createContext("/search", new WordCountHandler(text));

        // Set the executor
        server.setExecutor(executor);

        // Start the server
        server.start();
        System.out.println("Server started on port 8000...");
    }

    private static String loadBook(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}
```

**WordCountHandler.java**
```java
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class WordCountHandler implements HttpHandler {
    private final String text;

    public WordCountHandler(String text) {
        this.text = text;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response;
        int statusCode;

        // Get the query from the URI
        String query = exchange.getRequestURI().getQuery();
        if (query == null || !query.startsWith("word=")) {
            statusCode = HttpURLConnection.HTTP_BAD_REQUEST;
            response = "Invalid query. Use 'word=<word>'";
        } else {
            String word = query.substring("word=".length());
            int count = countOccurrences(word);
            response = Integer.toString(count);
            statusCode = HttpURLConnection.HTTP_OK;
        }

        // Send the response
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private int countOccurrences(String word) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }
}
```

## 2. Testing the HTTP Server

### 2.1. Overview

We’ll use Apache JMeter to measure the performance of the HTTP server by sending a large number of requests and analyzing the throughput.

### 2.2. Apache JMeter Setup

1. **Install Apache JMeter**:
    - Download and install JMeter from [Apache JMeter](https://jmeter.apache.org/).

2. **Create a Test Plan**:
    - **Thread Group**: Simulates concurrent users.
        - Set the number of threads (users) to 200.
    - **CSV Data Set Config**: Reads search words from a file.
        - File path: `search_words.csv`
        - Variable name: `word`
    - **HTTP Request**: Sends requests to the server.
        - Server name: `localhost`
        - Port number: `8000`
        - Method: `GET`
        - Path: `/search`
        - Parameters: `word=${word}`
    - **Listeners**:
        - **Summary Report**: Provides throughput and other metrics.
        - **View Results Tree**: For debugging and inspecting individual requests.

**search_words.csv** (Example content)
```
hello
world
peace
war
Tolstoy
```

**JMeter Test Plan Configuration**

1. **Open JMeter**.
2. **Add a Thread Group**:
    - Right-click on **Test Plan** → **Add** → **Threads (Users)** → **Thread Group**.
    - Set the number of threads to 200.
3. **Add a CSV Data Set Config**:
    - Right-click on **Thread Group** → **Add** → **Config Element** → **CSV Data Set Config**.
    - Set the file path to `search_words.csv` and variable name to `word`.
4. **Add an HTTP Request**:
    - Right-click on **Thread Group** → **Add** → **Sampler** → **HTTP Request**.
    - Configure the request with the server details and path.
5. **Add Listeners**:
    - Right-click on **Thread Group** → **Add** → **Listener** → **Summary Report**.
    - Right-click on **Thread Group** → **Add** → **Listener** → **View Results Tree**.

### 2.3. Running the Test

1. **Save** the test plan in JMeter.
2. **Run** the test plan to start sending requests and measuring performance.
3. **Analyze Results**:
    - Check the Summary Report for throughput and performance metrics.
    - Inspect individual request results in the View Results Tree.

## 3. Performance Analysis

- **ThreadPool Size Impact**:
    - Start with a ThreadPool size of 1 and measure the throughput.
    - Increase the ThreadPool size and observe the performance improvements.
    - Note that throughput improves with additional threads up to the number of physical cores and virtual cores.

- **Optimization Insights**:
    - Using a ThreadPool and handling requests concurrently improves performance.
    - Beyond the number of virtual cores, additional threads do not further improve throughput.
