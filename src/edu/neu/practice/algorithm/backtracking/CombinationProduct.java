package edu.neu.practice.algorithm.backtracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationProduct {

    public static List<List<Integer>> combinationProduct(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtracking(nums, res, new ArrayList<>(), 1, 0);
        for(List<Integer> temp : res){
            System.out.println(temp);
        }
        return res;
    }

    private static void backtracking(int[] nums,List<List<Integer>> res, List<Integer> temp, int product, int start) {
        if(start == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        backtracking(nums, res,temp, product * nums[start],start + 1 );
        temp.add(nums[start]);
        backtracking(nums, res,temp, product * nums[start],start + 1 );
        temp.remove(temp.size() - 1);
    }

    public static List<Integer> combinationProductII(int[] nums){
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        backtrackingII(nums, res, 1, 0);
        return res;
    }

    private static void backtrackingII(int[] nums, List<Integer> res, int product, int start) {
        if(start == nums.length){
            res.add(product);
            return;
        }
//        if(start != 0  && nums[start] == nums[start - 1]){return;}

        backtrackingII(nums, res, product * nums[start],start + 1 );

        backtrackingII(nums, res, product,start + 1 );
    }

    public static void main(String[] args) {
        System.out.println(combinationProduct(new int[]{2, 2, 3}));
        System.out.println(combinationProductII(new int[]{2, 2, 3}));
    }
}
