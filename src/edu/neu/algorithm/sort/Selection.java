package edu.neu.algorithm.sort;

import java.util.Random;

public class Selection {

    public static void selectionSort(int[] nums){
        for(int i = 0; i < nums.length - 1; i ++){
            int min = i + 1;
            for(int j = i + 1;j < nums.length; j ++){
                if(nums[j] < nums[min]){
                    min = j;
                }
            }
            swap(nums, min ,i);
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
        selectionSort(nums);
        for(int i = 0; i < 100; i ++){
            System.out.print(nums[i] + ", ");
        }
    }
}
