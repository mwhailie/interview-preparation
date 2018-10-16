package edu.neu.practice.companies.airbnb;

public class PourWater {
    public int[] dropRain(int[] heights, int V, int K){
        while(V-- > 0){
            int index = K;
            for(int i = K - 1; i >= 0; i --){
                if(heights[i] > heights[index]){
                    break;
                }else if(heights[i] < heights[index]){
                    index = i;
                }
            }
            if(index != K){
                heights[index] ++;
                for(int r : heights){
                    System.out.print(r + ", ");
                }
                System.out.println();
                continue;
            }
            for(int i = K + 1; i < heights.length; i ++){
                if(heights[i] > heights[index]){
                    break;
                }else if(heights[i] < heights[index]){
                    index = i;
                }
            }
            heights[index] ++;
            for(int r : heights){
                System.out.print(r + ", ");
            }
            System.out.println();
        }
        return heights;
    }
    public static void main(String[] args) {
        PourWater s = new PourWater();
        int[] res = s.dropRain(new int[]{2,1,1,2,1,2,2}, 4, 3);
        for(int r : res){
            System.out.print(r + ", ");
        }
        System.out.println();
         res = s.dropRain(new int[]{2,2,1,2,2,2,2}, 1, 2);
        for(int r : res){
            System.out.print(r + ", ");
        }
    }
}
