package edu.neu.practice.companies.airbnb;

import java.util.*;

/*
    You’re given an array of CSV strings representing search results. Results are sorted by a score initially. A
    given host may have several listings that show up in these results. Suppose we want to show 12 results
    per page, but we don’t want the same host to dominate the results. Write a function that will reorder
    the list so that a host shows up at most once on a page if possible, but otherwise preserves the ordering.
    Your program should return the new array and print out the results in blocks representing the pages.

    Input: An array of csv strings, with sort score number of results per page. example:

    "host_id,listing_id,score,city"
    "1,28,300.1,San Francisco"

    https://leetcode.com/problems/rearrange-string-k-distance-apart/description/
 */
public class Pagination {
    public List<String> displayPages(List<String> inputs, int pageSize) {
        List<String> displayPages = new ArrayList<>();
        if(inputs == null ||inputs.size() == 0){
            return displayPages;
        }
        Set<String> host_ids = new HashSet<>();
        boolean reachEnd = false;
        int i = -1;
        while(i != inputs.size() - 1){
            i++;
            String displayPage = inputs.get(i);
            String host_id = inputs.get(i).split(",")[0];
            if(!host_ids.contains(host_id) || reachEnd){
                host_ids.add(host_id);
                displayPages.add(displayPage);
                inputs.remove(i);
                i = -1;
            }

            if(host_ids.size() == pageSize){
                host_ids.clear();
                i = -1;
                if(!inputs.isEmpty()){
                    displayPages.add("page");
                }
            }
            if(i == inputs.size() - 1){
                i = -1;
                reachEnd = true;
            }

        }
        return displayPages;
    }
    public List<String> displayPages2(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();
        if (input == null || input.size() == 0) {
            return res;
        }
        List<String> visited = new ArrayList<>();
        Iterator<String> iter = input.iterator();
        boolean reachEnd = false;
        while (iter.hasNext()) {
            String curr = iter.next();
            String hostId = curr.split(",")[0];
            if (!visited.contains(hostId) || reachEnd) {
                res.add(curr);
                visited.add(hostId);
                iter.remove();
            }
            if (visited.size() == pageSize) {
                visited.clear();
                reachEnd = false;
                if (!input.isEmpty()) {
                    res.add(" ");
                }
                iter = input.iterator();
            }
            if (!iter.hasNext()) {
                iter = input.iterator();
                reachEnd = true;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Pagination p = new Pagination();
        List<String> inputs = new ArrayList<>();
        inputs.add("1, 0");
        inputs.add("2, 0");
        inputs.add("3, 0");
        inputs.add("4, 0");
        inputs.add("1, 0");
        inputs.add("5, 0");
        inputs.add("1, 0");
        inputs.add("2, 0");
        inputs.add("3, 0");
        inputs.add("1, 0");
        inputs.add("3, 0");
        System.out.println(p.displayPages2(inputs,5));
    }
}
