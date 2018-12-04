package edu.neu.datastructure;

import java.util.*;

/*
    Refer to https://gist.github.com/joelittlejohn/5565410
 */
public class CacheWithExpiration{

    class ListNode{
        int key;
        int val;
        long time;
        long ttl;
        ListNode next;
        ListNode prev;
        public ListNode(int key, int val, long ttl) {
            this.key = key;
            this.val = val;
            this.ttl = ttl;
            this.time = System.nanoTime();
        }
        public boolean isExpired(){
            return time + ttl < System.nanoTime();
        }
    }
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;
    int capacity;
    long DEFAULT_TIME_TO_LIVE = 20;
    public CacheWithExpiration(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(0, 0, DEFAULT_TIME_TO_LIVE);
        tail = new ListNode(0, 0, DEFAULT_TIME_TO_LIVE);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode node = map.get(key);
        removeFromList(node);
        if(node.isExpired()){
            map.remove(key);
            return -1;
        }
        insertToHead(node);
        map.put(key, node);
        return node.val;
    }

    public void put(int key, int value, int ttl) {
        ListNode node = null;
        if(map.containsKey(key)){
            node = map.get(key);
            removeFromList(node);
            node.val = value;
        }else{
            node = new ListNode(key,value, ttl);
        }
        insertToHead(node);
        map.put(key, node);
        if(map.size() > capacity){
            map.remove(tail.prev.key);
            removeFromList(tail.prev);
        }
    }

    public void insertToHead(ListNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void removeFromList(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void clearExpired() {
        for (int k : map.keySet()) {
            this.get(k);
        }
    }

}
