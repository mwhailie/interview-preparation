package edu.neu.companies.linkedin;

import java.util.Arrays;

public class DistinctPair {
    public static int numberOfPair(int[] a, int k){
        Arrays.sort(a);
        int res = 0;
        int i = 0, j = a.length - 1;
        while(i < j){
            if(a[i] + a[j] == k){
                res++; i ++; j--;
            }else if(a[i] + a[j] < k){
                i ++;
            }else{
                j --;
            }
            while(i != 0 && a[i] == a[i - 1]){
                i ++;
            }
            while(j != a.length - 1 && a[j] == a[j + 1]){
                j --;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(numberOfPair(new int[]{1,1,46}, 47));
        System.out.println(numberOfPair(new int[]{1,1,1,461,46,1}, 47));
        System.out.println(numberOfPair(new int[]{1,44,22,4556}, 47));
    }
}
