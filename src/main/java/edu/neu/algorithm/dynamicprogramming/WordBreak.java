package edu.neu.algorithm.dynamicprogramming;

import java.util.*;

public class WordBreak {
    // 1. DFS O(n ^ n) LTE
     public List<String> wordBreak(String s, List<String> wordDict) {
         List<String> res = new ArrayList<>();
         dfs(s, wordDict, res, "", 0);
         return res;
     }
     private void dfs(String cWord, List<String> dictionary, List<String> res, String temp, int start) {
         if(start == cWord.length()){
             res.add(new String(temp.trim()));
         }
         for(int i = start; i < cWord.length(); i ++){
             String word = cWord.substring(start, i + 1);
             if(dictionary.contains(word)){
                 temp = temp + " " + word;
                 dfs(cWord, dictionary, res, temp, i + 1);
                 temp = temp.substring(0, temp.length() - word.length() - 1);
             }
         }
     }
    public List<String> longest(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        List<List<String>> ress = new ArrayList<>();
        longest(s, wordDict, ress, res, new ArrayList<>(), 0);
        System.out.println(res);
//        System.out.println(ress);
        return res;
    }
    private void longest(String cWord,  List<String> dictionary, List<List<String>> ress, List<String> res,  List<String> temp, int start) {
        if(start == cWord.length()){
            if(res.isEmpty()||res.size() < temp.size()){
//                System.out.println(temp);
//                ress.add(new ArrayList<>(temp));
                res.clear();
                res.addAll(temp);
//                Collections.copy(res, temp);
            }
        }
        for(int i = start; i < cWord.length(); i ++){
            String word = cWord.substring(start, i + 1);
            if(dictionary.contains(word)){
                temp.add(word);
                longest(cWord, dictionary, ress, res, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    // 2. DFS with Memorize O(n ^ 3)
     public List<String> wordBreak2(String s, List<String> wordDict) {
         return dfs(s, wordDict, 0);
     }
     Map<Integer, List<String>> memo = new HashMap<>();
     private List<String> dfs(String s, List<String> dictionary, int start) {

         if(memo.containsKey(start)){
             return memo.get(start);
         }
         List<String> res = new LinkedList<>();
         if(start == s.length()){
             res.add("");
         }
         for(int i = start; i < s.length(); i ++){
             String word = s.substring(start, i + 1);
             if(dictionary.contains(word)){
                 List<String> list = dfs(s, dictionary, i + 1);
                 for(String l : list){
                     res.add(word + (l.equals("") ? "" : " ") + l);
                 }
             }
         }
         memo.put(start, res);
         return res;
     }

    // 3. DP O(n ^ 3)
    public List<String> wordBreak3(String s, List<String> wordDict) {
        Map<Integer, List<String>> memo = new HashMap<>();
        for(int end = 1; end < s.length(); end ++){
            memo.put(end, new ArrayList<>());
        }
        List<String> init = new ArrayList<>();
        init.add("");
        memo.put(0, init);
        for(int end = 1; end <= s.length(); end ++){
            List<String> list = new ArrayList<>();
            for(int start = 0; start < end; start ++){
                if(wordDict.contains(s.substring(start,end)) && !memo.get(start).isEmpty()){
                    for(String res : memo.get(start)){
                        list.add(res + " " + s.substring(start,end));
                    }
                }
            }
            memo.put(end, list);
        }
        return memo.get(s.length());
    }

    public static void main(String[] args) {
        WordBreak w = new WordBreak();
        List<String> dict = new ArrayList<>();

        dict.add("foo");
        dict.add("bar");
        System.out.println(w.longest("foobar", dict));
        dict.add("a");
        dict.add("b");
        dict.add("c");
        dict.add("cde");
        dict.add("de");
        dict.add("bcd");
        System.out.println(w.longest("abcde", dict));
    }
}
