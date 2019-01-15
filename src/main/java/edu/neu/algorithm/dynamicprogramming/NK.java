package edu.neu.algorithm.dynamicprogramming;

public class NK {

//想喝n瓶酒，k个空瓶换一瓶酒，需要几瓶酒

    //dp[i] : 想喝 i 瓶酒时，最少需要几瓶酒
    //dp[i] = min(dp[i - 1] + 1, dp[i - k]);
    //result : dp[n] : 想喝 n 瓶酒时，最少需要几瓶酒
    public static int minNumberOfBottle(int n, int k){
        if(n <= k){
            return n;
        }
        int total = 0;
        int emptybottle = 0;
        for(int i = 1; i <= n; i ++){
            if(emptybottle >= k){
                emptybottle = 0;
            }else {
                total ++;
            }
            emptybottle ++;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(minNumberOfBottle(8,1));
        System.out.println(minNumberOfBottle(8,2));
        System.out.println(minNumberOfBottle(8,3));
        System.out.println(minNumberOfBottle(8,4));
        System.out.println(minNumberOfBottle(8,5));
        System.out.println(minNumberOfBottle(8,6));
        System.out.println(minNumberOfBottle(8,7));
        System.out.println(minNumberOfBottle(8,8));
        System.out.println(minNumberOfBottle(8,10));

    }
}
