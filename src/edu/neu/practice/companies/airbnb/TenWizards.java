package edu.neu.practice.companies.airbnb;

import java.util.*;

public class TenWizards {
    class WizardPath{
        int id;
        int dist;
        public WizardPath(int id) {
            this.id = id;
        }
    }
    public List<Integer> wizard(List<List<Integer>> wizards, int
            start, int end) {
        if (wizards == null || wizards.size() == 0) return null;
        int n = wizards.size();
        int[] path = new int[n];
        Map<Integer, WizardPath> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            path[i] = i;
            map.put(i, new WizardPath(i));
        }
        map.get(start).dist = 0;
        Queue<WizardPath> pq = new PriorityQueue<>(n, new Comparator<WizardPath>() {
            @Override
            public int compare(WizardPath w1, WizardPath w2) {
                return w1.dist - w2.dist;
            }
        });
        Set<Integer> visited = new HashSet<>();
        pq.offer(map.get(start));
        while (!pq.isEmpty()) {
            WizardPath curr = pq.poll();
            visited.add(curr.id);
            for (int neighbor : wizards.get(curr.id)) {
                WizardPath next = map.get(neighbor);
                int fee = (int) Math.pow(next.id - curr.id, 2);
                if (curr.dist + fee < next.dist) {
                    path[next.id] = curr.id;
                    pq.remove(next);
                    next.dist = curr.dist + fee;
                    pq.offer(next);
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        int t = end;
        while (t != start) {
            res.add(t);
            t = path[t];
        }
        res.add(start);
        Collections.reverse(res);
        return res;
    }
    static class Wizard{
        int id;
        int dist;
        public Wizard(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
    public static int getMinCost(List<List<Integer>> list) {
        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<Wizard> pq = new PriorityQueue<>(new Comparator<Wizard>() {
            public int compare(Wizard w1, Wizard w2) {
                return w1.dist - w2.dist;
            }
        });

        for (int i : list.get(0)) {
            pq.add(new Wizard(i, i * i));
        }
        visited.add(0);
        while (!pq.isEmpty()) {
            Wizard curr = pq.poll();
            visited.add(curr.id);
            if (curr.id == 9) {
                return curr.dist;
            }
            for (int i : list.get(curr.id)) {
                if (!visited.contains(i)) {
                    pq.add(new Wizard(i, curr.dist + (int)Math.pow((i - curr.id) ,2)));
                }
            }
        }

        return Integer.MAX_VALUE;
    }
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1, 4, 9));
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3));
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(5));
        list.add(Arrays.asList(6));
        list.add(Arrays.asList(7));
        list.add(Arrays.asList(8));
                list.add(Arrays.asList(9));
        System.out.println(getMinCost(list));
    }
}
