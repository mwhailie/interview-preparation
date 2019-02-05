package edu.neu.companies.twilio;

import java.util.*;

public class ThreeSum {
    List<List<Integer>> threeSum(int[] nums, int target){
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 2; i ++){
            for(int j = i + 1; j < nums.length - 1; j ++){
                int sum = nums[i] + nums[j];
                for(int k = j + 1; j < nums.length ; k ++){
                    if(sum + nums[k] == target){
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }

                }
            }
        }
        return res;
    }
    List<List<Integer>> threeSumI(int[] nums, int target){
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length - 1; i ++){
            for(int j = i + 1; j < nums.length; j ++){
                if(set.contains(target - nums[i] - nums[j])){
                    List<Integer> pair = new ArrayList<>();
                    pair.add(nums[i]);
                    pair.add(nums[j]);
                    pair.add(target - nums[i] - nums[j]);
                    res.add(pair);
                }
            }
            set.add(nums[i]);
        }
        return res;
    }
    List<List<Integer>> threeSumII(int[] nums, int target){
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++; hi--;
                    } else if (nums[lo] + nums[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }
}
