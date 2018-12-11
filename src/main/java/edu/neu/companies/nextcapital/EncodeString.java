package edu.neu.companies.nextcapital;

public class EncodeString {
    public static String encodeString(String s){
        s = s.replaceAll("S|s", "5");
        if(s.length() % 2 == 1 && Character.isDigit(s.charAt(s.length()/2))){
            if(s.length() == 1){
                s = "" + (2 * (s.charAt(0) - '0'));
            }else{
                int index = s.length()/2;
                String first = s.substring(0, index), last = s.substring(index + 1);
                s =  first+ (2 * (s.charAt(index) - '0')) + last;
            }
        }
        char[] cs = s.toCharArray();
        if(cs.length % 2 == 0){
            char first = encodeChar(cs[0]);
            char last = encodeChar(cs[cs.length - 1]);
            cs[0] = last;
            cs[cs.length - 1] = first;
        }
        s = new String(cs);
        int start = s.toLowerCase().indexOf("nextcapital");
        if(start != -1){
            reverse(cs, start, start + 3);
        }
        return new String(cs);
    }

    public static char encodeChar(char c){
        if(Character.isUpperCase(c)){
            c = Character.toLowerCase(c);
        }else{
            c = Character.toUpperCase(c);
        }
        return c;
    }
    public static void reverse(char[] cs, int start, int end){
        while(start < end){
            char c = cs[start];
            cs[start] = cs[end];
            cs[end] = c;
            start ++ ;
            end --;
        }
    }

    static String encodeStringII(String inputString) {
        int prevNumberVal = 0;
        int prevLetterVal = 0;
        char[] a = inputString.toCharArray();
        for (int i = 0; i < a.length; i++) {
        if (Character.isUpperCase(a[i])) {
            // capital case
            int curr = a[i] - 'A';
            curr += prevLetterVal;
            curr %= 26;
            prevLetterVal = curr;
            curr += 65;
            a[i] = (char)curr;
        } else if (Character.isLowerCase(a[i])) {
            int curr = a[i] - 'a';
            curr += prevLetterVal;
            curr %= 26;
            prevLetterVal = curr;
            curr += 97;
            a[i] = (char)curr;
        } else if (Character.isDigit(a[i])){
            int curr = a[i] - '0';
            curr += prevNumberVal;
            curr %= 10;
            prevNumberVal = 9 - curr;
            a[i] = (char)(curr + '0');
        }
        // else ignore
        }
        return new String(a);
    }

    public static void main(String[] args) {
        System.out.println(encodeString("ssssFFFSSSS"));
        System.out.println(encodeString("GoCardinals"));
        System.out.println(encodeString("Doge"));
        System.out.println(encodeString("nExTcapITalxnextcapital"));
        System.out.println(encodeString("ThreeSThree"));
    }
}
