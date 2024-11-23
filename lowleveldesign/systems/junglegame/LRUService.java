package lowleveldesign.systems.junglegame;

import java.util.HashMap;

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

interface Cache {
    void put();
    int get();
}

interface Log {

}

// UnHill Architecture
//

class LRUCacheStrategy implements Cache {

    public void put() {
        // do something
        // log.util("logging");
    }

    public int get() {
        // do something;
        return 0;
    }
}

// (0,0) ->(1,1) -> (2,10) -> (0,0)
class LRUCache {
    int size;
    Node head;
    Node tail;
    public HashMap<Integer, Node> cache;

    public LRUCache(int size) {
        this.size = size;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        cache = new HashMap<>();
    }

    private void addNode(Node node) {
        Node save = head.next;
        node.prev = head;
        head.next = node;
        node.next = save;
        save.prev = node; // root cause
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            // update existing key;
            Node node = cache.get(key);
            removeNode(node);
            Node newNode = new Node(key, value);
            addNode(newNode);
            cache.remove(key);
            cache.put(key, newNode);

        } else {
            if(cache.size() == size) {
                // cache is full
                Node node = tail.prev;
                removeNode(tail.prev);
                cache.remove(node.key);

                Node newNode = new Node(key, value);
                addNode(newNode);
                cache.put(key, newNode);

            } else {
                Node node = new Node(key, value);
                addNode(node);
                cache.put(key, node);
            }
        }
    }

    public void get(int key) {
        if(!cache.containsKey(key))  {
            System.out.println("Not present");
            return;
        }

        Node node = cache.get(key);
        removeNode(node);
        Node newNode = new Node(node.key, node.value);
        addNode(newNode);
        cache.remove(key);
        cache.put(key, newNode);
    }

    public void print() {
        Node curr = head.next;

        while(curr!=null && curr!=tail) {
            System.out.print(curr.key+" "+curr.value +"::::");
            curr = curr.next;
        }

        System.out.println("");
    }
}



public class LRUService {
    public static void main(String args[]) {
        LRUCache cacheSystem = new LRUCache(5);
        cacheSystem.put(1, 10);
        cacheSystem.put(2, 20);
        cacheSystem.put(3, 30);
        cacheSystem.put(4, 40);
        cacheSystem.put(5, 50);

        cacheSystem.print();

        cacheSystem.put(1, 15);

        cacheSystem.print();

        cacheSystem.put(6, 60);
        cacheSystem.print();

        cacheSystem.get(4);

        cacheSystem.print();
    }
}
