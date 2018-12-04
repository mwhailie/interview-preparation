package edu.neu.companies.google;

import java.util.HashMap;
import java.util.Map;

public class GoogleFruit {
    public int pickFruit(int[] trees){
        if(trees == null || trees.length == 0){
            return 0;
        }
        if(trees.length <= 0){
            return trees.length;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 0, max = 0;
        while(right < trees.length){
            if(map.size() <= 2){
                int fruit = trees[right];
                map.put(fruit, right);
                right ++;
            }
            if(map.size() > 2){
                int leftMost = trees.length;
                for(int index : map.values()){
                    leftMost = Math.min(leftMost, index);
                }
                map.remove(trees[leftMost]);
                left = leftMost + 1;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    public static void main(String[] args){
        GoogleFruit googleFruit = new GoogleFruit();
        int[] trees = new int[]{1,2,1,3,4,3,5,1,2};
        System.out.println(googleFruit.pickFruit(trees));
        trees = new int[]{1,2,1,2,1,2,1};
        System.out.println(googleFruit.pickFruit(trees));

    }
}
