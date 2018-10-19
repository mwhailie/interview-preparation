package edu.neu.practice.algorithm.graph;

import com.sun.xml.internal.rngom.digested.DPattern;

public class RegularExpression {
//    Recursive
//    O((T + P) * 2^(T + P))
    public boolean isMatch(String s, String p) {
        if( p.isEmpty() ){
            return s.isEmpty();
        }
        boolean isFirstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if(p.length() >= 2 && p.charAt(1) == '*'){
            return isFirstMatch && isMatch(s.substring(1), p) || isMatch(s, p.substring( 2));
        } else {
            return isFirstMatch  && isMatch(s.substring(1), p.substring(1));
        }
    }
//    DP
//    Time: O(TP) Space: O(TP)
//    dp[i][j] is pattern.substring(j) regex of text.substring(i)
    public boolean isMatchDP(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
