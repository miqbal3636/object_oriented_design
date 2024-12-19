package com.spsoft.hashmap;

public class MyHashMap {
    private class Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int SIZE = 10001;
    private Node[] bucket;

    public MyHashMap() {
        bucket = new Node[SIZE];
    }

    /**
     * Adds a key-value pair to the HashMap or updates the value if the key already exists.
     */
    public void put(int key, int value) {
        int index = hash(key);
        Node node = bucket[index];
        if (node == null) {
            bucket[index] = new Node(key, value);
            return;
        }
        Node prev = null;
        while (node != null) {
            if (node.key == key) {
                node.value = value;
                return;
            }
            prev = node;
            node = node.next;
        }
        prev.next = new Node(key, value);
    }

    /**
     * Retrieves the value associated with the given key.
     * @return the value if found, or -1 if the key does not exist.
     */
    public int get(int key) {
        int index = hash(key);
        Node node = bucket[index];
        while (node != null) {
            if (node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        return -1;
    }

    /**
     * Removes the key-value pair from the HashMap.
     */
    public void remove(int key) {
        int index = hash(key);
        Node node = bucket[index];
        Node prev = null;
        while (node != null) {
            if (node.key == key) {
                if (prev != null) {
                    prev.next = node.next;
                } else {
                    bucket[index] = node.next;
                }
                return;
            }
            prev = node;
            node = node.next;
        }
    }

    /**
     * Computes the hash index for a given key.
     * @param key the key to be hashed.
     * @return the computed hash index.
     */
    private int hash(int key) {
        return Integer.hashCode(key) % SIZE;
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // The map is now [[1,1]]
        myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        System.out.println(myHashMap.get(1)); // return 1
        System.out.println(myHashMap.get(3)); // return -1 (not found)
        myHashMap.put(2, 1); // update the existing value
        System.out.println(myHashMap.get(2)); // return 1
        myHashMap.remove(2); // remove the mapping for 2
        System.out.println(myHashMap.get(2)); // return -1 (not found)
    }
}