package com.spsoft.list;
/* FIFO Queue */
/* 1 2 3 4 5 */
public interface Deque<E> {
    public Node<E> addLast(E e);
    public Node<E> addLast(Node<E> node);
    public E removeFirst();
    public void remove(Node<E> node);
    public boolean offer(E e);
    public E poll();
    public E peek();
    public int size();
    public boolean isEmpty();
}
