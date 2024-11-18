package com.spsoft.list;

import java.util.Iterator;

public class IterableLinkedListTest {
    static IterableLinkedList<Integer> list = new IterableLinkedList<Integer>();
    public static void printList(){
        System.out.println("Printing the updated list...");
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
    public static void printListUsingIterator(){
        System.out.println("Printing the updated list...");
        Iterator<Integer> iter = list.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next()+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        list.addLast(1);
        Node<Integer> node =  list.addLast(2);
        Node node3 =list.addLast(3);
        list.removeFirst();
        printList();
        list.addLast(4);
        printList();
        list.remove(node);
        printList();
        Node node5 = list.addLast(5);
        printList();
        list.remove(node5);
        printList();
        list.remove(node3);
        printList();
        list.removeFirst();
        printListUsingIterator();
        list.addLast(6);
        printList();

    }


}
