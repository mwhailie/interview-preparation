package edu.neu.companies.forusall;

import java.util.*;

public class Building {

    /*
     * Complete the 'findMinDistance' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER w
     *  2. INTEGER h
     *  3. INTEGER n
     */

        /*
        public static int findMinDistance(int w, int h, int n) {
        // Write your code here
            if(n == 0){
                return Integer.MAX_VALUE;
            }
            if(w == 0 || h == 0 ){
                return 0;
            }
            if(n == 1){
                return Math.max(w, h)/2;
            }
            if(h == 1){
                if(n == 2){
                    return (w + 1) /4;
                }
                return w/(n + 1);
            }
            if(w == 1){
                if(n == 2){
                    return (h + 1) /4;
                }
                return h/(n + 1);
            }

            if(n == 2){
                return Math.max(w, h)/3;
            }
            return (int)Math.ceil(w * h / (double)9) ;
        }
        */

        public static int findMinDistance(int w, int h, int n) {
            if (w == 0 || h == 0) {
                return 0;
            }
            if (n == 1) {
                return Math.max(w, h) / 2;
            }

            boolean[][] building = new boolean[w][h];

            List<Integer> offices = new ArrayList<>();
            int[] globalMin = new int[]{Integer.MAX_VALUE};
            findMinDistance(building,0 , w, h, n, offices, globalMin);
            return globalMin[0];
        }

    public static void findMinDistance(boolean[][] buildings, int start, int w, int h, int n,
                                       List<Integer> offices, int[] globalMin) {
            if(n == 0){
                int max = bfs(w, h, offices);
//                for(int office : offices){
//                    System.out.print("(" + office/h + ", " + office % h + ") ");
//                }
//                System.out.println("max : " + max);
                globalMin[0] = Math.min(max, globalMin[0]);
                return;
            }
            for(int i = start; i < w * h; i++){

                if(offices.contains(i)){
                    continue;
                }
                offices.add(i);
                findMinDistance(buildings, i, w, h, n - 1,offices, globalMin);
                offices.remove(offices.size() - 1);

            }
    }

    private static int bfs(int w, int h, List<Integer> offices) {
        boolean[][] visited = new boolean[w][h];
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        for(int i : offices){
            q.add(new int[]{i/h, i%h});
        }
//        for(int[] office: q){
//            System.out.print("(" + office[0] + ", " + office[1] + ") ");
//        }
//        System.out.println();
        int step = 0;

        while(!q.isEmpty()){

            int size = q.size();
//            System.out.println("step "+ step + ", size " + size);
            for(int i = 0; i < size; i ++){
                int[] curr = q.poll();
                if(visited[curr[0]][curr[1]]){
                    continue;
                }
                visited[curr[0]][curr[1]] = true;
                for(int[] dir : dirs){
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if(x >= 0 && x < w && y >=0 && y < h && ! visited[x][y]){
//                        System.out.println("(" + x + ", " + y + ") ");
                        q.add(new int[]{x, y});
                    }
                }
            }
            step ++;
        }
        return step - 1;
    }

    public static void main(String[] args) {
        System.out.println(findMinDistance(2, 3, 2));
        System.out.println(findMinDistance(4, 4, 2));
        System.out.println(findMinDistance(4, 4, 3));
        System.out.println(findMinDistance(4,  6, 2));

    }
}
