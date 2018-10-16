package edu.neu.practice.companies.airbnb;

import java.util.*;

public class SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        int ROW = board.length;
        int COLUMN = board[0].length;
        String start = new String();
        String end = new String();
        for(int i = 0; i < ROW; i ++){
            for(int j = 0; j < COLUMN; j ++){
                start += "" + (board[i][j]);
            }
        }
        int i = 1;
        while(i  < ROW * COLUMN){
            end+= "" + (i ++);
        }
        end+= "" + ("0");
        System.out.println(end);
        if(start.equals(end)){
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        q.offer(start.toString());
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            level++;
            for(i = 0; i < size; i++){
                String curr = q.poll();
                System.out.println(curr+"--------------------");
                int position = curr.indexOf("0");
                int positionX = position/COLUMN;
                int positionY = position%COLUMN;
                for(int[] direction : directions){
                    int newPositionX = positionX + direction[0];
                    int newPositionY = positionY + direction[1];
                    if(newPositionX < 0 || newPositionX >= ROW || newPositionY < 0 || newPositionY >= COLUMN){
                        continue;
                    }
                    int newPosition = newPositionX * COLUMN + newPositionY;
                    String neighbor = getNeighbor( curr.toCharArray(), position, newPosition);
                    System.out.println(neighbor);
                    if(neighbor.equals(end)){
                        return level;
                    }
                    if(visited.contains(neighbor)){
                        continue;
                    }
                    q.offer(neighbor);
                    visited.add(curr);
                }
            }
        }
        return level;
    }
    private String getNeighbor(char[] cs , int position, int newPostion){
        char c = cs[position];
        cs[position] = cs[newPostion];
        cs[newPostion] = c;
        return new String(cs);
    }

    public static void main(String[] args) {
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        System.out.println(slidingPuzzle.slidingPuzzle(new int[][]{{4,1,2},{5,0,3}}));
    }
}
