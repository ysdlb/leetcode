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
        // 用 parent 数组记录每个节点的父节点，相当于指向父节点的指针，所以 parent 数组内实际存储着一个森林（若干棵多叉树）。
        private final int[] parent;
        // 记录树的「重量」
        // 用 size 数组记录着每棵树的重量，目的是让 union 后树依然拥有平衡性，而不会退化成链表，影响操作效率。
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

        /**
         * 在 find 函数中进行路径压缩，保证任意树的高度保持在常数，使得 union 和 connected API 时间复杂度为 O(1)。
         *
         * 既然有了路径压缩，size 数组的重量平衡还需要吗？这个问题很有意思，因为路径压缩保证了树高为常数（不超过 3），那么树就算不平衡，高度也是常数，基本没什么影响。
         * 东哥认为，论时间复杂度的话，确实，不需要重量平衡也是 O(1)。但是如果加上 size 数组辅助，效率还是略微高一些
         *
         * 比如 [0, 0, 0, 3, 0, 0]
         * union(5, 3)
         * 结果可能为是 size 为 5 的树连接到 size 为 1 的树上
         * 路径压缩时候要对四个节点进行路径压缩
         * 而 union(3, 5) 只需要对 3 这个节点进行路径压缩
         */
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
