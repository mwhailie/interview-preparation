package edu.neu.companies.quora;

public class FindPeak {
    //1. recursive
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        return helper(nums, 0, nums.length - 1);
    }
    public int helper(int[] nums, int start, int end){
        if(start == end){
            return start;
        }
        int mid = start + (end - start)/2;
        if(nums[mid] > nums[mid+1]){
            return helper(nums, start, mid);
        }else{
            return helper(nums, mid + 1, end);
        }
    }
    // 2.Iterative
//     public int findPeakElement(int[] nums) {
//         if(nums == null || nums.length == 0){
//             return 0;
//         }
//         int start = 0;
//         int end = nums.length - 1;
//         while(start + 1< end){
//             int mid = start + (end - start)/2;
//             if(nums[mid] > nums[mid+1]){
//                 end = mid;
//             }
//             else{
//                 start = mid + 1;
//             }
//         }

//         return nums[start] > nums[end] ? start : end;
//     }
}
