//有 n 个网络节点，标记为 1 到 n。 
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， 
//wi 是一个信号从源节点传递到目标节点的时间。 
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= n <= 100 
// 1 <= times.length <= 6000 
// times[i].length == 3 
// 1 <= ui, vi <= n 
// ui != vi 
// 0 <= wi <= 100 
// 所有 (ui, vi) 对都 互不相同（即，不含重复边） 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列） 👍 540 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution743 {
    /**
     * 求最短路径
     * Dijkstra 算法: BFS 算法加上 distance 距离过滤
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        List<int[]>[] graph = buildGraph(times, n);

        // 简单的 Dijsktra 算法
        // 记录 k 节点到每个节点的最小距离
        int[] distTo = new int[n+1];
        // 我们要求最小值
        Arrays.fill(distTo, INF);

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
        distTo[k] = 0;
        queue.offer(new int[]{k, 0});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int from = node[0], fromStartDist = node[1];
            if (fromStartDist > distTo[from])
                continue;

            for (int[] edge: adj(graph, from)) {
                int to = edge[0];
                int weight = edge[1];

                int distance = distTo[from] + weight;
                // 如果这条路径比原来的更短
                if (distance < distTo[to]) {
                    distTo[to] = distance;
                    queue.offer(new int[]{to, distance});
                }
            }
        }

        int ret = -1;
        for (int i = 1; i <= n; i++) {
            // i 节点无法到达
            if (distTo[i] == INF)
                return -1;
            if (i != k)
                ret = Math.max(ret, distTo[i]);
        }
        return ret;
    }

    /**
     * @return 临接表表示的图
     */
    private List<int[]>[] buildGraph(int[][] edges, int n) {
        // 节点从 1 开始
        List<int[]>[] ret = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            ret[i] = new ArrayList<>();
        }

        for (int[] edge: edges) {
            int from = edge[0], to = edge[1];
            int weight = edge[2];
            int[] e = new int[]{to, weight};
            ret[from].add(e);
        }
        return ret;
    }

    /**
     * @param graph 图
     * @param k 节点 k
     * @return k 的临接点
     */
    private List<int[]> adj(List<int[]>[] graph, int k) {
        return graph[k];
    }

    public static void main(String[] args) {
        int[][] arg = new int[][]{{2,1,1}, {2,3,1}, {3,4,1}};
        Solution743 solution743 = new Solution743();
        solution743.networkDelayTime(arg, 4, 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution743_SeeIt {
    /**
     * 求最短路径
     * Dijkstra 算法: BFS 算法加上 distance 距离过滤
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        List<int[]>[] graph = buildGraph(times, n);

        // 简单的 Dijsktra 算法
        // 记录 k 节点到每个节点的最小距离
        int[] distTo = new int[n+1];
        // 我们要求最小值
        Arrays.fill(distTo, INF);

        Queue<Integer> queue = new LinkedList<>();
        distTo[k] = 0;
        queue.offer(k);

        while (!queue.isEmpty()) {
            int from = queue.poll();

            for (int[] edge: adj(graph, from)) {
                int to = edge[0];
                int weight = edge[1];

                int distance = distTo[from] + weight;
                // 如果这条路径比原来的更短
                if (distance < distTo[to]) {
                    distTo[to] = distance;
                    queue.offer(to);
                }
            }
        }

        int ret = -1;
        for (int i = 1; i <= n; i++) {
            // i 节点无法到达
            if (distTo[i] == INF)
                return -1;
            if (i != k)
                ret = Math.max(ret, distTo[i]);
        }
        return ret;
    }

    /**
     * @return 临接表表示的图
     */
    private List<int[]>[] buildGraph(int[][] edges, int n) {
        // 节点从 1 开始
        List<int[]>[] ret = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            ret[i] = new ArrayList<>();
        }

        for (int[] edge: edges) {
            int from = edge[0], to = edge[1];
            int weight = edge[2];
            int[] e = new int[]{to, weight};
            ret[from].add(e);
        }
        return ret;
    }

    /**
     * @param graph 图
     * @param k 节点 k
     * @return k 的临接点
     */
    private List<int[]> adj(List<int[]>[] graph, int k) {
        return graph[k];
    }

    public static void main(String[] args) {
        int[][] arg = new int[][]{{1,2,1}, {2,3,2}, {1,3,1}};
        Solution743_SeeIt solution743 = new Solution743_SeeIt();
        solution743.networkDelayTime(arg, 3, 2);
    }
}
