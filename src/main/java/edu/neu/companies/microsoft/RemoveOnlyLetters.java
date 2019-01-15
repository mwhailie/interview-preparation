package edu.neu.companies.microsoft;

public class RemoveOnlyLetters {
    public String reverseOnlyLetters(String S) {
        char[] cs = S.toCharArray();
        int slow = 0, fast = 0;
        while(fast < cs.length){
            while(fast < cs.length &&
                    (Character.isDigit(cs[fast])
                            || Character.isLetterOrDigit(cs[fast]))){
                fast ++;
            }
            if(slow < fast){
                reverse(cs, slow, fast - 1);
            }
            fast ++;
            slow = fast;
        }
        return new String(cs);
    }

    public void reverse(char[] cs, int slow, int fast){
        while(slow < fast){
            char c = cs[slow];
            cs[slow] = cs[fast];
            cs[fast] = c;
            slow ++;
            fast --;
        }
    }
}
