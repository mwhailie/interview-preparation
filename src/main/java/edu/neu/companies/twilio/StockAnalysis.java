package edu.neu.companies.twilio;

import java.util.*;

public class StockAnalysis {
    String[] computeParameterValue(String[][] source){
        Map<String, String> map = new HashMap<>();
        for(String[] kv : source){
            map.put(kv[0], kv[1]);
        }
        String[] result = new String[map.size()];
        int i = 0;
        for(String value : map.values()){
            result[i ++] = value;
        }
        return result;
    }
}
