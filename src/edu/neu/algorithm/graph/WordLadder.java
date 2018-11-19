package edu.neu.algorithm.graph;

import java.util.*;

public class WordLadder {
    Map<String, Set<String>> graph = new HashMap<>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> findLadders = new ArrayList<>();
        if(!wordList.contains(endWord)){
            return findLadders;
        }
        makeGraph(beginWord, wordList);
        System.out.println(graph);
        bfs(new HashSet<>(), new ArrayList<>(), beginWord, endWord, findLadders);
        return findLadders;
    }
    public void bfs(Set<String> visited, List<String> route, String beginWord, String endWord, List<List<String>> findLadders){
        Queue<String> toVisit = new LinkedList<>();
        toVisit.add(beginWord);
        while(!toVisit.isEmpty()){
            String curr = toVisit.poll();
            if(visited.contains(curr)){
                continue;
            }
            visited.add(curr);
            route.add(curr);
            if(curr.equals(endWord)){
                findLadders.add(new ArrayList<>(route));
                return;
            }
            for(String neighbor : graph.get(curr)){
                toVisit.add(neighbor);
            }
        }
    }
    public void makeGraph(String beginWord, List<String> wordList) {
        for(String word : wordList){
            graph.put(word, new HashSet<>());
        }
        graph.put(beginWord, new HashSet<>());
        for(String word1 : wordList){
            if(isNeighbor(word1, beginWord)){
                graph.get(word1).add(beginWord);
                graph.get(beginWord).add(word1);
            }
            for(String word2 : wordList){
                if(isNeighbor(word1, word2)){
                    graph.get(word1).add(word2);
                    graph.get(word2).add(word1);
                }
            }
        }
    }
    public boolean isNeighbor(String beginWord, String endWord){
        int diff = 0;
        for(int i = 0; i < endWord.length(); i++){
            if(beginWord.charAt(i) != endWord.charAt(i) ){
                diff++;
            }
        }
        return diff==1;
    }

    public static void main(String[] args) {
        WordLadder w = new WordLadder();
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        System.out.println(w.findLadders("hit", "cog",list));
    }
}
