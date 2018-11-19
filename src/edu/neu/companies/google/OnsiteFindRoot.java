package edu.neu.companies.google;


import java.util.*;

public class OnsiteFindRoot {
    private class TreeNode {
        int value;
        TreeNode[] children;
    }

    public TreeNode findRoot(List<TreeNode> nodes){
        Set<TreeNode> set = new HashSet<>();
        for(TreeNode node : nodes){
            for(TreeNode child :node.children){
                set.add(child);
            }
        }
        for(TreeNode node : nodes){
            if(!set.contains(node)){
                return node;
            }
        }
        return null;
    }

    public Integer findRootII(List<TreeNode> nodes){
        int sum = 0;
        for(TreeNode node : nodes){
            sum += node.value;
            for(TreeNode child :node.children){
                sum -= child.value;
            }
        }
        return sum;
    }

    private class BoardingPass {
        String depart;
        String des;
    }

    public List<String> findTrip(List<BoardingPass> passes){
        Map<String,String> map = new HashMap<>();
        for(BoardingPass pass : passes){
            map.put(pass.depart, pass.des);
        }
        String start = "";
        for(BoardingPass pass : passes){
            if(!map.containsKey(pass.depart)){
                start = pass.depart;
            }
        }
        List<String> trip = new ArrayList<>();
        trip.add(start);
        while(map.containsKey(start)){
            String des = map.get(start);
            trip.add(des);
            start = des;
        }
        return trip;
    }
}
