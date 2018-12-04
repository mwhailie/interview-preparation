package edu.neu.algorithm.sort;

import java.util.Random;

public class Insertion {
    public static void insertionSort(int[] nums){
        for(int i = 1; i < nums.length; i ++){
//            int j = i - 1;
            for(int k = 0; k < 10; k ++){
                System.out.print(nums[k] + ", ");
            }
            System.out.println();
            for (int j = i; j > 0 && (nums[j]< nums[j-1]); j--) {
                swap(nums, j, j-1);
            }

        }
    }

    private static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[10];
        Random r = new Random();
        for(int i = 0; i < 10; i ++){
            nums[i] = r.nextInt(10);
        }
        insertionSort(nums);
        for(int i = 0; i < 10; i ++){
            System.out.print(nums[i] + ", ");
        }
    }
}
