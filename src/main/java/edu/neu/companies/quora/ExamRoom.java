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
    static String electionWinner(String[] votes) {
        Map<String, Integer> map = new HashMap<>();
        List<String> candidates = new ArrayList<>();
        for(String vote : votes){
            if(!map.containsKey(vote)){
                candidates.add(vote);
            }
            map.put(vote, map.getOrDefault(vote, 0) + 1);
        }
        System.out.println(map);
        Collections.sort(candidates, ((a, b) -> (map.get(a) != map.get(b)? map.get(b) - map.get(a):a.compareTo(b) )));
        return candidates.get(0);

    }

    public static void main(String[] args) {
        System.out.println(electionWinner(new String[]{"Victor", "Victor", "Alex"}));
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
