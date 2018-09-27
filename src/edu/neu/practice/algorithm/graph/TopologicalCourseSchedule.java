package edu.neu.practice.algorithm.graph;

import java.util.*;

public class TopologicalCourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph.put(i, new HashSet<Integer>());
        }
        for(int[] prerequisite : prerequisites){
            graph.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        int taken = 0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            taken++;
            for(int neighbor : graph.get(course)){
                indegree[neighbor] --;
                if(indegree[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }
        return taken == numCourses;
    }
}
