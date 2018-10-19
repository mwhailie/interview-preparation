package edu.neu.practice.companies.airbnb;

import java.util.*;

public class Kingdom {
    int ROW ;
    int COLUMN ;
    public int calculate_score(List<String> input){
        int score = 0;
        ROW = input.size();
        COLUMN = input.get(0).split(" ").length;
        char[][] type = new char[ROW][COLUMN];
        int[][] crown = new int[ROW][COLUMN];
        for(int i = 0; i < ROW; i ++){
            String[] properties = input.get(i).split(" ");
            for(int j = 0; j < COLUMN; j ++){
                type[i][j] = properties[j].charAt(0);
                crown[i][j] = properties[j].charAt(1) - '0';
            }
        }
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < ROW; i ++){
            for(int j = 0; j < COLUMN; j ++){
                int id  = i * COLUMN + j;

                if(crown[i][j] != 0 && !visited.contains(id)){
                    //System.out.println(id);
                    score += bfs(crown, type, id, visited, type[i][j]);
                    System.out.println("score " + score);
                }
            }
        }
        return score;
    }
    private int bfs(int[][] crown , char[][] type , int id,  Set<Integer> visited, char currType){
        int numOfCrowm = 0;
        int numOfLand = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(id);
        while(!q.isEmpty()){
            int curr = q.poll();
            if(visited.contains(curr)){
                continue;
            }
            visited.add(curr);
            int currRow = curr/COLUMN;
            int currCol = curr%COLUMN;
            numOfLand ++;
            // System.out.println("poll" + id);
            // if(crown[currRow][currCol] != 0){
            numOfCrowm += crown[currRow][currCol];
            System.out.println(" id " + curr + " numOfCrowm " + numOfCrowm + " numOfLand " + numOfLand);

            // }
            if(currRow + 1 < ROW && type[currRow + 1][currCol] == currType
                    && !visited.contains( (currRow + 1) * COLUMN + currCol)){
                q.add((currRow + 1) * COLUMN + currCol);
            }
            if(currRow - 1 >= 0 && type[currRow - 1][currCol] == currType
                    && !visited.contains( (currRow - 1) * COLUMN + currCol)){
                q.add((currRow - 1) * COLUMN + currCol);
            }
            if(currCol + 1 < COLUMN && type[currRow][currCol + 1] == currType
                    && !visited.contains(currRow * COLUMN + currCol + 1)){
                q.add(currRow * COLUMN + currCol + 1);
            }
            if(currCol - 1 >= 0 && type[currRow][currCol - 1] == currType
                    && !visited.contains( currRow* COLUMN + currCol - 1)){
                q.add(currRow* COLUMN + currCol - 1);
            }
        }
        return numOfLand * numOfCrowm;
    }
    public static void main(String[] args){
        Kingdom k = new Kingdom();
        List<String> input = new ArrayList<String>();
        input.add("G0 W1 W1 W0 P2");
        input.add("W0 W0 F0 F0 F0");
        input.add("W0 W1 F0 S2 S1");
        input.add("G0 X0 G1 G0 G0");
        input.add("S0 M2 M0 G1 F0");
        System.out.println(k.calculate_score(input));
    }
}
