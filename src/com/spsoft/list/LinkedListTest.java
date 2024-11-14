package com.spsoft.list;

public class LinkedListTest {
    private static LinkedList<Character> linkedList = new LinkedList<Character>();

    public static void main(String args[]){
        linkedList.addLast('a');
        Node<Character> nodeB = linkedList.addLast('b');
        System.out.println("size of linked list: "+linkedList.size());
        linkedList.removeFirst();
        System.out.println("size of linked list: "+linkedList.size());
        linkedList.remove(nodeB);
        System.out.println("size of linked list: "+linkedList.size());
        linkedList.addLast('c');
        System.out.println("size of linked list: "+linkedList.size());

    }
}
