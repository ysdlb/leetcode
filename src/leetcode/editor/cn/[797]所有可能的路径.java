//给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序） 
//
// 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。 
//
// 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：graph = [[1,2],[3],[3],[]]
//输出：[[0,1,3],[0,2,3]]
//解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
// 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
//输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
// 
//
// 示例 3： 
//
// 
//输入：graph = [[1],[]]
//输出：[[0,1]]
// 
//
// 示例 4： 
//
// 
//输入：graph = [[1,2,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,2,3],[0,3]]
// 
//
// 示例 5： 
//
// 
//输入：graph = [[1,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,3]]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 2 <= n <= 15 
// 0 <= graph[i][j] < n 
// graph[i][j] != i（即，不存在自环） 
// graph[i] 中的所有元素 互不相同 
// 保证输入为 有向无环图（DAG） 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 回溯 👍 208 👎 0


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution797 {
    /**
     * 有向无环图的遍历
     * 从 0 节点走到 graph.length 节点
     * 递归遍历 + 回溯
     */
    private List<List<Integer>> res;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        res = new ArrayList<>();
        traverse(graph, 0, new LinkedList<>());
        return res;
    }

    /**
     * @param graph 图
     * @param k 图中的节点
     * @param path 从 0 节点走过来的路径
     */
    private void traverse(int[][] graph, int k, Deque<Integer> path) {
        path.add(k);

        /*
         * 如果此时是最后一个节点, 则记录下这条路径
         */
        if (k == graph.length - 1) {
            res.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }

        for (int node: graph[k]) {
            traverse(graph, node, path);
        }

        // 回去的时候, 从该路径退出这个节点
        path.removeLast();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
