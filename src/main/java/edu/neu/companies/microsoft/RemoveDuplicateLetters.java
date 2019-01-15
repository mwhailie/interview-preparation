package edu.neu.companies.microsoft;

import java.util.*;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        List<Character> letters = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for(char key : map.keySet()) if(map.get(key) == 1){
            letters.add(key);
        }
        Collections.sort(letters);
        String res = "";
        for(char key : letters){
            res += key;
        }
        return res;
    }

}
