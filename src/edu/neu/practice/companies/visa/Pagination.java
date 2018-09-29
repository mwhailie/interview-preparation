package edu.neu.practice.companies.visa;

import java.util.*;

public class Pagination {

    public static List<String> fetchItemsToDisplay(String[][] items,
                                                   int sortParameter, int sortOrder,
                                                   int x, int y) {
        int start = x * y;
        if(start > items.length){
            return null;
        }
        Arrays.sort(items, new Comparator<String[]>(){
            @Override
            public int compare(String[] item1, String[] item2){
                if(sortParameter == 0){
                    if(sortOrder == 1){
                        return item2[0].compareTo(item1[0]);
                    }else{
                        return item1[0].compareTo(item2[0]);
                    }
                }
                int i1 = Integer.parseInt(item1[sortParameter]);
                int i2 = Integer.parseInt(item2[sortParameter]);
                if(sortOrder == 1){
                    return i2 - i1;
                }else{
                    return i1 - i2;
                }
            }
        });
        List<String> res = new ArrayList<>();
        for(int i = 0; i < x && start + i < items.length; i ++){
            res.add(items[start + i][0]);
        }
        return res;
    }
    public static void main(String[] args) {
        String[][] items = new String[3][3];
        items[0] = new String[]{"Item1", "3", "5"};
        items[1] = new String[]{"Item2", "32", "15"};
        items[2] = new String[]{"Item3", "30", "3"};
        fetchItemsToDisplay(items, 1, 1,1,0);
        fetchItemsToDisplay(items, 1, 1,1,1);
        fetchItemsToDisplay(items, 1, 1,1,2);
        fetchItemsToDisplay(items, 1, 1,2,1);
        int x = 2437, y = 875;
        while(x != y){
            if(x > y){
                x = x-y;
            }else {
                y = y-x;
            }
        }
        System.out.println("x  " + x);
    }
}
