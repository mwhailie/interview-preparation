package edu.neu.companies.google;

import java.util.*;

public class CandyCrush {
/*
    如何初始化Candy Crush的棋盘? 限制有：
    1. 开始是八乘八的棋牌
    2. 有A,B,C,D四种糖
    3. 不能有三种连续的糖在一起（上下左右相连的连续三个不行）

    讲了下思路，然后小哥觉得可以就开始写代码，期间小哥给了一些提示。写完了以后开始follow up
    followup 1： 如果棋盘大小和糖的种类是变量怎么办？
    followup 2： 若何判断这个新初始化的棋盘的难易程度
*/

    public int[][] initiateBoard(int n, int k){
        int[][] board = new int[n][n];
        Random r = new Random(new Random().nextInt());
        List<Integer> chess = new ArrayList<>();
        for(int i = 1; i <= k; i ++){
            chess.add(i);
        }
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < n; j ++){
                int next = chess.get(r.nextInt(k));
                while(i - 2 >= 0 && next == board[i - 1][j] && next == board[i - 2][j]
                        ||j - 2 >= 0 && next == board[i][j-1] && next == board[i][j-2]){
                    next = chess.get(r.nextInt(k));
                }
                board[i][j] = next;
            }
        }
        return board;
    }



    public int[][] candyCrush(int[][] board) {
        int R = board.length, C = board[0].length;
        boolean todo = false;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c + 2 < C; ++c) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r][c+1]) && v == Math.abs(board[r][c+2])) {
                    board[r][c] = board[r][c+1] = board[r][c+2] = -v;
                    todo = true;
                }
            }
        }
        for (int r = 0; r + 2 < R; ++r) {
            for (int c = 0; c < C; ++c) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r+1][c]) && v == Math.abs(board[r+2][c])) {
                    board[r][c] = board[r+1][c] = board[r+2][c] = -v;
                    todo = true;
                }
            }
        }

        for (int c = 0; c < C; ++c) {
            int wr = R - 1;
            for (int r = R-1; r >= 0; --r)
                if (board[r][c] > 0)
                    board[wr--][c] = board[r][c];
            while (wr >= 0)
                board[wr--][c] = 0;
        }
//        show(board);
        return todo ? candyCrush(board) : board;
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
        CandyCrush cc = new CandyCrush();
        int[][] board = cc.initiateBoard(8, 4);
        cc.show(board);
        cc.show(cc.candyCrush(board));
    }
}
