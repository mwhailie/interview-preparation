package edu.neu.companies.airbnb;

import java.net.*;
import java.io.*;

import java.util.*;
public class GuessNumber {

    private int guessServer(String guess) {
        Scanner scanner = new Scanner( System.in );
        scanner.nextLine();
        Integer ans = null;
        try{
            Socket socket = new Socket("localhost", 8090 );

            // Read from this object.
            ObjectInputStream in = new ObjectInputStream( socket.getInputStream() );
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.write(1111);
            ans = ( Integer ) in.readObject();
            System.out.println("(Client) Answer read from server : " + ans );
            out.close();
            in.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return ans;

    }
    private String genNumber(List<Integer> guessed, int c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guessed.size(); i++)
            sb.append(guessed.get(i));
        for (int i = guessed.size(); i < 4; i++)
            sb.append(c);
        return sb.toString();
    }

    public String guess() {
        List<Integer> res = new ArrayList<>();
        List<Integer> cands = new ArrayList<Integer>();
        for(int i = 1; i <= 5; i++){
            cands.add(i);
        }
// System.out.println("\nstart to guess " + target + " ...");
// System.out.println("res: " + res);
// System.out.println("candList: " + candList);
        int counter = 0;
        Iterator<Integer> iter = cands.iterator();
        while (iter.hasNext() && res.size() < 4) {
            int cand = iter.next();
            counter++;
            int guessedCount = res.size();
            String guessCand = genNumber(res, cand);
            int guessRes = guessServer(guessCand);
// System.out.println("cand: " + cand);
// System.out.println("guessRes: " + guessRes);
            if (guessRes == guessedCount) {
                iter.remove();
            } else if (guessRes > guessedCount) {
                for (int i=guessedCount; i< guessRes; i++) {
                    res.add(cand);
                }
                iter.remove();
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < res.size(); i++)
                    sb.append(res.get(i));
                return sb.toString();
            }
        }
        if (res.size() < 4) {
            for (int i=res.size(); i<4; i++)
                res.add(6);
        }
// System.out.println("guessed " + counter + " times");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++)
            sb.append(res.get(i));
        return sb.toString();
    }



    public String findSecretWord(String[] wordlist) {
        while (true){
            HashMap<String, Integer> count = new HashMap<>();
            for (String w1 : wordlist)
                for (String w2 : wordlist)
                    if (match(w1, w2) == 0)
                        count.put(w1, count.getOrDefault(w1 , 0) + 1);
            String word = new String();
            int minimax = 1000;
            for (String w1 : wordlist){
                if(count.getOrDefault(w1, 0) < minimax){
                    word = w1;
                    minimax = count.getOrDefault(w1, 0);
                }
            }
            // int guess = new Random().nextInt(wordlist.length);
            // String word = wordlist[guess];

            int match = guessServer(word);
            if(match == word.length()){
                return new String(word);
            }
            List<String> wordlist2 = new ArrayList<>();
            for(String possibleWord : wordlist){
                if(match(word, possibleWord) == match){
                    wordlist2.add(possibleWord);
                }
            }
            wordlist = wordlist2.toArray(new String[wordlist2.size()]);
        }
    }
    private int match(String word1, String word2){
        int match = 0;
        for(int i = 0; i < word1.length(); i ++){
            if(word1.charAt(i) == word2.charAt(i)){
                match ++;
            }
        }
        return match;
    }

    public static List<String> combine(int n, int k) {
        List<String> combs = new ArrayList<>();
        backtracking(combs, new ArrayList<Integer>(), 1, n, k);
        return combs;
    }
    public static void backtracking(List<String> combs, List<Integer> comb, int start, int n, int k) {
        if(k==0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < comb.size(); i++)
                sb.append(comb.get(i));
            combs.add(sb.toString());
            return;
        }
        for(int i=start;i<=n;i++) {
            comb.add(i);
            backtracking(combs, comb, i, n, k-1);
            comb.remove(comb.size()-1);
        }
    }
    public static void main ( String [] args ) {
        System.out.println(combine(6,4));


    }
}
