package edu.neu.companies.twilio;

import java.util.Map;
import java.util.HashMap;

public class LRUCache<K,V> {

    class Node<K,V>{
        K key;
        V val;
        Node next;
        Node prev;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Map<K, Node> map;
    private Node head;
    private Node tail;
    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public void put(K key, V val){
        Node<K,V> node = null;
        if(map.containsKey(key)){
            node = map.get(key);
            node.val = val;
            removeNodeFromList(node);
        }else{
            node = new Node(key, val);
        }
        addNodeToHead(node);
        map.put(key, node);
        if(map.size() > capacity){
            map.remove(tail.prev.key);
            removeNodeFromList(tail.prev);
        }
    }

    public V get(K key){
        if(!map.containsKey(key)){
            return null;
        }
        Node<K,V> node = map.get(key);
        removeNodeFromList(node);
        addNodeToHead(node);
        return node.val;
    }

    private void removeNodeFromList(Node<K, V> node) {
        Node<K, V> prev = node.prev;
        Node<K, V> next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void addNodeToHead(Node<K, V> node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }


}
