package edu.neu.algorithm.dynamicprogramming;

public class DistinctSubsequence {
    // 1. dfs O(n ^ k)
    public int numDistinct(String s, String t) {
        int[] res = new int[1];
        dfs(s, t, 0, 0, res);
        return res[0];
    }
    public void dfs(String s, String t, int i, int j, int[] res) {
        if( j == t.length()){
            res[0] ++;
            return;
        }
        if(i == s.length()){
            return;
        }
        for(int k = i ; k < s.length(); k ++) if(s.charAt(k) == t.charAt(j)){
            dfs(s, t, k + 1, j + 1, res);
        }
    }
    // 2. dp O(kn)
    public int numDistinctDP(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= n; i ++) {
            dp[0][i] = 1;
        }
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(s.charAt(j) == t.charAt(i)){
                    dp[i + 1][j + 1] = dp[i][j] + dp[i + 1][j];
                }else{
                    dp[i + 1][j + 1] = dp[i + 1][j];
                }
                // System.out.print(dp[i + 1][j + 1] + ", ");
            }
            // System.out.println();
        }
        return dp[m][n];
    }

}
