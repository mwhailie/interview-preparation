package edu.neu.practice.algorithm.backtracking;

import java.util.*;

public class CombinationSum {
    /*
    Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
    The same repeated number may be chosen from candidates unlimited number of times.

    re-use, no duplicates in array
     */

    public List<List<Integer>> combinationSumI(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return results;
        }
        Arrays.sort(candidates);
        backTrackingI(candidates, target, new ArrayList<>(), results, 0);
        return results;
    }

    private void backTrackingI(int[] candidates, int target, List<Integer> result, List<List<Integer>> results, int start) {
        if(target == 0){
            results.add(new ArrayList<>(result));
            return;
        }else if(target < 0) {
            return;
        }
        for(int i = start; i < candidates.length; i ++ ){
            result.add(candidates[i]);
            backTrackingI(candidates, target - candidates[i], result, results, i);//re-use
            result.remove(result.size() - 1);
        }
    }

    /*
    Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
    Each number in candidates may only be used once in the combination.

    no-use, duplicates in array

    time:
    n - length of candidates
    k - average length of answer
    O(k * 2^n)
     */
    public List<List<Integer>> combinationSumII(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return results;
        }
        Arrays.sort(candidates);
        backTrackingII(candidates, target, new ArrayList<>(), results, 0);
        return results;
    }

    private void backTrackingII(int[] candidates, int target, List<Integer> result, List<List<Integer>> results, int start) {
        if(target == 0){
            results.add(new ArrayList<>(result));
            return;
        }else if(target < 0) {
            return;
        }
        for(int i = start; i < candidates.length; i ++ ){
            if(i != start && candidates[i] == candidates[i - 1]){// skip
                continue;
            }
            result.add(candidates[i]);
            backTrackingII(candidates, target - candidates[i], result, results, i + 1);
            result.remove(result.size() - 1);
        }
    }
    /*
        dp O(n * target)
     */
    public int coins(int[] coins, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int coin : coins){
            for(int i = 0; i <= target; i ++){
                if(i - coin >= 0){
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[target];
    }
    /*
        dp O(n * target)
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i = 0; i <= target; i ++){
            for(int num : nums){
                if(i - num >= 0){
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
