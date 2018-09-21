package edu.neu.practice.forusall;

public class Isomorphic {

    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] map = new int[512];
        for(int i = 0; i < s.length(); i ++){
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if(map[c1] != map[c2 + 256]){
                return false;
            }
            map[c1] = i + 1;
            map[c2 + 256] = i + 1;
        }
        return true;
    }
}
