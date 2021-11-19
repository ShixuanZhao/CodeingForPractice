package A_MyPractice;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class DeleteNode {
    // delete the node with one child;
    public TreeNode deleteNodes(TreeNode root) {
        if (root == null) {
            return root;
        }
        root.left = deleteNodes(root.left);
        root.right = deleteNodes(root.right);
        if (root.left != null && root.right != null || root.left == null && root.right == null) {
            return root;
        }
        return root.left == null ? root.right : root.left;
    }
     static class Codec {
         private static final String SPLITTER = ",";
         //use "X" to represent null
         private static final String NN = "X";

         // Encodes a tree to a single string.
         public String serialize(TreeNode root) {
             StringBuilder sb = new StringBuilder();
             Queue<TreeNode> queue = new LinkedList<>();
             queue.offer(root);
             TreeNode cur = root;
             while (!queue.isEmpty()) {
                 cur = queue.poll();
                 if (cur == null) {
                     sb.append(NN).append(SPLITTER);
                 } else {
                     sb.append(cur.val).append(SPLITTER);
                     queue.offer(cur.left);
                     queue.offer(cur.right);
                 }
             }
             return sb.toString();
         }

         // Decodes your encoded data to tree.
         public TreeNode deserialize(String data) {
             String[] values = data.split(SPLITTER);
             if (values[0].equals(NN)) {
                 return null;
             }
             TreeNode root = new TreeNode(Integer.valueOf(values[0]));
             Queue<TreeNode> queue = new ArrayDeque<>();
             queue.offer(root);
             for (int i = 1; i < values.length; i++) {
                 TreeNode cur = queue.poll();
                 if (!values[i].equals(NN)) {
                     TreeNode left = new TreeNode(Integer.valueOf(values[i]));
                     cur.left = left;
                     queue.offer(left);
                 }
                 if (!values[++i].equals(NN)) {
                     TreeNode right = new TreeNode(Integer.valueOf(values[i]));
                     cur.right = right;
                     queue.offer(right);
                 }
             }
             return root;
         }

         public static void main(String[] args) {
             DeleteNode test = new DeleteNode();
             Codec temp = new Codec();
             StringBuilder sb = new StringBuilder();
             sb.append(10).append(",").append(5).append(",").append(12).append(",").append(6).append(",").append("X");
             TreeNode root = temp.deserialize(sb.toString());
             TreeNode newRoot = test.deleteNodes(root);
             String res = temp.serialize(newRoot);
             System.out.println(res);
         }
     }
}
