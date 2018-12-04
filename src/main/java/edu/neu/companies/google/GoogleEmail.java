package edu.neu.companies.google;

import java.util.*;

public class GoogleEmail {

    public int mergeEmail(List<String> emails){
        Set<String> set = new HashSet<>();
        Set<String> duplicate = new HashSet<>();
        int ans = 0;
        for(String email: emails){
            String local = email.split("@")[0];
            if(local.contains("+")){
                local=local.split("\\+")[0];
            }
            local = removeDot(local);
            email = local + email.split("@")[1];
            if(!set.add(email)){
                if(duplicate.add(email)){
                    ans++;
                }
            }
        }
        return ans;
    }
    public String removeDot(String email){
        char[] cs = email.toCharArray();
        int i = 0, j = 0;
        while(j < cs.length){
            if(j == 0 || j == cs.length - 1 ||cs[j] != '.'){
                cs[i++] =cs[j];
            }
            j++;
        }
        return new String(Arrays.copyOf(cs,i));
    }
    public static void main(String[] args){
        GoogleEmail googleEmail = new GoogleEmail();

        System.out.println(googleEmail.removeDot("...joe.doe"));
        System.out.println(googleEmail.removeDot("j.....o.e.d.o.e"));
        System.out.println(googleEmail.removeDot(".j.o.e.d.o.e."));
        System.out.println(googleEmail.removeDot("j.o.e.d.o.e."));

        List<String> emails = new ArrayList<>();
        emails.add("joe.doe@husky.neu.edu");
        emails.add("joe.doe+123@husky.neu.edu");
        emails.add("joe.doe+123@gmail.com");
        emails.add("joe.doe@gmail.com");
        emails.add("+joe.doe+123@gmail.com");
        emails.add("joe.doe@gmail.com");
        System.out.println(googleEmail.mergeEmail(emails));

    }
}

