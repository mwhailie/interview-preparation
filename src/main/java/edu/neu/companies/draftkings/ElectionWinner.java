package edu.neu.companies.draftkings;

import java.util.*;

public class ElectionWinner {
    static String electionWinner(String[] votes) {
        Map<String, Integer> map = new HashMap<>();
        List<String> candidates = new ArrayList<>();
        for(String vote : votes){
            if(!map.containsKey(vote)){
                candidates.add(vote);
            }
            map.put(vote, map.getOrDefault(vote, 0) + 1);
        }
        System.out.println(map);
        Collections.sort(candidates, ((a, b) -> (map.get(a) != map.get(b)? map.get(b) - map.get(a):a.compareTo(b) )));
        return candidates.get(0);

    }

    public static void main(String[] args) {
        System.out.println(electionWinner(new String[]{"Victor", "Victor", "Alex"}));
    }

}
