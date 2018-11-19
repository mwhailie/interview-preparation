package edu.neu.companies.airbnb;

import java.util.LinkedList;
import java.util.Queue;

public class OceanIsland {
    public void floodFill(char[][] board, int i, int j) {
        if (board[i][j] != 'W' || board[i][j] == 'O') {
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i * board[0].length + j);
        board[i][j] = 'O';
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            int m = pos / board[0].length;
            int n = pos % board[0].length;
            if (m + 1 < board.length && board[m + 1][n] == 'W') {
                queue.add((m + 1) * board[0].length + n);
                board[m + 1][n] = 'O';
            }
            if (m - 1 >= 0 && board[m - 1][n] == 'W') {
                queue.add((m - 1) * board[0].length + n);
                board[m - 1][n] = 'O';
            }
            if (n + 1 < board[0].length && board[m][n + 1] == 'W') {
                queue.add(m * board[0].length + n + 1);
                board[m][n + 1] = 'O';
            }
            if (n - 1 >= 0 && board[m][n - 1] == 'W') {
                queue.add(m * board[0].length + n - 1);
                board[m][n - 1] = 'O';
            }
        }
    }
}
