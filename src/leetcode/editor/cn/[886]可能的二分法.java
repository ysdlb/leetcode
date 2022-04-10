//给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。 
//
// 每个人都可能不喜欢其他人，那么他们不应该属于同一组。 
//
// 形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。 
//
// 当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：N = 4, dislikes = [[1,2],[1,3],[2,4]]
//输出：true
//解释：group1 [1,4], group2 [2,3]
// 
//
// 示例 2： 
//
// 
//输入：N = 3, dislikes = [[1,2],[1,3],[2,3]]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 2000 
// 0 <= dislikes.length <= 10000 
// dislikes[i].length == 2 
// 1 <= dislikes[i][j] <= N 
// dislikes[i][0] < dislikes[i][1] 
// 对于 dislikes[i] == dislikes[j] 不存在 i != j 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 140 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.List;

/**
 * 同 785：判断二分图
 */
class Solution886 {
    private boolean isOk = true;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        if (n == 0) return true;

        List<Integer>[] graph = buildGraph(n, dislikes);
        int[] visited = new int[n];

        // 图可能不连通, 遍历每一个节点
        for (int node = 0; node < n; node++) {
            if (visited[node] == 0)
                // 如果节点未访问过, 设置一个初始值, 遍历
                visited[node] = 1;
                traverse(graph, node, visited);
        }
        return isOk;
    }

    /**
     * @param graph 无向图的邻接表
     * @param node 当前访问的点
     * @param visited 访问记录, 0: 未访问过; 1: 已经染为颜色1; 2: 已经染为颜色2
     *
     * 总会遍历到一条 path 的最后一个节点 a 和 a 的下一个节点 b, 此时 a 与 b 颜色可能相同; 判断之, 此时形成真正全覆盖
     */
    private void traverse(List<Integer>[] graph, int node, int[] visited) {
        // 染色失败
        if (!isOk) return;

        for (int n: graph[node]) {
            if (visited[n] == 0) {
                // 如果节点未访问过, 根据前置节点 node 设置不同的颜色, 继续深度遍历
                visited[n] = visited[node] == 1 ? 2 : 1;
                traverse(graph, n, visited);
            } else if (visited[node] == visited[n]) {
                // 如果已经被访问过, 且前置 node 和 当前节点颜色相同 gg
                isOk = false;
            }
        }
    }

    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        // 无向图是双向的
        for (int[] dislike: dislikes) {
            graph[dislike[0] - 1].add(dislike[1] - 1);
            graph[dislike[1] - 1].add(dislike[0] - 1);
        }
        return graph;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
