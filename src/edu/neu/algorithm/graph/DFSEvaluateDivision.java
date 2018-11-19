package edu.neu.algorithm.graph;

import java.util.*;

public class DFSEvaluateDivision {
//    O(min(M * N, N + V^2 + M))
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        Map<String, Map<String, Double>> graph = new HashMap<>();
        for(int i = 0; i < equations.length; i ++){
            if(!graph.containsKey(equations[i][0])){
                graph.put(equations[i][0],new HashMap<>());
            }
            if(!graph.containsKey(equations[i][1])){
                graph.put(equations[i][1],new HashMap<>());
            }
            graph.get(equations[i][0]).put(equations[i][1], values[i]);
            graph.get(equations[i][1]).put(equations[i][0], 1/ values[i]);

        }
        double[] results = new double[queries.length];
        for(int i = 0; i < queries.length; i ++){
            String[] query = queries[i];
            if(!graph.containsKey(query[0]) || !graph.containsKey(query[1])){
                results[i] = (-1.0);
            }
            Set<String> visited = new HashSet<>();
            results[i] = dfs(graph, query[0], query[0], query[1], 1.0, visited);
        }
        return results;
    }
    double dfs(Map<String, Map<String, Double>> graph, String start, String cur, String to,
               double ratio, Set<String> visited){
        System.out.println(cur);
        visited.add(cur);
        if (graph.get(cur).containsKey(to)) {
            return ratio * graph.get(cur).get(to);
        }
        Map<String, Double> neighbors = graph.get(cur);
        for(String neighbor : neighbors.keySet()){
            if(!visited.contains(neighbor)){
                double newRatio = ratio * neighbors.get(neighbor);
                //M极大时，构建图
                graph.get(start).put(neighbor, newRatio);
                graph.get(neighbor).put(start, 1 / newRatio);
                double result = dfs(graph, start, neighbor, to, newRatio
                        , visited);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }


}
