package lowleveldesign.designpatterns;

class LazySingleton {
    public static LazySingleton instance;
    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

class ThreadSafeSingleton {
    public static ThreadSafeSingleton instance;
    private ThreadSafeSingleton() {};

    public static synchronized ThreadSafeSingleton getInstance() {
        if(instance == null) {
            instance = new ThreadSafeSingleton();
        }

        return instance;
    }

}
public class Singleton {
    public static void main(String args[]) {
        ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
    }
}
