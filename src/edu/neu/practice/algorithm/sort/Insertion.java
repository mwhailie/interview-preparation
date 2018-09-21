package edu.neu.practice.algorithm.sort;

import java.util.Random;

public class Insertion {
    public static void insertionSort(int[] nums){
        for(int i = 1; i < nums.length; i ++){
            int j = i - 1;
            while(j >= 0 && nums[j] > nums[i]){
                swap(nums, j-- ,i--);
            }
        }
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
        insertionSort(nums);
        for(int i = 0; i < 100; i ++){
            System.out.print(nums[i] + ", ");
        }
    }
}
