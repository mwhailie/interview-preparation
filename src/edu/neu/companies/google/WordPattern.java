package edu.neu.companies.google;

import java.util.*;

public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        Map<Character, String> code = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (code.get(c) == null) {
                if (code.values().contains(word)) {
                    return false;
                }
                code.put(c, word);
            } else {
                if (!code.get(c).equals(word)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> dict = new HashMap<>();
        Set<String> set = new HashSet<>();
        return wordPatternMatch(pattern, str, 0, 0, dict, set);
    }

    public boolean wordPatternMatch(String pattern, String str, int startOfPattern, int startOfString,
                                    Map<Character, String> dict, Set<String> set) {
        if (startOfPattern == pattern.length() && startOfString == str.length()) {
            return true;
        }
        if (startOfPattern == pattern.length() || startOfString == str.length()) {
            return false;
        }
        char c = pattern.charAt(startOfPattern);
        if (dict.get(c) != null) {
            String word = dict.get(c);
            if (str.startsWith(word, startOfString)) {
                return wordPatternMatch(pattern, str, startOfPattern + 1, startOfString + word.length(), dict, set);
            } else {
                return false;
            }
        }
        for (int i = startOfString + 1; i <= str.length(); i++) {
            String word = str.substring(startOfString, i);
            if (set.contains(word)) {
                continue;
            }
            set.add(word);
            dict.put(c, word);
            if (wordPatternMatch(pattern, str, startOfPattern + 1, startOfString + word.length(), dict, set)) {
                return true;
            }
            set.remove(word);
            dict.remove(c, word);
        }
        return false;
    }

    //DFS O(2 ^ n) -> LTE
    public int numDecodings(String s) {
        int[] solution = new int[1];
        dfs(s, 0, solution);
        return solution[0];
    }

    public void dfs(String s, int start, int solution[]) {
        if (start == s.length()) {
            solution[0]++;
            return;
        }
        if (start > s.length()) {
            return;
        }
        char c = s.charAt(start);
        if (c == '0') {
            return;
        }
        dfs(s, start + 1, solution);
        if (start + 1 < s.length() && Integer.parseInt(s.substring(start, start + 2)) <= 26) {
            // System.out.println(solution[0]);
            dfs(s, start + 2, solution);
        }
    }


//  DFS with Memcache O(n) time, O(n) space
 	public int numDecodingsDFSwithCache(String s) {
        int[] solution = new int[1];
 		return dfs(s, 0, solution, new HashMap<>());
 	}
 	public int dfs(String s, int start, int solution[], Map<Integer, Integer> map){
 		if(start == s.length()){
 			return 1;
 		}
         if(map.containsKey(start))
             return map.get(start);

         		char c = s.charAt(start);
 		if(c == '0'){
 			return 0;
 		}
        solution[0] = 0;
 	    solution[0] += dfs(s, start + 1, solution, map);
 		if(start + 1 < s.length() && Integer.parseInt(s.substring(start, start + 2)) <= 26){
 	        solution[0] += dfs(s, start + 2, solution, map);
 		}
         map.put(start, solution[0]);
         return solution[0];
 	}
// 	DP O(n) time O(n) space
    public int numDecodingsDP(String s) {
            int n = s.length() ;
            int[] dp = new int[n + 1];
            if(s.charAt(0) == '0'){
                return 0;
            }
            dp[0] = 1;
            dp[1] = s.charAt(0) == '0' ? 0 : 1;

            for(int i = 2 ; i <= n; i ++){
                if(s.charAt(i - 1) >= '1') {
                    dp[i] += dp[i-1];
                }
                if(Integer.valueOf(s.substring(i-2, i)) >= 10 && Integer.valueOf(s.substring(i-2, i)) <= 26) {
                    dp[i] += dp[i-2];
                }
            }
            return dp[n];
        }
    // DP O(n) time O(1) space
    public int numDecodingsDPwithPointer(String s) {
        int n = s.length() ;
        if(s.charAt(0) == '0'){
            return 0;
        }
        int prev = 1;
        int curr = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i <= n; i ++){
            int temp = 0;
            if(s.charAt(i - 1) != '0'){
                temp += curr;
            }
            if (Integer.valueOf(s.substring(i - 2, i)) >= 10 && Integer.valueOf(s.substring(i - 2, i)) <= 26){
                temp += prev;
            }
            prev = curr;
            curr = temp;
        }
        return curr;
    }
}
