package lowleveldesign.designpatterns.creational;

// Singleton comes into picture when we only want to have 1 instance of a class. We will not be able to create objects of this class and things needed will be
// decalred static meaning avl without object;

// This is the simple initialition;
class Singleton1 {
    private static Singleton1 instance; // this is the instance of this Singleton1 class;

    // Even our constructor is made private;
    private  Singleton1() {

    }

    public static Singleton1 getInstance() {
        // now this will give instance to whoever needs;
        if(instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}

// This is eager implementation;
class Singleton2 {
    private static Singleton2 instance = new Singleton2();

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return instance;
    }
}

// Okayish Threadsafe
class Singleton3 {
    private static Singleton3 instance;
    private Singleton3() {};

    public static synchronized Singleton3 getInstance() {
        if(instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}

// Best threadsafe to only lock if it was null;
class Singleton4 {
    private static Singleton4 instance;
    private Singleton4() {};

    public static Singleton4 getInstance() {
        if(instance == null) {
            // now lock it;
            synchronized (Singleton4.class) {
                instance = new Singleton4();
            }
        }

        return instance;
    }
}

public class Singleton {
    public static void main(String[] args) {
        Singleton4 ins = Singleton4.getInstance();
    }
}
