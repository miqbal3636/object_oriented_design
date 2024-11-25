package com.spsoft.lrucache;

/* FIFO Double Ended Queue */
public interface MyDeque {
    public Node addLast(Node node);
    public Node removeFirst();
    public void remove(Node node);
    public boolean isEmpty();
    public int size();
}