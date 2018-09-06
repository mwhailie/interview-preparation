package edu.neu.practice.twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwitterPrimeTree {
    static class Node{
        int value;
        int numOfPrimeInSubTree;
        List<Node> children;
        public Node( int value) {
            this.value = value;
            this.numOfPrimeInSubTree = -1;
            children = new ArrayList<>();
        }
    }
    static public List<Integer> primeQuery(int n , List<Integer> u,
                                    List<Integer> v,
                                    List<Integer> values,
                                    List<Integer> queries){
        Node[] nodeList = new Node[n];
        for(int i = 0; i < values.size(); i++){
                Node node = new Node( values.get(i));
                nodeList[i] = node;
        }
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i < n; i++){
            tree.add(new ArrayList<>());
        }
        for(int i = 0; i < u.size(); i++){
            tree.get(u.get(i) - 1).add(v.get(i) - 1);
        }
        List<Integer> primeQuery = new ArrayList<>();
        for(int i  = 0; i < queries.size(); i ++){
            int p = primeQuery(tree, nodeList, queries.get(i));
            primeQuery.add(p);
            System.out.print(p + " ");
        }
        return primeQuery;
    }
    static private int primeQuery(List<List<Integer>> tree, Node[] nodeList, int query){
        Node root = nodeList[query - 1];
        if(root.numOfPrimeInSubTree == -1){
            int num = isPrime(root.value)? 1 : 0;
            for(int child : tree.get(query - 1)){
                num += primeQuery(tree, nodeList, child + 1);
            }
            root.numOfPrimeInSubTree = num;
        }
        return root.numOfPrimeInSubTree;
    }
    private static boolean isPrime(int value) {
        if (value < 2) return false;
        if (value == 2) return true;
        if (value % 2 == 0) return false;
        for (int i = 3; i * i <= value; i += 2)
            if (value % i == 0) return false;
        return true;
    }

    public static void main(String[] args){
        primeQuery(6, new ArrayList<Integer>(Arrays.asList(1,2,2,1,3)),
                        new ArrayList<Integer>(Arrays.asList(2,4,5,3,6)),
                new ArrayList<Integer>(Arrays.asList(2,2,6,5,4,3)),
                new ArrayList<Integer>(Arrays.asList(1,4,5,6,2)));
    }
}


