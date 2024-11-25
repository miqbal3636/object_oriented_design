package com.spsoft.lrucache1;

// Leetcode 146. LRU Cache
// https://leetcode.com/problems/lru-cache/description/

import java.util.*;

public class LRUCache {

    // Hash map to store key-node pairs for O(1) access
    private final Map<Integer, Node> cacheMap;
    // Maximum capacity of the cache
    private final int capacity;
    private MyDeque deque;

    // Constructor to initialize the LRU cache
    public LRUCache(int capacity) {
        this(capacity, new DoublyLinkList());
    }

    public LRUCache(int capacity, MyDeque deque) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.deque = deque;
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

    // Adds a new key-value pair to the cache or updates the value if it already
    // exists
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
            deque.addLast(newNode);

            if (deque.size() > capacity) {
                // Remove the least recently used node
                Node tail = deque.removeFirst();
                cacheMap.remove(tail.key); // Also remove it from the map
            }
        }
    }

    private void moveToLast(Node node){
        deque.remove(node);
        deque.addLast(node);
    }
}

/* FIFO Double Ended Queue */
interface MyDeque {
    public Node addLast(Node node);

    public Node removeFirst();

    public void remove(Node node);

    public boolean isEmpty();

    public int size();
}

class Node {
    public int key;
    public int value;
    Node next;
    Node prev;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class DoublyLinkList implements MyDeque {
    private int size = 0;
    Node first;
    Node last;

    @Override
    public Node addLast(Node node) {
        final Node l = last;
        //next node of last node should be null
        node.next = null;
        last = node;
        if (l == null)
            first = node;
        else
        {
            l.next = node;
            node.prev = l;
        }

        size++;
        return node;
    }

    @Override
    public Node removeFirst() {
        final Node f = first;
        first = f.next;
        if (first == null)
            last = null;
        else
            first.prev = null;
        size--;
        return f;
    }

    @Override
    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null)
            prev.next = node.next;
        if (next != null)
            next.prev = prev;
        if (node == last)
            last = node.prev;
        if (node == first)
            first = node.next;
        size--;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }
}