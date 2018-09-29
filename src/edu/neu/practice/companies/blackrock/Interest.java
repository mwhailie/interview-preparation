package edu.neu.practice.companies.blackrock;

import java.io.*
        ;
import java.nio.charset.StandardCharsets;

public class Interest {
    /*    P = (monthly rate * Loan amount) / (1 - (1+monthly interest rate)^-n) Here n is the number of payment periods.?
            6000~5~6~0

     */
    public static String interest(String input) {
        String[] inputs = input.split("~");
        double totalLoan = Integer.parseInt(inputs[0]);
        int n = Integer.parseInt(inputs[1]) * 12;
        double annualRate = Double.parseDouble(inputs[2])/100;
        double downPayment = Double.parseDouble(inputs[3]);
        double loan = totalLoan - downPayment;
        double monthlyRate = annualRate/12;
        double p = monthlyRate * loan / (1 - Math.pow( (1 + monthlyRate), - n ));
        double totalInterestPayment = p * n - loan ;
        return "$" + String.format("%.2f", p) + "~" + "$" + Math.round(totalInterestPayment);
    }
    public static void main(String[] args) throws IOException {
//        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
//        BufferedReader in = new BufferedReader(reader);
        String line = "25000~10~6~0";
//        while ((line = in.readLine()) != null) {
            System.out.println(interest(line));
//        }
    }
}
