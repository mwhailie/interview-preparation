package edu.neu.practice.companies.airbnb;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/*
    Leetcode #251 Flatten 2D Vector
    Leetcode #341 Flatten Nested List Iterator
 */
public class FlattenListIterator implements Iterator<Integer>{
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
    Stack<NestedInteger> stack;

    public FlattenListIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for(int i = nestedList.size() - 1; i >= 0 ; i --){
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(stack.peek().isInteger()){
                return true;
            }else{
                NestedInteger curr = stack.pop();
                for(int i = curr.getList().size() - 1; i >= 0 ; i --){
                    stack.push(curr.getList().get(i));
                }
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        NestedInteger curr = stack.pop();
        return curr.getInteger();
    }

//    public static void main(String[] args) {
//        FlattenListIterator f = new FlattenListIterator
//    }
}
