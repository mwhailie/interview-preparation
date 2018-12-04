package edu.neu.companies.quora;

import java.util.*;

public class FormTeam {
    public static long formTeam(int k, int[] students){
        Map<Integer, Long> frequency = new HashMap<>();
        for(int student : students){
            frequency.put(student, frequency.getOrDefault(student, 0L) + 1);
        }
        long res = 0l;

        for(int score : frequency.keySet() ){
            if(score >= k){
                continue;
            }
            long freq = frequency.get(score);
            if(score * 3 == k && frequency.get(score) >= 3){
                res += freq * (freq - 1) * (freq - 2)/6;
            }else if(score * 2 < k && frequency.get(score) >= 2 && frequency.containsKey(k - score * 2)){
                res += (freq * (freq - 1))/2 * frequency.get(k - score * 2);
            }
            for(int score2 : frequency.keySet()){
                int score3 = k - score - score2;
                if(score2 <= score || score3 <= score2){
                    continue;
                }
                if(frequency.containsKey(score3)){
                    res += freq * frequency.get(score2) * frequency.get(score3);
                }
            }
        }
        return res;
    }
    public static long formTeam3(int k, int[] students){
        TreeMap<Integer, Long> frequency = new TreeMap<>(Collections.reverseOrder());
        for(int student : students){
            frequency.put(student, frequency.getOrDefault(student, 0L) + 1);
        }
        long res = 0l;

        for(int score : frequency.keySet() ){
            if(score >= k){
                continue;
            }
            long freq = frequency.get(score);
            if(score * 3 == k && frequency.get(score) >= 3){
                res += freq * (freq - 1) * (freq - 2)/6;
            }else if(score * 2 < k && frequency.get(score) >= 2 && frequency.containsKey(k - score * 2)){
                res += (freq * (freq - 1))/2 * frequency.get(k - score * 2);
            }
            for(int score2 : frequency.headMap(score, false).keySet()){
                int score3 = k - score - score2;
                if(score3 <= score2){
                    continue;
                }
                if(frequency.containsKey(score3)){
                    res += freq * frequency.get(score2) * frequency.get(score3);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(formTeam3(20, new int[]{4, 5, 10, 7, 7,1,3,4,4,8}));
    }
    public static int formTeam2(int k, int[] students){
        TreeMap<Integer, Integer> frequency = new TreeMap<>();
        for(int student : students){
            frequency.put(student, frequency.getOrDefault(student, 0) + 1);
        }
        int res = 0;

        for(int score : frequency.keySet() ){
            if(score >= k){
                continue;
            }
            int freq = frequency.get(score);
            if(score * 3 == k && frequency.get(score) >= 3){
                // System.out.println(score + " * 3");
                res += freq * (freq - 1) * (freq - 2)/6;
            }
            if(score * 2 < k && frequency.get(score) >= 2 && frequency.containsKey(k - score * 2)){
                // System.out.println(score + " * 2 + " + (k - score * 2));
                res += (freq * (freq - 1))/2 * frequency.get(k - score * 2);
            }
            Set<Integer> visited = new HashSet<>();
            for(int score2 : frequency.keySet()){
                if(score2 <= score || score2 + score * 2 == k || 2 * score2 + score == k || visited.contains(score2) || k - score - score2 <= score2){
                    continue;
                }
                if(frequency.containsKey(k - score - score2)){
                    // System.out.println(score + " + " + score2 + " + " + (k - score - score2));
                    visited.add(score2);
                    visited.add(k - score - score2);
                    res += freq * frequency.get(score2) * frequency.get(k - score - score2);
                }
            }
        }
        return res;
    }
    public long threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int target = -nums[i];
                int j = i + 1, k = nums.length - 1;
                while (j < k) {
                    if (nums[j] + nums[k] == target) {
                        result++;
                        while (j < k && nums[j] == nums[j + 1]) j++;
                        while (j < k && nums[k] == nums[k - 1]) k--;
                        j++;
                        k--;
                    } else if (nums[j] + nums[k] < target) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }
        return result;
    }
}
