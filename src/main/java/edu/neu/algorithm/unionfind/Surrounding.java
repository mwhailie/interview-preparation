package edu.neu.algorithm.unionfind;

public class Surrounding {
    class UnionFind{
        int[] root;
        public UnionFind(int n){
            root = new int[n + 1];
            for(int i = 0; i <= n; i ++){
                root[i] = i;
            }
        }
        void union(int i, int j){
            int rootI = find(i);
            int rootJ = find(j);
            if(rootI == rootJ){
                return;
            }else if(rootI < rootJ){
                root[rootI] = rootJ;
            }else {
                root[rootJ] = rootI;
            }
        }
        int find(int i){
            while(root[i] != i){
                i = root[i];
            }
            return i;
        }
    }
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0 ){
            return;
        }
        int COLUMN = board[0].length;
        int ROW = board.length;
        int n = ROW * COLUMN;
        UnionFind uf = new UnionFind(n);
        int[][] directions = new int[][]{{0,1},{1,0}};
        for(int row = 0; row < ROW ; row ++){
            int col = 0;
            if(board[row][col] == 'O'){
                uf.union(row * COLUMN + col, n);
            }
            col = COLUMN - 1;
            if(board[row][col] == 'O'){
                uf.union(row * COLUMN + col, n);
            }
        }
        for(int col = 0; col < COLUMN; col ++){
            int row = 0;
            if(board[0][col] == 'O'){
                uf.union(row * COLUMN + col, n);
            }
            row = ROW - 1;
            if(board[row][col] == 'O'){
                uf.union(row * COLUMN + col, n);
            }
        }
        for(int row = 0; row < ROW; row ++){
            for(int col = 0; col < COLUMN; col ++){
                if(board[row][col] == 'X'){
                    continue;
                }
                for(int[] direction : directions){
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];
                    if(newRow >= ROW || newCol >= COLUMN ||board[newRow][newCol] == 'X'){
                        continue;
                    }
                    int newId = newRow * COLUMN + newCol;
                    int id = row * COLUMN + col;
                    uf.union(newId, id);
                }
            }
        }
        for(int row = 0; row < ROW - 1; row ++){
            for(int col = 0; col < COLUMN - 1; col ++){
                if(board[row][col] == 'O' && uf.find(row * COLUMN + col) != n){
                    board[row][col] = 'X' ;
                }
            }
        }
    }

    public static void main(String[] args) {
        Surrounding s = new Surrounding();
        char[][] board = new char[4][4];
        for(int row = 0; row < board.length; row ++){
            for(int col = 0; col < board[0].length; col ++) {
                board[row][col] = 'X';
            }
        }
        board[0][0] = 'O';
        board[1][0] = 'O';
        board[1][1] = 'O';
        board[2][2] = 'O';
        for(int row = 0; row < board.length; row ++){
            System.out.println();
            for(int col = 0; col < board[0].length; col ++) {
                System.out.print(board[row][col] + " ");
            }
        }
        s.solve(board);
        for(int row = 0; row < board.length; row ++){
            System.out.println();
            for(int col = 0; col < board[0].length; col ++) {
                System.out.print(board[row][col] + " ");
            }
        }
    }
}

