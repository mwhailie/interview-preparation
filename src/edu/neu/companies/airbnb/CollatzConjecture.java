package edu.neu.companies.airbnb;

import java.util.*;
/*
    http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=273149
 */
public class CollatzConjecture {

    public static int findNumOfStep(int n){
        if(n <= 1){
            return 1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,2);
        return findNumOfStep(n, map);
    }

    private static int findNumOfStep(int n, Map<Integer, Integer> map){
        if(map.containsKey(n)){
            return map.get(n);
        }
        int res = 0;
        if(n % 2 == 0){
            res = findNumOfStep(n/2, map) + 1;
        }else{
            res = findNumOfStep(n * 3 + 1, map) + 1;
        }
        map.put(n, res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findNumOfStep(7));
    }
}

