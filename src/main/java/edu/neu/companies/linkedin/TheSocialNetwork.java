package edu.neu.companies.linkedin;

import java.util.*;

public class TheSocialNetwork {
    public void socialGraph(int[] counts){
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i = 0; i < counts.length; i ++){
            int groupSize = counts[i];
            if(map.get(groupSize) == null){
                map.put(groupSize, new ArrayList<>());
            }
            map.get(groupSize).add(i);
        }
        List<List<Integer>> ress = new ArrayList<>();
        for(int key : map.keySet()){
            List<Integer> ids = map.get(key);
//            int numOfGroup = ids.size()/key;
            List<Integer> res = new ArrayList<>();
            for(int i = 0; i < ids.size(); i ++){
                if(i != 0 && i % key == 0){
                    res = new ArrayList<>();
                    ress.add(res);
                }
                res.add(ids.get(i));
            }
        }
        Collections.sort(ress, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });
    }
    public String[]  usernameSyetem(String[] usernames){
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0; i < usernames.length; i ++){
            int dup = map.getOrDefault(usernames[i], 0);
            map.put(usernames[i], dup + 1);
            if(dup != 0){
                usernames[i] = usernames[i] + dup;
            }
        }
        return usernames;
    }

}
