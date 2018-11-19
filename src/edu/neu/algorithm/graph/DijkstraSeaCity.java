package edu.neu.algorithm.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DijkstraSeaCity {
    /*
            1 2 3 4
    Sea		3 2 1 7     City
            2 3 2 4
            9 0 3 3
     */

    public int lowestSeaLevel(int[][] board){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return board[o1[0]][o1[1]] - board[o2[0]][o2[1]];
            }
        });
        int ROW = board.length;
        int COLUMN = board[0].length;
        int[][] directions = new int[][]{{1,0},{-1, 0},{0,1},{0, - 1}};
        boolean[][] visited = new boolean[ROW][COLUMN];
        for(int row = 0; row < ROW; row++){
            pq.offer(new int[]{0, row});
        }
        int max = Integer.MIN_VALUE;
        while(!pq.isEmpty()){
            int[] lowestPoint = pq.poll();
            visited[lowestPoint[0]][lowestPoint[1]] = true;
            max = Math.max(max, board[lowestPoint[0]][lowestPoint[1]]);
            if(lowestPoint[1] == COLUMN - 1){
                break;
            }
            for(int[] direction : directions){
                int lowestPointX = lowestPoint[0] + direction[0];
                int lowestPointY = lowestPoint[1] + direction[1];
                if(lowestPointX < 0 || lowestPointX >= ROW ||
                        lowestPointY < 0 || lowestPointY >= COLUMN ||
                        visited[lowestPointX][lowestPointY]){
                    continue;
                }
                pq.offer(new int[]{lowestPointX, lowestPointY});
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] board = new int[4][4];
        board[0] = new int[]{ 1,2,3,4};
        board[1] = new int[]{ 3,2,1,7};
        board[2] = new int[]{ 2,3,2,4};
        board[3] = new int[]{ 9,0,3,3};
        DijkstraSeaCity sc = new DijkstraSeaCity();

        System.out.println(sc.lowestSeaLevel(board ));
    }
}
