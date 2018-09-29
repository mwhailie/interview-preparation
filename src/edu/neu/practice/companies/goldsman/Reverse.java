package edu.neu.practice.companies.goldsman;

public class Reverse {
    static public String reverse(String expression){
        char[] cs = expression.toCharArray();
        reverse(cs, 0, cs.length - 1);
        for(int i = 0; i < cs.length; i ++){
            int start = i;
            while( i < cs.length && (Character.isDigit(cs[i]) || cs[i] == '.')
                    || i + 1 < cs.length && cs[i] == '-' && isAllSign(cs[i + 1])
                    ||  i == cs.length - 1 && cs[i] == '-' ){
                i ++;
            }

            reverse(cs, start, i - 1);
        }
        return new String(cs);
    }

    private static boolean isAllSign(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static private void reverse(char[] cs, int i, int j) {
        while(i < j){
            char c = cs[i];
            cs[i] = cs[j];
            cs[j] = c;
            i ++; j --;
        }
    }
    public static void main(String[] args){
        System.out.println(reverse("1+2*9-23"));
        System.out.println(reverse("1*2.4+9.6-23.89"));

        System.out.println(reverse("1*2.4+-9.6-23.89"));
        System.out.println(reverse("-1*2.4+-9.6-23.89"));

    }
}
