package practice;
import java.util.*;

// I want to run multiple threads that will go over loop pararelle;y
public class ThreadPractice {
    public static void main(String args[]) {
        // What is the purpose of the thread;
        // So I have lets' say 100 array elements and I want to process 50-50 paraelly not in a single trhead;

        int arr[] = new int[100];
        int n = arr.length;

        for(int i=0;i<n;i++) {
            arr[i] = i;
        }

        // now I need two threads;
        int ind1 = 0;
        int ind2 = 30;

        long st = System.nanoTime();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=ind1;i<ind2;i++) {
                    System.out.println("This is being executed by"+""+Thread.currentThread().getName());
                }
            }
        });
        thread1.setName("t1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=ind2;i>0;i++) {
                    System.out.println("This is being executed by"+""+Thread.currentThread().getName());
                }
            }
        });

        thread2.setName("t2");
        thread2.setDaemon(true); // this will make sure the applicaiton ends when it is no longer required;


        thread1.start();
        thread2.start();

//        thread1.interrupt();
//
        long et = System.nanoTime();

        System.out.println("time" +":"+ (et - st));


        // threadJoin means we will wait for a particular thread to finish


        // now if the thread is normall and

        // We want to optimise the performancey
        // Latency - Time taken to complete one request
        // tRhoguhtput - Number of tasks completed in a given epriod of time.

        // now big task it would take 10 mins now I divied it into sub tasks and each task takes 1 min pararelly so total time is 1 min;
        // when the process has good amount of I/O
        // Throughput means number of tasks done;
        // Let's say 1 tasks takes T time;
        // // you ahve T time; Throght is 1
        // now you brok those into N taks and each taks takes t/N yime;
        // N - T
        // T/N
        // 1 - T
        // N - T/N
        // N/T
        // ` 1 to T
        //  N

    }
}
