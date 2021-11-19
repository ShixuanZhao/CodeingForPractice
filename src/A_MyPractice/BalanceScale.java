package A_MyPractice;
import java.util.*;

public class BalanceScale {
    //construct the graph using adj list
//    public int whoIsHeavier(List<List<Integer>> countComparisons) {
//        List<List<Integer>> graph = new LinkedList<>();
//        //graph(i), i is the number of object
//        for (int i = 0; i <= countComparisons.get(0).get(0); i++) {
//            graph.add(new LinkedList<>());
//        }
//        for (int i = 1; i <= countComparisons.get(0).get(1); i++) {
//            graph.get(countComparisons.get(i).get(0)).add(countComparisons.get(i).get(1));
//        }
//        int b1 = bfs(countComparisons.get(countComparisons.size() - 1).get(0),
//                countComparisons.get(countComparisons.size() - 1).get(1), graph);
//        int b2 = bfs(countComparisons.get(countComparisons.size() - 1).get(1),
//                countComparisons.get(countComparisons.size() - 1).get(0), graph);
//        return b1 == -1 ? b2 : b1;
//    }
//
//    private int bfs(int src, int dest, List<List<Integer>> graph) {
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(src);
//        while (!queue.isEmpty()) {
//            int  cur = queue.poll();
//            if (cur == dest) {
//                return src;
//            }
//            if (graph.get(cur).size() != 0) {
//                queue.offer(graph.get(cur).get(0));
//            }
//        }
//        return -1;
//    }

    public int whoIsHeavier(List<List<Integer>> countComparisons) {
        //construct graph using map
        int size = countComparisons.size();
        int m = countComparisons.get(size - 1).get(0);
        int n = countComparisons.get(size - 1).get(1);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= size - 2; i++) {
            int x = countComparisons.get(i).get(0);
            int y = countComparisons.get(i).get(1);
            graph.putIfAbsent(x, new LinkedList<>());
            graph.get(x).add(y);
        }
        //run bfs twice
        int temp = bfs(m, n, graph);
        return temp == -1 ? bfs(n, m, graph) : temp;
    }

    private int bfs(int src, int target, Map<Integer, List<Integer>> graph) {
        if (!graph.containsKey(src)) {
            return - 1;
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == target) {
                return src;
            }
            List<Integer> childList = graph.get(cur);
            if (childList == null) {
                continue;
            }
            for (int child : childList) {
                q.offer(child);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BalanceScale test = new BalanceScale();
        List<List<Integer>> input = new LinkedList<>();
        input.add(Arrays.asList(5, 2));
        input.add(Arrays.asList(1, 2));
        input.add(Arrays.asList(2, 3));
        input.add(Arrays.asList(1, 3));
//        input.add(Arrays.asList(10, 3));
//        input.add(Arrays.asList(9, 1));
//        input.add(Arrays.asList(9, 2));
//        input.add(Arrays.asList(9, 3));
//        input.add(Arrays.asList(9, 4));
        System.out.println(test.whoIsHeavier(input));
    }
}
