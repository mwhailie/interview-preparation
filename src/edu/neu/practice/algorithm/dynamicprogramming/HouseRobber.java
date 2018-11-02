package edu.neu.practice.algorithm.dynamicprogramming;

public class HouseRobber {

    //最多只能跳过一个时的最大值
    public static int skip(int[] nums){
        int skipPrevious = 0;
        int notSkipPrevious = 0;
        for(int i = 0; i < nums.length; i ++){
            int skipThis = notSkipPrevious;
            int notSkipThis = Math.max(skipPrevious, notSkipPrevious) + nums[i];
            skipPrevious = skipThis;
            notSkipPrevious = notSkipThis;
        }
        return Math.max(skipPrevious, notSkipPrevious);
    }
    //最多能跳过两个

//    dp(i, non-skip) = dp(i-1) + num[i];
//    dp(i, skip) = max(dp(i-1, non-skip), dp(i-1, skip, non-skip));
//    dp(i, skip, non-skip) = dp(i-1, non-skip)
//    dp(i) = max(dp(i, non-skip), dp(i, skip))
//
//    dp(0, non-skip) = num[0]
//    dp(0, skip) = 0
//    dp(0, skip, non-skip) = 0
//    dp(0) = num[0] > 0 ? num[0] : 0

    public static int skipII(int[] nums){
        int[][]dp = new int[nums.length][4];
        dp[0][0] = nums[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = Math.max(dp[0][0], 0);
        for(int i = 1; i < nums.length; i ++){
            dp[i][0] = dp[i - 1][3] + nums[i];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = dp[i - 1][0];
            dp[i][3] = Math.max(dp[i][0], Math.max(dp[i][1], dp[i][2]));
        }
        return dp[nums.length - 1][3];
    }

    public static void main(String[] args) {
        System.out.println(skip(new int[]{2, -1, -2,  3, -1, -2, -5, 2, 3}));
        System.out.println(skipII(new int[]{2, -1, -2,  3, -1, -2, -5, 2, 3}));
    }
    /*
    2, -1, -2,  3, -1, -2, -5, 2, 3
    2  1   0    4   3   2  -2  4  7
    0  2   1    0   4   3   2  -2
    2  2   1    4   4   3   2  4
     */

}
