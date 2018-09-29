package edu.neu.practice.companies.google;

public class PickHouse {
    /*
    房子編號 0 1 2 3 4 5 6 7
    你要找的某個人(e.g. Michael)可能住在某間房子
    你可以用query(house id, 人名)知道Micheal在house id的左邊還是右邊還是就住在那

    假設Micheal住在house id 2
    query(1, "Michael") return 左邊
    query(6, "Michael") return 右邊
    query(2, "Michael") return 就在這裡!
    假設每個query $1
    請設計一個演算法使成本最低

    follow-up:
    若query的cost每個house都不同
    Ex: house 0的cost是10、house 1的cost是20、依此類推
    那麼你的演算法要如何修改?


    Leetcode 375

     */

    /* The guess API is defined in the parent class GuessGame.
       @param num, your guess
       @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
          int guess(int num); */
    public int guessNumber(int n) {
        int max = n, min = 1;
        int mid = 0;
        while (min < max) {
            mid = min + (max - min) / 2;
            int compare = guess(mid);
            if (compare == 1) {
                min = mid + 1;
            } else if (compare == -1) {
                max = mid - 1;
            } else {
                return mid;
            }
        }
        return guess(max) == 0 ? max : (guess(min) == 0 ? min : -1);
    }
    //fake api
    int guess(int max){
        return 0;
    }
    //Follow up :
    //Recursive n!
    public int getMoneyAmount(int n) {
        return money(1, n);
    }
    public int money(int start, int end){
        if(start >= end ){
            return 0;
        }
        int minCost = Integer.MAX_VALUE;
        for(int i = start; i <= end; i ++){
            int left = money(start, i - 1);
            int right = money(i + 1, end);
            int res = i + Math.max(left, right);
            minCost = Math.min(res, minCost);
        }
        return minCost;
    }
    //DP N^3
    //dp[i][j] stands for minCost from i to j
    public int getMoneyAmountDP(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int len = 2; len <= n; len ++){
            for(int start = 1; start + len - 1 <= n ; start ++){
                int minCost = Integer.MAX_VALUE;
                for(int i = start; i < start + len - 1; i ++){
                    int left = dp[start][i - 1];
                    int right = dp[i + 1][start + len - 1];
                    int res = i + Math.max(left, right);
                    minCost = Math.min(res, minCost);
                }
                dp[start][start + len - 1] = minCost;
            }
        }
        return dp[1][n];
    }

}
