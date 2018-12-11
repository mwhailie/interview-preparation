package edu.neu.datastructure;

import java.util.*;

public class RateLimiter {
    /*
     * @param timestamp: the current timestamp
     * @param event: the string to distinct different event
     * @param rate: the format is [integer]/[s/m/h/d]
     * @param increment: whether we should increase the counter
     * @return: true or false to indicate the event is limited or not
     */

    Map<String, Map<Integer,Integer>> counter = new HashMap<>();

    public boolean isRatelimited(int timestamp, String event, String rate, boolean increment) {
        // write your code here
        if(!counter.containsKey(event)){
            counter.put(event, new HashMap<>());
            counter.get(event).put(timestamp, increment? 1 : 0);
            return false;
        }
        int period = 1;
        int max = Integer.parseInt(rate.split("/")[0]);
        char c = rate.split("/")[1].charAt(0);
        switch (c){
            case 's':
                period = 1;
                break;
            case 'm':
                period = 60;
                break;
            case 'h':
                period = 3600;
                break;
            case 'd':
                period = 3600 * 24;
                break;
        }

        int sum = 0;
        Map<Integer, Integer> bucket = counter.get(event);
        for(int ts : bucket.keySet())if(timestamp - ts < period){
            sum += bucket.get(ts);
        }
        if(sum >= max){
            return true;
        }
        if(increment){
            bucket.put(timestamp , bucket.getOrDefault(timestamp, 0) + 1);
            counter.put(event, bucket);
        }
        return false;
    }

    Map<String, List<Integer>> counters = new HashMap<>();

    public boolean isRatelimitedII(int timestamp, String event, String rate, boolean increment) {
        // write your code here
        if(!counters.containsKey(event)){
            counters.put(event, new ArrayList<>());
            if(increment){
                counters.get(event).add(timestamp);
            }
            return false;
        }
        int period = 1;
        int max = Integer.parseInt(rate.split("/")[0]);
        char c = rate.split("/")[1].charAt(0);
        switch (c){
            case 's':
                period = 1;
                break;
            case 'm':
                period = 60;
                break;
            case 'h':
                period = 3600;
                break;
            case 'd':
                period = 3600 * 24;
                break;
        }

        List<Integer> bucket = counters.get(event);
        int sum;
        sum = binarySearch(bucket, timestamp - period + 1);
        if(sum >= max){
            return true;
        }
        if(increment){
            bucket.add(timestamp);
            counters.put(event, bucket);
        }
        return false;
    }

    private int binarySearch(List<Integer> bucket, int target) {
        int start = 0, end = bucket.size() - 1, index = -1;
        if(bucket.isEmpty() || bucket.get(end) < target){
            return 0;
        }
        while(start <= end){
            int mid = start + (end - start)/2;
            if(bucket.get(mid) == target){
                index = mid;
                end = mid - 1;
            }else if(bucket.get(mid) > target) {
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }

        return bucket.size() - end;
    }

    public static void main(String[] args) {
        RateLimiter rl = new RateLimiter();
        System.out.println(rl.isRatelimitedII(1, "login", "3/m", true));
        System.out.println(rl.isRatelimitedII(11, "login", "3/m", true));
        System.out.println(rl.isRatelimitedII(21, "login", "3/m", true));
        System.out.println(rl.isRatelimitedII(30, "login", "3/m", true));
        System.out.println(rl.isRatelimitedII(65, "login", "3/m", true));
        System.out.println(rl.isRatelimitedII(300, "login", "3/m", true));

    }
}
