package edu.neu.practice.companies.google;
import edu.princeton.cs.algs4.*;

import java.util.*;

public class MazeGeneration {
    private int n;                 // dimension of maze
    private boolean[][] top;     // is there a wall to north of cell i, j
    private boolean[][] right;
    private boolean[][] bottom;
    private boolean[][] left;
    private boolean[][] visited;
    private boolean done = false;

    public MazeGeneration(int n) {
        this.n = n;
        StdDraw.setXscale(0, n+2);
        StdDraw.setYscale(0, n+2);
        init();
        generate();
    }

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            visited[x][0] = true;
            visited[x][n+1] = true;
        }
        for (int y = 0; y < n+2; y++) {
            visited[0][y] = true;
            visited[n+1][y] = true;
        }


        // initialze all walls as present
        top = new boolean[n+2][n+2];
        right  = new boolean[n+2][n+2];
        bottom = new boolean[n+2][n+2];
        left  = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            Arrays.fill(top[x], true);
            Arrays.fill(right[x], true);
            Arrays.fill(bottom[x], true);
            Arrays.fill(left[x], true);
        }
    }


    // generate the maze
    private void generate(int x, int y) {
        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {
                double r = StdRandom.uniform(4);
                if (r == 0 && !visited[x][y+1]) {
                    top[x][y] = false;
                    bottom[x][y+1] = false;
                    generate(x, y + 1);
                    break;
                }
                else if (r == 1 && !visited[x+1][y]) {
                    right[x][y] = false;
//                    left[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                else if (r == 2 && !visited[x][y-1]) {
                    bottom[x][y] = false;
                    top[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                else if (r == 3 && !visited[x-1][y]) {
                    left[x][y] = false;
                    right[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }

    // generate the maze starting from lower left
    private void generate() {
        generate(1, 1);
    }



    // solve the maze using depth-first search
    private void solve(int x, int y) {
        if (x == 0 || y == 0 || x == n+1 || y == n+1) return;
        if (done || visited[x][y]) return;
        visited[x][y] = true;

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show();
        StdDraw.pause(30);

        // reached middle
        if (x == n/2 && y == n/2) done = true;

        if (!top[x][y]) solve(x, y + 1);
        if (!right[x][y])  solve(x + 1, y);
        if (!bottom[x][y]) solve(x, y - 1);
        if (!left[x][y])  solve(x - 1, y);

        if (done) return;

        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show();
        StdDraw.pause(30);
    }

    // solve the maze starting from the start state
    public void solve() {
        for (int x = 1; x <= n; x++)
            for (int y = 1; y <= n; y++)
                visited[x][y] = false;
        done = false;
        solve(1, 1);
    }

    // draw the maze
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(n/2.0 + 0.5, n/2.0 + 0.5, 0.375);
        StdDraw.filledCircle(1.5, 1.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                if (bottom[x][y]) StdDraw.line(x, y, x+1, y);
                if (top[x][y]) StdDraw.line(x, y+1, x+1, y+1);
                if (left[x][y])  StdDraw.line(x, y, x, y+1);
                if (right[x][y])  StdDraw.line(x+1, y, x+1, y+1);
            }
        }
        StdDraw.show();
        StdDraw.pause(1000);
    }



    // a test client
    public static void main(String[] args) {
//        int n = Integer.parseInt(args[0]);
        MazeGeneration maze = new MazeGeneration(60);
        StdDraw.enableDoubleBuffering();
        maze.draw();
        maze.solve();
    }

}
