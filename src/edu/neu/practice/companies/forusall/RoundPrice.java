package edu.neu.practice.companies.forusall;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RoundPrice {
    public static int[] roundPrice(double[] prices) {
        double total = 0;
        int floorTotal = 0;


        PriorityQueue<Integer> indexes = new PriorityQueue<Integer>(new Comparator<Integer>(){
                    @Override
                    public int compare(Integer i1, Integer i2){

                        double dist1 = Math.ceil(prices[i1]) - prices[i1] == 0? 1 : Math.ceil(prices[i1]) - prices[i1] ;
                        double dist2 = Math.ceil(prices[i2]) - prices[i2] == 0? 1 : Math.ceil(prices[i2]) - prices[i2] ;
                        if(dist1 < dist2){
                            return -1;
                        }else if(dist1 == dist2){
                            return 0;
                        }else{
                            return 1;
                        }
                    }
                });
        for(int i = 0; i < prices.length; i ++){
            total += prices[i];
            floorTotal += Math.floor(prices[i]);
            indexes.offer(i);
        }
        int roundedTotal = (int) Math.round(total);
        int numberToCeil = roundedTotal - floorTotal;

        for(int i = 0; i < numberToCeil; i ++){
            indexes.poll();
        }
        int[] res = new int[prices.length];
        System.out.print("[");
        for(int i = 0; i < prices.length; i++){
            if(indexes.contains(i)){
                res[i] = (int)Math.floor(prices[i]);
            }else{
                res[i] = (int)Math.ceil(prices[i]);
            }
            System.out.print(res[i] + ", ");
        }
        System.out.print("]");
        return res;
    }

    public static void main(String[] args) {
        roundPrice(new double[]{2.2, 2.4, 2.6});
        roundPrice(new double[]{5.40, 3.30, 5.00});
    }
}
