package edu.neu.algorithm.trie;

import java.util.*;

public class AutoCompletion {
    class TrieNode{
        boolean isEnd;
        int frequency;
        TrieNode[] children;
        public TrieNode(){
            children = new TrieNode[27];
        }
    }
    class Node {
        String word;
        int freq;
        Node(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }
    void insert(String s, int freq){
        insert(root, s, 0, freq);
    }
    TrieNode insert(TrieNode root, String s, int start, int freq){
        if(root == null){
            root = new TrieNode();
        }
        if(start == s.length()){
            root.isEnd = true;
            root.frequency = freq;
            return root;
        }
        char c = s.charAt(start);
        if(c == ' '){
            root.children[26] = insert(root.children[26], s, start + 1, freq);
        }else{
            root.children[c - 'a'] = insert(root.children[c - 'a'], s, start + 1, freq);
        }
        return root;
    }
    List<Node> stringsWithPrefix(String prefix){
        TrieNode node = get(prefix);
        List<Node> res = new ArrayList<>();
        collect(node, new StringBuilder(prefix), res);
        return res;
    }
    TrieNode get(String prefix){
        return get(root, prefix, 0);
    }
    TrieNode get(TrieNode root, String prefix, int start){
        if(root == null ){
            return null;
        }
        if(start == prefix.length()){
            return root;
        }
        if(prefix.charAt(start) == ' '){
            return get(root.children[26], prefix, start + 1);
        }
        return get(root.children[prefix.charAt(start) - 'a'], prefix, start + 1);
    }
    void collect(TrieNode root, StringBuilder prefix, List<Node> res){
        if(root == null){
            return;
        }
        if(root.isEnd){
            res.add(new Node(prefix.toString(), root.frequency));
        }
        for(int i = 0; i < 26; i ++){
            prefix.append((char)(i + 'a'));
            collect(root.children[i], prefix, res);
            prefix.deleteCharAt(prefix.length() - 1);
        }
        prefix.append(' ');
        collect(root.children[26], prefix, res);
        prefix.deleteCharAt(prefix.length() - 1);
    }

    TrieNode root;
    String search;

    public AutoCompletion(String[] words, int[] time) {
        this.root = new TrieNode();
        search = "";
        for(int i = 0; i < words.length; i++){
            insert(root, words[i], 0, time[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            if(get(search) == null){
                insert(search, 1);
            }else{
                insert(search, get(search).frequency + 1);
            }
            search = "";
            return new ArrayList<>();
        }

        search += c;
        List<Node> nodes = stringsWithPrefix(search);
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.freq == b.freq ?
                a.word.compareTo(b.word) : b.freq - a.freq));
        pq.addAll(nodes);
        List < String > res = new ArrayList < > ();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.poll().word);
        }
        return res;
    }
}
