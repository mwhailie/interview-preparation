package edu.neu.companies.google;
import java.util.*;
import java.io.*;

class Solution {

    public int calculateError(int k, int[] ingredients){
        Arrays.sort(ingredients);
        int error = 0;
        for(int i = 0; i < k / 2; i ++){
            error += Math.pow(i - ingredients[i * 2], 2);
            error += Math.pow(i - ingredients[i * 2 + 1], 2);
        }
        if(k % 2 == 1){
            error += Math.pow(k/2 - ingredients[k - 1], 2);
        }
        return error;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int numOfDataset = sc.nextInt();
        Solution burger = new Solution();
        try{
            for(int i = 0; i < numOfDataset; i ++){
                int size = sc.nextInt();
                int[] ingredients = new int[size];
                for(int j = 0; j < size; j++){
                    ingredients[j] = sc.nextInt();
                }
                System.out.println("Case #" + (i + 1) + ": " + burger.calculateError(size, ingredients));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        sc.close();
    }
}
