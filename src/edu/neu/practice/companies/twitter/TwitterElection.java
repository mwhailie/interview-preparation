package edu.neu.practice.companies.twitter;

import java.util.HashMap;
import java.util.Map;

public class TwitterElection {
    static public String electWinner(String[] votes){
        Map<String,Integer> map = new HashMap<>();
        for(String vote : votes){
            map.put(vote, map.getOrDefault(vote, 0) + 1);
        }
        String winner = votes[0];
        int max = map.get(winner);
        for(String candidate : map.keySet()){
            if(max < map.get(candidate) || max == map.get(candidate) && winner.compareTo(candidate) > 1){
                max = map.get(candidate);
                winner = candidate;
            }
        }
        return winner;
    }
    public static void main(String[] args){
        System.out.println(electWinner(new String[]{"A", "D","D","A","B","B","D","C","C","C"}));
    }
}
