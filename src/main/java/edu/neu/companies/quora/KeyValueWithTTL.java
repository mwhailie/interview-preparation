package edu.neu.companies.quora;


import java.util.HashMap;
import java.util.Map;

public class KeyValueWithTTL {

    class Node{
        int value;
        int ttl;
        long timestamp;

        public Node(int value, int ttl) {
            this.value = value;
            this.ttl = ttl;
            this.timestamp = System.currentTimeMillis();
        }

        boolean isExpired(){
            return timestamp + ttl > System.currentTimeMillis();
        }
    }
    Map<Integer, Node> map;

    public KeyValueWithTTL() {
        map = new HashMap<>();
    }

    void put(int key, int val, int ttl){
        map.put(key, new Node(val, ttl));
    }

    int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        if(node.isExpired()){
            map.remove(key);
            return -1;
        }
        return node.value;
    }

    void remove(int key){
        if(!map.containsKey(key)){
            return;
        }
        Node node = map.get(key);
        map.remove(key);
    }

    private void cleanUp(){
        for(int key : map.keySet()){
            get(key);
        }
    }

}
