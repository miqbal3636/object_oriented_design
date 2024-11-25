package com.spsoft.lrucache;

public class DoublyLinkList implements MyDeque {
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
            first.prev = null; //make the prev node of current first (f.next) to null
        size--;
        return f;
    }

    @Override
    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if(prev != null)
            prev.next = node.next;
        if(next != null)
            next.prev = prev;
        if(node == last)
            last = node.prev;
        if(node == first)
            first = node.next;
        size--;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return this.size;
    }
}
