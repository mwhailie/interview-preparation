package edu.neu.companies.stripe;


import java.lang.reflect.Array;
import java.util.*;

public class MapWithTimestampII {

    Map<Integer, List<Pair>> map;

    public MapWithTimestampII(){
        map = new HashMap<>();
    }

    public void put(int key, int value){
        put(key, value, System.nanoTime());
    }
    public void put(int key, int value, long timestamp){
        map.compute(key, (k, v) -> v == null ? new ArrayList<>() : v).add(new Pair(timestamp, value));
    }

    public Integer get(int key, long timestamp){
        if(!map.containsKey(key)){
            return null;
        }
        List<Pair> valuesWithTimestamp = map.get(key);
        int indexBefore = greatestBefore(valuesWithTimestamp, timestamp + 1);
        if(indexBefore == -1){
            return null;
        }
        return valuesWithTimestamp.get(indexBefore).value;
    }

    private int greatestBefore(List<Pair> valuesWithTimestamp, long timestamp) {
        int left = 0, right = valuesWithTimestamp.size() - 1;
        if(valuesWithTimestamp.isEmpty() || valuesWithTimestamp.get(left).timestamp > timestamp){
            return -1;
        }
        if(valuesWithTimestamp.get(right).timestamp <= timestamp){
            return right;
        }
        while(left < right){
            int mid = left + (right - left)/2;
            if(valuesWithTimestamp.get(mid).timestamp >= timestamp) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left - 1;
    }



    public void remove(int key){
        map.remove(key);
    }

    private class Pair {
        Long timestamp;
        int value;

        public Pair(Long timestamp, int value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }
    public void print(){
        for(int key : map.keySet()){
            List<Pair> values = map.get(key);
            for(Pair p : values){
                System.out.println(key + ", " + p.value);
            }
        }
    }
    public static void main(String[] args) {
        MapWithTimestampII m = new MapWithTimestampII();
        for(int i = 0; i < 100; i ++){
            m.put(1, i);
        }
//        System.out.println(m.get(1,System.nanoTime() - 100));
//        System.out.println(m.get(1,System.nanoTime() - 1000000000));

//

        for(int i = 0; i < 100; i ++){
            m.put(2, i, i);
        }

        for(int i = 0; i < 100; i ++){
            System.out.println(m.get(2,i));
        }
        m.get(2,1);
        System.out.println(m.get(2,-1));
        System.out.println(m.get(2,0));
        System.out.println(m.get(2,200));
        System.out.println(m.map);
//        m.print();
    }
}
