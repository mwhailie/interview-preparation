package edu.neu.companies;

import java.util.*;

public class ClosestCapacity {

    List<List<Integer>> findClosestCapacityPair(List<Integer> foregroundList,
                                                List<Integer> boregroundList,
                                                int capacity){
        Map<Integer, List<Integer>> foregroundMap = new HashMap<>();
        TreeMap<Integer, List<Integer>> boregroundMap = new TreeMap<>();
        for(int id = 0; id < foregroundList.size(); id ++){
            int memory = foregroundList.get(id);
            foregroundMap.compute(memory, (k, v) -> v == null ? new ArrayList<>() : v).add(id);
        }
        for(int id = 0; id < boregroundList.size(); id ++){
            int memory = boregroundList.get(id);
            boregroundMap.compute(memory, (k, v) -> v == null ? new ArrayList<>() : v).add(id);
        }

        int closestFore = 0, closestBore = 0;
        List<List<Integer>> res = new ArrayList<>();
        for(int foreMemory : foregroundMap.keySet()){

            int boreMemory = boregroundMap.ceilingKey(capacity - foreMemory);

            if(capacity - (boreMemory + foreMemory) < capacity - (closestFore + closestFore)){
                closestFore = foreMemory;
                closestBore = boreMemory;
                if(closestFore + closestBore == capacity){
                    break;
                }
            }
        }
        for(int fordId : foregroundMap.get(closestFore)){
            for(int bordId : boregroundMap.get(closestBore)){
                res.add(new ArrayList<>(Arrays.asList(bordId, fordId)));
            }
        }
        return res;
    }
}
