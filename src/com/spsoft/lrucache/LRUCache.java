package com.spsoft.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // Hash map to store key-node pairs for O(1) access
    private final Map<Integer, Node> cacheMap;
    // Maximum capacity of the cache
    private final int capacity;
    private MyDeque myDeque;

    // Constructor to initialize the LRU cache
    public LRUCache(int capacity) {
        this(capacity, new DoublyLinkList());
    }

    public LRUCache(int capacity, MyDeque myDeque) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.myDeque = myDeque;
    }

    // Retrieves the value of the node associated with the given key
    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        Node node = cacheMap.get(key);
        // Move the accessed node to the head to maintain LRU order
        moveToLast(node);

        return node.value;
    }

    // Adds a new key-value pair to the cache or updates the value if it already exists
    public void put(int key, int value) {
        if (cacheMap.containsKey(key)) {
            Node node = cacheMap.get(key);
            node.value = value;
            // Move the updated node to the head
            moveToLast(node);
        } else {
            Node newNode = new Node(key, value);
            cacheMap.put(key, newNode);
            // Add the new node to the head of the linked list
            myDeque.addLast(newNode);

            if (myDeque.size() > capacity) {
                // Remove the least recently used node
                Node tail = myDeque.removeFirst();
                cacheMap.remove(tail.key); // Also remove it from the map
            }
        }
    }

    private void moveToLast(Node node){
        myDeque.remove(node);
        myDeque.addLast(node);
    }
}
