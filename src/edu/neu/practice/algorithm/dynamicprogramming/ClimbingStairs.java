package edu.neu.practice.algorithm.dynamicprogramming;

public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n <= 2){
            return n;
        }
        int[] numOfWays = new int[n];
        numOfWays[0] = 1;
        numOfWays[1] = 2;
        for(int i = 2; i < n; i ++){
            numOfWays[i] = numOfWays[i - 1] + numOfWays[i - 2];
        }
        return numOfWays[n - 1];
    }
}

