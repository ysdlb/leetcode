// from: https://www.cxyzjd.com/article/qq_43349112/115256617
// 题目链接
// https://leetcode-cn.com/problems/number-of-connected-components-in-an-undirected-graph/
//
// 描述
// 给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连
// 分量的数目。
//
// 注意:
// 你可以假设在 edges 中不会出现重复的边。而且由于所以的边都是无向边，[0, 1] 与 [1, 0]  相同，所以它们不会
// 同时在 edges 中出现。
// 示例
// 示例 1:
//
// 输入: n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]
//
//      0          3
//      |          |
//      1 --- 2    4
//
// 输出: 2
// 示例 2:
//
// 输入: n = 5 和 edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
//
//      0           4
//      |           |
//      1 --- 2 --- 3
//
// 输出:  1
// 初始代码模板
// class Solution {
//
//     public int countComponents(int n, int[][] edges) {
//
//
//     }
// }

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution323 {

    public int countComponents(int n, int[][] edges) {
        // todo
        return 0;
    }

    private static class UF {
        // 连通分量个数
        private int count;
        // 存储一棵树
        private final int[] parent;
        // 记录树的「重量」
        private final int[] size;

        // n 为图中节点的个数
        public UF(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 将节点 p 和节点 q 连通
        public void union(int p, int q) {
            // 判断 p 和 q 是否联通
            // 如果联通, 则不做操作
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;

            // 小树接到大树下面，较平衡
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            // 两个连通分量合并成一个连通分量
            count--;
        }

        // 判断节点 p 和节点 q 是否连通
        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }

        // 返回节点 x 的连通分量根节点
        private int find(int x) {
            while (parent[x] != x) {
                // 进行路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        // 返回图中的连通分量个数
        public int count() {
            return count;
        }
    }
}
