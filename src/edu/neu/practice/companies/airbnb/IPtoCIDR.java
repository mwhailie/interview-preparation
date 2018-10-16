package edu.neu.practice.companies.airbnb;

import java.util.*;

public class IPtoCIDR {
    public List<String> ipToCIDR(String ip, int n) {
        long start = ipToLong(ip);
        List<String> ans = new ArrayList();
        while (n > 0) {
            int mask = Math.max(33 - bitLength(Long.lowestOneBit(start)),
                    33 - bitLength(n));
            ans.add(longToIP(start) + "/" + mask);
            start += 1 << (32 - mask);
            n -= 1 << (32 - mask);
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
