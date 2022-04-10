//给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!
//=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。 
//
// 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：["a==b","b!=a"]
//输出：false
//解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
// 
//
// 示例 2： 
//
// 输入：["b==a","a==b"]
//输出：true
//解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
// 
//
// 示例 3： 
//
// 输入：["a==b","b==c","a==c"]
//输出：true
// 
//
// 示例 4： 
//
// 输入：["a==b","b!=c","c==a"]
//输出：false
// 
//
// 示例 5： 
//
// 输入：["c==c","b==d","x!=z"]
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= equations.length <= 500 
// equations[i].length == 4 
// equations[i][0] 和 equations[i][3] 是小写字母 
// equations[i][1] 要么是 '='，要么是 '!' 
// equations[i][2] 是 '=' 
// 
// Related Topics 并查集 图 数组 字符串 👍 202 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution990 {
    /**
     * 用相等关系构建并查集合
     * (即等式两边是图中的一个个节点, 相等关系将它们构建为一个个联通子图)
     *
     * 然后遍历所有不等式, 若它们联通, 则改算式集合肯定是冲突的
     *
     * ps: 维护两个并查集, 相等式的在不等并查集判断是否联通, 然后在相等并查集将它们联通
     * 不等式反之
     */
    public boolean equationsPossible(String[] equations) {
        UnionFind unionFind = new UnionFind(26);
        List<Integer> notEqual = new ArrayList<>();

        for (int i = 0; i < equations.length; i++) {
            String s = equations[i];
            if (s.charAt(1) == '=') {
                unionFind.union(s.charAt(0) - 'a', s.charAt(3) - 'a');
            } else {
                notEqual.add(i);
            }
        }

        for (int i : notEqual) {
            String s = equations[i];
            if (unionFind.isConnected(s.charAt(0) - 'a', s.charAt(3) - 'a'))
                return false;
        }
        return true;
    }

    private static class UnionFind {
        private final int[] parent;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        /**
         * 联通节点 a 和 节点 b
         */
        public void union(int a, int b) {
            int aRoot = findRoot(a);
            int bRoot = findRoot(b);
            parent[aRoot] = bRoot;
        }

        /**
         * @param a 节点a
         * @param b 节点b
         * @return a b 是否联通
         */
        public boolean isConnected(int a, int b) {
            return findRoot(a) == findRoot(b);
        }

        /**
         * @param node 任意节点
         * @return node 的根节点
         */
        public int findRoot(int node) {
            while (parent[node] != node) {
                // 路径压缩
                parent[node] = parent[parent[node]];
                node = parent[node];
            }
            return node;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
