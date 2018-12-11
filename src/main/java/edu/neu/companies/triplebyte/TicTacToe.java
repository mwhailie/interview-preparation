package edu.neu.companies.triplebyte;

import java.util.Scanner;

public class TicTacToe {
    int[][] board;
    int n;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
        this.n = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        boolean win = true;
        for(int i = 0; i < n; i ++){
            if(board[row][i] != player){
                win = false;
                break;
            }
        }
        if(win){
            return player;
        }
        win = true;
        for(int i = 0; i < n; i ++){
            if(board[i][col] != player){
                win = false;
                break;
            }
        }
        if(win){
            return player;
        }
        win = true;
        for(int i = 0; i < n; i ++){
            if(board[i][i] != player){
                win = false;
                break;
            }
        }
        if(win){
            return player;
        }
        win = true;
        for(int i = 0; i < n; i ++){
            if(board[n - 1 - i][i] != player){
                win = false;
                break;
            }
        }
        if(win){
            return player;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("n:");
        int n = sc.nextInt();
        TicTacToe t = new TicTacToe(n);
        int result = 0;
        while(result == 0){
            System.out.println("x:");
            int x = sc.nextInt();
            System.out.println("y:");
            int y = sc.nextInt();
            result = t.move(x, y, 1);

        }
        System.out.println("Player " + result + " win!");
    }
}
