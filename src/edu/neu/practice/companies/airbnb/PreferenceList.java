package edu.neu.practice.companies.airbnb;

import java.util.*;

public class PreferenceList {
    public List<Integer> getPreference(List<List<Integer>> preferences) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (List<Integer> l : preferences) {
            for (int i = 0; i < l.size() - 1; i++) {
                int from = l.get(i);
                int to = l.get(i + 1);
                if (!map.containsKey(from)) {
                    inDegree.put(from, 0);
                    map.put(from, new HashSet<>());
                }
                if (!map.containsKey(to)) {
                    inDegree.put(to, 0);
                    map.put(to, new HashSet<>());
                }
                map.get(from).add(to);
                inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int k : inDegree.keySet()) {
            if (inDegree.get(k) == 0) {
                q.offer(k);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int curr = q.poll();
            res.add(curr);
            for (int neighbor : map.get(curr)) {
                int degree = inDegree.get(neighbor) - 1;
                inDegree.put(neighbor, degree);
                if (degree == 0) q.offer(neighbor);
            }
        }
        return res;
    }
}
