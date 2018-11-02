package edu.neu.practice.companies.google;

import java.util.*;

public class EvaluationDivision {

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
        int n = graph.size();
        double[] results = new double[queries.length];
        for(int i = 0; i < queries.length; i ++){
            String[] query = queries[i];
            if(!graph.containsKey(query[0]) || !graph.containsKey(query[1])){
                results[i] = (-1.0);
            }else{
                Set<String> visited = new HashSet<>();
                results[i] = dfs(graph, query[0], query[1], 1.0, visited);
            }
        }
        return results;
    }
    private double dfs(Map<String, Map<String, Double>> graph, String from, String to,
                       double ratio, Set<String> visited){
        // System.out.println(from);
        visited.add(from);
        if (graph.get(from).containsKey(to)) {
            return ratio * graph.get(from).get(to);
        }
        Map<String, Double> neighbors = graph.get(from);
        for(String neighbor : neighbors.keySet()){
            if(!visited.contains(neighbor)){
                double newRatio = ratio * neighbors.get(neighbor);
                double result = dfs(graph, neighbor, to, newRatio , visited);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }
    /*
        queries - M
        Source -> N
        Variable -> V

        M * (V + N)
        O(N + V^2 + M)
        BFS/DFS -> O(V + E)

        O(min(M * N, N + V^2 + M))

     */
    class Source {
        String a;
        String b;
        double r;
    }

    class Query {
        String a;
        String b;
    }

    List<Double> solution(List<Source> sources, List<Query> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for(Source source :  sources){
            if(!graph.containsKey(source.a)){
                graph.put(source.a, new HashMap<>());
            }
            if(!graph.containsKey(source.b)){
                graph.put(source.b, new HashMap<>());
            }
            graph.get(source.a).put(source.b, source.r);
            graph.get(source.b).put(source.a, 1/source.r);
        }
        int n = graph.size();
        List<Double> results = new ArrayList<>();
        for(Query query : queries){
            if(!graph.containsKey(query.a) || !graph.containsKey(query.b)){
                results.add(-1.0);
            }
            Set<String> visited = new HashSet<>();
            results.add(dfs(graph, query.a, query.a, query.b, 1.0, visited));
        }
        return results;
    }
    double dfs(Map<String, Map<String, Double>> graph, String start, String cur, String to,
               double ratio, Set<String> visited){

        visited.add(cur);
        if (graph.get(cur).containsKey(to)) {
            return ratio * graph.get(cur).get(to);
        }
        Map<String, Double> neighbors = graph.get(cur);
        for(String neighbor : neighbors.keySet()){
            if(!visited.contains(neighbor)){
                double newRatio = ratio * neighbors.get(neighbor);
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
