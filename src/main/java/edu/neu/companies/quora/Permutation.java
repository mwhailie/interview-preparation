package edu.neu.companies.quora;

public class Permutation {
    public void prevPermutaion(int[] nums){
        // Find index of the last element of the string
        int n = nums.length - 1;

        // Find largest index i such that str[i - 1] > str[i]
        int i = n;
        while (i > 0 && nums[i - 1] <= nums[i]) {
            i--;
        }
        //acsending order, no previous permutation
        if (i <= 0)
            return;
        int j = i - 1;

        // Find rightmost element's index that is less than nums[i - 1]
        while (j + 1 <= n && nums[j + 1] <= nums[i - 1]) {
            j++;
        }
        swap(nums, i - 1, j);
        reverse(nums, i, nums.length - 1);
    }


    public void nextPermutation(int[] nums) {
        int i = nums.length - 2 ;
        while( i>= 0 && nums[i] >= nums[i + 1]){
            i--;
        }
        //descending, last permulation, no next permulation
        if(i <= 0){
            return;
        }
        // Find rightmost element's index that is more than nums[i]
        int j =  nums.length - 1;
        while(j >= 0 && nums[j] <= nums[i]){
            j--;
        }
        swap(nums,i, j);
        reverse(nums, i + 1,  nums.length - 1);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i, int j) {
        while(i < j){
            swap(nums,i++, j --);
        }
    }
}
