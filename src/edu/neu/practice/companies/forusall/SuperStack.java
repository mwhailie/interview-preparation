package edu.neu.practice.companies.forusall;

import java.util.*;

public class SuperStack {

     class ListNode{
        int key;
        int increment;
        int val;
        ListNode prev;
        ListNode next;
        public ListNode(int key, int increment, int val) {
            this.increment = increment;
            this.key = key;
            this.val = val;
        }
    }
     ListNode head;
     ListNode tail;
     int size;

     HashMap<Integer, ListNode> map;

    public SuperStack(){
        size = 0;
        map = new HashMap<>();
        head = new ListNode(0, 0,0);
        tail = new ListNode(0, 0,0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }

     void pop(){
        ListNode node = head.next;
        head.next = node.next;
        node.next.prev = head;
        head.next.increment += node.increment;
        map.remove(size);
        size --;
    }

     void push(int i){
        size++;
        ListNode node =  new ListNode(size, 0, i);
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        map.put(size, node);
    }

     void inc(int k, int e){
        ListNode node ;
        if(map.containsKey(k)){
            node = map.get(k);
        }else {
            node = map.get(size);
        }
        node.increment +=e;
        map.put(k, node);
    }
    private void peek() {
        if(size != 0){
            System.out.println(head.next.val + head.next.increment);
        }else{
            System.out.println("EMPTY");
        }
    }
    /*
     * Complete the function below.
     */
     public void superStack(String[] operations) {
        for(String operation : operations){
            String[] inputs = operation.split(" ");
            if("push".equals(inputs[0])){
                push(Integer.parseInt(inputs[1]));
            }else if("pop".equals(inputs[0])){
                pop();
            }else{
                inc(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));
            }
            peek();
        }
    }

    public static void main(String[] args) {
        SuperStack superStack = new SuperStack();
        String operationString = "push 3, push 5, push 2, inc 3 1, pop, push 1, inc 2 2, push 4, pop, pop, pop";
        superStack.superStack(operationString.split(", "));
    }
}
