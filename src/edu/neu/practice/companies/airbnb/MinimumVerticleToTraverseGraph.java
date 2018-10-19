package edu.neu.practice.companies.airbnb;

import java.util.*;

public class MinimumVerticleToTraverseGraph {
    private void search(Map<Integer, Set<Integer>> map, int start, int curr,
                        Set<Integer> visited, Set<Integer> currVisited, Set<Integer> res){
        currVisited.add(curr);
        visited.add(curr);
        for(int neighbor : map.get(curr)){
            if(res.contains(neighbor) && neighbor != start ){
                res.remove(neighbor);
            }
            if(!currVisited.contains(currVisited)){
                search(map, start, neighbor, visited, currVisited, res);
            }
        }
    }
    public List<Integer> findMin(int[][] edges, int n){
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            map.put(i, new HashSet<>());
        }
        for(int[] edge : edges){
            map.get(edge[0]).add(edge[1]);
        }
        Set<Integer> res = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < n; i++){
            if(visited.contains(i)){
                continue;
            }
            search(map, i, i, visited, new HashSet<>(), res);
        }
        return new ArrayList<Integer> (res);
    }
}
