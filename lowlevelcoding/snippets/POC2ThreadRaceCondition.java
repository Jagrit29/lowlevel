package lowlevelcoding.snippets;

public class POC2ThreadRaceCondition {
    public static void main(String args[]) throws InterruptedException {
        // To run multiple threads on bankaccount, we first need the same particular process for it
        // now process is nothing but instatiation of that;
        BankAccount account = new BankAccount();

        // now on this bankaccount let's run two threads which can be two different users withdrawing at the similar time;

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // now here t1 will try to remove the money
                for(int i=0;i<2;i++) {
                    account.withdraw(700);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // now here t1 will try to remove the money
                for(int i=0;i<2;i++) {
                    account.withdraw(700);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(account.getBalance());
    }
}

// Now let's assume this is my program and I want to run multiple threads on this particular program;
class BankAccount {
    private int balance = 1000; // shared resource;

    public void withdraw(int amount) {
        if(balance >= amount) {
            System.out.println(Thread.currentThread().getName() +" is about to withdraw " +amount);
            balance -= amount;
            System.out.println(Thread.currentThread().getName() +" Remaining balance " +balance);
        } else {
            System.out.println(Thread.currentThread().getName() +" Balance is less ");
        }
    }

    public int getBalance() {
        return balance;
    }
}