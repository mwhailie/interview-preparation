package edu.neu.companies.oscarhealth;

import java.util.*;

public class CloneGraph {

    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    //1. DFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode root){
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return clone(root, map);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode root, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if(root == null){
            return null;
        }
        if(map.containsKey(root)){
            return map.get(root);
        }
        map.put(root, new UndirectedGraphNode(root.label));
        for(UndirectedGraphNode neighbor : root.neighbors){
            map.get(root).neighbors.add(clone(neighbor, map));
        }
        return map.get(root);
    }



     public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode node) {
         if(node == null){

             return null;
         }
         // copy nodes
         List<UndirectedGraphNode> nodes = getNodes(node);
         HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
         for(UndirectedGraphNode n : nodes){
             System.out.print(n + " ");
             mapping.put(n,  new UndirectedGraphNode(n.label));
         }
         // copy neighbors(edges)
         for (UndirectedGraphNode n : nodes) {
             UndirectedGraphNode newNode = mapping.get(n);
             for(UndirectedGraphNode neighbor : n.neighbors){
                 UndirectedGraphNode newNeighbor = mapping.get(neighbor);
                 newNode.neighbors.add(newNeighbor);
             }
         }
         return mapping.get(node);
     }
     public List<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
         Queue<UndirectedGraphNode> q = new LinkedList<>();
         Set<UndirectedGraphNode> hash = new HashSet<>();
         q.add(node);
         hash.add(node);
         while(!q.isEmpty()){
             UndirectedGraphNode n = q.poll();
             for(UndirectedGraphNode neighbor : n.neighbors){
                 if(hash.contains(neighbor)){
                     continue;
                 }
                 q.add(neighbor);
                 hash.add(neighbor);
             }
         }
         return new ArrayList<UndirectedGraphNode>(hash);
     }
}
