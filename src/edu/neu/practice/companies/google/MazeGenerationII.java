package edu.neu.practice.companies.google;

import java.util.*;

public class MazeGenerationII {
    private int[][] board;
    private int[][] directions;
    int n;
    Random r;
    public MazeGenerationII(int n) {
        this.n = n;
        board = new int[n][n];
        directions = new int[][]{{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
        for(int i = 0; i < n; i += 2){
            Arrays.fill(board[i], 1);
        }
        for(int i = 1; i < n; i += 2){
            for(int j = 0; j < n; j += 2){
                board[i][j] = 1;
            }
        }
        show(board);
        r = new Random();
        boolean[][] visited = new boolean[n][n];
        dfs(board, visited, 1, 1);
        show(board);
    }

    private void dfs(int[][] board, boolean[][] visited, int x, int y) {

        visited[x][y] = true;
        while(isValid(x, y + 2) && !visited[x][y+2]
                || isValid(x + 2, y) && !visited[x+2][y]
                || isValid(x, y - 2) && !visited[x][y-2]
                || isValid(x - 2, y) &&!visited[x-2][y]){
            int randomIndex = 0;
            while(true){
                randomIndex = r.nextInt(4);
                int[] randomDirection = directions[randomIndex];
                int nextX = x + randomDirection[0], nextY = y + randomDirection[1];
                if(isValid(nextX, nextY) && !visited[nextX][nextY]){
                    switch (randomIndex){
                        case 0:
                            board[x + 1][y] = 0;
                            break;
                        case 1:
                            board[x][y + 1] = 0;
                            break;
                        case 2:
                            board[x - 1][y] = 0;
                            break;
                        case 3:
                            board[x][y - 1] = 0;
                            break;
                    }
                    dfs(board, visited, nextX, nextY);
                    break;
                }
            }

        }


    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    public void show(int[][] board) {
        for(int[] i : board) {
            for(int j : i) {
                System.out.print(j+" ");
            }
            System.out.println("");
        }
        System.out.println("----------------");
    }

    public static void main(String[] args) {
        MazeGenerationII m = new MazeGenerationII(15);
    }
}
