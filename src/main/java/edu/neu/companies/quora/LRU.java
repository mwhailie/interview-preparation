package edu.neu.companies.quora;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    class ListNode{
        int key;
        int val;
        ListNode prev;
        ListNode next;
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    HashMap<Integer, ListNode> map ;
    //    int size;
    int capacity;
    ListNode head;
    ListNode tail;

    public LRU(int capacity) {
        this.capacity = capacity;
//        size = 0;
        map = new HashMap<>();
        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }

    public void removeFromList(ListNode node){
        node.prev.next = node.prev;
        node.next.prev = node.next;
    }

    public void insertToHead(ListNode node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    public int get(int key) {
        ListNode node = map.get(key);
        if(node == null){
            return -1;
        }else{
            removeFromList(node);
            insertToHead(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        ListNode node = map.get(key);
        if(node == null){
            node = new ListNode(key, value);
            map.put(key, node);
        }else{
            node.val = value;
            removeFromList(node);
        }
        insertToHead(node);
        if(map.size() > capacity){
            map.remove(tail.prev.key);
            removeFromList(tail.prev);
        }
    }
}
