package edu.neu.practice.linkedin;

import java.util.*;

public class MinUniqueArraySum {
    public static int getMinimumUniqueSum(int[] arr){
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : arr){
            while(freq.get(num) != null){
                num++;
            }
            freq.put(num, num);
            res += num;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getMinimumUniqueSum(new int[]{1,1,1,1,1,1}));
    }
}
