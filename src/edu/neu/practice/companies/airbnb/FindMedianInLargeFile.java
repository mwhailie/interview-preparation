package edu.neu.practice.companies.airbnb;

import java.util.ArrayList;

public class FindMedianInLargeFile {
    public static long findMedianInLargeFile(int[] nums, long k, long lo, long hi){
        if(lo >= hi){
            return lo;
        }
        int n = 0;
        long mid = lo + (hi - lo) / 2;
        for(int num: nums){
            if(num <= mid){
                n++;
            }
        }
        if(n == k){
            return mid;
        } else if(n < k){
            return findMedianInLargeFile(nums, k, mid + 1, hi);
        }else{
            return findMedianInLargeFile(nums, k, lo, mid - 1);
        }
    }
    public static double findMedianInLargeFile(int[] nums){
        int n = 0;
        for(int num: nums){
            n++;
        }
        if(n % 2 == 1){
            return (double)findMedianInLargeFile(nums, n/2+1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }else{
            return
                    (double)
                            (findMedianInLargeFile(nums, n/2,Integer.MIN_VALUE,Integer.MAX_VALUE)
                                    +findMedianInLargeFile(nums, n/2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE))/2;
        }
    }
    public static void main(String[] args) {
        int[] a = new int[]{ 1, 2, 3, 4, 5 ,6,7,8,9,10};

        System.out.println(findMedianInLargeFile(a));

        a = new int[]{ 1, 2, 3, 4, 5 ,6,7,8,9};
        System.out.println(findMedianInLargeFile(a));

    }
}
