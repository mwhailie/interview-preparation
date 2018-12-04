package edu.neu.companies.twitter;

import java.util.Arrays;

public class TwitterClosest {
    static public int[] closest (String input, int[] indexs){
        if(input == null || input.length() == 0){
            return new int[0];
        }
        int[] result = new int[indexs.length];
        Arrays.fill(result, -1);
        int n = indexs.length;
        char[] cs = input.toCharArray();
        for(int i = 0; i < n; i++) {
            int index = indexs[i];
            char target = cs[index];
            int left = index - 1, right = index + 1;
            while (left >= 0 || right < cs.length) {
                if (left >= 0 && cs[left] == target) {
                    result[i] = left;
                    break;
                }
                if (right < cs.length && cs[right] == target) {
                    result[i] = right;
                    break;
                }
                left--;
                right++;
            }
        }
        return result;
    }
    public static void main(String[] args){
        int[] res = closest("hackerrank", new int[]{0,1,2,3,4,5,6,7,8,9});
        for(int i :res){
            System.out.print(i + ", ");
        }
    }
}
