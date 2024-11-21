package com.spsoft.list;

import java.util.*;

public class CloneableLinkedList<E> extends SerializableLinkedList<E> implements Cloneable {

    // shallow copy
    public Object cloneShallow() {
        LinkedList<E> clone = null;
        try {
            clone = (LinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    // deep copy
    public Object clone() {
        LinkedList<E> clone = null;
        try {
            clone = (LinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // Put clone into "virgin" state
        clone.first = clone.last = null;
        clone.size = 0;

        // Initialize clone with our elements (make a deep copy)
        // Using a HashMap to keep track of old node and new node
        Map<Node<E>, Node<E>> nodeMap = new HashMap<Node<E>, Node<E>>();

        //For all nodes int he list create new node with the item and add to hashmap
        Node<E> currentNode = first;
        while (currentNode != null) {
            Node<E> newNode = new Node<>(null, cloneItem(currentNode.item), null);
            nodeMap.put(currentNode, newNode);
            currentNode = currentNode.next;
        }

        //For all nodes update prev and next by getting the corresponding new node from hashmap
        currentNode = first;
        while (currentNode != null) {
            Node<E> newNode = nodeMap.get(currentNode);
            newNode.prev = nodeMap.get(currentNode.prev);
            newNode.next = nodeMap.get(currentNode.next);
            clone.addLast(newNode);
            currentNode = currentNode.next;
        }

        return clone;
    }

    private E cloneItem(E e){
        //to be implemented
        return e;
    }
}
