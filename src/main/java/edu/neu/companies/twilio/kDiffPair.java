package edu.neu.companies.twilio;

import java.util.*;
import java.util.HashMap;

public class kDiffPair {
    //return index/number/ count?
    //duplicate number?
    List<List<Integer>> twoMinus(int[] nums, int k){
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 1; i ++){
            for(int j = i + 1; j < nums.length; j ++){
                if(Math.abs(nums[i] - nums[j]) == k){
                    res.add(Arrays.asList(nums[i], nums[j]));
                }
            }
        }
        return res;
    }
    List<List<Integer>> twoMinusII(int[] nums, int k){
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++){
            if(map.containsKey(nums[i] - k)  ){
                res.add(Arrays.asList(nums[i], nums[i] - k));
            }else if(map.containsKey(nums[i] + k)){
                res.add(Arrays.asList(nums[i], nums[i] + k));
            }
            map.put(nums[i], i);
        }
        return res;
    }
    //Sort + Binary Search O(NlogN)
    List<List<Integer>> twoMinusIII(int[] nums, int k){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 1; i ++){
            int index = binarySearch(nums, i, nums.length - 1, nums[i] + k);
            if(index > 0){
                res.add(Arrays.asList(nums[i], nums[index]));
            }
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }
    private int binarySearch(int[] nums, int lo, int hi, int target) {
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] < target){
                lo = mid + 1;
            }else if(nums[mid] > target){
                hi = mid - 1;
            }else{
                return mid;
            }
        }
        return -(lo + 1);
    }

    public static void main(String[] args) {
        kDiffPair t = new kDiffPair();
        System.out.println(t.twoMinus(new int[]{1, 2, 3}, 1));
        System.out.println(t.twoMinusII(new int[]{1, 2, 3}, 1));
        System.out.println(t.twoMinusIII(new int[]{1, 2, 3}, 1));
        System.out.println(t.binarySearch(new int[]{1, 2, 3}, 1));
        System.out.println(t.binarySearch(new int[]{1, 2, 4}, 2));
        System.out.println(t.binarySearch(new int[]{1, 2, 4}, 3));
        System.out.println(t.binarySearch(new int[]{1, 2, 4}, 4));
    }
}
