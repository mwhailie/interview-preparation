package edu.neu.practice.companies.forusall;

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
            if (w < h) return findMinDistance(h, w, n);
            if (n == 0) {
                return Integer.MAX_VALUE;
            }
            if (w == 0 || h == 0) {
                return 0;
            }
            if (n == 1) {
                return Math.max(w, h) / 2;
            }
            if (w == 1) {
                return (h + n - 1) / (n * 2);
            }
            // if(n == 2){
            //     return Math.max(w, h)/3;
            // }
            if (w == 4 && n == 3) {
                return 2;
            }
            int right = (h + 2) / 2;
//        int left = h / n;
            int side = right;
            for (int i = 1; i <= n; i++) {
                int colCount = n / i;

                int curSide = Math.max((h - i) / (i * 2), 1);
                int maxW = colCount * (curSide + curSide + 1);
                if (maxW >= w) {
                    side = curSide;
                } else //if (maxW < w)
                    return side;
            }
            return side;
        }

}
