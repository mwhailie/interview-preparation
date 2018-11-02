package edu.neu.practice.algorithm.sort;

import edu.neu.practice.algorithm.list.ListNode;

import java.util.*;

public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.value - b.value);
        ListNode fakeHead = new ListNode(0);
        ListNode tail = fakeHead;
        for(ListNode list : lists){
            if(list != null){
                pq.add(list);
            }
        }
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            if(node.next != null){
                pq.add(node.next);
            }
            tail.next = node;
            tail = tail.next;

        }
        return fakeHead.next;
    }
}
