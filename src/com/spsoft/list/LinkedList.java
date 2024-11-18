package com.spsoft.list;

import java.util.NoSuchElementException;

public class LinkedList<E> implements Deque<E> {
    int size = 0;
    Node<E> first;
    Node<E> last;

    @Override
    public Node<E> addLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        System.out.println("added Node valued: "+e+" at the end of list");
        return newNode;
    }


    @Override
    public Node<E> addLast(Node<E> node) {
        final Node<E> l = last;
        last = node;
        if (l == null)
            first = node;
        else
            l.next = node;
        size++;
        System.out.println("added Node valued: "+node.item +" at the end of list");
        return node;
    }

    public E removeFirst(){
        final Node<E> f = first;
        if(f == null)
            throw new NoSuchElementException();
        return removeFirst(f);
    }

    @Override
    public void remove(Node<E> node) {
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if(prev != null)
            prev.next = node.next;
        if(next != null)
            next.prev = prev;
        if(node == last)
            last = node.prev;
        if(node == first)
            first = node.next;
        size--;
        System.out.println("removed node valued: "+node.item);

    }

    @Override
    public boolean offer(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : removeFirst(f);
    }

    @Override
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    //needs to implement
    public Node<E> addFirst(E e) {
        return null;
    }

    @Override
    //needs to implement
    public E removeLast() {
        return null;
    }

    private E removeFirst(Node<E> f) {
        assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        System.out.println("removed node valued: "+f.item +" from the start of the list");
        return element;
    }
}
