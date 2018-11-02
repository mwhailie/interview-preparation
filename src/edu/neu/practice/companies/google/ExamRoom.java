package edu.neu.practice.companies.google;

import java.util.PriorityQueue;

public class ExamRoom {
    int N;
    PriorityQueue<Interval> pq;

    public ExamRoom(int N){
        this.N = N;
        pq = new PriorityQueue<>((a, b) -> (b.end - b.start) == (a.end - a.start)?
                a.start - b.start : (b.end - b.start) - (a.end - a.start));
        pq.offer(new Interval(-1, N + 1));
    }

    int seat(){
        int seat = 0;
        Interval largestEmptyInterval = pq.poll();
        if(largestEmptyInterval.start == - 1){
            seat = 0;
        }else if(largestEmptyInterval.end == N + 1) {
            seat = N;
        }
        pq.offer(new Interval(largestEmptyInterval.start, seat));
        pq.offer(new Interval(seat, largestEmptyInterval.end));
        return seat;
    }

    void leave(int p){
        Interval prev = null;
        Interval next = null;
        for(Interval i : pq){
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
        pq.remove(prev);
        pq.remove(next);
        pq.offer(new Interval(prev.start, next.end));

    }

    private class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
