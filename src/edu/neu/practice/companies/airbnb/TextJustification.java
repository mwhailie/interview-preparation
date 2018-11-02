package edu.neu.practice.companies.airbnb;

import java.util.*;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int first = 0;
        List<String> res = new ArrayList<>();
        while(first < words.length){
            //first : index of first word in this line
            //last : index of last word in this line
            //last - first : number of word
            //last - first - 1 : number of places between word

            int last = first + 1, total = words[first].length();
            while(last < words.length && total + words[last].length() + 1 <= maxWidth){
                //at least a space between words
                total += (words[last].length() + 1);
                last ++;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(words[first]);
            // if this line last line or there is only 1 word in this line, left, append spaces
            if(last == words.length || last - first - 1 == 0){
                for(int i = first + 1; i < last; i ++){
                    sb.append(" ");
                    sb.append(words[i]);
                }
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            }else{
                int space = (maxWidth - total) / (last - first - 1);
                int left = (maxWidth - total) % (last - first - 1);

                for(int i = first + 1; i < last; i ++){
                    sb.append(" ");
                    for(int k=space; k > 0; k--) {
                        sb.append(" ");
                    }
                    if(left -- > 0){
                        sb.append(" ");
                    }

                    sb.append(words[i]);
                }
            }
            res.add(sb.toString());
            first = last;
        }
        return res;
    }

//    https://leetcode.com/problems/sentence-screen-fitting/description/
    // LTE O(row * (rows * col/ average length))
     public int wordsTyping(String[] sentence, int rows, int cols) {
         int first = 0;
         int time = 1;
         int initRow = rows;
         while(rows -- > 0){
             if(first >= sentence.length){
                 first = 0;
                 time ++;
             }
             int last = first + 1;
             if(last >= sentence.length){
                 last = 0;
                 time++;
             }
             int total = sentence[first].length();
             if(total > cols){
                 return 0;
             }
             while(total + sentence[last].length() + 1 <= cols){
                 total += (sentence[last].length() + 1);
                 last ++;
                 if(last >= sentence.length){
                     last = 0;
                     time ++;
                 }
             }
             if(last == sentence.length){
                 return initRow/(initRow - rows) * time;
             }

                 first = last;
         }
         if(first != sentence.length){
             time --;
         }
         return time;
     }

    // LTE O(max( row, N * (cols / average length)) )
    public int wordsTyping2(String[] sentence, int rows, int cols) {
        int N = sentence.length;
        int[] nextIndex = new int[N];
        int[] times = new int[N];
        for(int i = 0; i < N; i ++){
            int total = 0;
            int last = i;
            int time = 0;
            while(total + sentence[last].length() <= cols){
                total += (sentence[last].length() + 1);
                last ++;
                if(last == sentence.length){
                    last = 0; time++;
                }
            }
            times[i] = time;
            nextIndex[i] = last;
        }
        int ans = 0;
        int curr = 0;
        for(int i = 0; i < rows; i ++){
            ans += times[curr];
            curr = nextIndex[curr];
        }
        return ans;
    }
    public static void main(String[] args) {
        Thread tHREA = new Thread(){
                @Override
                public void run(){
                    System.out.println("");
                }
        };
        TextJustification t = new TextJustification();
        System.out.println(t.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
    }

}
