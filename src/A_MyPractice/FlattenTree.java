package A_MyPractice;

   class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


public class FlattenTree {
    public void flatten(TreeNode root) {
        flatten(root, null);
    }

    private void flatten(TreeNode root, TreeNode prev) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (prev != null) {
            prev.right = root;
            prev.left = null;
        }
        prev = root;
        flatten(left, prev);
        flatten(right, prev);
    }

    public static void main(String[] args) {
        FlattenTree test = new FlattenTree();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
       test.flatten(root);
    }
}
