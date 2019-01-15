package edu.neu.companies.microsoft;

public class LongestValidParathese {
    // 1. Stack
    // public int longestValidParentheses(String s) {
    //     Stack<Integer> stack = new Stack<>();
    //     stack.push(-1);
    //     int max = 0;
    //     for(int i = 0; i < s.length(); i ++){
    //         char c = s.charAt(i);
    //         if(c == '('){
    //             stack.push(i);
    //         }else{
    //             stack.pop();
    //             if(stack.isEmpty()){
    //                 stack.push(i);
    //             }else{
    //                 max = Math.max(max, i - stack.peek());
    //             }
    //         }
    //     }
    //     return max;
    // }
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        int count = 0;
        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            if(c == '('){
                count ++;
            }else if(count > 0){
                dp[i] = dp[i - 1] + 2;
                if(i - dp[i]> 0){
                    dp[i] += dp[i - dp[i]];
                }
                max = Math.max(max, dp[i]);
                count --;
            }
        }
        return max;
    }
}
