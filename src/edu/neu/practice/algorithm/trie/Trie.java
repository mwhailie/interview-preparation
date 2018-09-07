package edu.neu.practice.algorithm.trie;

import java.util.LinkedList;
import java.util.Queue;

public class Trie {
    class TrieNode{
        boolean isEnd = false;
        TrieNode[] children = new TrieNode[256];
    }
//    给你一个List of String，和另外一个string作为prefix，要返回list里面所有的前缀和给定prefix匹配的string。
    TrieNode root;
    int n;
//  Recursive
    public void put(String input){
         root = put(root,input, 0);
//        System.out.println(input);
    }
    public TrieNode put(TrieNode x, String input, int d){
        if(x == null){
            x = new TrieNode();
        }
        if(input.length() == d){
            if(!x.isEnd ){
                n++;
            }
            x.isEnd = true;
            return x;
        }
        char c = input.charAt(d);
//        System.out.println(c);
        x.children[c] = put(x.children[c], input, d + 1);
        return x;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new LinkedList<>();
        TrieNode x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private TrieNode get(TrieNode x, String prefix, int d) {
        if(x == null){
            return null;
        }
        if(d == prefix.length()){
            return x;
        }
        return get(x.children[prefix.charAt(d)], prefix, d + 1);
    }

    private void collect(TrieNode x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.isEnd) results.offer(prefix.toString());
        for (char c = 0; c < 256; c++) {
            prefix.append(c);
            collect(x.children[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    public static void main(String[] args){
        Trie trie = new Trie();
        trie.put("abc");
        trie.put("abcdef");
        trie.put("abceee");
        trie.put("abcffff");
        trie.put("bscffff");
        trie.put("abscffff");
        for(String s : trie.keysWithPrefix("abc")){
            System.out.println(s);
        }
    }
}
