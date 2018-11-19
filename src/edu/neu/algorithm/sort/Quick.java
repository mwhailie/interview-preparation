package edu.neu.algorithm.sort;

import java.util.Random;

public class Quick {
    public void quickSort(int[] nums){
        sort(nums, 0, nums.length - 1);
    }
    public void sort(int[] nums, int lo, int hi){
        if(lo >= hi){
            return;
        }
        int pivot = partition(nums, lo, hi);
        sort(nums, lo , pivot - 1);
        sort(nums, pivot + 1, hi);
    }
    public int partition(int[] nums, int lo, int hi){
        int pivot = nums[lo];
        int i = lo  , j = hi + 1;
        while(true){
            while(nums[++i] < pivot){
                if(i == hi){
                    break;
                }
            }
            while(nums[--j] > pivot){
                if(j == lo){
                    break;
                }
            }
            if (i >= j) break;
            swap(nums, i , j);
        }
        swap(nums, lo, j);
        return j;
    }
    private static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[100];
        Random r = new Random();
        for(int i = 0; i < 100; i ++){
            nums[i] = r.nextInt(100);
        }
        Quick quick = new Quick();
        quick.quickSort(nums);
        for(int i = 0; i < 100; i ++){
            System.out.print(nums[i] + ", ");
        }
    }
}
