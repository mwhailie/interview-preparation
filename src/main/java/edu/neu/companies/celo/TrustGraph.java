package edu.neu.companies.celo;

import java.util.*;

public class TrustGraph {
    boolean checkTrust(int node, int[][] trustGraph, int[] pretrustedPeers, int trustThreshold){
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((i1, i2)-> i1[0] - i2[0]);

        Map<Integer, Integer> trustTo = new HashMap();

        for(int pretrustedPeer : pretrustedPeers){
            pq.offer(new int[]{0, pretrustedPeer});
            trustTo.put(pretrustedPeer, 0);
        }
        if(trustTo.containsKey(node)){
            return true;
        }
        while(!pq.isEmpty()){
            int[] edge = pq.poll();
            int trust = edge[0];
            int from = edge[1];
            for(int to = 0; to < trustGraph.length; to++) {
                if (from == to || trustGraph[from][to] == 0 || trustGraph[from][to] > trustThreshold) {
                    continue;
                }
                int newTrust = trust + trustGraph[from][to];
//                if(newTrust > trustThreshold){
//                    continue;
//                }
                if(!trustTo.containsKey(to) || trustTo.get(to) > newTrust){
                    pq.offer(new int[]{newTrust, to});
                    trustTo.put(to, newTrust);
                }
            }
        }
        return trustTo.containsKey(node) && trustTo.get(node) < trustThreshold;
    }

//    public static void main(String[] args) {
//        TrustGraph trustGraph = new TrustGraph();
//        System.out.println(trustGraph.checkTrust());
//    }
}
