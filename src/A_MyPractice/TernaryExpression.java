package A_MyPractice;

import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/convert-ternary-expression-binary-tree/
//        Given a string that contains ternary expressions.
//        The expressions may be nested, task is convert the given ternary expression to a binary Tree.
//        Input : expression =  a?b?c:d:e
//          a  Output :
//        /  \
//        b    e
//        /  \
//        c    d
// Class to represent Tree node
class Node
{
    char data;
    Node left, right;

    public Node(char item)
    {
        data = item;
        left = null;
        right = null;
    }
}

public class TernaryExpression {
    public Node convert(String input) {
        int[] cur = new int[1];
        return helper(input, cur);
    }

    private Node helper(String input, int[] cur) {
        Node root = new Node(input.charAt(cur[0]));
        cur[0]++;
        while (cur[0] < input.length()) {
            if (cur[0] < input.length() && input.charAt(cur[0]) == '?') {
                cur[0]++;
                root.left = helper(input, cur);
            } else if (cur[0] < input.length() && input.charAt(cur[0]) == ':') {
                cur[0]++;
                root.right = helper(input, cur);
            }
        }

        return root;
    }

    // function print tree
    public void printTree(Node root)
    {
        if (root == null) {
            return;
        }
        System.out.print(root.data +" ");
        printTree(root.left);
        printTree(root.right);
    }

    // Driver program to test above function
    public static void main(String args[])
    {
        String exp = "a?b?c:d:e";
        TernaryExpression tree = new TernaryExpression();
        Node root = tree.convert(exp);
        tree.printTree(root) ;
    }
}
