package edu.neu.companies.microsoft;

import java.util.*;

public class CopyListWithRandomPointer {
     class RandomListNode {
          int label;
          RandomListNode next, random;
          RandomListNode(int x) { this.label = x; }
     }
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null){
            return head;
        }
        List<RandomListNode> copyRandomList = getListNode(head);
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        //copy nodes
        for(RandomListNode node : copyRandomList){
            map.put(node, new RandomListNode(node.label));
        }
        //copy edges
        for(RandomListNode node : copyRandomList){
            RandomListNode newNode = map.get(node);
            if(node.next != null){
                newNode.next = map.get(node.next);
            }
            if(node.random != null){
                newNode.random = map.get(node.random);
            }
        }
        return map.get(head);
    }
    public List<RandomListNode> getListNode(RandomListNode head) {
        Queue<RandomListNode> q = new LinkedList<>();
        HashSet<RandomListNode> set = new HashSet<>();
        q.add(head);
        set.add(head);
        while(!q.isEmpty()){
            RandomListNode node = q.poll();
            if(node.next != null && !set.contains(node.next)){
                q.add(node.next);
                set.add(node.next);
            }
            if(node.random != null && !set.contains(node.random)){
                q.add(node.random);
                set.add(node.random);
            }
        }
        return new ArrayList<>(set);
    }}
