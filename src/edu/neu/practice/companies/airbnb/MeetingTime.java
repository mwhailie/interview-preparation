package edu.neu.practice.companies.airbnb;

import java.util.*;

public class MeetingTime {
        class Point implements Comparable<Point> {
            int time;
            boolean isStart;
            Point(int time, boolean isStart) {
                this.time = time;
                this.isStart = isStart;
            }
            @Override
            public int compareTo(Point that) {
                if (this.time != that.time || this.isStart == that.isStart) {
                    return this.time - that.time;
                } else {
                    return this.isStart ? -1 : 1;
                }
            }
        }
        class Interval {
            int start;
            int end;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }
        public List<Interval> getAvailableIntervals(List<List<Interval>>
                                                            intervals, int k) {
            List<Interval> res = new ArrayList<>();
            List<Point> points = new ArrayList<>();
            for (List<Interval> intervalList : intervals) {
                for (Interval interval : intervalList) {
                    points.add(new Point(interval.start, true));
                    points.add(new Point(interval.end, false));
                }
            }
            Collections.sort(points);
            int count = 0;
            Integer availableStart = null;
            for (int i = 0; i < points.size(); i++) {
                Point point = points.get(i);
                if (point.isStart) {
                    count++;
                    if (availableStart == null && i == 0 && count <=
                            intervals.size() - k) {
                        availableStart = point.time;
                    } else if (availableStart != null && count ==
                            intervals.size() - k + 1) {
                        res.add(new Interval(availableStart, point.time));
                        availableStart = null;
                    }
                } else {
                    count--;
                    if (count == intervals.size() - k && i < points.size() - 1)
                    {
                        availableStart = point.time;
                    } else if (availableStart != null && i == points.size() - 1
                            && count <= intervals.size() - k) {
                        res.add(new Interval(availableStart, point.time));
                        availableStart = null;
                    }
                }
            }
            return res;
        }

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        List<Interval> merged = new ArrayList<>();
        for(Interval interval : intervals){
            if(merged.isEmpty() || interval.start > merged.get(merged.size() - 1).end){
                merged.add(interval);
            }else {
                merged.get(merged.size() - 1).end = Math.max(interval.end, merged.get(merged.size() - 1).end);
            }
        }
        return merged;
    }
}
