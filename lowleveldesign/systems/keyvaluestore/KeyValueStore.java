package lowleveldesign.systems.keyvaluestore;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Class To Store Key and Value
class Entry<K, V>{
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class KeyValueStore<K, V> {
    private static int SIZE = 16;
    private List<List<Entry<K, V>>> buckets;
    private List<ReadWriteLock> bucketLocks;

    public KeyValueStore() {
        buckets = new ArrayList<>(SIZE);
        bucketLocks = new ArrayList<>(SIZE);

        for(int i=0;i<SIZE;i++) {
            buckets.add(new ArrayList<>());
            bucketLocks.add(new ReentrantReadWriteLock());
        }
    }

    // getBucketIndex from the given key using Hash Function
    private int getBucketIndex(K key) {
        return key.hashCode() % SIZE;
    }

    // puts new value into the key value store;
    public boolean put(K key, V value) {
        int index = getBucketIndex(key);
        ReadWriteLock bucketLock = bucketLocks.get(index);

        // lock the writeLock
        bucketLock.writeLock().lock();
        try {
            List<Entry<K, V>> bucket = buckets.get(index);
            for(Entry<K, V> entry : bucket) {
                if(entry.key.equals(key)) {
                    // it's already there, can use getter/setters here as well;
                    entry.value = value;
                    return true;
                }
            }

            bucket.add(new Entry<>(key, value));
            return true;
        } finally {
            bucketLock.writeLock().unlock();
        }
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        ReadWriteLock bucketLock = bucketLocks.get(index);

        // lock the readlock for this bucket
        bucketLock.readLock().lock();
        try {
            List<Entry<K, V>> bucket = buckets.get(index);

            for(Entry<K, V> entry: bucket) {
                if(entry.key.equals(key)) {
                    return entry.value;
                }
            }

            return null;
        } finally {
            bucketLock.readLock().unlock();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        KeyValueStore<String, Integer> kv = new KeyValueStore<>();

        // Thread to add elements;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    String key = "key " + i;
                    if(kv.put(key, i)) {
                        System.out.println("Succesffuly added key "+ i);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    String key = "key " + i;
                    System.out.println("Fetch value " + kv.get(key));
                }
            }
        });

        t2.start();
        t1.start();
        t1.join();
        t2.join();
    }

}
