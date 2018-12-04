package edu.neu.companies.airbnb;

import java.util.*;

public class EditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if(s == null && t == null || s.isEmpty() && t.isEmpty() || s.equals(t) || s.length() - t.length() > 1){
            return false;
        }
        if(s.length() == t.length() ){
            return match(s, t, true);
        }else{
            return s.length() > t.length() ? match(s, t, true)  : match(t, s, false) ;
        }
    }
    public boolean match(String s, String t, boolean sameLength) {
        boolean skipOne = true;
        int i = 0, j = 0;
        while(i < s.length() && j < t.length()){
            if(s.charAt(i) != t.charAt(j)){
                if(!skipOne){
                    return false;
                }
                skipOne = false;
                if(sameLength){
                    i++;j ++;
                }else{
                    i++;
                }
            }else{
                i++; j++;
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
    private void search(String curr, String target, int k, TrieNode root,
                        int[] prevDist, List<String> result) {
        if (root.isLeaf) {
            if (prevDist[target.length()] <= k) {
                result.add(curr);
            } else {
                return;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (root.children[i] == null) {
                continue;
            }
            int[] currDist = new int[target.length() + 1];
            currDist[0] = curr.length() + 1;
            for (int j = 1; j < prevDist.length; j++) {
                if (target.charAt(j - 1) == (char) (i + 'a')) {
                    currDist[j] = prevDist[j - 1];
                } else {
                    currDist[j] = Math.min(Math.min(prevDist[j - 1],
                            prevDist[j]), currDist[j - 1]) + 1;
                }
            }
            search(curr + (char) (i + 'a'), target, k, root.children[i],
                    currDist, result);
        }
    }
    public List<String> getKEditDistance(String[] words, String target, int k)
    {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0 || target == null ||
                target.length() == 0 || k < 0) {
            return res;
        }
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        TrieNode root = trie.root;
// The edit distance from curr to target
        int[] prev = new int[target.length() + 1];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = i;
        }
        search("", target, k, root, prev, res);
        return res;
    }
    class TrieNode {
        TrieNode[] children;
        boolean isLeaf;
        public TrieNode() {
            children = new TrieNode[26];
        }
    }
    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        // Add a word into trie
        public void insert(String s) {
            if (s == null || s.length() == 0) {
                return;
            }
            TrieNode p = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (p.children[c - 'a'] == null) {
                    p.children[c - 'a'] = new TrieNode();
                }
                if (i == s.length() - 1) {
                    p.children[c - 'a'].isLeaf = true;
                }
                p = p.children[c - 'a'];
            }
        }
    }

    public static void main(String[] args) {
        EditDistance e = new EditDistance();
        System.out.println(e.isOneEditDistance("ab", "cab"));
    }
}
