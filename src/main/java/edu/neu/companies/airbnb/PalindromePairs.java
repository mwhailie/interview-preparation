package edu.neu.companies.airbnb;

import java.util.*;
// re-use same word?
public class PalindromePairs {
//   时间复杂度是一样的，O(n * k^2)
    public List<List<Integer>> palindromePairsTrie(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for(int i = 0; i < words.length; i++){
            addWord(root, words[i], i);
        }
        for(int i = 0; i < words.length; i++){
            searchWord(root, words[i], i,  res);
        }
        return res;
    }
    private void addWord(TrieNode root, String word, int index){
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';

            if (root.children[j] == null) {
                root.children[j] = new TrieNode();
            }
            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }
            root = root.children[j];
        }
        root.list.add(index);
        root.index = index;
    }
    private void searchWord(TrieNode root, String word,
                            int index, List<List<Integer>> res){
        for (int j = 0; j < word.length(); j++) {
            if (root.index >= 0 && root.index != index && isPalindrome(word, j, word.length() - 1)) {
                res.add(Arrays.asList(index, root.index));
            }
            root = root.children[word.charAt(j) - 'a'];
            if (root == null) return;
        }
        for (int j : root.list) {
            if (index == j) continue;
            res.add(Arrays.asList(index, j));
        }
    }
    class TrieNode{
        TrieNode[] children;
        int index;
        List<Integer> list;
        public TrieNode(){
            children = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }
    private boolean isPalindrome(String word, int i, int j){
        while(i < j){
            if(word.charAt(i++) != word.charAt(j --)){
                return false;
            }
        }
        return true;
    }


//  Iterative O(n * k^2) k is average length of word
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }
        for(int i = 0; i < words.length; i++){
            String word = new StringBuilder(words[i]).reverse().toString();
            for(int j = 0; j <= word.length(); j ++){
                String prefix = word.substring(0, j);
                String suffix = word.substring(j);
                if(isPalindrome(suffix)){
                    if(map.containsKey(prefix) && map.get(prefix) != i){
                        res.add(Arrays.asList(new Integer[]{map.get(prefix), i}));
                    }
                }
                if(isPalindrome(prefix) && prefix.length()!=0){
                    if(map.containsKey(suffix) && map.get(suffix) != i){
                        res.add(Arrays.asList(new Integer[]{i, map.get(suffix)}));
                    }
                }
            }
        }
        return res;
    }
    private boolean isPalindrome(String word){
        int i = 0, j = word.length() - 1;
        while(i < j){
            if(word.charAt(i++) != word.charAt(j --)){
                return false;
            }
        }
        return true;
    }
}
