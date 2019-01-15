package edu.neu.companies.microsoft;

import java.util.*;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
