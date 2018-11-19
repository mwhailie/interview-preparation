package edu.neu.algorithm.dynamicprogramming;

public class UniquePath {
    /*
        dp:
        path[i][j] stands for the number of UniquePath from (0, 0) to (i, j)

        initialize:
        for i from 0 to m
            path[i][0] = 1
        for j from 0 to n
            path[0][j] = 1

        rule:
        for i from 0 to m
            for j from 0 to n
                path[i][j] = path[i - 1][j] + path[i][j - 1];

        answer:
        path[m - 1][n - 1]
     */
    public int uniquePaths(int m, int n) {
        int[][] path = new int[m][n];
        for(int i = 0; i < m ; i ++){
            path[i][0] = 1;
        }
        for(int i = 0; i < n ; i ++){
            path[0][i] = 1;
        }
        for(int i = 1; i < m ; i ++){
            for(int j = 1; j < n ; j ++){
                path[i][j] = path[i - 1][j] + path[i][j - 1];
            }
        }
        return path[m - 1][n - 1];
    }
}
