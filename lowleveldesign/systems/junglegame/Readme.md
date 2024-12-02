
LRU Cache System (Least Recently Used) 
Requirements 
1. LRU will have a size 
2. User can add a new key value pair
3. User can get a key value pair 
4. User can update a value for the key 
5. If the size is full, remove the least recently 


Entities & Design
1 LRU Class 

Design / Data Strucures 
1. List + Hash Function + 
2. HashMap + LinkedList
3. HashMap + DoubledLinkedList

1. put(key, value) - O(1) 
2. get(key) - O(1)
3. delete(key) - O(1)
4. print() - LRU cache (O(N)) (MRU -> LRU)



// 4 In Memory Cache System (data)

// 4 cache servers
// Key = 10, 4 servers

// 10 % 4 = 2

// Removal (Key:ServerInstance, Score) - SortedSet(Score) O(log(N))
// 13, 4
// 11, 3
// 10, 2 

// 4 clients -> Key = 10, (LB%4) - Consistent Hashing