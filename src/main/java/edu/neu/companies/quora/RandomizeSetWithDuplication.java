package edu.neu.companies.quora;

import java.util.*;

public class RandomizeSetWithDuplication {
    Map<Integer, Set<Integer>> randomizedSet;
    List<Integer> list;
    Random r;

    /** Initialize your data structure here. */
    public RandomizeSetWithDuplication() {
        randomizedSet = new HashMap<>();
        list = new ArrayList<>();
        r = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contains = randomizedSet.containsKey(val);
        if(!contains){
            randomizedSet.put(val, new HashSet<>());
        }
        randomizedSet.get(val).add(list.size());
        list.add(val);
        return !contains;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!randomizedSet.containsKey(val)){
            return false;
        }
        int index = randomizedSet.get(val).iterator().next();
        randomizedSet.get(val).remove(index);
        if(randomizedSet.get(val).isEmpty()){
            randomizedSet.remove(val);
        }
        if(index < list.size() - 1){
            int swap = list.get(list.size() - 1);
            list.set(index, swap);
            randomizedSet.get(swap).remove(list.size() - 1);
            randomizedSet.get(swap).add(index);
        }
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = r.nextInt(list.size());
        return list.get(index);
    }
}
