package edu.neu.companies.twitter;

public class TwitterNoPair {
    static public int[] minimalOperations(String[] inputs){
        if(inputs == null || inputs.length == 0){
            return null;
        }
        int[] minimalOperations = new int[inputs.length];
        for(int i = 0; i < inputs.length; i ++){
            char[] cs = inputs[i].toCharArray();
            minimalOperations[i] = minimalOperation(cs);
//            System.out.println(minimalOperations[i]);
        }
        return minimalOperations;
    }

    /*
      baa
      start end
      0     1
      1     2
      1     3
      baaabaaa
      start end
      0     1
      1     2
      1     3
      1     4
    */

    static private int minimalOperation(char[] cs) {
        if(cs.length <= 1){
            return 0;
        }
        int start = 0, end = 1;
        int operation = 0;

        while(end < cs.length){
            if(cs[start] == cs[end]){
                while(end < cs.length && cs[start] == cs[end]){
                    end++;
                }
                operation += (end - start)/2;
                start = end;
                end ++;
            }else{
                start ++;
                end ++;
            }
        }
        return operation;
    }

    public static void main(String[] args){
        minimalOperations(new String[]{"ab", "abab"});
    }
}
