package edu.neu.companies.sonos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sonos {
    public static List<Integer> reverseArrayList(List<Integer> list){
        ArrayList<Integer> revArrayList = new ArrayList<Integer>();
        for (int i = list.size() - 1; i >= 0; i--) {
            revArrayList.add(list.get(i));
        }
        return revArrayList;
    }
    public static List<Integer> reverseArrayList1(List<Integer> list){
        Collections.reverse(list);
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list = reverseArrayList(list);
        System.out.println(list);
    }
}
