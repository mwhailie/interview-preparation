package edu.neu.practice.companies.airbnb;

import java.util.*;

public class IntersectRectangle {
    private boolean intersect(int[][] r1, int[][] r2) {
        return r1[0][0] < r2[0][0] && r1[0][1] < r2[0][1] && r1[1][0] >
                r2[0][0] && r1[1][1] > r2[0][1] ||
                r1[0][0] < r2[1][0] && r1[0][1] < r2[1][1] && r1[1][0] >
                        r2[1][0] && r1[1][1] > r2[1][1];
    }
    private int find(int val, int[] parents) {
        while (parents[val] != val) {
            val = parents[val];
        }
        return val;
    }
    private boolean union(int i, int j, int[] parents) {
        int root1 = find(i, parents);
        int root2 = find(j, parents);
        if (root1 == root2) {
            return false;
        }
        parents[root1] = root2;
        return true;
    }
    public int countIntersection(int[][][] rectangles) {
        int[] parents = new int[rectangles.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        int N = rectangles.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (intersect(rectangles[i], rectangles[j])) {
                    if (union(i, j, parents)) {
                        count++;
                    }
                }
            }
        }

        return N - count;
    }
}
