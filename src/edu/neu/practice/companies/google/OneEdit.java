package edu.neu.practice.companies.google;

public class OneEdit {
    public boolean isOneEditDistance(String s, String t) {
        if(s == null && t == null || s.isEmpty() && t.isEmpty() || s.equals(t) || Math.abs(s.length() - t.length()) > 1){
            return false;
        }
        if(s.length() == t.length() ){
            return isModify(s, t);
        }else{
            return s.length() > t.length() ? isDelete(s, t)  : isDelete(t, s) ;
        }
    }
    public boolean isModify(String s, String t) {
        int diff = 0;
        for(int i = 0; i < s.length(); i ++){
            if(s.charAt(i) != t.charAt(i)){
                diff++;
            }
        }
        return diff == 1;
    }
    public boolean isDelete(String s, String t) {
        for(int i = 0,j = 0; i < s.length() && j <t.length(); i ++, j ++){
            if(s.charAt(i) != t.charAt(j)){
                return s.substring(i + 1).equals(t.substring(j));
            }
        }
        return true;
    }
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i ++){
            dp[i][0] = i;
        }
        for(int i = 0; i <= m; i ++){
            dp[0][i] = i;
        }
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                if(word1.charAt(i) == word2.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j];
                }else{
                    int insert = dp[i + 1][j];
                    int delete = dp[i][j + 1];
                    int update = dp[i][j];
                    dp[i + 1][j + 1] = insert < delete? (insert < update? insert : update):(delete < update? delete : update);
                    dp[i + 1][j + 1] ++;
                }
            }
        }
        return dp[n][m];
    }
}
