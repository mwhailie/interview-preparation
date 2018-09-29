package edu.neu.practice.companies.blackrock;


import java.io.IOException;
import java.util.*;

public class Stock {
    /*
     Vodafone,STOCK,10,50,0
     |Google,STOCK,15,50,0
     |Microsoft,BOND,15,100,0.05
     :
     Vodafone,STOCK,15,50,0
     |Google,STOCK,10,50,0
     |Microsoft,BOND,15,100,0.05


     */
    static class Asset {
        String name;
        int shares;
        double price;
        double accruedInterest;
        boolean stock;
        double  marketValue;
        double percentage;

        public Asset(String name, int shares, double price, double accruedInterest, boolean stock) {
            this.name = name;
            this.shares = shares;
            this.price = price;
            this.accruedInterest = accruedInterest;
            this.stock = stock;
            this.marketValue = stock? shares * price : shares * (price + accruedInterest)* 0.01;
        }

    }
    public static String interest(String input) {

        String[] portfolios = input.split(":")[0].split("\\|");
        String[] benchmarks = input.split(":")[1].split("\\|");
        TreeMap<String,Asset> portfolioMap = new TreeMap<>();
        TreeMap<String,Asset> benchmarkMap = new TreeMap<>();
        double pTotal = 0;
        double bTotal = 0;
        for(String portfolio : portfolios){
//                System.out.println(portfolio);
            String[] asset = portfolio.split(",");
//            for(String a : asset ){
//                System.out.println(a);
//            }
            Asset assest = new Asset(asset[0], Integer.parseInt(asset[2]),Double.parseDouble(asset[3]), Double.parseDouble(asset[4]), asset[1].equals("STOCK"));
            portfolioMap.put(asset[0],assest);
            pTotal += assest.marketValue;
        }
        for(String benchmark : benchmarks){

            String[] asset = benchmark.split(",");

            Asset assest = new Asset(asset[0], Integer.parseInt(asset[2]),Double.parseDouble(asset[3]), Double.parseDouble(asset[4]), asset[1].equals("STOCK"));
            benchmarkMap.put(asset[0],assest);
            bTotal += assest.marketValue;
        }
        List<String> res = new ArrayList<>();
        for(String assetName : portfolioMap.keySet()){
            Asset pAsset = portfolioMap.get(assetName);
            Asset bAsset = benchmarkMap.get(assetName);
            pAsset.percentage = pAsset.marketValue/pTotal;
            double targetPercent = bAsset.marketValue/bTotal;
            int targetAmount = 0; //  (int)(cur.share * targetPercent); /**/
            double targetValue = targetPercent * pTotal;
            if (pAsset.stock)
                targetAmount = (int)Math.round(targetValue / pAsset.price);
            else
                targetAmount = (int)Math.round( targetValue / (0.01 * (pAsset.price + pAsset.accruedInterest)));

            if (targetAmount == pAsset.shares) continue;
            else {
                String transaction = (pAsset.shares < targetAmount ? "BUY," : "SELL,")
                        + pAsset.name + ","
                        + Math.abs(targetAmount - pAsset.shares);
                res.add(transaction);
            }

        }
        return String.join("\n", res);

    }
    public static void main(String[] args) throws IOException {
        String line = "Vodafone,STOCK,10,50,0|Google,STOCK,15,50,0|Microsoft,BOND,15,100,0.05:Vodafone,STOCK,15,50,0|Google,STOCK,10,50,0|Microsoft,BOND,15,100,0.05";
        System.out.println(interest(line));
//        }
    }
}
