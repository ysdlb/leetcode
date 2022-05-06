//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 10⁵ 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1012 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.List;

/**
 * 仅判断是否有环
 */
class HasCycle207 {
    /**
     * 深度优先遍历，加回溯 onPath 判断有无环的产生
     * 类似题目: 210, 310, 630
     */
    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return true;

        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        boolean[] visited = new boolean[numCourses];

        // 因为图可能存在不连通, 所以要对每一个节点进行遍历
        for (int node = 0; node < numCourses; node++) {
            traverse(graph, node, visited, new boolean[numCourses]);
        }
        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int node, boolean[] visited, boolean[] onPath) {
        // 先判断是否发现环
        if (onPath[node] || hasCycle) {
            hasCycle = true;
            return;
        }

        if (visited[node]) return;

        // 将第一次遍历的节点 node 标记为已遍历
        visited[node] = true;
        // 开始遍历 node 的后继节点
        onPath[node] = true;
        for(int n: graph[node])
            traverse(graph, n, visited, onPath);
        onPath[node] = false;
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