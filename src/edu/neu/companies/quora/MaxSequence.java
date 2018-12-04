package edu.neu.companies.quora;

import java.util.Arrays;

public class MaxSequence {
    public static int max(int[] nums){
        int[] dp = new int[nums.length];
        int j = 0;
        for(int i = 0; i < nums.length; i ++){
            if(i != 0 && nums[i] == nums[i - 1] + 1){
                j++;
            }
            dp[j] += nums[i];
        }
        for(int i = 0; i <= j; i ++){
            System.out.print(dp[i] + " ");
        }
        int pickPrev = dp[0];
        int notPickPrev = 0;
        for(int i = 1; i <= j; i ++){
            int pick = dp[i] + notPickPrev;
            int notPick = pickPrev;
            pickPrev = pick;
            notPickPrev = notPick;
        }
        return Math.max(pickPrev, notPickPrev);
    }

    public static int maxII(int[] nums){
        int[][] dp = new int[nums.length][3];
        dp[0][0] = nums[0];
        dp[0][2] = nums[0];
        for(int i = 1; i < nums.length; i ++){
            if(nums[i] == nums[i - 1] ){
                dp[i][0] = dp[i - 1][0] + nums[i];
                dp[i][1] = dp[i - 1][1];
            }else {
                if(nums[i] != nums[i - 1] + 1){
                    dp[i][0] = dp[i - 1][2] + nums[i];
                }else {
                    dp[i][0] = dp[i - 1][1] + nums[i];
                }
                dp[i][1] = dp[i - 1][2];
            }
            dp[i][2] = Math.max(dp[i][0],dp[i][1]);
        }
        for(int i = 0; i < dp.length; i ++){
            System.out.println(Arrays.toString(dp[i]));

        }
        return dp[nums.length - 1][2];
    }

    public static void main(String[] args) {
        System.out.println(max(new int[]{1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3}));
        System.out.println(max(new int[]{1, 1, 2, 2, 2, 2, 2, 2, 4}));
        System.out.println(maxII(new int[]{1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3}));
        System.out.println(maxII(new int[]{1, 1, 2, 2, 2, 2, 2, 2, 4}));
    }
}
