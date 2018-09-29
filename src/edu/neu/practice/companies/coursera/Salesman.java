package edu.neu.practice.companies.coursera;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Salesman {
    int getDiscountedPrices(int[] prices){
        int discountedPrice = 0;
        Stack<Integer> s = new Stack<>();
        List<Integer> noDiscount = new ArrayList<>();
        List<Integer> discountedPrices = new ArrayList<>();
        s.push(0);
        for(int i = prices.length - 1; i >= 0; i --){
            while(s.peek() != 0 && s.peek() > prices[i]){
                s.pop();
            }
            if(s.peek() == 0 ){//if there is no larger prices on the left
                noDiscount.add(i);
            }
            discountedPrices.add(prices[i] - s.peek());
            discountedPrice += (prices[i] - s.peek());
            s.push(prices[i]);
        }
        System.out.println(discountedPrice);
        for(int i = noDiscount.size() - 1; i >= 0; i --){
            System.out.print(noDiscount.get(i) + " ");
        }
        System.out.println(" prices");
        for(int i :prices){
            System.out.print(i + " ");
        }
        System.out.println("Discounted prices");
        for(int i = discountedPrices.size() - 1; i >= 0; i --){
            System.out.print(discountedPrices.get(i) + " ");
        }
        return discountedPrice;
    }

    public static void main(String[] args) {
        Salesman salesman = new Salesman();
        salesman.getDiscountedPrices(new int[]{5,1,3,4,6,2});
    }
}
