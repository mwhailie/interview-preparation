package edu.neu.practice.algorithm.graph;

import java.util.*;

public class Bipartite {
    public boolean isBipartite(int[][] graph) {
        List<List<Integer>> map = new ArrayList<>();
        for(int v = 0; v < graph.length; v++){
            map.add(new ArrayList<>());
        }
        for(int v = 0; v < graph.length; v++){
            for(int w : graph[v]){
                map.get(w).add(v);
                map.get(v).add(w);
            }
        }
        int n = map.size();
        Stack<Integer> cycle = null;  // odd-length cycle
        boolean[] isBipartite = new boolean[1];
        isBipartite[0] = true;
        boolean[] visited = new boolean[n];
        int[] edgeTo = new int[n];
        boolean[] color = new boolean[n];
        for(int v = 0; v < graph.length; v++){
            if(!visited[v]){
                dfs(map, visited, color, edgeTo, v, isBipartite, cycle);
            }
        }

        return isBipartite[0];
    }
    public void dfs(List<List<Integer>> map, boolean[] visited, boolean[] color, int[] edgeTo, int v,
                    boolean[] isBipartite, Stack<Integer> cycle){

        visited[v] = true;
        if (cycle != null) return;
        for(int w : map.get(v)){
            if(!visited[w]){
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(map, visited, color, edgeTo, w, isBipartite, cycle);
            }else if(color[v] == color[w]){
                isBipartite[0] = false;
                cycle = new Stack<Integer>();
                cycle.push(w);  // don't need this unless you want to include start vertex twice
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }
}
