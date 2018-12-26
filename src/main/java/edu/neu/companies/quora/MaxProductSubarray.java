package edu.neu.companies.quora;

public class MaxProductSubarray {
    int maxProduct(int[] nums){
        int result = nums[0], max = nums[0], min = nums[0];
        for(int i = 1; i < nums.length; i ++){
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), max);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]),min);
            result = Math.max(max, result);
        }
        return result;
    }
}
