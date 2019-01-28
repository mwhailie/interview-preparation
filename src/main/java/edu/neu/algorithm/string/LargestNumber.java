package edu.neu.algorithm.string;

import java.util.*;

public class LargestNumber {
    class StringComparator implements Comparator<String> {
        public int compare(String a, String b){
            int i = 0;
            while(i < a.length() && i < b.length()){
                if(a.charAt(i) != b.charAt(i)) return b.charAt(i) - a.charAt(i);
                i ++;
            }
            if(i == a.length()){
                return compare(a, b.substring(i));
            }else{
                return compare(a.substring(i), b);
            }
        }
    }
    public String largestNumber(int[] nums) {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i ++){
            res.add("" + nums[i]);
        }
        Collections.sort(res, new StringComparator());
        String s = String.join("", res);
        int i = 0;
        while(i < s.length() && s.charAt(i) == '0'){
            i ++;
        }
        return i == s.length() ? "0" : s.substring(i);
    }
}
