//给定一个二叉树，我们在树的节点上安装摄像头。 
//
// 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。 
//
// 计算监控树的所有节点所需的最小摄像头数量。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[0,0,null,0,0]
//输出：1
//解释：如图所示，一台摄像头足以监控所有节点。
// 
//
// 示例 2： 
//
// 
//
// 输入：[0,0,null,0,null,0,null,null,0]
//输出：2
//解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
// 
//
// 
//提示： 
//
// 
// 给定树的节点数的范围是 [1, 1000]。 
// 每个节点的值都是 0。 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 411 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution968 {
    /**
     * 求最小摄像头数量, 直觉用 DP 来做, 这里是一个树形 DP
     *
     * 根据题目要求, 每个节点可能是三种状态之一 (每个节点只依赖其左右子树)
     *  a. 安装相机 (无论其左右孩子有没有安装相机, 孩子有没有被覆盖到, 它总能被覆盖掉, 其孩子也可以) 总相机数 +1
     *  b. 不安装相机, 但可以被子节点覆盖到 (左右孩子至少一个安装了相机, 即只有一个装了(另一个一定可以被覆盖到）, 两个都装了) 总相机数 +0
     *  c. 不安装相机, 也不可以被子节点覆盖到 (左右孩子没有一个安装相机, 根据 a 推倒, 其父节点必须安装相机; 根据 b 推倒, 左右孩子一定被覆盖到) 总相机树 +0
     *
     *  root(a) = min{left(a), left(b), left(c)} + min{right(a), right(b), right(c)} + 1
     *  root(b) = min{left(a) + right(b), left(b) + right(a), left(a) + right(b)}
     *  root(c) = left(b) + right(b)
     *
     * 对任意一个叶子节点, 其 a,b,c 为 [1,+INF,0]
     * 解释:
     *  叶子节点可以安装相机, 所以 a == 1;
     *  叶子节点不安装相机的情况下, 还要被子节点覆盖到, 这不可能
     *  叶子节点不安装相机的情况下, 不可能被子节点覆盖到, 所以什么都不用做
     *
     * 对任意一个空节点, 其 a,b,c 为 [+INF, 0, +INF] (通过叶子节点反推)
     * 解释:
     *  空节点安装不了相机, a = INF
     *  空节点没有相机, 天然被覆盖掉, b = 0
     *  和 b 冲突, c = INF
     *
     * 最终满足题意的值为 a 或者 b, 直接去 min(a, b)
     *
     * 二叉树DP，留存贪心思路
     * 类似 979
     */
    public int minCameraCover(TreeNode root) {
        int[] r = cover(root);
        return Math.min(r[0], r[1]);
    }

    private int[] cover(TreeNode root) {
        if (root == null) {
            int INF = Integer.MAX_VALUE / 2;
            return new int[]{INF, 0, INF};
        }

        int[] left = cover(root.left);
        int[] right = cover(root.right);

        int a = Math.min(left[0], Math.min(left[1], left[2]))
                + Math.min(right[0], Math.min(right[1], right[2]))
                + 1;
        int b = Math.min(left[0] + right[0], Math.min(left[0] + right[1], left[1] + right[0]));
        int c = left[1] + right[1];
        return new int[]{a, b, c};
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
