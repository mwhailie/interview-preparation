package edu.neu.practice.companies.google;

import java.util.*;

public class RandomMarble {
    //O(N) N - # of color
    public static int getRandomMarble(int[] marbles){
        int total = 0;
        for(int numberOfMarble : marbles){
            total += numberOfMarble;
        }
        Random r = new Random();
        int pickedIndex = r.nextInt(total);
        int color = 0;
        while(pickedIndex - marbles[color] > 0){
            pickedIndex -= marbles[color ++];
        }
        return color;
    }
    public static int getRandomMarbleII(int[] marbles){
        int total = marbles.length;
        Random r = new Random();
        int pickedIndex = r.nextInt(total);
        int pickedColor = marbles[pickedIndex];
        int swap = marbles[-- total];
        marbles[pickedIndex] = swap;

        return pickedColor;
    }

    public static void main(String[] args) {
        System.out.println(getRandomMarble(new int[]{4,4,5,8}));
        System.out.println(getRandomMarbleII(new int[]{0,2,1,1,3,2,0}));
    }
}
