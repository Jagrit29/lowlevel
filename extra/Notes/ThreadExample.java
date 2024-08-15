package extra.Notes;

public class ThreadExample {
    // I want to run multiple tasks at once;
    public static void main(String args[]) {
        // I want to say Jagrit1 and JAgrit2 both separately;
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<200;i++) {
                    System.out.println("JJ1");
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int k=0;k<200;k++) {
                    System.out.println("BB1");
                }
            }
        });

        // nof if this was just a forloop I would get all the JJ first and then BBS; because both are running;
        thread1.start();
        thread2.start(); // now lets's ee how it goes;

        // I get mismtach because both are being running
    }
}
