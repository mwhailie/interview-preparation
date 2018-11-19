package edu.neu.companies.airbnb;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> succeeding = new HashMap<>();
        String prev = words[0];
        for(int j = 1; j < words.length; j++){
            String word = words[j];
            int i = 0;
            while(i < word.length() && i < prev.length()){
                if(word.charAt(i) != prev.charAt(i)){
                    if(succeeding.get(prev.charAt(i)) == null){
                        succeeding.put(prev.charAt(i), new HashSet<>());
                    }
                    succeeding.get(prev.charAt(i)).add(word.charAt(i));

                    break;
                }
                i++;
            }
            prev = word;
        }
        Map<Character, Integer> indegree = new HashMap<>();
        for(String word : words){
            for(int i = 0; i < word.length(); i++){
                indegree.put(word.charAt(i), 0);
            }
        }
        for(Character pre : succeeding.keySet()){
            for(Character next : succeeding.get(pre)){
                indegree.put(next, indegree.get(next) + 1);
            }
        }
        Queue<Character> q = new LinkedList<>();
        for(Character c : indegree.keySet()){
            if(indegree.get(c) == 0){
                q.add(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            Character c = q.poll();
            sb.append(c);
            if(!succeeding.containsKey(c)){
                continue;
            }
            for(Character next : succeeding.get(c)){
                indegree.put(next, indegree.get(next) - 1);
                if(indegree.get(next) == 0){
                    q.add(next);
                }
            }
        }
        return sb.length() != indegree.size()? "" : sb.toString();
    }
}
