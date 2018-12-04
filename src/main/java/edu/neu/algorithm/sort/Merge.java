package edu.neu.algorithm.sort;

import java.util.Random;

public class Merge {
    public void mergeSort(int[] nums){
        int[] aux = new int[nums.length];
        sort(nums, aux, 0, nums.length - 1);
    }
    public void sort(int[] nums, int[] aux, int lo, int hi){
        if(lo >= hi){
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort(nums, aux, lo, mid);
        sort(nums, aux, mid + 1, hi);
        merge(nums, aux, lo, mid, hi);
    }
    public void merge(int[] nums, int[] aux, int lo, int mid, int hi){
        int i = lo, j = mid + 1;

//        System.out.println();
        for(int k = lo; k <= hi; k ++){
            aux[k] = nums[k];
//            System.out.print(nums[k] + ", ");
        }
        for(int k = lo; k <= hi; k ++){
            if(i > mid){
                nums[k] = aux[j ++];
            }else if(j > hi){
                nums[k] = aux[i ++];
            }else if(aux[j] > aux[i]){
                nums[k] = aux[i ++];
            }else{
                nums[k] = aux[j ++];
            }
        }
//        System.out.println();
//        for(int k = lo; k <= hi; k ++){
//            System.out.print(nums[k] + ", ");
//        }
    }
    public static void main(String[] args) {
        int[] nums = new int[100];
        Random r = new Random();
        for(int i = 0; i < 100; i ++){
            nums[i] = r.nextInt(100);
        }
        Merge merge = new Merge();
        merge.mergeSort(nums);
        for(int i = 0; i < 100; i ++){
            System.out.print(nums[i] + ", ");
        }
    }
}
