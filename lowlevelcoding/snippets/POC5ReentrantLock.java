package lowlevelcoding.snippets;

import java.util.concurrent.locks.ReentrantLock;

class BankAccount2 {
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount2(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + " withdrawing " + amount);
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " new balance: " + balance);
            }
        } finally {
            lock.unlock();
        }
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " depositing " + amount);
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " new balance: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public void transfer(BankAccount2 targetAccount, double amount) {
        lock.lock();  // Acquire lock for this account
        try {
            System.out.println(Thread.currentThread().getName() + " transferring " + amount + " to " + targetAccount);
            this.withdraw(amount);  // Reentrant lock ensures we can lock again
            targetAccount.deposit(amount);  // Safe deposit into target account
        } finally {
            lock.unlock();  // Release lock
        }
    }
}

public class POC5ReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        BankAccount2 accountA = new BankAccount2(1000);
        BankAccount2 accountB = new BankAccount2(500);

        // Creating two threads that will perform the transfer operation
        Thread t1 = new Thread(() -> accountA.transfer(accountB, 200), "Thread 1");
        Thread t2 = new Thread(() -> accountA.transfer(accountB, 300), "Thread 2");

        t1.start();
        t2.start();

        // Wait for both threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

