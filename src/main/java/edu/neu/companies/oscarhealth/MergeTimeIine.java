package edu.neu.companies.oscarhealth;

import java.util.*;

public class MergeTimeIine {
    class Period{
        int startTime;
        int endTime;
        boolean status;

        public Period(int startTime, int endTime, boolean status) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.status = status;
        }
    }

    class Point{
        int time;
        boolean first;
        boolean status;
        public Point(int time, boolean first, boolean status){
            this.time = time;
            this.first = first;
            this.status = status;
        }
    }

    List<Period> merge(List<Period> periods1, List<Period> periods2){
        List<Point> points = new ArrayList<>();
        for(Period period : periods1){
            points.add(new Point(period.startTime, true, period.status));
        }
        for(Period period : periods2){
            points.add(new Point(period.startTime, false, period.status));
        }
        Collections.sort(points, (a,b)-> a.time - b.time);
        List<Period> res = new ArrayList<>();
        boolean status1 = false, status2 = false;
        int prev = Integer.MIN_VALUE;
        for(Point point : points){
            if(point.time != Integer.MIN_VALUE){
                res.add(new Period(prev, point.time, status1 && status2));
            }
            if(point.first){
                status1 = point.status;
            }else {
                status2 = point.status;
            }
            prev = point.time;
        }
        res.add(new Period(prev, Integer.MIN_VALUE, status1 && status2));
        return res;
    }
}
