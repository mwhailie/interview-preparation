package edu.neu.companies.twilio;

import java.util.*;

public class KSubsequence {

    long kSub(int k, int[] nums){

        long mod[] = new long[k];

        // Traverse original array and compute cumulative
        // sum take remainder of this current cumulative
        // sum and increase count by 1 for this remainder
        // in mod[] array
        long cumSum = 0;
        for (int i = 0; i < nums.length; i++) {
            cumSum += nums[i];
            int index = (int)((cumSum % k) + k) % k;
            // as the sum can be negative, taking modulo twice
            mod[index]++;
        }
        // add the elements which are divisible by k itself
        // i.e., the elements whose sum = 0
        long result = mod[0];

        // Traverse mod[]
        for (int i = 0; i < k; i++) {
            // If there are more than one prefix subarrays
            // with a particular mod value.
            if (mod[i] > 1)
                result += (mod[i] * (mod[i] - 1)) / 2;
        }


        return result;
    }
}
