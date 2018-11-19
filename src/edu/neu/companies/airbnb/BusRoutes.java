package edu.neu.companies.airbnb;

import java.util.*;

public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T){
            return 0;
        }
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Set<Integer>> ids = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < routes.length; i ++){
            Set<Integer> routeSet = new HashSet<>();
            for(int stop : routes[i]){
                routeSet.add(stop);
            }
            ids.put(i, routeSet);
            if(routeSet.contains(S) && routeSet.contains(T)){
                return 1;
            }
            if(routeSet.contains(S)){
                q.add(i);
                visited.add(i);
            }
            map.put(i, new ArrayList<>());
        }
        Set<Integer> result = new HashSet<>();
        for(int i = 0; i < routes.length - 1; i ++){
            for(int j = i + 1; j < routes.length; j ++){
                result.clear();
                result.addAll(ids.get(i));
                result.retainAll(ids.get(j));
                if(!result.isEmpty()){
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }
        int level = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i ++){
                int curr = q.poll();
                Set<Integer> currRoute = ids.get(curr);
                if(currRoute.contains(T)){
                    return level;
                }
                for(int neighbor : map.get(curr)){
                    if(visited.contains(neighbor)){
                        continue;
                    }
                    q.add(neighbor);
                    visited.add(neighbor);
                }
            }

            level ++;
        }
        return -1;
    }
}
