//给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 
//的一条无向边，且该边遍历成功的概率为 succProb[i] 。 
//
// 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。 
//
// 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, 
//end = 2
//输出：0.25000
//解释：从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
// 
//
// 示例 2： 
//
// 
//
// 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, 
//end = 2
//输出：0.30000
// 
//
// 示例 3： 
//
// 
//
// 输入：n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
//输出：0.00000
//解释：节点 0 和 节点 2 之间不存在路径
// 
//
// 
//
// 提示： 
//
// 
// 2 <= n <= 10^4 
// 0 <= start, end < n 
// start != end 
// 0 <= a, b < n 
// a != b 
// 0 <= succProb.length == edges.length <= 2*10^4 
// 0 <= succProb[i] <= 1 
// 每两个节点之间最多有一条边 
// 
// Related Topics 图 最短路 堆（优先队列） 👍 102 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1514 {

    /**
     * 求最短路径
     * 相似题目: 743, 1631
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Pair>[] graph = buildGraph(edges, succProb, n);

        double[] distTo = new double[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        distTo[start] = 1.0;

        while (!queue.isEmpty()) {
            int from = queue.poll();
            for (Pair pair: graph[from]) {
                int to = pair.to;
                double distance = distTo[from] * pair.distance;
                if (distance > distTo[to]) {
                    distTo[to] = distance;
                    queue.offer(to);
                }
            }
        }

        return distTo[end];
    }

    private List<Pair>[] buildGraph(int[][] edges, double[] succProb, int n) {
        List<Pair>[] graph = new List[n];
        // 人傻了, 这个代码
        // Arrays.fill(graph, new ArrayList<>());
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph[edge[0]].add(Pair.of(edge[1], succProb[i]));
            graph[edge[1]].add(Pair.of(edge[0], succProb[i]));
        }
        return graph;
    }

    private static class Pair {
        int to;
        double distance;

        public static Pair of(int to, double distance) {
            Pair pair = new Pair();
            pair.to = to;
            pair.distance = distance;
            return pair;
        }
    }

    public static void main(String[] args) {
        Solution1514 solution1514 = new Solution1514();
        int[][] edges = new int[][]{{0,1}, {1,2}, {0,2}};
        double[] succProb = new double[]{0.5,0.5,0.2};
        solution1514.maxProbability(3, edges, succProb, 0, 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
