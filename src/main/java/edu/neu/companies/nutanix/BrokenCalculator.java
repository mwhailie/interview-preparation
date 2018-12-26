package edu.neu.companies.nutanix;

import java.util.*;

public class BrokenCalculator {
    int minStep(int n, int m){
        if(n >= m){
            return n - m;
        }
        if(n - 1 == m || n * 2 == m){
            return 1;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        int step = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Integer curr = q.poll();
                if (visited.contains(curr)) {
                    continue;
                }
                visited.add(curr);
                if(curr == m){
                    return step;
                }
                if (curr - 1 < m && curr - 1 > 0) {
                    q.add(curr - 1);
                }
                if(curr < 10000){
                    q.add(curr * 2);
                }
            }
            step ++;
        }
        return m - n;
    }
}
