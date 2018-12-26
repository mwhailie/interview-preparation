package edu.neu.companies.stripe;

//import java.security.Key;

import java.util.*;

public class MapWithTimestamp {

    private Map<Integer, TreeMap<Long, Integer>> map;

    public MapWithTimestamp(){
        map = new HashMap<>();
    }
    //O(logN)
    public void put(int key, int value){
        put(key, value, System.nanoTime());
    }

    public void put(int key, int value, long timestamp){
        map.compute(key, (k, v) -> v == null ? new TreeMap<>() : v).put(timestamp, value);
    }
    //O(logN)
    public Integer get(int key, long timestamp){
        if(!map.containsKey(key)){
            return null;
        }
        TreeMap<Long, Integer> valuesWithTimestamp = map.get(key);
        Long keyBefore = valuesWithTimestamp.floorKey(timestamp);
        if(keyBefore == null){
            return null;
        }
        return valuesWithTimestamp.get(keyBefore);
    }

    public void remove(int key){
        map.remove(key);
    }

    public void print(){
        for(int key : map.keySet()){
            TreeMap<Long, Integer> values = map.get(key);
            for(long t : values.keySet()){
                System.out.println(key + ", " + values.get(t));
            }
        }
    }

    public static void main(String[] args) {
        MapWithTimestamp m = new MapWithTimestamp();
        for(int i = 0; i < 100; i ++){
            m.put(1, i);
        }
        System.out.println(m.get(1,System.nanoTime() - 100));
        System.out.println(m.get(1,System.nanoTime() - 1000000));

        System.out.println(m.map.toString());
        m.print();
    }
}
