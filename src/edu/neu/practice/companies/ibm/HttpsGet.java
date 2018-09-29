package edu.neu.practice.companies.ibm;

import java.io.*;
import java.net.*;

public class HttpsGet {

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
//            System.out.println();
//            System.out.print(line);
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println(getHTML("https://jsonmock.hackerrank.com/api/countries/search?name=ch"));
    }
}
