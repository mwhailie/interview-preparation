package edu.neu.companies.nextcapital;

import java.util.*;

public class DeveloperTeam {
    public static int[] getPointsByTeam(
            HashMap<String, Integer> developerPoints,
            String[][] developerPairs
    ) {
        /* Enter your code here */
        Map<String, List<String>> graph = new HashMap<>();
        for(String[] pair : developerPairs) {
            graph.compute(pair[0], (k, v) -> v == null ? new ArrayList<>() : v).add(pair[1]);
            graph.compute(pair[1], (k, v) -> v == null ? new ArrayList<>() : v).add(pair[0]);
        }
        System.out.println(graph);
        Set<String> visited = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for(String developer : developerPoints.keySet()){
            if(visited.contains(developer)){
                continue;
            }
            int point = bfs(developer, graph, visited, developerPoints);
            res.add(point);
        }
        return res.stream().mapToInt(i->i).toArray();
    }

    private static int bfs(String developer, Map<String, List<String>> graph, Set<String> visited,
                           HashMap<String, Integer> developerPoints
    ) {
        int totalPoint = 0;
        Queue<String> q = new LinkedList<>();
        q.add(developer);
        while(!q.isEmpty()) {
            String curr = q.poll();
            if(visited.contains(curr)){
                continue;
            }
            visited.add(curr);
            System.out.println(curr);

            totalPoint += developerPoints.get(curr);
            if(graph.containsKey(curr)){
                for(String pair : graph.get(curr)){
                    q.add(pair);
                }
            }
        }
        return totalPoint;
    }
}
