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

    public static void main(String[] args) {
        TextJustification t = new TextJustification();
        System.out.println(t.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
    }

}
