package A_MyPractice;

import java.util.HashSet;
import java.util.Set;

/**
 You are given a binary tree as a sequence of parent-child pairs.
 For example, the tree represented by the node pairs below:
 (A,B) (A,C) (B,G) (C,H) (E,F) (B,D) (C,E)
 may be illustrated in many ways, with two possible representations below:
 A   /  \  B    C / \  / \G  D  E   H       \            F
 A   /  \  B    C / \  / \D  G H   E        /       F
 Below is the recursive definition for the S-expression of a tree:
 S-exp(node) = ( node->val
 (S-exp(node->first_child))(S-exp(node->second_child))), if node != NULL
 = "", node == NULL
 where, first_child->val < second_child->val (lexicographically smaller)
 This tree can be represented in a S-expression in multiple ways.
 The lexicographically smallest way of expressing this is as follows:
 (A(B(D)(G))(C(E(F))(H)))
 We need to translate the node-pair representation into an S-expression
 (lexicographically smallest one), and report any errors that do not conform to the definition of a binary tree.
 The list of errors with their codes is as follows:
 Error Code          Type of error
 E1                 More than 2 children
 E2                 Duplicate Edges
 E3                 Cycle present
 E4                 Multiple roots
 E5                 Any other error
 Input Format:
 Input must be read from standard input.
 Input will consist of on line of parent-child pairs. Each pair consists of two node names separated by a single comma and enclosed in parentheses. A single space separates the pairs.
 Output:
 The function must write to standard output.
 Output the given tree in the S-expression representation described above.
 There should be no spaces in the output.
 Constraints:
 There is no specific sequence in which the input (parent,child) pairs are represented.
 The name space is composed of upper case single letter (A-Z) so the maximum size is 26 nodes.
 Error cases are to be tagged in the order they appear on the list. For example,
 if one input pair raises both error cases 1 and 2, the output must be E1.
 Sample Input #00
 (B,D) (D,E) (A,B) (C,F) (E,G) (A,C)
 Sample Output #00
 (A(B(D(E(G))))(C(F)))
 Sample Input #01
 (A,B) (A,C) (B,D) (D,C)
 Sample Output #01
 E3
 Explanation
 Node D is both a child of B and a parent of C, but C and B are both child nodes of A.
 Since D tries to attach itself as parent to a node already above it in the tree, this forms a cycle.
 Idea:
 Use a 26*26 graph to represent the input tree. Then check for each error in the order, finally use
 a recursive DFS to form the tree and the output SExpression.
 E1: Invalid input format
 E2: Duplicate Edge. Check when constructing the graph, if graph[x][y] is already true, E2=true.
 E3: More than 2 children. Check if graph[i][j], j from 0 to 25 has more than two cell that is true.
 E4: Multiple roots: traverse all nodes in the HashSet, if no edge connected TO this node, then it must be a root.
 即这个顶点没有入度，没有其他的点指向这个点，那么这个点必然是root. If number of roots > 1, return "E4".
 Note: if number of roots == 0, then there must also be a cycle, return "E3".
 E5: Cycle Present: Check when looking for the root, starting from each root, use recursive DFS to check if there is
 a cycle in the graph.
 */
public class GetSExpression {
    public String getSExpression(String s) {
        //(A, B) A->B graph[0][1] we can also use adjacency list
        boolean[][] graph= new boolean[26][26];
        Set<Character> nodes = new HashSet<>();
        //construct graph and check E2: duplicate edges
        boolean E2 = false;
        char[] c = s.toCharArray();
//        Sample Input #00
//        (B,D) (D,E) (A,B) (C,F) (E,G) (A,C)
        for (int i = 1; i < s.length(); i += 6) {
            int x = c[i] - 'A';
            int y = c[i + 2] - 'A';
            if (graph[x][y]) {
                E2 = true;
            }
            graph[x][y] = true;
            nodes.add(c[i]);
            nodes.add(c[i + 2]);
        }
        //check error E1: more than 2 children
        //graph[i][j] i refer to parent and j refer to child
        boolean E1 = false;
        for (int i = 0; i < 26; i++) {
            //number of children
            int cnt = 0;
            for (int j = 0; j < 26; j++) {
                if (graph[i][j]) {
                    cnt++;
                }
            }
            if (cnt > 2) {
                return "E1";
            }
        }
        //return E2 after return E1
        if (E2) {
            return "E2";
        }
        //check E3: cycle and E4: multiple roots
        int numOfRoots = 0;
        char root = ' ';
        //only check the letter that in the tree
        for (Character node : nodes) {
            //如果当前node的入度为0，即没有其他node指向他，那么他就是root（他不能作为孩子）
            //look who refer to the current node
            for (int i = 0; i < 26; i++) {
                if (graph[i][node - 'A']) {
                    break;
                }
                //no other node refer to the cur node
                if (i == 25) {
                    numOfRoots++;
                    root = node;
                    //run dfs from the root, and remember to mark the status of each node to check whether exist a cycle
                    boolean[] visited = new boolean[26];
                    if (isCycle(node, graph, visited)) {
                        return "E3";
                    }
                }
            }
        }
        if (numOfRoots == 0) {
            return "E2";
        }
        if (numOfRoots > 1) {
            return "E4";
        }
        //if no edge in input String, invalid input error
        if (root == ' ') {
            return "E5";
        }
        //Without any error, get the output using recursion
        return getRes(root, graph);
    }

    private  boolean isCycle(char node, boolean[][] graph, boolean[] visited) {
        //if we have already visit the node, must have a cycle
        if (visited[node - 'A']) {
            return true;
        }
        visited[node - 'A'] = true;
        //go to its children
        for (int i = 0; i < 26; i++) {
            if (graph[node - 'A'][i]) {
                if (isCycle((char)(i + 'A'), graph, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    //use dfs to get the expression/construct the tree
    private String getRes(char root, boolean[][] graph) {
        //if no children, the left and right would be empty
        String left = "";
        String right = "";
        //we can know when call this method, the input is valid, there must be a binary tree
        //So for any parent, first search for left child and then for right child
        for (int i = 0; i < 26; i++) {
            if (graph[root - 'A'][i]) {
                left = getRes((char) (i + 'A'), graph);
                //continue to search for the right child if exist
                for (int j = i + 1; j < 26; j++) {
                    if (graph[root - 'A'][j]) {
                        right = getRes((char) (j + 'A'), graph);
                        break;
                    }
                }
                break;
            }
        }
        return "(" + root + left + right + ")";
    }

    public static void main(String[] args) {
        GetSExpression test = new GetSExpression();
        String s = "(B,D) (D,E) (A,B) (C,F) (E,G) (A,C)";
        System.out.println(test.getSExpression(s));
    }
}
