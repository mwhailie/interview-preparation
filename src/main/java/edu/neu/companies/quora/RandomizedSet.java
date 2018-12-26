package edu.neu.companies.quora;

import java.util.*;

public class RandomizedSet {
    Map<Integer, Integer> randomizedSet;
    List<Integer> list;
    Random r;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        randomizedSet = new HashMap<>();
        list = new ArrayList<>();
        r = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(randomizedSet.containsKey(val)){
            return false;
        }
        randomizedSet.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!randomizedSet.containsKey(val)){
            return false;
        }
        int index = randomizedSet.get(val);
        if(index < list.size() - 1){
            int swap = list.get(list.size() - 1);
            list.set(index, swap);
            randomizedSet.put(swap, index);
        }
        randomizedSet.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = r.nextInt(list.size());
        return list.get(index);
    }
}
