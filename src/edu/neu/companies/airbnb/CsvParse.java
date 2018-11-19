package edu.neu.companies.airbnb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvParse {
    /*
        John,Smith,john.smith@gmail.com,Los Angeles,1
        Jane,Roberts,janer@msn.com,"San Francisco, CA",0
        "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
        """Alexandra Alex"""
        John|Smith|john.smith@gmail.com|Los Angeles|1
        Jane|Roberts|janer@msn.com|San Francisco, CA|0
        Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1
        "Alexandra Alex"
        */


    public static String parseCSV(String str) {
        List<String> res = new ArrayList<>();
        boolean inQuote = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (inQuote) {
                if (str.charAt(i) == '\"') {
                    if (i < str.length() - 1 && str.charAt(i + 1) == '\"') {
                        sb.append("\"");
                        i++;
                    } else {
                        inQuote = false;
                    }
                } else {
                    sb.append(str.charAt(i));
                }
            } else {
                if (str.charAt(i) == '\"') {
                    inQuote = true;
                } else if (str.charAt(i) == ',') {
                    res.add(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append(str.charAt(i));
                }
            }
        }

        if (sb.length() > 0) {
            res.add(sb.toString());
        }
        return String.join("|", res);
    }


        public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
            Scanner scanner = new Scanner(System.in);
            List<String> res = new ArrayList<>();
            boolean inQuote = false;
            StringBuilder sb = new StringBuilder();
            while(scanner.hasNextLine()){
                parseCSV(scanner.next());
                System.out.println("[first_name], [age] years old, is from [city] and is interested in [interests].");
            }
            int myInt = scanner.nextInt();
            scanner.close();
        }

}
