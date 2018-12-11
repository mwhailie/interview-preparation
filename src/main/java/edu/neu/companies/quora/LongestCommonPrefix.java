package edu.neu.companies.quora;

public class LongestCommonPrefix {
     public String longestCommonPrefix(String[] strs) {
         if(strs == null || strs.length == 0){
             return "";
         }
         int i = 0;
         for(i = 0; i < strs[0].length(); i++){
             char c = strs[0].charAt(i);
             for(int j = 1; j < strs.length ; j++){
                 if(i == strs[j].length() ||strs[j].charAt(i) != c){
                     return strs[0].substring(0,i);
                 }
             }
         }
         return strs[0];
     }
    public static String longestCommonPrefixII(String[] words){
        if(words == null || words.length == 0 || words[0].isEmpty()){
            return "";
        }
        for(int i = 0; i < words[0].length(); i++){
            char c = words[0].charAt(i);
            for(int j = 1; j < words.length; j ++){
                if(i >= words[j].length() || c != words[j].charAt(i)){
                    return words[0].substring(0,i);
                }
            }
        }
        return words[0];
    }
}
