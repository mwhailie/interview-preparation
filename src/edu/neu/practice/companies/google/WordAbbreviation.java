package edu.neu.practice.companies.google;

import java.util.*;

public class WordAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        dfs(new StringBuilder(), 0, word, res);
        for(String abb : res){
            System.out.print(abb+", ");
        }

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
    public  int minTimeToBuild1(String A, String B) {
        List<String> res = new ArrayList<>();
        dfs1(new String(), 0, B ,res);
        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        int time = 0;
        while(!A.isEmpty()){
            boolean findPrefix = false;
            for(String abb : res) {
                if (A.startsWith(abb)) {
                    System.out.println(A + " " + abb);
                    findPrefix = true;
                    time ++;
                    A = A.substring(abb.length());
                    break;
                }
            }
            if(!findPrefix){
                break;
            }
        }

        return A.isEmpty()? time : -1;
    }
    private void dfs1(String s, int start, String word, List<String> res){
        if(!s.isEmpty()){
            res.add(new String(s));
        }
        for(int i = start; i< word.length(); i++){
            dfs1(s + word.charAt(i), i + 1, word, res);
        }
    }
    public  int minTimeToBuild2(String a, String b) {
        int i = 0;
        int count = 0;
        while (i < a.length()) {
            int j = 0;
            int prev = i;
            while (i < a.length() && j < b.length()) {
                if (a.charAt(i) == b.charAt(j)) {
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
            if (prev == i) return -1;
            count++;
        }
        return count;
    }
    public static void main(String[] args) {
        WordAbbreviation wa = new WordAbbreviation();
//        wa.generateAbbreviations("word");
        System.out.println(wa.minTimeToBuild1("ABCABACAD","ABCD"));
        System.out.println(wa.minTimeToBuild2("ABCABACAD","ABCD"));
//        wa.generateAbbreviations("internationalization");
    }
}
