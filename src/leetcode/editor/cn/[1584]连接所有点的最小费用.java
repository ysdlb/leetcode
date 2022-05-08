//给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。 
//
// 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 
//val 的绝对值。 
//
// 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//输出：20
//解释：
//
//我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
//注意到任意两个点之间只有唯一一条路径互相到达。
// 
//
// 示例 2： 
//
// 
//输入：points = [[3,12],[-2,5],[-4,1]]
//输出：18
// 
//
// 示例 3： 
//
// 
//输入：points = [[0,0],[1,1],[1,0],[-1,1]]
//输出：4
// 
//
// 示例 4： 
//
// 
//输入：points = [[-1000000,-1000000],[1000000,1000000]]
//输出：4000000
// 
//
// 示例 5： 
//
// 
//输入：points = [[0,0]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= points.length <= 1000 
// -10⁶ <= xi, yi <= 10⁶ 
// 所有点 (xi, yi) 两两不同。 
// 
// Related Topics 并查集 数组 最小生成树 👍 202 👎 0


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1584 {
    /**
     * 所有的点两两不同, 我们可以用 points 数组的 index 来表示每个 point
     *  用 (point1, point2, weight) 来表示每条无向边
     *
     *  对每条边按最小权重优先排序, 以此顺序通过并查集保证不出现环的情况下构成一颗树, 就是最小生成树
     *  同样用到并查集的题目: 990
     *  类似题目: 261, 1135
     */
    public int minCostConnectPoints(int[][] points) {
        List<int[]> edges = buildGraph(points);
        // 贪心思想, 按边的权重, 从小到大排序
        edges.sort(Comparator.comparingInt(e -> e[2]));
        UnionFind unionFind = new UnionFind(points.length);

        int minCost = 0;
        for (int[] edge: edges) {
            int p = edge[0], q = edge[1], weight = edge[2];
            // 如果已经联通, 则直接跨过去
            if (unionFind.isConnected(p, q))
                continue;

            // 加入联通分量, 因为必定所有节点相连, 所以最终的 unionFind.getCount() == 1
            // 因为它不会生成环, 所以可以加入最小生成树
            unionFind.union(p, q);
            minCost += weight;
        }
        return minCost;
    }

    /**
     * @param points 点集合
     * @return (point1, point2, weight) 组成的图
     */
    private List<int[]> buildGraph(int[][] points) {
        List<int[]> ret = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i+1; j < points.length; j++) {
                int[] point1 = points[i], point2 = points[j];

                int weight = Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
                int[] edge = new int[]{i, j, weight};
                ret.add(edge);
            }
        }
        return ret;
    }

    private static class UnionFind {
        // parent 数组的 index 表示 point 点, value 表示他的联通分量根节点
        private final int[] parent;

        // 联通分量的数量
        private int count;

        // 每个联通分量的节点数目
        private final int[] weight;

        public UnionFind(int size) {
            parent = new int[size];
            weight = new int[size];
            for (int i = 0; i < size; i++) {
                // 当 i == parent[i] 时, 表示它是 root 节点
                parent[i] = i;
                weight[i] = 1;
            }
            count = size;
        }

        /**
         * 联通节点 p 和 q
         */
        public void union(int p, int q) {
            int rootP = findRoot(p);
            int rootQ = findRoot(q);
            if (rootP == rootQ) return;

            // 这里可以随便接, 优化下就是小树接到大树下面, 更平衡一些
            if (weight[rootP] < weight[rootQ]) {
                parent[rootP] = rootQ;
                weight[rootQ] += weight[rootP];
            } else {
                parent[rootQ] = rootP;
                weight[rootP] += weight[rootQ];
            }
            // 联通分量的数目减去 1
            count--;
        }

        /**
         * @return 节点 p 和 节点 q 是否联通
         */
        public boolean isConnected(int p, int q) {
            return findRoot(p) == findRoot(q);
        }

        /**
         * @return 节点 x 的联通分量跟节点
         * 顺便进行路径压缩
         */
        public int findRoot(int x) {
            while (x != parent[x]) {
                // 路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public int getCount() {
            return count;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
