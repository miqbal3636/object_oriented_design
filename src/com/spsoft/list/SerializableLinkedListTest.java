package com.spsoft.list;

import java.io.*;

public class SerializableLinkedListTest {
    public static void main(String args[]) throws FileNotFoundException {
        SerializableLinkedList<String> linkedList = new SerializableLinkedList<>();
        linkedList.addLast("Apple");
        linkedList.addLast("Banana");
        linkedList.addLast("Mango");
        //Write linkedList object to file
        try (var out = new ObjectOutputStream(new FileOutputStream("liknkedlist.ser"))){
            out.writeObject(linkedList);
            System.out.println("Serialization was done successfully");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (var out = new ObjectInputStream(new FileInputStream("liknkedlist.ser"))){
            out.readObject();
            System.out.println("Deserialization was done successfully");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
