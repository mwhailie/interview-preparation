package edu.neu.practice.companies.airbnb;

import java.util.*;

public class CombinationSum {
    public List<List<Double>> getCombo(double[] prices, double money){
        Arrays.sort(prices);
        List<List<Double>> combos = new ArrayList<>();
        getCombo(prices, money, 0, combos, new ArrayList<>());
        return combos;
    }
    public void getCombo(double[] prices, double money, int start,
                         List<List<Double>> combos, List<Double> combo){
        if(Math.abs(money) < 0.001){
            combos.add(combo);
        }
        if(money < 0){
            return;
        }
        for(int i = start; i < prices.length; i ++){
            if (i > start && prices[i] == prices[i - 1]) {
                continue;
            }
            if (prices[i] > money) {
                break;
            }
            combo.add(prices[i]);
            getCombo(prices, money - prices[i], start + 1, combos, combo);
            combo.remove(combo.size() - 1);
        }
    }
}
