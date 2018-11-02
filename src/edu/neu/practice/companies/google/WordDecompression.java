package edu.neu.practice.companies.google;

import java.util.*;

public class WordDecompression {
    /*
        一个string decompression的题。。不知道是不是原题反正没见过。。题目如下. 围观我们
        2[abc]3[a]c => abcabcaaac;     2[ab3[d]]2[cc] => abdddabdddcc
             输入                   输出
        一开始用了一个栈，写着写着嵌套的逻辑卡住了，最后用俩stack解决。。然后follow-up问的是不要输出string而是输出解压后的
        K-th character，主要也还是嵌套情况就从内到外把疙瘩解开以后再算。。然后我问俩问题就结束了。整体感觉妹子面试官人很nice 反应很快而且不是特别picky的
     */
    public static String wordDecompression(String s){
        Stack<Character> stack = new Stack();
        Stack<Integer> numStack = new Stack();
        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            System.out.print(c + " ");
            if(Character.isDigit(c)) {
                int repeat = c - '0';
                while(i + 1< s.length() && Character.isDigit(s.charAt(i + 1))){
                    i ++;
                    repeat = repeat * 10 + (s.charAt(i) - '0');
                }
                numStack.push(repeat);
            }else if(c != ']'){
                stack.push(c);
            }else {
                StringBuilder sb = new StringBuilder();
                while(stack.peek() != '['){
                    sb.append(stack.pop());
                }
                stack.pop();
                int repeat = numStack.pop();
                for(int j = 0; j < repeat; j ++){
                    for(int next = sb.length() - 1; next >= 0; next --){
                        stack.push(sb.charAt(next));
                    }
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
    public static String wordDecompressionII(String s){
        return dfs(s, "", 0, s.length() - 1);
    }
    public static String dfs(String s, String temp, int start, int end){
        if(start == s.length()){
            return new String(temp);
        }
        char c = s.charAt(start);
        if(c == ']'){
            return new String(temp);
        }
        for(int i = start; i < s.length(); i ++){
            c = s.charAt(i);
            int repeat = 1;
            if(Character.isDigit(c)){
                 repeat = c - '0';
                while(i + 1< s.length() && Character.isDigit(s.charAt(i + 1))){
                    i ++;
                    repeat = repeat * 10 + (s.charAt(i) - '0');
                }
            }
            String next = dfs(s, temp, i, end);
//            for(repeat){
//                temp +=
//            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(wordDecompression("2[abc]3[a]c"));
        System.out.println(wordDecompression("2[ab3[d]]2[cc]"));
    }
}
