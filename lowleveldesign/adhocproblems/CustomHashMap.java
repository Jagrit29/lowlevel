package lowleveldesign.adhocproblems;

// Entry class to hold key-value pairs
class Entry {
    String key;
    String value;
    Entry next;  // For handling collisions (chaining)

    Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

public class CustomHashMap {
    private static final int SIZE = 10;  // The size of the hash table (array)
    private Entry[] table;               // Array to store entries (key-value pairs)

    // Constructor to initialize the table
    public CustomHashMap() {
        table = new Entry[SIZE];
    }

    // Hash function to generate an index for a given key
    private int hash(String key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    // Put method to insert a key-value pair into the HashMap
    public void put(String key, String value) {
        int index = hash(key);
        Entry newEntry = new Entry(key, value);

        // If no entry exists at this index, insert the new entry
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            // Handle collisions using chaining (linked list)
            Entry current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update value if key already exists
                    return;
                }
                if (current.next == null) break;
                current = current.next;
            }
            current.next = newEntry; // Add new entry at the end of the list
        }
    }

    // Get method to retrieve a value for a given key
    public String get(String key) {
        int index = hash(key);
        Entry current = table[index];

        // Traverse the linked list at the index to find the key
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;  // Return the value if key is found
            }
            current = current.next;
        }
        return null;  // Return null if key is not found
    }

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        map.put("apple", "fruit");
        map.put("carrot", "vegetable");
        map.put("dog", "animal");

        System.out.println(map.get("apple"));   // Output: fruit
        System.out.println(map.get("carrot"));  // Output: vegetable
        System.out.println(map.get("dog"));     // Output: animal
        System.out.println(map.get("cat"));     // Output: null (key not found)
    }
}
