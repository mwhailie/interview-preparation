package edu.neu.companies.oscarhealth;

import java.util.Arrays;

public class CoffeeMachine {

    static boolean canProduce(int[] button, int min, int max){
        Arrays.sort(button);
        return dfs(button, min, max, 0, 0);
    }
    static boolean canProduceDP(int[] button, int min, int max){
        boolean[] dp = new boolean[max + 1];
        Arrays.sort(button);
        for(int i = 0 ; i < button.length && button[i] <= max; i ++){
            dp[button[i]] = true;
        }
        for(int i = 1 ; i < dp.length; i ++){
            if(dp[i]){
                continue;
            }
            for(int j = 0 ; j < button.length && i - button[j] > 0; j ++){
                if(dp[i - button[j]]){
                    dp[i] = true;
                }
            }

        }
        return dp[max + 1];
    }

    static boolean dfs(int[] button, int min, int max, int curr, int start){
        if(curr >= min && curr <= max){
            return true;
        }
        if(curr > max){
            return false;
        }
        for(int i = start; i < button.length; i++){
            if(dfs(button, min, max, curr + button[i], start)){
                return true;
            }
        }
        return  false;
    }

    public static void main(String[] args) {
        System.out.println(canProduce(new int[]{4,7,13}, 8, 9));
        System.out.println(canProduce(new int[]{4,7,13}, 9, 10));
        System.out.println(canProduce(new int[]{3,6,9}, 10, 11));
        System.out.println(canProduce(new int[]{3,6,9}, 17, 21));
    }
}
