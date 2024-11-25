package com.spsoft.lrucache1;

import com.spsoft.lrucache.DoublyLinkList;
import com.spsoft.lrucache.LRUCache;

public class LRUCacheTest {
    public static void main(String args[]){
        LRUCacheTest lruCacheTest = new LRUCacheTest();
        lruCacheTest.test();
    }
    private void test(){
        int capacity =2;
        com.spsoft.lrucache.LRUCache lRUCache = new LRUCache(capacity, new DoublyLinkList());
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println("get(1): "+lRUCache.get(1)); // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println("get(2): "+lRUCache.get(2));   // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println("get(1): "+lRUCache.get(1));    // return -1 (not found)
        System.out.println("get(3): "+lRUCache.get(3));    // return 3
        System.out.println("get(4): "+lRUCache.get(4));    // return 4
    }
}
