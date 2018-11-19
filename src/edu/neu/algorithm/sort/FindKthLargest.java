package edu.neu.algorithm.sort;

public class FindKthLargest {
    public static int findKthLargest(int[] nums, int k){
        int start = 0, end = nums.length - 1;
        k = nums.length - k;

        while(start < end){
            int pivot = partition(nums, start, end);
            if(pivot == k){
                break;
            }else if(pivot > k){
                end = pivot - 1;
            }else{
                start = pivot + 1;
            }
        }
        return nums[k];
    }
    private static int partition(int[] nums, int lo, int hi){
        int i = lo, j = hi + 1;
        while(true){
            while(nums[++i] < nums[lo]){
                if(i == hi){
                    break;
                }
            }
            while(nums[--j] > nums[lo]){
                if(j == lo){
                    break;
                }
            }
            if(i >= j){
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, lo, j);
        return j;
    }
    private static void swap(int[] nums, int i, int j){
        int swap = nums[i];
        nums[i] = nums[j];
        nums[j] = swap;
    }

}
