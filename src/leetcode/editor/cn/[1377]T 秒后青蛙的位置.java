//给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下： 
//
// 
// 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。 
// 青蛙无法跳回已经访问过的顶点。 
// 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。 
// 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。 
// 
//
// 无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。 
//
// 返回青蛙在 t 秒后位于目标顶点 target 上的概率。与实际答案相差不超过 10⁻⁵ 的结果将被视为正确答案。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
//输出：0.16666666666666666 
//解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙
//在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。 
// 
//
// 示例 2： 
//
// 
//
// 
//输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
//输出：0.3333333333333333
//解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。 
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 100 
// edges.length == n - 1 
// edges[i].length == 2 
// 1 <= ai, bi <= n 
// 1 <= t <= 50 
// 1 <= target <= n 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 图 👍 89 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1377 {

    /* 1377.T 秒后青蛙的位置: https://leetcode.cn/problems/frog-position-after-t-seconds/
     *
     * 树从 root 到任意节点，最多只有 1 条路径, 记录这条路径上的分母
     * 其实不是一颗树，而是无向图
     *
     * 其实也是一颗树, 但不是按照树的形式给的
     * [[2,1],[3,2],[4,1],[5,1],[6,4],[7,1],[8,7]]
     * 青蛙总是从顶点 1 开始，可以向树的任何方向跳
     * 路径仍然只有一条
     */
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] tree = new ArrayList[n+1];
        Arrays.setAll(tree, e -> new ArrayList<>());
        // 哨兵, 1 是根节点，它没有父节点; 而其他的子节点列表中一定存在该节点的父节点.
        tree[1].add(0);
        for (int[] edge: edges) {
            int from = edge[0], to = edge[1];
            tree[from].add(to);
            tree[to].add(from);
        }

        long dfs = dfs(tree, 0, 1, t, target);
        return dfs == 0 ? 0.0d : 1.0d/dfs;
    }

    private long dfs(List<Integer>[] tree, int fa, int cur, int t, int target) {
        // 如果还能跳，就一定继续跳；是不可能停在原地的
        if (t == 0) return cur == target ? 1 : 0;
        // 如果没有孩子，不能跳了，那么就一直停在原地；每个 tree 元素里一定存在一个父节点
        if (cur == target) return tree[cur].size() == 1 ? 1 : 0;

        for (int e: tree[cur]) {
            if (e == fa) continue;
            long v = dfs(tree, cur, e, t-1, target);
            if (v != 0) // 里面一定有一个是 fa 节点
                return v * (tree[cur].size()-1);
        }
        return 0;
    }

    public static void main(String[] args) {
        {
            Solution1377 solu = new Solution1377();
            int[][] edges = new int[][]{{2,1}, {3,2}, {4,1}, {5,1}, {6,4}, {7,1},{8,7}};
            double i = solu.frogPosition(8, edges, 7, 7);
            System.out.println(i);
        }
        {
            Solution1377 solu = new Solution1377();
            int[][] edges = new int[][]{{1, 3}, {1, 2}, {1, 7}, {2, 4}, {2, 6}, {3, 5}};
            double i = solu.frogPosition(7, edges, 2, 4);
            System.out.println(i);
        }
        {
            Solution1377 solu = new Solution1377();
            int[][] edges = new int[][]{{2,1}, {3, 2}};
            double i = solu.frogPosition(3, edges, 1, 2);
            System.out.println(i);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
