package edu.neu.companies.google;

import java.util.*;

public class OnsiteCompoundWord {
    public List<String> findLongestSubstring(String cWord, List<String> dict){
        Set<String> dictionary = new HashSet<>();
        dictionary.addAll(dict);
        List<String> candidate = new ArrayList<>();
        dfs(cWord, dictionary, res, candidate, 0);
        return res;
    }
    List<String> res = new ArrayList<>();
    private void dfs(String cWord, Set<String> dictionary, List<String> res, List<String> candidate, int start) {
        if(start == cWord.length()){
//            System.out.println(candidate);
            if(res.isEmpty() || res.size() < candidate.size()){
//                System.out.println(candidate);
                res.clear();
                res.addAll(candidate);
//                res = new ArrayList<>(candidate);
//                (List<String>)candidate.clone();
            }
//            return;
        }
        for(int i = start; i < cWord.length(); i ++){
            String word = cWord.substring(start, i + 1);
            if(dictionary.contains(word)){
                candidate.add(word);
                dfs(cWord, dictionary, res, candidate, i + 1);
                candidate.remove(candidate.size() - 1);
            }
        }
    }
    public int wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length() + 1];
//        dp[0] = true;
        for(int i = 0; i <= s.length(); i ++){
            //if the word is in the dic
            if(dp[i] == 0 && wordDict.contains(s.substring(0,i))){
                dp[i] = 1;
            }
            //check the possible single splits
            for(int j = 1; j < i; j ++){
                if(dp[j] != 0 && wordDict.contains(s.substring(j,i))){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[s.length() ];
    }

    public static void main(String[] args) {
        OnsiteCompoundWord o = new OnsiteCompoundWord();
        List<String> dict = new ArrayList<>();

        dict.add("foo");
        dict.add("bar");
        System.out.println(o.findLongestSubstring("foobar", dict));
        dict.add("a");
        dict.add("b");
        dict.add("c");
        dict.add("cde");
        dict.add("de");
        dict.add("bcd");
        System.out.println(o.findLongestSubstring("abcde", dict));
    }
}
