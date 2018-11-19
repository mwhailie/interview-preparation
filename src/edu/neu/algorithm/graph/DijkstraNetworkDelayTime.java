package edu.neu.algorithm.graph;

import java.util.*;

public class DijkstraNetworkDelayTime {
        public int networkDelayTime(int[][] times, int N, int K) {
            List<List<int[]>> graph = new ArrayList<>();
            for(int i = 0; i <= N; i ++){
                graph.add(new ArrayList<>());
            }
            for(int[] time : times){
                graph.get(time[0]).add(new int[]{time[2], time[1]});
            }
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
                @Override
                public int compare(int[] i1, int[] i2){
                    return i1[0] - i2[0];
                }
            });
            Map<Integer, Integer> distTo = new HashMap<>();
            pq.offer(new int[]{0, K});
            while(!pq.isEmpty()){
                int[] now = pq.poll();
                int dist = now[0];
                int node = now[1];
                System.out.print("edge : [dist :" +dist+ ", node: " + node +"] ");
                System.out.print("distTo.containsKey(" +node+ ")= " + distTo.containsKey(node));
                if(distTo.containsKey(node)){
                    continue;
                }
                distTo.put(node, dist);
                System.out.println("   distTo.put(" + node + ", " +dist+ ")");
                for(int[] edge : graph.get(node)){
                    if(!distTo.containsKey(edge[1])){
                        pq.offer(new int[]{edge[0] + dist, edge[1]});
                    }
                }
            }
            if(distTo.size() != N){
                return -1;
            }
            int max = Integer.MIN_VALUE;
            for(int i = 1; i <= N; i ++){
                System.out.println("distTo: " + i + "---" + distTo.get(i));
                max = Math.max(max, distTo.get(i));
            }
            return max;
        }

    public static void main(String[] args) {
        DijkstraNetworkDelayTime d = new DijkstraNetworkDelayTime();
        int[][] times = new int[3][3];
        times[0] = new int[]{1, 2, 5};
        times[1] = new int[]{1, 3, 10};
        times[2] = new int[]{2, 3, 4};

        System.out.println(d.networkDelayTime(times, 3, 1));

        times = new int[3][3];
        times[0] = new int[]{2, 1, 1};
        times[1] = new int[]{2, 3, 1};
        times[2] = new int[]{3, 4, 1};

        System.out.println(d.networkDelayTime(times, 4, 2));

    }
}
