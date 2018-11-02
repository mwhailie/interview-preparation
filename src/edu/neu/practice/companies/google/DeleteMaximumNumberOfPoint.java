package edu.neu.practice.companies.google;

public class DeleteMaximumNumberOfPoint {
    int[] root;

    public int deleteMaximumNumberOfPoint(int[][] points){

        int n = points.length;
        root = new int[n];
        for(int i = 0; i < n; i++){
            root[i] = i;
        }
        int size = 0;
        for(int i = 0; i < n - 1; i ++){
            int x1 = points[i][0], y1 = points[i][1];
            for(int j = i + 1; j < n; j ++){
                int x2 = points[j][0], y2 = points[j][1];
                System.out.println(x1 == x2 || y1 == y2);

                if(x1 == x2 || y1 == y2){
                    System.out.println(find(i) +"==" + find(j));
                    if(union(i ,j)){
                        size ++;
                    }
                }
            }
        }
        return size;
    }

    private boolean union(int i, int j) {
        int root1 = find(i);
        int root2 = find(j);
        if(root1 == root2){
            return false;
        }
        root[root1] = root2;
        return true;
    }

    private int find(int i) {
        while(root[i] != i){
            i = root[i];
        }
        return i;
    }

    public static void main(String[] args) {
        DeleteMaximumNumberOfPoint d = new DeleteMaximumNumberOfPoint();
        System.out.println(d.deleteMaximumNumberOfPoint(new int[][]{{0,1},{1,0},{0,0}}));
        System.out.println(d.deleteMaximumNumberOfPoint(new int[][]{{0,1},{1,0},{0,0}}));
    }
}
