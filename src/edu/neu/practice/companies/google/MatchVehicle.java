package edu.neu.practice.companies.google;

import java.util.*;

public class MatchVehicle {
    /*
    a 2 3 1            t a 2 3
    9 5 4 d            9 c d 4
    b 6 8 7		   8 7 6 5
    e c h g

    test output
    (a,2)
    (b,9)
    (c,6)
    (d,7)
    (h,8)
    (e,5)
    (g,1)

     */
    public static char[][] match(char[][] matrix){
        int COLUMN = matrix.length;
        int ROW = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] pair1, int[] pair2){
                int dis1 = getDis(pair1[0]/COLUMN, pair1[0]%COLUMN, pair1[1]/COLUMN, pair1[1]%COLUMN);
                int dis2 = getDis(pair2[0]/COLUMN, pair2[0]%COLUMN, pair2[1]/COLUMN, pair2[1]%COLUMN);
                if(dis1 != dis2){
                    return dis1 - dis2;
                }else{
                    return matrix[pair1[0]/COLUMN][pair1[0]%COLUMN] - matrix[pair2[0]/COLUMN][pair2[0]%COLUMN];
                }
            }
        });
        Set<Integer> ppls = new HashSet<>();
        Set<Integer> cars = new HashSet<>();
        for(int i = 0; i < ROW; i ++){
            for(int j = 0; j < COLUMN; j ++){
                int id = i * COLUMN + j;
                if(Character.isDigit(matrix[i][j])){
                    cars.add(id);
                }else{
                    ppls.add(id);
                }
            }
        }
        int numOfPeople = ppls.size();
        char[][] res = new char[numOfPeople][2];
        for(int peopleID : ppls){
            for(int carID : cars){
                int[] pair = new int[]{peopleID, carID};
                pq.offer(pair);
            }
        }
        int i = 0;
        while(i < numOfPeople && !pq.isEmpty()){
            int[] pair = pq.poll();
            if(ppls.contains(pair[0]) && cars.contains(pair[1])){
                ppls.remove(pair[0]);
                cars.remove(pair[1]);
                res[i][0] = matrix[pair[0]/COLUMN][pair[0]%COLUMN];
                res[i][1] = matrix[pair[1]/COLUMN][pair[1]%COLUMN];
                i++;
            }
        }
        return res;
    }

    private static int getDis(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) +  Math.abs(y1 - y2);
    }

    public static void main(String[] args) {
        char[][] matrix = new char[4][4];
        matrix[0] = new char[]{'a','2','3','1'};
        matrix[1] = new char[]{'9','5','4','d'};
        matrix[2] = new char[]{'b','6','8','7'};
        matrix[3] = new char[]{'e','c','h','g'};
        char[][] match = match(matrix);
        for(char[] pair :  match){
            System.out.println("(" + pair[0] + ","+ pair[1] + ")");
        }

    }
}
