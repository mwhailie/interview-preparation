package edu.neu.companies.microsoft;

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        String res = "";
        int i = 0;
        Stack<Integer> nums = new Stack<>();
        Stack<String> substrings = new Stack<>();
        while(i < s.length()){
            if(Character.isDigit(s.charAt(i))){
                int count = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    count = count * 10 + (s.charAt(i ++) - '0');
                }
                nums.push(count);
                i --;
            }else if(s.charAt(i) == '['){
                substrings.push(res);
                res = "";
            }else if(s.charAt(i) == ']'){
                int repeat = nums.pop();
                // System.out.println("num " + num);
                String temp = substrings.pop();
                // System.out.println("substring " + substring);
                for(int j = 0; j < repeat; j ++){
                    temp = temp + res;
                }
                res = temp;
            }else{
                res = res + s.charAt(i);
            }
            i++;
        }
        return res;
    }
}
