package edu.neu.practice.companies.twitter;

import java.util.ArrayList;
import java.util.List;

public class TwitterSubsequence {
    static public String[] missingWord(String s, String t){
        if(s == null || t == null ){
            return null;
        }

        String[] completed = s.split("\\s+");
        String[] subsequence = t.split("\\s+");
        if(completed.length == 0 || completed.length == subsequence.length){
            return new String[] {};
        }
        List<String> missingwords = new ArrayList<>();
        int i = 0, j = 0;
        while(i < completed.length || j < subsequence.length){
            if(j >= subsequence.length || !completed[i].trim().equals(subsequence[j].trim())){
                missingwords.add(completed[i]);
                i ++;
            }else {
                i ++; j ++;
            }
        }
        return missingwords.toArray(new String[0]);
    }
    public static void main(String[] args){
        String[] missingwords = missingWord("I like cheese", "like");
        missingWord("i  am using hacherrank to improve programming", "am hacherrank to improve");
    }
}
/*
0  1          2     3          4  5
i  am         using hacherrank to improve programming
am hacherrank to    improve

i j res
0 0 []
1 0 [I]
2 1 [I]
3 1 [I, using]
4 2 [I, using]
5 3 [I, using]
6 4 [I, using]

I like cheese
like
i j res
0 0 []
1 0 [I]
2 1 [I]
3 1 [I, cheese]
 */