package edu.neu.companies.quora;

import java.util.*;

public class MaxInDataStream {
    int size;
    LinkedList<Integer> list;
    int sum;
    PriorityQueue<Integer> pq;

    /** Initialize your data structure here. */
    public MaxInDataStream(int size) {
        this.size = size;
        this.list = new LinkedList<>();
        this.pq = new PriorityQueue<>();
        sum = 0;
    }

    public double next(int val) {
        list.add(val);
        sum += val;
        if(list.size() > size){
            int remove = list.removeFirst();
            sum -= remove;
        }
        return sum / (double) list.size();
    }

    public double nextMax(int val) {
        list.add(val);
        pq.add(val);
        if(list.size() > size){
            int remove = list.removeFirst();
            pq.remove(remove);
        }
        return pq.peek();
    }
}
