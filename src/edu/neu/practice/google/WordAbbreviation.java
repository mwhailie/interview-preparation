package edu.neu.practice.google;

import java.util.*;

public class WordAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        dfs(new StringBuilder(), 0, word, res);
        return res;
    }
    private void dfs(StringBuilder sb, int start, String word, List<String> res){

        if(start == word.length()){
            res.add(sb.toString());
            return;
        }
        sb.append(word.charAt(start));
        dfs(sb, start + 1, word, res);
        sb.delete(sb.length() - 1, sb.length());
        for(int i = start; i< word.length(); i++){
            if(start == 0 || !Character.isDigit(sb.charAt(sb.length() - 1))){
                String len = "" + (i - start + 1);
                sb.append(len);
                dfs(sb, i + 1, word, res);
                sb.delete(sb.length() - len.length(), sb.length());
            }
        }
    }

    private void dfs(String s, int start, String word, List<String> res){
        if(start == word.length()){
            res.add(new String(s));
            return;
        }
        dfs(s + word.charAt(start), start + 1, word, res);
        for(int i = start; i< word.length(); i++){
            if(start == 0 || !Character.isDigit(s.charAt(s.length() - 1))){
                dfs(s + (i - start + 1), i + 1, word, res);
            }
        }
    }

    public static void main(String[] args) {
        WordAbbreviation wa = new WordAbbreviation();
        wa.generateAbbreviations("word");
        wa.generateAbbreviations("internationalization");
    }
}
