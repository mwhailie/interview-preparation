package edu.neu.algorithm.trie;

import java.util.*;

public class FileSystem {
    class Directory{
        HashMap< String, String > files = new HashMap < > ();
        Map<String, Directory> children = new HashMap<>();
    }

    Directory root;
    public FileSystem() {
        root = new Directory();
    }

    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        String[] names = path.split("/");
        Directory curr = root;

        if(!path.equals("/")){
            for(int i = 1; i < names.length - 1; i ++){
                String c = names[i];
                if(!curr.children.containsKey(c)){
                    return res;
                }
                curr = curr.children.get(c);
            }
            if (curr.files.containsKey(names[names.length - 1])) {
                res.add(names[names.length - 1]);
                return res;
            } else {
                curr = curr.children.get(names[names.length - 1]);
            }
        }
        for(String child : curr.children.keySet()){
            res.add(child);
        }
        for(String file : curr.files.keySet()){
            res.add(file);
        }
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        String[] names = path.split("/");
        Directory curr = root;
        if(!path.equals("/")){
            for(int i = 1; i < names.length; i ++){
                String c = names[i];
                if(!curr.children.containsKey(c)){
                    curr.children.put(c, new Directory());
                }
                curr = curr.children.get(c);
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] names = filePath.split("/");
        Directory curr = root;
        if(!filePath.equals("/")){
            for(int i = 1; i < names.length - 1; i ++){
                String c = names[i];
                if(!curr.children.containsKey(c)){
                    curr.children.put(c, new Directory());
                }
                curr = curr.children.get(c);
            }
        }
        curr.files.put(names[names.length - 1], curr.files.getOrDefault(names[names.length - 1], "") + content);
    }

    public String readContentFromFile(String filePath) {
        String[] names = filePath.split("/");
        Directory curr = root;
        if(!filePath.equals("/")){
            for(int i = 1; i < names.length - 1; i ++){
                String c = names[i];
                if(!curr.children.containsKey(c)){
                    return "";
                }
                curr = curr.children.get(c);
            }
        }

        return curr.files.get(names[names.length - 1]);
    }
}
