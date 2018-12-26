package edu.neu.companies.twilio;

import java.util.*;

public class DistinctPair {
    int numberOfPair(int[] a, int k){
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for(int num : a){
            if(set.contains(num)){
                continue;
            }
            if(set.contains(k - num)){
                count++;
            }
            set.add(num);
        }
        return count;
    }

    public static void main(String[] args) {
        DistinctPair d = new DistinctPair();
        System.out.println(d.numberOfPair(new int[]{1,2,3,6,7,8,9,1}, 10));
    }
}
