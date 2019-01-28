package edu.neu.companies.microsoft;

import java.util.*;

public class TwentyFourGame {
    public boolean judgePoint24(int[] nums) {
        List<Integer> set = new ArrayList<>();
        return dfs(nums, 0.0, set);
    }
    public boolean dfs(int[] nums, double curr, List<Integer> set) {
        System.out.print(curr);
        System.out.println(set);
        if(set.size() == 4){
            return curr == 24.0;
        }
        if(set.size() == 2){
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i <=3; i ++) if(!set.contains(i)){
                list.add(i);
            }
            int first = nums[list.get(0)];
            int second = nums[list.get(1)];
            if(curr * (first + second) == 24 || curr * (first - second) == 24
                    || curr * (second - first) == 24
                    || (first - second) / curr == 24
                    || curr / (second - first) == 24
                    || (second - first) / curr == 24){
                return true;
            }
        }
        for(int i = 0; i < nums.length; i ++){
            if(set.contains(i)){
                continue;
            }
            set.add(i);
            if(set.size() == 1) {
                if(dfs(nums, curr + nums[i], set)){
                    return true;
                }
            }else if(dfs(nums, curr + nums[i], set)
                        ||dfs(nums, curr - nums[i], set)
                        ||dfs(nums, nums[i] - curr, set)
                        ||dfs(nums, curr * nums[i], set)
                        ||dfs(nums, (curr / (double) nums[i]), set)
                        ||dfs(nums, nums[i] / curr, set)){
                    return true;
            }
            set.remove(set.size() - 1);
        }
        return false;
    }

    public static void main(String[] args) {
        TwentyFourGame game = new TwentyFourGame();
//        System.out.println(game.judgePoint24(new int[]{3,4,6,7}));
//        System.out.println(game.judgePoint24(new int[]{7,4,1,9}));
        System.out.println(game.judgePoint24(new int[]{3,3,8,8}));
    }
}
