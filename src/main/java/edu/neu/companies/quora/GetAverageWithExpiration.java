package edu.neu.companies.quora;

import java.util.*;
import java.util.List;

public class GetAverageWithExpiration {

    final static int HOUR = 60 * 60;
    Deque<Node> list;
    int sum;
    public GetAverageWithExpiration(){
        list = new LinkedList<>();
        sum = 0;
    }

    class Node{
        int value;
        int timestamp;

        public Node(int value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public void add(int val, int timestamp){
        sum += val;
        if(list.getLast().timestamp == timestamp) {
            list.getLast().value += val;
        }else {
            list.add(new Node(val, timestamp));
        }
    }

    public double getAverage(int timestamp){
        while(!list.isEmpty() && list.getFirst().timestamp + HOUR < timestamp){
            sum -= list.removeFirst().value;
        }
        return sum/(double) list.size();
    }
//
//    int[] vals = new int[3600];
//    int[] nums = new int[3600];
//    int[] timestamps = new int[3600];
//
//    public void add(int val, int timestamp){
//        int index =  (timestamp - 1) % 3600;
//        if(timestamps[index] != timestamp){
//            timestamps[index] = timestamp;
//            nums[index] = 1;
//            vals[index] = val;
//        }else{
//            nums[index] ++;
//            vals[index] += val;
//        }
//    }
//
//    public double getAverage(){
//        int sum = 0, num = 0;
//        for(int i = 0; i < 60; i ++){
//            if(new Date().getSeconds() - timestamps[i] < 3600){
//                sum += vals[i];
//                num += nums[i];
//            }
//        }
//        return sum/(double) num;
//    }
}
