package edu.neu.companies.quora;

import java.util.*;

public class RoomWithSensor {

    class Sensor implements Comparator<Sensor>{
        double x;
        double y;
        double r;

        public Sensor(int x, int y, double r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        @Override
        public int compare(Sensor s1, Sensor s2) {
            return s1.x != s2.x? (int) (s1.x - s2.x) : (int) (s1.y - s2.y);
        }
    }

    class UnionFind{
        int[] root;

        public UnionFind(int n) {
            this.root = new int[n];
            for(int i = 0; i < n; i ++){
                root[i] = i;
            }
        }

        boolean union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);
            if(rootA == rootB){
                return false;
            }
            root[rootA] = rootB;
            return true;
        }

        int find(int a){
            while(a != root[a]){
                a = root[a];
            }
            return a;
        }
    }
    boolean canPass(int m, int n, Sensor[] sensors){
        Arrays.sort(sensors);
        UnionFind uf = new UnionFind(sensors.length);
        for(int i = 0; i < sensors.length - 1; i ++){
            if(sensors[i].x - sensors[i].r <= 0 && sensors[i].x + sensors[i].r >= m){
                return false;
            }
            for(int j = i + 1; j < sensors.length ; j ++){
                if(isConnected(sensors[i], sensors[j])){
                    uf.union(i, j);
                }
            }
        }
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for(int i = 0; i < sensors.length; i ++){
            int root = uf.find(i);
            groups.compute(root, (k, v) -> v == null? new ArrayList<>() : groups.get(root)).add(i);
        }
        for(int i : groups.keySet()){
            List<Integer> group = groups.get(i);
            boolean blockLeft = false, blockRight = false;
            for(int j : group){
                if(sensors[i].x - sensors[i].r <= 0 ){
                    blockLeft = true;
                }
                if(sensors[i].x + sensors[i].r > m){
                    blockRight = true;
                }
                if(blockLeft && blockRight){
                    return false;
                }
            }
        }
        return false;
    }

    private boolean isConnected(Sensor sensor1, Sensor sensor2) {
        return Math.pow(sensor1.x - sensor2.x, 2) + Math.pow(sensor1.y - sensor2.y, 2)
                <= Math.pow(sensor1.r + sensor2.r, 2);
    }
}
