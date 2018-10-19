package edu.neu.practice.companies.airbnb;

import java.util.*;

public class BoggleGame {

    public static void main(String[] args) {
        char[][] board = {{'a','b'},{'c','d'},{'a','b'},{'c','d'}};
        String[] dict = {"ab","ac","acd","c","d"};
        System.out.println(boggle(board,dict));
    }

    public static ArrayList<String> boggle(char[][] board, String[] dict) {
        ArrayList<String> res = new ArrayList<>();
        Trie trie = new Trie();
        for(String s : dict) trie.insert(s);
        boolean[][] visited = new boolean[board.length][board[0].length];
        ArrayList<String> curRes = new ArrayList<>();

        dfs(board,visited,trie,res,curRes,0,0);

        return res;
    }

    public static void dfs(char[][] board, boolean[][] visited, Trie trie, ArrayList<String> res,
                           ArrayList<String> curRes, int x, int y) {
        int row = board.length, column = board[0].length;
        if(x == row) {
            if(curRes.size() > res.size()) {
                res.clear();
                for(String s : curRes) res.add(s);
            }
            return;
        }

        ArrayList<String> strs = new ArrayList<>();
        List<ArrayList<Integer>> paths = new ArrayList<>();

        dfs2(board,visited,trie,strs,paths,new StringBuilder(),new ArrayList<Integer>(),x,y);
        dfs(board,visited,trie,res,curRes,x+(y+1)/column,(y+1)%column);
        for(int i = 0; i < strs.size(); i++) {
            curRes.add(strs.get(i));
            for(int j : paths.get(i)) {
                visited[j/column][j%column] = true;
            }
            trie.delete(strs.get(i));

            dfs(board,visited,trie,res,curRes,x+(y+1)/column,(y+1)%column);

            curRes.remove(strs.get(i));
            for(int j : paths.get(i)) {
                visited[j/column][j%column] = false;
            }
            trie.insert(strs.get(i));
        }
    }

    public static void dfs2(char[][] board, boolean[][] visited, Trie trie, ArrayList<String> strs,
                            List<ArrayList<Integer>> paths, StringBuilder curString, ArrayList<Integer> curPath, int x, int y) {
        int row = board.length, column = board[0].length;
        if(x<0 || x>=row || y<0 || y>=column || visited[x][y]) return;
        curString.append(board[x][y]);
        if(!trie.hasPrefix(curString.toString())) {
            curString.deleteCharAt(curString.length()-1);
            return;
        }

        visited[x][y] = true;
        curPath.add(x*column+y);
        if(trie.hasWord(curString.toString())) {
            paths.add((ArrayList<Integer>)curPath.clone());
            strs.add(curString.toString());
        }

        dfs2(board,visited,trie,strs,paths,curString,curPath,x+1,y);
        dfs2(board,visited,trie,strs,paths,curString,curPath,x-1,y);
        dfs2(board,visited,trie,strs,paths,curString,curPath,x,y+1);
        dfs2(board,visited,trie,strs,paths,curString,curPath,x,y-1);

        curString.deleteCharAt(curString.length()-1);
        visited[x][y] = false;
        curPath.remove(curPath.size()-1);
    }
    public static class Trie {
        public Trie[] next;
        public boolean isWord;
        public Trie() {
            next = new Trie[26];
        }

        public void insert(String s) {
            Trie cur = this;
            for(char c : s.toCharArray()) {
                if(cur.next[c-'a'] == null) cur.next[c-'a'] = new Trie();
                cur = cur.next[c-'a'];
            }
            cur.isWord = true;
        }

        public boolean hasWord(String s) {
            Trie cur = this;
            for(char c : s.toCharArray()) {
                if(cur.next[c-'a'] == null) return false;
                cur = cur.next[c-'a'];
            }
            return cur.isWord;
        }

        public boolean hasPrefix(String s) {
            Trie cur = this;
            for(char c : s.toCharArray()) {
                if(cur.next[c-'a'] == null) return false;
                cur = cur.next[c-'a'];
            }
            return true;
        }

        public void delete(String s) {
            Trie cur = this;
            for(char c : s.toCharArray()) {
                if(cur.next[c-'a'] == null) return;
                cur = cur.next[c-'a'];
            }
            cur.isWord = false;
        }

    }
}

