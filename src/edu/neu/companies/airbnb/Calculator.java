package edu.neu.companies.airbnb;

import java.util.*;

public class Calculator {
    public int calculate(String s) {
        if(s == null || s.isEmpty()){
            return 0;
        }
        int sign = 1;
        int sum = 0;
        int temp = 0;
        Stack<Integer> stack = new Stack();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                temp = temp * 10 + (c - '0');
            }else if(c == '+'){
                sum += sign * temp;
                sign = 1;
                temp = 0;
            }else if(c == '-'){
                sum += sign * temp;
                sign = -1;
                temp = 0;
            }else if(c == '('){
                stack.push(sum);
                stack.push(sign);
                sign = 1;
                sum = 0;
            }else if(c == ')'){

                sum += sign * temp;
                temp = 0;
                sum *= stack.pop();
                sum += stack.pop();
            }
        }
        while(!stack.isEmpty()){
            sum += stack.pop();
        }
        if(temp != 0) sum += sign * temp;
        return sum;
    }
    public int calculate2(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        char[] cs = s.toCharArray();
        for(int i = 0; i < cs.length; i ++){
            char c = cs[i];
            if(Character.isDigit(c)){
                num = num * 10 + (c - '0');
            }
            if (!Character.isDigit(c) && c != ' ' || i == cs.length - 1){
                if(sign=='-'){
                    stack.push(-num);
                }
                if(sign=='+'){
                    stack.push(num);
                }
                if(sign=='*'){
                    stack.push(stack.pop()*num);
                }
                if(sign=='/'){
                    stack.push(stack.pop()/num);
                }
                sign = c;
                num = 0;
            }
        }
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}
