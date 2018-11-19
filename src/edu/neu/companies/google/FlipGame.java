package edu.neu.companies.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        char[] cs = s.toCharArray();
        List<String> res = new ArrayList<>();
        for(int i = 0; i < cs.length - 1; i ++){
            if(cs[i] == '+' && cs[i + 1] == '+'){
                cs[i] = '-';
                cs[i + 1] = '-';
                res.add(new String(cs));
                cs[i] = '+';
                cs[i + 1] = '+';
            }
        }
        return res;
    }
//  Backtracking O(N!!)
    public boolean canWin(String s) {
        return helper(s);
    }
    public boolean helper(String s){
        char[] cs = s.toCharArray();
        for(int i = 0; i < cs.length - 1; i ++){
            if(cs[i] == '+' && cs[i + 1] == '+'){
                cs[i] = '-';
                cs[i + 1] = '-';
                String nextStep = new String(cs);
                boolean win = !helper(nextStep);
                if(win){
                    return true;
                }
                cs[i] = '+';
                cs[i + 1] = '+';
            }
        }
        return false;
    }
    //with memorization (improve the performance but doesn't change time complexity)
    public boolean canWinWithMemorization(String s) {
        Map<String, Boolean> dp = new HashMap<>();
        return helper(s, dp);
    }
    public boolean helper(String s, Map<String, Boolean> dp){
        if(dp.containsKey(s)){
            return dp.get(s);
        }
        char[] cs = s.toCharArray();
        for(int i = 0; i < cs.length - 1; i ++){
            if(cs[i] == '+' && cs[i + 1] == '+'){
                cs[i] = '-';
                cs[i + 1] = '-';
                String nextStep = new String(cs);
                boolean win = !helper(nextStep, dp);
                if(win){
                    dp.put(s, true);
                    return true;
                }
                cs[i] = '+';
                cs[i + 1] = '+';
            }
        }
        dp.put(s, false);
        return false;
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal){
            return true;
        }
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if(sum < desiredTotal){
            return false;
        }
        boolean[] used = new boolean[maxChoosableInteger + 1];
        return canIWin(used, desiredTotal);
    }
    public boolean canIWin(boolean[] used, int desiredTotal) {
        if(desiredTotal <= 0){
            return false;
        }
        int key = format(used);
        for(int i = 1; i < used.length; i++ ){
            if(!used[i]){
                used[i] = true;
                if(!canIWin(used, desiredTotal - i)){
                    used[i] = false;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canIWinWithMemorization(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal){
            return true;
        }
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if(sum < desiredTotal){
            return false;
        }
        boolean[] used = new boolean[maxChoosableInteger + 1];
        Map<Integer, Boolean> memo = new HashMap<>();
        return canIWin(used, desiredTotal, memo);
    }
    public boolean canIWin(boolean[] used, int desiredTotal, Map<Integer, Boolean> memo) {
        if(desiredTotal <= 0){
            return false;
        }
        int key = format(used);
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        for(int i = 1; i < used.length; i++ ){
            if(!used[i]){
                used[i] = true;
                if(!canIWin(used, desiredTotal - i, memo)){
                    used[i] = false;
                    memo.put(key, true);
                    return true;
                }
                used[i] = false;
            }
        }
        memo.put(key, false);
        return memo.get(key);
    }
    private int format(boolean[] used){
        int sum = 0;
        for(boolean b : used){
            sum <<= 1;
            if(b){
                sum |= 1;
            }
        }
        return sum;
    }
    public boolean predictTheWinner(int[] nums) {
        return predictWinner(nums, 0, nums.length - 1)>= 0;
    }
    public int predictWinner(int[] nums, int start, int end) {
        if(start == end){
            return nums[start];
        }
        int pickStart = nums[start] - predictWinner(nums, start + 1, end);
        int pickEnd = nums[end] - predictWinner(nums, start, end - 1);
        return Math.max(pickStart, pickEnd);
    }

    public boolean predictTheWinnerWithMemorization(int[] nums) {
        Integer[][] memo = new Integer[nums.length][nums.length];
        return predictWinner(nums, 0, nums.length - 1, memo)>= 0;
    }
    public int predictWinner(int[] nums, int start, int end, Integer[][] memo) {
        if(start == end){
            return nums[start];
        }
        if(memo[start][end] != null){
            return memo[start][end];
        }
        int pickStart = nums[start] - predictWinner(nums, start + 1, end, memo);
        int pickEnd = nums[end] - predictWinner(nums, start, end - 1, memo);
        memo[start][end] = Math.max(pickStart, pickEnd);
        return Math.max(pickStart, pickEnd);
    }

    /*
        一道DP的题目。玩卡牌，N张卡，卡上有数字，可正可负。两个玩家，每个人最多可以选1，2或3张牌，自己先开始，问最多能获得的分数是多少，score就是player选择的卡上数字之和。
     */
    public boolean predictTheWinnerPractice(int[] nums) {
        if(nums.length <= 3){
            return true;
        }
        Integer[] memo = new Integer[nums.length];
        int i = predictWinner(nums, 0, memo);
        for(int j : memo){
            System.out.print(j + " ");
        }
        return i>= 0;
    }

    public int predictWinner(int[] nums, int start, Integer[] memo) {
        if(start >= nums.length ){
            return 0;
        }
        if(memo[start] != null){
            return memo[start];
        }
        int sum = 0, max = Integer.MIN_VALUE;
        for(int i = 0; i < 3 && start + i < nums.length; i ++){
            sum += nums[start + i];
            int pick = sum  - predictWinner(nums, start + 1 + i, memo);
            max = Math.max(pick, max);
        }
        memo[start] = max;
        return max;
    }
    public static void main(String[] args){
        FlipGame flipGame = new FlipGame();
        System.out.println(flipGame.predictTheWinnerPractice(new int[]{1,2,3}));
        System.out.println(flipGame.predictTheWinnerPractice(new int[]{1,2,3,224,4,5,6}));
        System.out.println(flipGame.predictTheWinnerPractice(new int[]{1,2,3,4,224,5,6}));
    }
}
