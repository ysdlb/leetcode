//现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 
//prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。 
//
// 
// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。 
// 
//
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：[0,1]
//解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
// 
//
// 示例 2： 
//
// 
//输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//输出：[0,2,1,3]
//解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。 
//
// 示例 3： 
//
// 
//输入：numCourses = 1, prerequisites = []
//输出：[0]
// 
//
// 
//提示：
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= numCourses * (numCourses - 1) 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// ai != bi 
// 所有[ai, bi] 匹配 互不相同 
// 
//
// 
//
// 拓展： 
//
// 
// 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。 
// 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。 
// 
// 拓扑排序也可以通过 BFS 完成。 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 503 👎 0


import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 深度优先遍历 && 后续遍历
 * 仅能输出一种拓扑排序结果
 * 拓扑排序的结果是后续遍历的逆序列
 *
 * 类似题目: 207, 310, 630
 */
class DFS210 {
    private boolean hasCycle = false;
    private int stackIndex;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return new int[]{};

        // 邻接表的形式保存图
        List<Integer>[] graph = this.buildGraph(numCourses, prerequisites);

        stackIndex = numCourses - 1;
        int[] visited = new int[numCourses], stack = new int[numCourses];

        // 图不一定联通, 所以要对每个节点都遍历一遍
        // 结束状态不存在搜索中的节点, 所以每次选择一个未搜索的节点, 进行遍历
        for (int from = 0; from < numCourses; from++) {
            if (visited[from] == 0)
                dfs(graph, from, visited, stack);
        }

        if (hasCycle)
            return new int[]{};
        return stack;
    }

    /**
     *
     * @param graph 图
     * @param node 当前遍历的节点
     * @param visited 节点状态, 0 未遍历; 1: 正在遍历 (搜索中) ; 2: 遍历完成
     * @param stack 栈, 由于大小已知, 所以可以配合 index 用 int[] 来实现
     */
    private void dfs(List<Integer>[] graph, int node, int[] visited, int[] stack) {
        // 有环
        if (visited[node] == 1 || hasCycle) {
            hasCycle = true;
            return;
        }

        // 遍历完成
        if (visited[node] == 2)
            return;

        // 设置为遍历中
        visited[node] = 1;
        for (int i: graph[node]) {
            dfs(graph, i, visited, stack);
        }
        // 设置为遍历完成
        visited[node] = 2;
        // 后续遍历位置压入当前节点
        stack[stackIndex--] = node;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] res = new LinkedList[numCourses];
        for (int i = 0; i < res.length; i++) {
            res[i] = new LinkedList<>();
        }

        for (int[] edge: prerequisites) {
            // from: edge[1];   to: edge[0];
            res[edge[1]].add(edge[0]);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
