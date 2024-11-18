package com.spsoft.list;

public class Node<E> {
    public E item;
    Node<E> next;
    Node<E> prev;
    Node(Node<E> prev, E item,Node<E> next) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }
}
