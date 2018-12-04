package edu.neu.companies.quora;

import java.util.*;

public class SerializeDeserializeTree {
    static class TreeNode{
        String val;
        TreeNode left;
        TreeNode right;
        public TreeNode(String val){
            this.val = val;
        }
    }
    // Encodes a tree to a single string.
     public static String serialize(TreeNode root) {
         ArrayList<TreeNode> q = new ArrayList<>();
         q.add(root);
         for(int i = 0 ; i < q.size(); i++){
             TreeNode node = q.get(i);
             if(node == null){
                 continue;
             }
             q.add(node.left);
             q.add(node.right);
         }
         while(q.get(q.size()-1) == null){
             q.remove(q.size()-1);
         }
         StringBuilder sb = new StringBuilder();
         sb.append(root.val);
         for(int i = 1 ; i < q.size(); i++){
             TreeNode node = q.get(i);
             if(node == null){
                 sb.append(",null");
             }else{
                 sb.append(",");
                 sb.append(node.val);
             }
         }
         return sb.toString();
     }

     // Decodes your encoded data to tree.
     public static TreeNode deserialize(String data) {

         String[] nodes = data.split(",");
         TreeNode root = new TreeNode(nodes[0]);
         ArrayList<TreeNode> q = new ArrayList<>();
         q.add(root);
         int index = 0;
         boolean isLeftChild = true;
         for(int i = 1 ; i < nodes.length; i++){
             if(!nodes[i].equals("null")){
                 TreeNode node = new TreeNode(nodes[i]);
                 if(isLeftChild){
                     q.get(index).left = node;
                 }else{
                     q.get(index).right = node;
                 }
                 q.add(node);
             }
             if(!isLeftChild){
                 index++;
             }
             isLeftChild = !isLeftChild;
         }
         return root;
     }

    public static String serializeDFS(TreeNode root){
        StringBuilder res = new StringBuilder();
        buildS(root,res);
        return res.toString();
    }
    public static void buildS(TreeNode root, StringBuilder res){
        if(root == null){
            res.append("#,");
//            res.append("# , ");
        }else{
            res.append(root.val.replace(",", "\\,")  + ",");
//            res.append(root.val.replace(",", ",,")  + " , ");
            buildS( root.left,  res);
            buildS( root.right,  res);
        }
    }

    public static TreeNode deserializeDFS(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split("(?<!\\\\),")));
//        nodes.addAll(Arrays.asList(data.split(" , ")));
        return buildTree(nodes);
    }

    private static TreeNode buildTree(LinkedList<String> nodes) {
        String val = nodes.remove();
        if (val.equals("#")) return null;
        else {
            TreeNode node = new TreeNode(val.replaceAll("\\\\,", ","));
//            TreeNode node = new TreeNode(val.replaceAll(",,", ","));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("1");
        root.left = new TreeNode("2");
        root.right = new TreeNode("3");
        root.right.left = new TreeNode("4");
        root.right.right = new TreeNode(",");
        String se = serializeDFS(root);
        System.out.println(se);
        root = deserializeDFS(se);
        se = serializeDFS(root);
        System.out.println(se);


        se = serialize(root);
        System.out.println(se);
        root = deserialize(se);
        se = serialize(root);
        System.out.println(se);
    }
}
