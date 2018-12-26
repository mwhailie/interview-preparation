package edu.neu.companies.quora;

import java.util.*;

public class MeetingRoom {
    static class Interval{
        int start;
        int end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    static class Point{
        int time;
        boolean isStart;
        public Point(int time, boolean isStart){
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public String toString() {
            return (isStart ? "+":"-" )+ time;
        }
    }

    public static int numOfMeeting(int time, List<Interval> meetings){
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        for(Interval meeting:  meetings){
            pq.add(new Point(meeting.start, true));
            pq.add(new Point(meeting.end, false));
        }

        TreeMap<Integer, Integer> buckets= new TreeMap<>();
        Point start = pq.poll();
        if(time < start.time){
            return 0;
        }
        buckets.put(start.time, 1);
        while(!pq.isEmpty()){
            Point p = pq.poll();
            if(p.isStart){
                buckets.put(p.time, buckets.getOrDefault(p.time, buckets.lastEntry().getValue()) + 1);
            }else{
                buckets.put(p.time, buckets.getOrDefault(p.time, buckets.lastEntry().getValue()) - 1);
            }
        }
        return buckets.ceilingEntry(time).getValue();
    }
    public static int numOfMeetingII(int time, List<Interval> meetings){
        List<Point> points = new ArrayList<>();
        for(Interval meeting:  meetings){
            points.add(new Point(meeting.start, true));
            points.add(new Point(meeting.end, false));
        }
        Collections.sort(points, (a, b) -> a.time - b.time);
        TreeMap<Integer, Integer> buckets= new TreeMap<>();
        for(int i = 0; i < points.size(); i ++){
            Point p = points.get(i);
            if(p.isStart){
                buckets.put(p.time, buckets.getOrDefault(p.time, buckets.lastEntry().getValue()) + 1);
            }else{
                buckets.put(p.time, buckets.getOrDefault(p.time, buckets.lastEntry().getValue()) - 1);
            }
        }
        return buckets.ceilingKey(time) == null? 0 : buckets.get(buckets.ceilingKey(time));
    }

    //O(nlogn)
    public static int numOfMeetingIII(int time, List<Interval> meetings){
        List<Point> points = new ArrayList<>();
        for(Interval meeting:  meetings){
            points.add(new Point(meeting.start, true));
            points.add(new Point(meeting.end, false));
        }
        Collections.sort(points, (a, b) -> a.time - b.time);
        int count = 0;
        for(int i = 0; i < points.size(); i ++){
            Point p = points.get(i);
            if(p.time > time){
                break;
            }
            if(p.isStart){
                count ++;
            }else{
                count --;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<Interval> meetings = new ArrayList<>();
        meetings.add(new Interval(1, 5));
        meetings.add(new Interval(4, 9));
        meetings.add(new Interval(2, 3));
        meetings.add(new Interval(3, 10));
        meetings.add(new Interval(9, 10));
        for(int i = 0; i <= 10; i ++ ){
            System.out.print(numOfMeeting(i, meetings) + ", ");
        }
        System.out.println();
        for(int i = 0; i <= 10; i ++ ){
            System.out.print(numOfMeetingIII(i, meetings) + ", ");
        }
    }
}
