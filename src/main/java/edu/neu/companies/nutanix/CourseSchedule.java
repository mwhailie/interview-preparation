package edu.neu.companies.nutanix;

import java.util.*;

public class CourseSchedule {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        makeGraph(numCourses, prerequisites, graph, inDegree);


        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                q.add(i);
            }
        }
        if(q.size() == 0){
            return new int[0];
        }
        int[] result = new int[numCourses];
        int taken = 0;
        while(!q.isEmpty()){
//            int size = q.size();
//            for(int i = 0; i < size; i ++){
                int course = q.poll();
                result[taken++] = course;
                if(taken == numCourses){
                    for(int c : result){
                        System.out.print(c + ",");
                    }
                    System.out.println();
                    return result;
                }
                for(int next : graph.get(course)){
                    if(--inDegree[next] == 0){
                        q.add(next);
                    }
                }
//            }
        }
        return new int[0];
    }
    public void makeGraph(int numCourses,
                          int[][] prerequisites,
                          Map<Integer, Set<Integer>> graph,
                          int[] inDegree) {

        for(int i = 0; i < numCourses; i++){
            graph.put(i, new HashSet<Integer>());
        }
        for(int[] prerequisite : prerequisites){
            inDegree[prerequisite[0]]++;
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        System.out.println(cs.findOrder(2, new int[][]{{1, 0}}));

        System.out.println(cs.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 0}}));
    }
}
