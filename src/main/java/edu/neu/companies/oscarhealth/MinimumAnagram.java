package edu.neu.companies.oscarhealth;

import java.util.*;

public class MinimumAnagram {
    static int countManipulations(String s1, String s2){
        if(s1.length() != s2.length()){
            return -1;
        }
        int[] freq = new int[128];
        for(char c : s1.toCharArray()){
            freq[c] ++;
        }
        int count = 0;
        for (char c : s2.toCharArray()){
            freq[c] --;

            if(freq[c] < 0){
                count ++;
            }
        }
        return count;
    }
    static int countManipulationsII(String s1, String s2){
        if(s1.length() != s2.length()){
            return -1;
        }
        Map<Character, Integer> freq = new HashMap<>();
        for(char c : s1.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        int count = 0;
        for (char c : s2.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) - 1);

            if(freq.get(c) < 0){
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countManipulations("aab", "aba"));
        System.out.println(countManipulations("ddcf", "cedk"));
    }
}
