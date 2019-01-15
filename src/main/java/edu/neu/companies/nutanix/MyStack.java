package edu.neu.companies.nutanix;

import java.util.*;

public class MyStack {
    // Design and implement a custom class MyStack of integers, and implement the methods reverse_stack()
// and sort_stack(), using just the stack primitives push(), pop(), top().


    Stack<Integer> s;

    void sort_stack(){
        if(s.isEmpty()){
            return;
        }
        int top = s.pop();
        sort_stack();
        insert_at_right(top);
    }

    void insert_at_right(int element){
        if(s.isEmpty() || s.peek() < element ){
            s.push(element);
        } else{
            int top = s.pop();
            insert_at_right(element);
            s.push(top);
        }
    }
        // s.top(), s.pop(), s.push()
        //LIFO
    void reverse_stack(){
            //size
            if(s.isEmpty()){
                return;
            }

            int top = s.pop();
            reverse_stack();
            insert_at_bottom(top);
        }

        void insert_at_bottom(int element) {
            if(s.isEmpty()){
                s.push(element);
                return;
            }
            int top = s.pop();
            insert_at_bottom(element);
            s.push(top);
        }


}
