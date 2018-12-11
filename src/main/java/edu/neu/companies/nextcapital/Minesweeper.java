package edu.neu.companies.nextcapital;

public class Minesweeper {

    int ROW;
    int COL;

    public int[][] calculate(char[][] board){
        ROW = board.length;
        COL = board[0].length;
        int[][] res = new int[ROW][COL];
        int[][] directions = new int[][]{{1, 0},{0, 1},{-1, 0},{0, -1}, {1, 1}, {1, -1},{-1, 1},{-1, -1}};
        boolean[] rows = new boolean[ROW];
        boolean[][] corner = new boolean[ROW][COL];
        for(int i = 0; i < ROW; i ++){
            for(int j = 0; j < COL; j ++){
                if(board[i][j] == 'm'){
                    rows[i] = true;
                    continue;
                }
                for(int[] direction : directions){
                    int x = i + direction[0];
                    int y = j + direction[1];
                    if(isValid(x, y) && board[x][y] == 'm'){
                        res[i][j] ++;
                        if(direction[0] != 0 && direction[1] != 0){
                            corner[i][j] = true;
                        }
                    }
                }
                if(i != 0 && board[i - 1][j] == 'm'){
                    res[i][j] = 2;
                }
                if(j != 0 && board[i][j - 1] == 'm'){
                    res[i][j] = 0;
                }
            }
        }
        print(res);
        for(int i = 0; i < ROW; i ++){
            for (int j = 0; j < COL; j++) {
                if(i%2 == 1 && rows[i])
                    res[i][j] *= 3;
                if(corner[i][j])
                    res[i][j] *= 2;
                if(board[i][j] == 'm')
                    res[i][j] = - 1;
            }
        }
        return res;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < ROW && y < COL;
    }

    public void print(int[][] res){
        for(int i = 0; i < ROW; i ++){
            for (int j = 0; j < COL; j++) {
                System.out.print(res[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("--------");
    }
    public static void main(String[] args) {
        Minesweeper m = new Minesweeper();
        char[][] board = new char[][]{{'.','m','.','.'}, {'.','.','.','.'}, {'.','.','.','m'}, {'m','.','.','.'}};
//        board[0] = new char[]{};
        m.print(m.calculate(board));
    }
}
