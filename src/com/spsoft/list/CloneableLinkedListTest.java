package com.spsoft.list;

public class CloneableLinkedListTest {
    public static void main(String args[]) {
        // Creating an empty LinkedList
        CloneableLinkedList<String> list = new CloneableLinkedList<>();
        // Use add() method to add elements in the list
        list.addLast("First");
        list.addLast("Second");
        list.addLast("Third");

        // Displaying the list
        System.out.println("Original List elements:");
        for(Object s : list){
            System.out.print((String)s+" ");
        }
        System.out.println();

        // Creating another linked list and copying
        CloneableLinkedList<String> clonedList = new CloneableLinkedList<>();
        clonedList = (CloneableLinkedList<String>) list.clone();

        // Displaying the cloned linked list
        System.out.println("Cloned List:");
        for(Object s : clonedList){
            System.out.print((String)s+" ");
        }
        System.out.println();

        System.out.println("Changing First Node in Original LinkedList to: 'Changed'");

        Node<String> firstNode = list.first;
        firstNode.item = "Changed";

        System.out.println("Original LinkedList elements after first node modified :");
        for(Object s : list){
            System.out.print((String)s+" ");
        }
        System.out.println();

        System.out.println("Cloned LinkedList elements:");
        for(Object s : clonedList){
            System.out.print((String)s+" ");
        }
    }

}
