package edu.neu.companies.twilio;

import edu.princeton.cs.algorithms.SequentialSearchST;

import java.util.LinkedList;

public class HashMap<K,V> {


    class Node<K,V>{
        K key;
        V val;
        Node<K,V> next;
        public Node(K key, V val, Node<K,V> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    Node<K,V>[] table;
    int CAPACITY;
    int size;

    public HashMap() {
        this(4);
    }

    public HashMap(int CAPACITY) {
        this.CAPACITY = CAPACITY;
        this.size = 0;
        this.table = new Node[CAPACITY];
    }

    public void put(K key, V val){

        if (this.size >= 10 * this.CAPACITY) {
            this.resize(2 * this.CAPACITY);
        }

        int i = this.hash(key);
        Node<K, V> head = table[i];
        // Check if key is already present
        while (head != null){
            if (head.key.equals(key)){
                head.val = val;
                return;
            }
            head = head.next;
        }
        // ! contains key
        size++;
        head = table[i];
        Node<K, V> newNode = new Node<>(key, val, head);
        table[i] = newNode;

    }

    private void resize(int chains) {
        HashMap<K, V> temp = new HashMap(chains);

        for(int i = 0; i < this.CAPACITY; ++i) {
            Node<K,V> head = table[i];

            while(head != null) {
                K key = head.key;
                temp.put(key, get(key));
            }
        }

        this.CAPACITY = temp.CAPACITY;
        this.size = temp.size;
        this.table = temp.table;
    }

    private V delete(K key) {
        int i = this.hash(key);
        Node<K, V> head = table[i];
        Node<K, V> prev = null;

        while (head != null){
            // If Key found
            if (head.key.equals(key))
                break;
            prev = head;
            head = head.next;
        }

        // If key was not there
        if (head == null)
            return null;

        // Reduce size
        size--;

        if (prev != null) {
            prev.next = head.next;
        }else {
            table[i] = head.next;
        }
        return head.val;
    }

    public V get(K key){
        int i = this.hash(key);
        Node<K, V> head = table[i];

        while(head != null) {
            if (key.equals(head.key)) {
                return head.val;
            }
            head = head.next;
        }
        return null;
    }

    public boolean contains(K key){
        return get(key) == null;
    }

    public int hash(K key){
        return key.hashCode() % CAPACITY;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public static void main(String[] args) {
        HashMap<String, Integer>map = new HashMap<>();
        map.put("this",1 );
        map.put("coder",2 );
        map.put("this",4 );
        map.put("hi",5 );
        System.out.println(map.size());
        System.out.println(map.get("this"));
        System.out.println(map.get("coder"));
        System.out.println(map.get("hi"));

        System.out.println(map.delete("this"));
        System.out.println(map.delete("this"));
        System.out.println(map.get("this"));
        System.out.println(map.size());
        System.out.println(map.isEmpty());

    }

}
