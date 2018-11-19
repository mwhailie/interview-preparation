package edu.neu.companies.visa;

public class CardGame {

    public static String cardGame(int[] maria, int[] andrea, String strategy) {
        int mariaScore = 0;
        int i = strategy.toLowerCase().equals("even")? 0:1;
        for(;i < maria.length; i += 2){
            mariaScore += (maria[i] - andrea[i]);
        }
        if(mariaScore > 0){
            return "Maria";
        }else if(mariaScore < 0){
            return "Andea";
        }else{
            return "Tie";
        }
    }

    public static void main(String[] args) {
        System.out.println(cardGame(new int[]{0},new int[]{0}, "even"));
        System.out.println(cardGame(new int[]{1,2,3},new int[]{3,2,1}, "even"));
        System.out.println(cardGame(new int[]{1,2,3},new int[]{3,2,1}, "odd"));
        System.out.println(cardGame(new int[]{1,1,4},new int[]{3,2,1}, "even"));
        System.out.println(cardGame(new int[]{1,1,4},new int[]{3,2,1}, "odd"));
    }
}
