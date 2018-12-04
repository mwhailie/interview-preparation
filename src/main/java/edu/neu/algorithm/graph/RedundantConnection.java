package edu.neu.algorithm.graph;

import java.util.*;

public class RedundantConnection {
    public int[] findRedudant(int[][] edges){
        List<int[]> candidates = new ArrayList<>();
        int[] root = new int[edges.length * 2];
        for(int[] edge : edges){
            if(root[edge[1]] != 0){
                candidates.add(new int[]{root[edge[1]], edge[1]});
                candidates.add(new int[]{edge[0], edge[1]});
            }else{
                root[edge[1]] = edge[0];
            }
        }
        for (int i = 0; i < root.length; i++){
            root[i] = i;
        }
        for (int[] edge : edges){
            int parent = edge[0], child = edge[1];
            if(find(parent, root) == child){
                if(candidates.isEmpty()){
                    return edge;
                }else{
                    return candidates.get(0);
                }
            }
            root[child] = parent;
        }
        return candidates.get(1);
    }

    private int find(int i, int[] root) {
        while(root[i] != i){
            i = root[i];
        }
        return i;
    }
}
