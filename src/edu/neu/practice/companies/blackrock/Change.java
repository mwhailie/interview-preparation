package edu.neu.practice.companies.blackrock;

import java.io.*;
import java.text.DecimalFormat;

public class Change {
        /**
         * Iterate through each line of input.
         */
        public static String change(String input){
            if(input == null || input.length() <= 3){
                return "ERROR";
            }
            String[] inputs = input.split(";");

            float actual = Float.valueOf(inputs[0]);
            float give = Float.valueOf(inputs[1]);
            if(give < actual){
                return "ERROR";
            }else if(give == actual){
                return "ZERO";
            }
            float change = give - actual;
            change = (float)Math.round(change * 100) / 100;
            String[] strs = new String[]{"ONE HUNDRED", "FIFTY", "TWENTY", "TEN", "FIVE", "TWO", "ONE", "HALF DOLLAR", "QUARTER", "DIME", "NICKEL", "PENNY"};
            float[] vals = new float[]{100f, 50f, 20f, 10f, 5f, 2f, 1f, 0.5f, 0.25f,0.1f, 0.05f,  0.01f };
            StringBuilder sb = new StringBuilder();
            while (change > 0) {
                if (Math.abs(change - 0.00) < 0.001 ) break;
                for (int i = 0; i < strs.length; i ++) {
                    if (change >= vals[i]) {
                        change -= vals[i];
                        change = (float)Math.round(change * 100) / 100;
                        sb.append(strs[i]);
                        sb.append(",");
                        break;
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        public static void main(String[] args) throws IOException {

            String line = "15.94;16.00";
            System.out.println(change(line));
        }

}
