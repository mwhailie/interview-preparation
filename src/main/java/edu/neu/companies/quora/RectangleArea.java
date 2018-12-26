package edu.neu.companies.quora;

import java.util.*;
import java.util.List;

public class RectangleArea {
    class Interval{
        int x1;
        int x2;
        int y;
        boolean isLeft;

        public Interval(int x1, int x2, int y, boolean isLeft) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.isLeft = isLeft;
        }
    }

    public int rectangleUnion(int[][] rectangles){
        if(rectangles.length == 1){

        }
        if(rectangles.length == 2){
            int[] rec1 = rectangles[0];
            int[] rec2 = rectangles[1];
            int left = Math.max(rec1[0],rec2[0]), right = Math.max(Math.min(rec1[2],rec2[2]), left);
            int bottom = Math.max(rec1[1],rec2[1]), top = Math.max(Math.min(rec1[3],rec2[3]), bottom);
            return (rec1[2]-rec1[0])*(rec1[3]-rec1[1])
                    + (rec2[2]-rec2[0])*(rec2[3]-rec2[1])
                    - (right-left)*(top-bottom) ;
        }
        List<Interval> intervals = new ArrayList<>();
        for(int[]rectangle: rectangles){
            intervals.add(new Interval(rectangle[0],rectangle[3],rectangle[0], true));
            intervals.add(new Interval(rectangle[0],rectangle[3],rectangle[1], false));
        }
        Collections.sort(intervals, (a,b)-> a.y == b.y ? a.x1 - b.x1 : a.y - b.y);
        List<Interval> actives = new ArrayList<>();
        int ans = 0;
        int pre_y = intervals.get(0).y;
        for(Interval interval : intervals){
            int query = 0;
            int cur_y = interval.y;
            int cur_x = - 1;
            for(int i = 1; i < actives.size(); i ++){
                cur_x = Math.max(cur_x, actives.get(i).x1 );
                query += Math.max(actives.get(i).x2 - cur_x, 0);
                cur_x = Math.max(cur_x, actives.get(i).x2);
            }
            ans += (cur_y - pre_y) * query;
            if(interval.isLeft){
                actives.add(interval);
                Collections.sort(actives, (a,b)-> a.x1 != b.x1 ? a.x1 - b.x1 : a.x2 - b.x2);
            }else{
                for(Interval active : actives){
                    if(active.x1 == interval.x1 && active.x2 == interval.x2 && active.y == interval.y){
                        actives.remove(active);
                        break;
                    }
                }
            }
            pre_y = cur_y;
        }
        return ans;
    }
}
