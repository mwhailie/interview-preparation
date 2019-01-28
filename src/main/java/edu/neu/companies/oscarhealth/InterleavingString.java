package edu.neu.companies.oscarhealth;

public class InterleavingString {
    public boolean isInterleave(char[] s1, char[] s2, char[] s3) {

        if(s1.length + s2.length != s3.length){
             return false;
         }

         return  isInterleave(s1, 0, s2, 0, s3, 0);
     }
    public boolean isInterleave(char[] s1, int index1, char[] s2, int index2, char[] s3, int index3) {

        if(s1.length + s2.length != s3.length){
            return false;
        }
        if(index1 == s1.length){
            for(int i = 0; i + index2 < s2.length; i ++) if(s2[index2 +i] != s3[index3 + i]){
                return false;
            }
            return true;
        }
        if(index2 == s2.length){
            for(int i = 0; i + index1 < s1.length; i ++) if(s1[index1 +i] != s3[index3 + i]){
                return false;
            }
            return true;
        }
        if(s1[index1] != s3[index3] && s2[index2] != s3[index3]){
            return false;
        }
        if(s1[index1] != s3[index3]){
            return isInterleave(s1, index1, s2, index2 + 1, s3, index3 + 1);
        }
        if(s2[index2] != s3[index3]){
            return isInterleave(s1, index1 + 1, s2, index2, s3, index3 + 1);
        }
        return  isInterleave(s1, index1, s2, index2 + 1, s3, index3 + 1) || isInterleave(s1, index1 + 1, s2, index2, s3, index3 + 1);
    }
    public boolean isInterleave(String s1, String s2, String s3) {

        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        if(s1.isEmpty()){
            return s2.equals(s3);
        }
        if(s2.isEmpty()){
            return s1.equals(s3);
        }
        boolean[][] matrix = new boolean[s1.length() + 1][s2.length() + 1];
        matrix[0][0] = true;
        int i = 1;
        while(i < matrix.length && matrix[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1)){
            matrix[i ++ ][0] = true;
        }
        i = 1;
        while(i < matrix[0].length && matrix[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1)){
            matrix[0][i ++] = true;
        }
        for(i = 1; i < matrix.length; i ++){
            for(int j = 1; j < matrix[0].length; j ++){
                matrix[i][j] = matrix[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) ||
                        matrix[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1) ;
            }
        }
        return matrix[s1.length() ][s2.length() ];
    }
}
