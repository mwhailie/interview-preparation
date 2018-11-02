package edu.neu.practice.companies.google;

import java.util.*;

/*
    https://leetcode.com/problems/isomorphic-strings/description/

    Given two strings s and t, determine if they are isomorphic.

    Two strings are isomorphic if the characters in s can be replaced to get t. Two characters may map to the same character and a character may map to itself.\

    Example 1:
    Input: s = "egg", t = "add"
    Output: true

    Example 2:
    Input: s = "egg", t = "ddd"
    Output: true

    Follow up:
    Now there is one more mapping constrain that cycling mapping is not allowed.

    Example 1:
    Input: s = "ab", t = "ba"
    Output: false
    Explanation: It used to return true. But after we add the new constrain, it returns false since it has cycling mapping
    a -> b
    b -> a

    Example 2:
    Input: s = "abcd", t = "bcda"
    Output: false
    Explanation: It returns false since it has cycling mapping
    a -> b
    b -> c
    c -> d
    d -> a

 */
public class Isomorphic {
    public boolean isMatch(String from, String to){

        HashMap<Character, Character> map = new HashMap<>();

        if(from == null && to == null){
            return true ;
        }
        if(from == null || to == null){
            return false ;
        }
        if(from.length() !=  from.length()){
            return false ;
        }
        for(int i = 0; i < from.length(); i ++){
            char cFrom = from.charAt(i);
            char cTo = to.charAt(i);
            if(map.containsKey(cFrom)){
                if(map.get(cFrom) != cTo){
                    return false;
                }
            }else {
                map.put(cFrom, cTo);
            }
        }
        return true;
    }

    public boolean isMatchII(String from, String to){

        HashMap<Character, Character> map = new HashMap<>();

        if(from == null && to == null){
            return true ;
        }
        if(from == null || to == null){
            return false ;
        }
        if(from.length() !=  from.length()){
            return false ;
        }
        for(int i = 0; i < from.length(); i ++){
            char cFrom = from.charAt(i);
            char cTo = to.charAt(i);
            if(map.containsKey(cFrom)){
                if(map.get(cFrom) != cTo){
                    return false;
                }
            }else {
                map.put(cFrom, cTo);
            }
        }
        return isDAG(map);
    }

    boolean isDAG(HashMap<Character, Character> map) {
        Map<Character, Set<Character>> depMap= new HashMap<>();
        for (Character from: map.keySet()) {
            char to = map.get(from);
            depMap.compute(to, (k, v) -> v == null ? new HashSet<>() : v).add(from);
        }
        Set<Character> visited = new HashSet<>();
        for (char c : depMap.keySet()) {
            if (!visited.contains(c)) {
                if (!helper(depMap, visited, c, map.size() + 1)) {
                    return false;
                }
            }
        }
        return true;
    }
    boolean helper(Map<Character, Set<Character>> depMap, Set<Character> visited, char cur, int limit) {
        if (limit == 0) {
            return false;
        }
        if(!depMap.containsKey(cur)){
            return false;
        }
        for (char depChar: depMap.get(cur)) {
            if (!visited.contains(depChar)) {
                if (!helper(depMap, visited, depChar, limit - 1)) {
                    return false;
                }
            }
        }
        visited.add(cur);
        return true;
    }
    public boolean isMatchIIWithIndegree(String from, String to){
        HashMap<Character, Character> map = new HashMap<>();
        if(from == null && to == null){
            return true ;
        }
        if(from == null || to == null){
            return false ;
        }
        if(from.length() !=  from.length()){
            return false ;
        }
        for(int i = 0; i < from.length(); i ++){
            char cFrom = from.charAt(i);
            char cTo = to.charAt(i);
            if(map.containsKey(cFrom)){
                if(map.get(cFrom) != cTo){
                    return false;
                }
            }else {
                map.put(cFrom, cTo);
            }
        }
        return isDAGWithInDegree(map);
    }
    boolean isDAGWithInDegree(HashMap<Character, Character> map) {
        Map<Character, Integer> indegree = new HashMap<>();

        for (Character from : map.keySet()) {
            char to = map.get(from);
            indegree.put(from, indegree.getOrDefault(from, 0));
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
        }
        Set<Character> visited = new HashSet<>();
        Queue<Character> q = new LinkedList<>();
        for (Character c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                q.offer(c);
            }
        }
        while (!q.isEmpty()) {
            Character curr = q.poll();
            visited.add(curr);
            if (!map.containsKey(curr)) {
                continue;
            }
            Character to = map.get(curr);
            indegree.put(to, indegree.get(to) - 1);
            if (indegree.get(to) == 0) {
                q.offer(to);
            }
        }
        return visited.size() == indegree.size();
    }
    public boolean isMatchIIWithDFS(String from, String to){
        HashMap<Character, Character> map = new HashMap<>();
        if(from == null && to == null){
            return true ;
        }
        if(from == null || to == null){
            return false ;
        }
        if(from.length() !=  from.length()){
            return false ;
        }
        for(int i = 0; i < from.length(); i ++){
            char cFrom = from.charAt(i);
            char cTo = to.charAt(i);
            if(map.containsKey(cFrom)){
                if(map.get(cFrom) != cTo){
                    return false;
                }
            }else {
                map.put(cFrom, cTo);
            }
        }
        return isDAGWithDfs(map);
    }

    Stack<Character> cycle;
    private boolean isDAGWithDfs(Map<Character, Character> map) {
        Set<Character> visited = new HashSet<>();
//        Stack<Character> cycle = null;
        Set<Character> onStack = new HashSet<>();
        for (char v : map.keySet()) {
            if (!visited.contains(v) && cycle == null) dfs(map, v, visited, onStack);
        }
        return cycle == null;
    }
    private void dfs(Map<Character, Character> map, char v, Set<Character> visited, Set<Character> onStack) {
        onStack.add(v);
        visited.add(v);
        char w = map.get(v);
        // short circuit if directed cycle found
        if (cycle != null) return;

        // found new vertex, so recur
        else if (!visited.contains(w)) {
            dfs(map, w, visited, onStack);
        }
        // trace back directed cycle
        else if (onStack.contains(w)) {
            cycle = new Stack<Character>();
        }
        onStack.remove(v);
    }

    public static void main(String[] args) {
        Isomorphic i = new Isomorphic();
//        System.out.println(i.isMatch("ab", "bb"));
//        System.out.println(i.isMatch("abcd", "bcda"));
//        System.out.println(i.isMatchII("ab", "dd"));
//        System.out.println(i.isMatchII("abcd", "bcda"));
//        System.out.println(i.isMatchIIWithIndegree("ab", "dd"));
        System.out.println(i.isMatchIIWithIndegree("abcd", "bcda"));
        System.out.println(i.isMatchIIWithDFS("abcd", "bcda"));
    }
}
