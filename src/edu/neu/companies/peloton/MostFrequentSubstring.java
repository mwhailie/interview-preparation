package edu.neu.companies.peloton;

import java.util.*;

public class MostFrequentSubstring {

    public static int getMaxOcurrence(String s, int minLength, int maxLength, int maxUnique) {
        if(s.length() == 0){
            return 1;
        }
        int max = 0;
        HashMap<String, Integer> freq = new HashMap<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < minLength && i < s.length(); i++){
            map.put(s.charAt(i), i);
        }
        for(int i = 0; i + minLength <= s.length(); i++){

            map.put(s.charAt(i + minLength - 1), i + minLength - 1);

            if (map.size() > maxUnique) {
                int min = s.length();
                for (int left : map.values()) {
                    min = Math.min(left, min);
                }
                map.remove(s.charAt(min));
                i = min ;
                continue;
            }

            String substring = s.substring(i, i + minLength);
            freq.put(substring, freq.getOrDefault(substring, 0) + 1);
            max = Math.max(max, freq.get(substring));
        }
        System.out.println(freq);
        return max;
    }
    public static int getMaxOcurrence2(String s, int minLength, int maxLength, int maxUnique) {
        int max = 0, start = 0, end = 0, uniqueChar = 0;
        HashMap<String, Integer> freq = new HashMap<>();
        int[] count = new int[256];

        while(end < s.length()){
            if(count[s.charAt(end)] == 0){
                uniqueChar ++;
            }
            count[s.charAt(end)] ++;
            while(end - start + 1 > maxLength){
                count[s.charAt(start)] --;
                if(count[s.charAt(start)] == 0){
                    uniqueChar --;
                }
                start ++;
            }
            while (uniqueChar > maxUnique) {
                count[s.charAt(start)]--;
                if(count[s.charAt(start)] == 0){
                    uniqueChar--;
                }
                start++;
            }
            for(int i = start; end - i + 1>= minLength; i ++){
                String substring = s.substring(i, end + 1);
                freq.put(substring, freq.getOrDefault(substring, 0) + 1);
                max = Math.max(max, freq.get(substring));
            }
            end ++;
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(getMaxOcurrence("", 2, 4, 26));
        System.out.println(getMaxOcurrence("abcde", 2, 4, 26));
        System.out.println(getMaxOcurrence("ababa", 2, 4, 26));
        System.out.println(getMaxOcurrence("ababab", 2, 3, 4));

    }
}
