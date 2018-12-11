package edu.neu.companies.nutanix;

import java.util.*;

public class CircularArray {
    int N;
    public int circularArrayLoop(int[] nums) {
        N = nums.length;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            //self loop / visited
            if(nums[i] % N == 0){
                continue;
            }

            int currIndex = i;

            List<Integer> visitedIndex = new ArrayList<>();

            //The loop must be "forward" or "backward'.
            while(nums[currIndex] * nums[i] > 0){
                if(visitedIndex.contains(currIndex)){
                    count ++;
                    System.out.println(visitedIndex);
                    break;
                }
                visitedIndex.add(currIndex);
                currIndex = next(currIndex, nums);
            }
            //mark visited
            currIndex = i;
            while (nums[currIndex] * nums[i] > 0) {
                nums[currIndex] = 0;
                currIndex = next(currIndex, nums);
            }
        }

        return count;
    }
    public int next(int i, int[] nums) {
        return i + nums[i] >= 0 ? (i + nums[i]) % N : N + ((i + nums[i]) % N);
    }

    public static void main(String[] args) {
        CircularArray ca = new CircularArray();
        System.out.println(ca.circularArrayLoop(new int[] {2, -1, 1, 2, 2}));
        System.out.println(ca.circularArrayLoop(new int[] {2, 3, 1, 2, 2}));
        System.out.println(ca.circularArrayLoop(new int[] {-1, 2}));//self loop

    }
}
