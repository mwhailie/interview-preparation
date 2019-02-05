package edu.neu.companies.twilio;

import java.util.HashMap;
import java.util.*;


public class TopK {

    List<Integer> findTopK(int[] nums, int k){
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->freq.get(a) - freq.get(b));
        for(int key : freq.keySet()){
            pq.offer(key);
            if(pq.size() > k){
                pq.poll();
            }
        }
        List<Integer> res = new ArrayList<>(pq);
        return res;
    }

    List<Integer> findTopKII(int[] nums, int k){
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] bucket = new ArrayList[nums.length];
        for(int key : freq.keySet()){
            int frequency = freq.get(key);
            if(bucket[frequency] == null){
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for(int i = bucket.length - 1; i >= 0 && k > 0; i --) if(bucket[i] != null){
            res.addAll(bucket[i]);
            k -= bucket[i].size();
        }
        return res;
    }

    public static void main(String[] args) {
        TopK t = new TopK();
        System.out.println(t.findTopK(new int[] {1,8,9,8,5,8,1,0,3,4,1,3,11,9,1, 0}, 5));
        System.out.println(t.findTopKII(new int[] {1,8,9,8,5,8,1,0,3,4,1,3,11,9,1, 0}, 5));
    }
}
