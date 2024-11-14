package com.spsoft.list;

public class Node<E> {
    public E key;
    Node<E> next;
    Node<E> prev;
    Node(Node<E> prev, E key,Node<E> next) {
        this.key = key;
        this.next = next;
        this.prev = prev;
    }
}
