package edu.neu.companies.airbnb;

import java.util.*;

public class IPtoCIDR {
    /*
     We start at
255.0.0.7 -> 11111111 00000000 00000000 00000111 (IP form -> Integer form) n=10
We get "255.0.0.7/32" because there are 0 leading zeroes, the range is 32- 0 bit shifted=32. We used 1 so n = 9
255.0.0.7 + 2^0 = 255.0.0.8 255.0.0.8 -> 11111111 00000000 00000000 00001000
We get "255.0.0.8/29". The number of leading zeros is 3 so we can use 2^3 digits from this range. We shifted 1 -> 2 -> 4 -> 8 three times so the range is 32-3 bits shifted=29. We used 2^3 =8 so n = 1.
255.0.0.8 + 2^3 = 255.0.0.16
255.0.0.16->11111111 00000000 00000000 00010000
We get "255.0.0.16/32".Theres 4 leading zero's but we only have n=1 remaining so we can only take 2^0 (maximum power of two less than n and less than 2^leading zeroes). the range is 32- 0 bit shifted =32. We used 2^0 so n=0.
255.0.0.16+2^0 = 255.0.0.17 but n=0 so we're done
1. Convert the IP string into an Integer

2. Greedily take the biggest block of IP Addresses at each step

3. At each step convert back into String Form
     */
    public List<String> ipToCIDR(String ip, int n) {
        long curr = ipToLong(ip);
        List<String> ans = new ArrayList();
        while (n > 0) {
            int maxBits = Long.numberOfTrailingZeros(curr);
            int maxAmount = 1<<maxBits;
            int bitVal = 1;
            int count = 0;
            while(bitVal< n && count< maxBits){
                bitVal<<=1;
                ++count;
            }
            if(bitVal>n){
                bitVal>>=1;
                --count;
            }
            ans.add(toString(curr,32-count));
            n-= bitVal;
            curr+=(bitVal);
        }
        return ans;
    }
    private long ipToLong(String ip) {
        long ans = 0;
        for (String x: ip.split("\\.")) {
            ans = 256 * ans + Integer.valueOf(x);
        }
        return ans;
    }
    private String toString(long number, int range){
        //convert every 8 into an integer
        final int WORD_SIZE = 8;
        StringBuilder sb = new StringBuilder();
        for(int i=3; i>=0; --i){
            sb.append(Long.toString(((number>>(i*WORD_SIZE))&255)));
            sb.append(".");
        }
        sb.setLength(sb.length()-1);
        sb.append("/");
        sb.append(Integer.toString(range));
        return sb.toString();
    }
    private String longToIP(long x) {
        System.out.println(x >> 24);
        System.out.println(x >> 16);
        System.out.println((x >> 16)% 256);
        return (x >> 24) + "."+ (x >> 16) % 256 + "."+ (x >> 8) % 256+ "."+ x % 256;
    }
    private int bitLength(long x) {
        if (x == 0) return 1;
        int ans = 0;
        while (x > 0) {
            x >>= 1;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        IPtoCIDR iPtoCIDR = new IPtoCIDR();
        System.out.println(iPtoCIDR.ipToCIDR("255.0.0.7", 10));
        System.out.println(iPtoCIDR.ipToCIDR("255.0.0.8", 10));
    }
}
