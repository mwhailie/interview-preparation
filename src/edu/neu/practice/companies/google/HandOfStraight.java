package edu.neu.practice.companies.google;

import java.util.*;

public class HandOfStraight {
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num : hand){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        while(!map.isEmpty()){
            int first = map.firstKey();
            for(int i = 0; i < W; i ++){
                int next = i + first;
                if(!map.containsKey(next)){
                    return false;
                }
                if(map.get(next) == 1){
                    map.remove(next);
                }else{
                    map.put(next, map.get(next) - 1);
                }
            }
        }
        return true;
    }
}
