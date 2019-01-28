package edu.neu.companies.microsoft;

public class BitManipulation {

//    https://leetcode.com/problems/counting-bits/description/
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            res[i] = res[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
        return res;
    }

    //https://leetcode.com/problems/reverse-bits/description/
    public int reverseBits(int n) {
        if(n==0) return 0;
        int result=0;
        for(int i=0;i<32;i++){
            result <<= 1;
            result = result|(n&1);
            n = n>>1;
        }
        return result;
    }
//    https://leetcode.com/problems/single-number/description/
    public int singleNumber(int[] nums) {
        int x = 0;
        for(int i : nums){
            x ^= i;
        }
        return x;
    }
}
