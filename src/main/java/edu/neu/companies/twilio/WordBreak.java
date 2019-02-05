package edu.neu.companies.twilio;

import java.util.*;

public class WordBreak {

    List<String> wordDict;
    public boolean dfs(String input, List<String> wordDict){
        this.wordDict = wordDict;
        return dfs(input, 0);
    }

    public boolean dfs(String input, int index){
        if(index == input.length()){
            return true;
        }
        for(int i = index; i <= input.length(); i ++){
            if(isWord(input.substring(index, i)) && dfs(input, index + i)) {
                return true;
            }
        }
        return false;
    }

    public boolean isWord(String s){
        return wordDict.contains(s);

    }
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i ++){
            for(int j = 0; j < i; j ++){
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    // break;
                }
            }
        }
        return dp[s.length() ];
    }
}
