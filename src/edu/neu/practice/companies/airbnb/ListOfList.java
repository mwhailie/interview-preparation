package edu.neu.practice.companies.airbnb;

import java.util.*;

public class ListOfList {
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
    public class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
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
    }
    public class Solution implements Iterator<Integer> {
        private Iterator<List<Integer>> rowIter;
        private Iterator<Integer> colIter;
        public Solution(List<List<Integer>> vec2d) {
            rowIter = vec2d.iterator();
            colIter = Collections.emptyIterator();
        }
        @Override
        public Integer next() {
            return colIter.next();
        }
        @Override
        public boolean hasNext() {
            while ((colIter == null || !colIter.hasNext()) && rowIter.hasNext())
                colIter = rowIter.next().iterator();
            return colIter != null && colIter.hasNext();
        }
        @Override
        public void remove() {
            while (colIter == null && rowIter.hasNext())
                colIter = rowIter.next().iterator();
            if (colIter != null)
                colIter.remove();
        }
    }
}
