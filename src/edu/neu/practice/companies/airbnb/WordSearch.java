package edu.neu.practice.companies.airbnb;

import java.util.*;

public class WordSearch {

//    can be O(4^ N)
    int ROW, COLUMN;

    public boolean exist(char[][] board, String word) {
        ROW = board.length;
        COLUMN = board[0].length;
        boolean[][] visited = new boolean[ROW][COLUMN];
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COLUMN; j++){
                if(exist(board, visited, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean exist(char[][] board, boolean[][] visited, String word, int i, int j, int start) {
        if(start == word.length()){
            return true;
        }
        if(i >= ROW || i < 0 || j >= COLUMN|| j < 0){
            return false;
        }
        if(visited[i][j]){
            return false;
        }
        if(board[i][j] != word.charAt(start)){
            return false;
        }

        visited[i][j] = true;
        if(exist(board, visited, word, i + 1, j, start + 1) || exist(board, visited, word, i, j + 1, start + 1)
                || exist(board, visited, word, i - 1, j, start + 1) || exist(board, visited, word, i, j - 1, start + 1)){
            return true;
        }
        visited[i][j] = false;
        return false;
    }

    class TrieNode{
        boolean isWord = false;
        TrieNode[] children = new TrieNode[26];
    }
    private void insert(TrieNode root, String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
    }
    TrieNode root;

    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        for(String word: words){
            insert(root, word);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        List<String> res = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(root.children[board[i][j] - 'a'] != null){
                    wordSearch(board, visited, root, i, j, "", res);
                }
            }
        }
        return res;
    }
    public void wordSearch(char[][] board, boolean[][] visited, TrieNode root,
                           int i , int j, String word, List<String> res){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]){
            return;
        }
        if(root.children[board[i][j] - 'a'] == null){
            return;
        }
        root = root.children[board[i][j] - 'a'];
        visited[i][j] = true;
        if(root.isWord && !res.contains(word + board[i][j])){
            res.add(word + board[i][j]);
        }
        wordSearch(board, visited, root, i + 1, j, word + board[i][j], res);
        wordSearch(board, visited, root, i - 1, j, word + board[i][j], res);
        wordSearch(board, visited, root, i, j + 1, word + board[i][j], res);
        wordSearch(board, visited, root, i, j - 1, word + board[i][j], res);
        visited[i][j] = false;
    }
}
