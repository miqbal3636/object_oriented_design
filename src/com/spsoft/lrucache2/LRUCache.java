package com.spsoft.lrucache2;

import java.util.*;

// Leetcode 146. LRU Cache
// https://leetcode.com/problems/lru-cache/description/

// LRUCache class implementing LRU caching mechanism

public class LRUCache {
    class Node{
        int key;
        int value;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    Set<Node> cache;
    HashMap<Integer, Node> hm;
    int capacity;

    public LRUCache(int capacity) {
        cache = new LinkedHashSet<>();
        hm = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        int value = -1;
        if (hm.containsKey(key)) {
            Node node = hm.get(key);
            value = node.value;
            cache.remove(node); // remove node with O(1)
            cache.add(node); // add last
        }
        return value;
    }

    public void put(int key, int value) {
        if (!hm.containsKey(key)) {
            Node nodeToInsert = new Node(key,value);
            cache.add(nodeToInsert); //will be adding in the tail/rear or at the end of the list
            hm.put(key, nodeToInsert);
            if (cache.size() > capacity) {
                Node head =  cache.iterator().next(); //find the head of the linked Hashset which is also the front.
                cache.remove(head); //remove from front/head in the cache
                hm.remove(head.key); //remove the head from the hashmap as well
            }
        } else { //Update exiting value in cache
            Node nodeToUpdate = hm.get(key);
            cache.remove(nodeToUpdate); //remove the node that needs to be updated in O(1)
            nodeToUpdate.value = value; //Update the value of the node
            cache.add(nodeToUpdate); //add the note in the rear/end of the linked hash set
        }
    }

}