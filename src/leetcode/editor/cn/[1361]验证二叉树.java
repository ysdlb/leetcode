//二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。 
//
// 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。 
//
// 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。 
//
// 注意：节点没有值，本问题中仅仅使用节点编号。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
//输出：true
// 
//
// 示例 2： 
//
// 
//
// 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
//输出：false
// 
//
// 示例 3： 
//
// 
//
// 输入：n = 2, leftChild = [1,0], rightChild = [-1,-1]
//输出：false
// 
//
// 示例 4： 
//
// 
//
// 输入：n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10^4 
// leftChild.length == rightChild.length == n 
// -1 <= leftChild[i], rightChild[i] <= n - 1 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 并查集 图 二叉树 👍 84 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1361 {
    /**
     * 二叉树与图的区别
     * 1. 每个节点至多两个子节点
     * 2. 联通分量最终只有一个
     * 3. 无向图中不能出现环
     * 4. 一个子节点只有一个父节点
     *
     * 两个子节点: 因为只有两个数组, 所以天然满足
     * 只需要用并查集保证另外两个
     */
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            int root = i;
            int left = leftChild[i], right = rightChild[i];

            if (left != -1) {
                if (unionFind.isConnected(root, left))
                    return false;
                unionFind.union(root, left);
            }

            if (right != -1) {
                if (unionFind.isConnected(root, right))
                    return false;
                unionFind.union(root, right);
            }
        }
        return unionFind.getCount() == 1;
    }

    private static class UnionFind {
        private final int[] parent;
        private final int[] parentNum;
        private int count;

        public UnionFind(int size) {
            parent = new int[size];
            parentNum = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            count = size;
        }

        /**
         * @param p root
         * @param q children
         */
        public void union(int p, int q) {
            int rootP = findRoot(p);
            int rootQ = findRoot(q);

            parent[rootP] = rootQ;
            parentNum[q]++;
            count--;
        }

        /**
         * @param p root
         * @param q children
         * @return q 目前有父亲 或者 p, q 联通
         */
        public boolean isConnected(int p, int q) {
            return parentNum[q] == 1 || findRoot(p) == findRoot(q);
        }

        private int findRoot(int p) {
            while (p != parent[p]) {
                // 压缩
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        int[] left = new int[]{1, -1, -1};
        int[] right = new int[]{-1, -1, 1};
        Solution1361 solution1361 = new Solution1361();
        solution1361.validateBinaryTreeNodes(3, left, right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
