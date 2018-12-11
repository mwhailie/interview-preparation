package edu.neu.companies.nextcapital;

import edu.neu.companies.airbnb.MeetingTime;

import java.util.*;

public class Bike {
//    class Point implements Comparable<Point> {
//        int time;
//        boolean isStart;
//        Point(int time, boolean isStart) {
//            this.time = time;
//            this.isStart = isStart;
//        }
//        @Override
//        public int compareTo(Point that) {
//            if (this.time != that.time || this.isStart == that.isStart) {
//                return this.time - that.time;
//            } else {
//                return this.isStart ? -1 : 1;
//            }
//        }
//    }
    static class Interval{
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static int max(int pathLength, List<Interval> florists){
        if(pathLength == 0){
            return 0;
        }
        if(florists.size() <= 3){
            return florists.size();
        }
        Collections.sort(florists, (a, b) -> a.end - b.end);
        int count = 0;
        int end1 = 0, end2 = 0, end3 = 0;
        for(int i = 1; i < florists.size(); i++){
            if (florists.get(i).start >= end1 && florists.get(i).start >= end2 && florists.get(i).start >= end3) {
                if(end1 >= end2 && end1 >= end3){
                    end1 = florists.get(i).end;
                }else if(end2 >= end1 && end2 >= end3){
                    end2 = florists.get(i).end;
                }else{
                    end3 = florists.get(i).end;
                }
                count++;
            } else if (florists.get(i).start >= end1){
                end1 = florists.get(i).end;
                count++;
            }else if (florists.get(i).start >= end2){
                end2 = florists.get(i).end;
                count++;
            }else if (florists.get(i).start >= end3){
                end3 = florists.get(i).end;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<Interval> florist = new ArrayList<>();
        florist.add(new Interval(1, 10));
        florist.add(new Interval(1, 6));
        florist.add(new Interval(2, 8));
        florist.add(new Interval(3, 5));
        florist.add(new Interval(3, 5));
        System.out.println(max(9, florist));

    }
}
