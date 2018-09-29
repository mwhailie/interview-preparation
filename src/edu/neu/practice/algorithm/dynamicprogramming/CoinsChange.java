package edu.neu.practice.algorithm.dynamicprogramming;

import java.util.*;

public class CoinsChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);
        for (int i = 0; i < coins.length && coins[i] <= amount; i++) {
            dp[coins[i]] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == 1) {
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length && i - coins[j] > 0; j++) {
                if (dp[i - coins[j]] == 0) {
                    continue;
                }
                min = Math.min(dp[i - coins[j]] + 1, min);
            }
            dp[i] = min == Integer.MAX_VALUE ? 0 : min;
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }
}
