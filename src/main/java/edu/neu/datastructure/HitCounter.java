package edu.neu.datastructure;

import java.util.*;

public class HitCounter {

    int[] count;
    int[] timestamps;

    /** Initialize your data structure here. */
    public HitCounter() {
        count = new int[300];
        timestamps = new int[300];
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index =  (timestamp - 1) % 300;
        if(timestamps[index] != timestamp){
            timestamps[index] = timestamp;
            count[index] = 1;
        }else{
            count[index] ++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {

        int sum = 0;
        for(int i = 0;  i >= 0 && i < 300; i ++){
            if( timestamp - timestamps[i] < 300 ){
                sum += count[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        HitCounter h = new HitCounter();
        h.hit(1);
        h.hit(1);
        h.hit(1);
        h.hit(300);
        System.out.println(h.getHits(300));
        h.hit(300);
        System.out.println(h.getHits(300));
        h.hit(301);
        System.out.println(h.getHits(301));

    }
}
