package com.spsoft.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableLinkedList<E> extends LinkedList implements Iterable<E>, Iterator<E>{
    private Node<E> lastReturned;
    private Node<E> next;
    private int nextIndex;

    public IterableLinkedList(){
        this.nextIndex= 0;
        this.next = first;
    }

    public IterableLinkedList(int nextIndex, Node next, int size){
        this.nextIndex= nextIndex;
        this.next = next;
        this.size = size;
    }

    @Override
    public Iterator<E> iterator() {
        final Node<E> f = first;
        return new IterableLinkedList<>(nextIndex,f,super.size);
    }

    @Override
    public boolean hasNext() {
        return nextIndex < size;
    }

    @Override
    public E next() {
        if (!hasNext())
            throw new NoSuchElementException();
        lastReturned = next;
        next = next.next;
        nextIndex++;
        return lastReturned.item;
    }


}
