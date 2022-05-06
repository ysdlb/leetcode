//存在一个 无向图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。给你一个二维数组 graph ，其中 graph[u]
// 是一个节点数组，由节点 u 的邻接节点组成。形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。该无向图同时具有
//以下属性：
// 
// 不存在自环（graph[u] 不包含 u）。 
// 不存在平行边（graph[u] 不包含重复值）。 
// 如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图） 
// 这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。 
// 
//
// 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称
//为 二分图 。 
//
// 如果图是二分图，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
//输出：false
//解释：不能将节点分割成两个独立的子集，以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。 
//
// 示例 2： 
//
// 
//输入：graph = [[1,3],[0,2],[1,3],[0,2]]
//输出：true
//解释：可以将节点分成两组: {0, 2} 和 {1, 3} 。 
//
// 
//
// 提示： 
//
// 
// graph.length == n 
// 1 <= n <= 100 
// 0 <= graph[u].length < n 
// 0 <= graph[u][i] <= n - 1 
// graph[u] 不会包含 u 
// graph[u] 的所有值 互不相同 
// 如果 graph[u] 包含 v，那么 graph[v] 也会包含 u 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 316 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class TraverseColor785 {

    private boolean isOk = true;

    /**
     * 遍历模拟染色
     *  a. 如果 v 未被染色，那么我们将其染成与 u 不同的颜色，并对 v 直接相连的节点进行遍历；
     *  b. 如果 v 被染色，并且颜色与 u 相同，那么说明给定的无向图不是二分图。我们可以直接退出遍历并返回 false 作为答案。
     *
     *  类似题目: 886
     */
    public boolean isBipartite(int[][] graph) {
        if (graph.length == 0) return true;

        byte[] visited = new byte[graph.length];
        for (int node = 0; node < graph.length; node++) {
            // 因为图可能不连通, 所以对每一个节点为起点遍历一遍
            if (visited[node] == 0) {
                // 如果节点未访问过, 因为这是一条 path 的起点, 所以我们要初始化一个颜色
                visited[node] = 1;
                traverse(graph, node, visited);
            }
        }
        return isOk;
    }

    /**
     * @param graph 图的邻接矩阵形式
     * @param node 要处理的点
     * @param visited 0：未访问; 1: 已经染为颜色 1; 2: 已经染为颜色2
     */
    private void traverse(int[][] graph, int node, byte[] visited) {
        if (!isOk) return;
        for(int n: graph[node]) {
            if (visited[n] == 0) {
                // 如果该节点未访问过, 则根据 node 节点染一个未染过的颜色
                // 隐含已经访问过的意思
                visited[n] = (byte) (visited[node] == 1 ? 2 : 1);
                // 继续遍历
                traverse(graph, n, visited);
            } else if (visited[node] == visited[n]) {
                // 已经访问的节点判断当前颜色是否合规,
                // 这里必须要判断, 一条 path 的最后一个节点颜色可能刚好和 path 中已经染过的节点颜色相同
                // 若相同, 则为 false
                isOk = false;
            }
        }
    }

    private static class Test {
        public static void main(String[] args) {
            int[][] graph = new int[][]{{1},{0,3},{3},{1,2}};
            System.out.println(new TraverseColor785().isBipartite(graph));
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
