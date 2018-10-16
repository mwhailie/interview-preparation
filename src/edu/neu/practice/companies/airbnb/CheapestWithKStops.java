package edu.neu.practice.companies.airbnb;

import java.util.*;

public class CheapestWithKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] flight : flights){
            int u = flight[0], v = flight[1], w = flight[2];
            if(!map.containsKey(u)){
                map.put(u, new HashMap<>());
            }
            map.get(u).put(v, w);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                new Comparator<int[]>(){
                    @Override
                    public int compare(int [] i1, int [] i2){
                        return i1[0] - i2[0];
                    }
                });
        pq.add(new int[]{0, K + 1, src});
        while(!pq.isEmpty()){
            int[] flight = pq.poll();
            int cost = flight[0];
            int k = flight[1];
            int from = flight[2];
            if(from == dst){
                return cost;
            }
            if(k <= 0){
                continue;
            }

            Map<Integer, Integer> adj = map.getOrDefault(from, new HashMap<>());
            for (int to : adj.keySet()) {
                pq.add(new int[] {cost + adj.get(to), k - 1, to});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CheapestWithKStops c = new CheapestWithKStops();
        System.out.println(c.findCheapestPrice(3,new int[][] {{0,1,100},{1,2,100},{0,2,500}},0, 2, 1));
    }
}
