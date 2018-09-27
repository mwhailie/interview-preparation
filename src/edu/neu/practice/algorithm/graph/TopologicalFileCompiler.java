package edu.neu.practice.algorithm.graph;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class TopologicalFileCompiler {

    class File {
        String name;
        File[] dependencies;
    }

    public boolean canCompile(File[] files){
        Map<File, Set<File>> map = new HashMap<>();
        Map<File, Integer> indegree = new HashMap<>();
        Set<File> compiledFiles = new HashSet<>();
        Queue<File> q = new LinkedList<>();
        for(File file: files){
            if(file.dependencies == null || file.dependencies.length == 0){
                indegree.put(file, 0);
                q.offer(file);
            }else{
                indegree.put(file, file.dependencies.length);
            }
            for(File dependency : file.dependencies){
                map.get(dependency).add(file);
            }
        }
        while (!q.isEmpty()){
            File compiledFile = q.poll();
            compiledFiles.add(compiledFile);
            for(File neighbor : map.get(compiledFile)){
                indegree.put(neighbor, indegree.get(neighbor)-1);
                if(indegree.get(neighbor) <= 0 && !compiledFiles.contains(neighbor)){
                     q.offer(neighbor);
                }
            }
        }
        return compiledFiles.size() == files.length;
    }
}
