package edu.neu.companies.quora;

import java.util.*;
import java.util.PriorityQueue;

public class ExamRoom {
    class Interval{
        int start;
        int end;
        int length;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
            if(start == -1){
                this.length = end;
            }else if(end == N){
                this.length = N - 1 - start;
            }else{
                this.length = Math.abs(end - start)/2;
            }
        }
    }

    PriorityQueue<Interval> q;
    int N;
    public ExamRoom(int N) {
        this.N = N;
        q = new PriorityQueue<>((a, b) -> (b.length == a.length?
                (a.start - b.start) : (b.length - a.length)));
        q.add(new Interval(-1 ,N));
    }
    // O(logN)
    public int seat() {
        int seat = 0;
        Interval i = q.poll();
        if(i.start == -1){
            seat = 0;
        } else if(i.end == N){
            seat = N - 1;
        } else{
            seat = (i.start + i.end)/2;
        }
        q.add(new Interval(i.start ,seat));
        q.add(new Interval(seat , i.end));
        return seat;
    }
    // O(N)
    public void leave(int p) {
        Interval prev = null, next = null;
        for(Interval i : q){
            if(i.start == p){
                next = i;
            }
            if(i.end == p){
                prev = i;
            }
            if(prev != null && next != null){
                break;
            }
        }
        q.remove(prev);
        q.remove(next);
        q.add(new Interval(prev.start , next.end));
    }

}
