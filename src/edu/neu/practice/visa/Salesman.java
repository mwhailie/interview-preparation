package edu.neu.practice.visa;

import java.util.*;

public class Salesman {
    public static int sale(int[] items, int afterMove) {
        int n = items.length;
        HashMap<Integer, Integer> frequecy = new HashMap<>();
        for(int item : items){
            frequecy.put(item, frequecy.getOrDefault(item, 0) + 1);
        }
        if(afterMove >= items.length){
            return frequecy.size();
        }
        int[] bucket = new int[items.length + 1];
        for(int key : frequecy.keySet()){
            bucket[frequecy.get(key)]++;
        }
        int topK = 0;
        for(int i = items.length ; i >= 0; i--){
            if(bucket[i] != 0){
                if(i >= afterMove){
                    return topK + 1;
                }
                if(i * bucket[i]>= afterMove){
                    return topK + afterMove/i + (afterMove%i == 0 ? 0 : 1);
                }
                topK += bucket[i];
                afterMove -= (bucket[i] * i);
            }
        }
        return topK;
    }

    public static void main(String[] args) {
        System.out.println(sale(new int[]{1,1,1,2,3,3}, 4));
        System.out.println(sale(new int[]{1,1,1,2,3,3}, 3));
        System.out.println(sale(new int[]{1,1,1,2,3,3}, 5));
    }

}
