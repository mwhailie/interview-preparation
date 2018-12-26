package edu.neu.companies.quora;

import java.util.*;
import java.util.List;

public class GetAverageWithExpiration {

//    final static int HOUR = 60 * 60 * 1000;
//    Deque<Node> list;
//    int sum;
//    public GetAverageWithExpiration(){
//        list = new LinkedList<>();
//        sum = 0;
//    }
//
//    class Node{
//        int value;
//        long timestamp;
//
//        public Node(int value) {
//            this.value = value;
//            this.timestamp = System.currentTimeMillis();
//        }
//
//        boolean isExpired(){
//            return timestamp + HOUR > System.currentTimeMillis();
//        }
//    }
//
//    public void add(int val){
//        sum += val;
//        list.add(new Node(val));
//    }
//
//    public double getAverage(){
//        while(!list.isEmpty() && list.getFirst().isExpired()){
//            sum -= list.removeFirst().value;
//        }
//        return sum/(double) list.size();
//    }

    int[] vals = new int[3600];
    int[] nums = new int[3600];
    int[] timestamps = new int[3600];

    public void add(int val, int timestamp){
        int index =  (timestamp - 1) % 3600;
        if(timestamps[index] != timestamp){
            timestamps[index] = timestamp;
            nums[index] = 1;
            vals[index] = val;
        }else{
            nums[index] ++;
            vals[index] += val;
        }
    }

    public double getAverage(){
        int sum = 0, num = 0;
        for(int i = 0; i < 60; i ++){
            if(new Date().getSeconds() - timestamps[i] < 3600){
                sum += vals[i];
                num += nums[i];
            }
        }
        return sum/(double) num;
    }
}
