package com.spsoft.lrucache;

public class Node {
    public int key;
    public int value;
    Node next;
    Node prev;
    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
