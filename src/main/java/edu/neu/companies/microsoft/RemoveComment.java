package edu.neu.companies.microsoft;

import java.util.*;

public class RemoveComment {
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean comment = false;
        for(String line : source){
            int i = 0;
            if(!comment){
                sb = new StringBuilder();
            }
            while(i < line.length()){
                char c =line.charAt(i);
                if(!comment && c == '/' && i + 1 < line.length() && line.charAt(i + 1) == '*'){
                    i += 2;
                    comment = true;
                }else if(!comment && c == '/' && i + 1 < line.length() && line.charAt(i + 1) == '/'){
                    break;
                }else if(comment && c == '*' && i + 1 < line.length() && line.charAt(i + 1) == '/'){
                    i ++;
                    comment = false;
                }else if(!comment && i < line.length()){
                    sb.append(c);
                }
                i ++;
            }
            if(!comment && !sb.toString().isEmpty()){
                res.add(sb.toString());
            }
        }
        return res;
    }
}
