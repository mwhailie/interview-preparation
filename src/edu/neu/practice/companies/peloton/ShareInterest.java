package edu.neu.practice.companies.peloton;

import java.util.*;

public class ShareInterest {
    public int maxShared(int friendsNodes, List<Integer> friendsFrom, List<Integer> friendsTo, List<Integer> friendsWeight) {
        int max_shared = 0;
        int max_product = 0;
        Map<Integer, Map<Integer,Set<Integer>>> map = new HashMap<>();
        int numOfEdge = friendsFrom.size();
        for(int i = 0; i < numOfEdge; i ++){
            int from = friendsFrom.get(i);
            int to = friendsTo.get(i);
            if(friendsFrom.get(i) > friendsTo.get(i)){
                from = friendsTo.get(i);
                to = friendsFrom.get(i);
            }
            if(!map.containsKey(from)){
                map.put(from, new HashMap<>());
            }
            if(!map.get(from).containsKey(to)){
                map.get(from).put(to, new HashSet<>());
            }
            map.get(from).get(to).add(friendsWeight.get(i));
            if(map.get(from).get(to).size() > max_shared){
                max_shared = map.get(from).get(to).size();
                max_product = from * to;
            }else if(map.get(from).get(to).size() == max_shared){
                max_product = Math.max(from * to, max_product);
            }
        }
        return max_product;
    }
}
