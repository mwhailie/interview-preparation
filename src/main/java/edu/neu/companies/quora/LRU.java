package edu.neu.companies.quora;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    class ListNode{
        int key;
        int value;
        ListNode next;
        ListNode prev;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    ListNode head;
    ListNode tail;
    Map<Integer, ListNode> map;

    public LRU() {
        map = new HashMap<>();
        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    void put(int key, int val, int ttl){
        ListNode newNode = null;
        if(map.containsKey(key)){
            newNode = map.get(key);
            newNode.value = val;
            removeFromList(newNode);
        }else{
            newNode = new ListNode(key, val);
        }
        map.put(key,newNode);
        addToHead(newNode);
    }

    int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode node = map.get(key);
        removeFromList(node);

        addToHead(node);
        return node.value;
    }

    void remove(int key){
        if(!map.containsKey(key)){
            return;
        }
        ListNode node = map.get(key);
        removeFromList(node);
        map.remove(key);

    }


    private void addToHead(ListNode newNode) {
        newNode.next = head.next;
        newNode.prev =head;
        newNode.next.prev = newNode;
        head.next = newNode;
    }

    private void removeFromList(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
}
