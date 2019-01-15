package edu.neu.companies.mathworks;

import java.util.*;

public class TheCostOfATree {

    int calCostOfTree(int[] arr){
        if(arr == null){
            return 0;
        }
        List<Integer> nums = new ArrayList<>();
        for(int num : arr){
            nums.add(num);
        }
        return calCostOfTree(nums);
    }

    private int calCostOfTree(List<Integer> nums) {
        if(nums.size() == 1){
            return 0;
        }
        if(nums.size() == 2){
            return nums.get(0) * nums.get(1);
        }
        int i = 0;
        int minPos = 0;
        int minCost = nums.get(0) * nums.get(1);

        for(; i < nums.size() - 1; i ++){
            if(minCost > nums.get(i) * nums.get(i + 1)){
                minPos = i;
                minCost = nums.get(i) * nums.get(i + 1);
            }
        }
        nums.set(minPos, Math.max(nums.get(minPos) , nums.get(minPos + 1)));
        nums.remove(minPos + 1);
        return minCost + calCostOfTree(nums);
    }

    public static void main(String[] args) {
        TheCostOfATree t = new TheCostOfATree();
//        System.out.println(t.calCostOfTree(new int[]{}));
        System.out.println(t.calCostOfTree(new int[]{1}));
        System.out.println(t.calCostOfTree(new int[]{1, 2}));
//        System.out.println(t.calCostOfTree(new int[]{1, 2, 3}));
//        System.out.println(t.calCostOfTree(new int[]{4, 6, 2}));
//        System.out.println(t.calCostOfTree(new int[]{1, 2, 3, 4}));

        System.out.println(t.calCostOfTree(new int[]{3, 2, 1, 4}));
     }
}
