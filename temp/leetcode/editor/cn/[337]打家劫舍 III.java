//小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。 
//
// 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接
//相连的房子在同一天晚上被打劫 ，房屋将自动报警。 
//
// 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [3,2,3,null,3,null,1]
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7 
//
// 示例 2: 
//
// 
//
// 
//输入: root = [3,4,5,1,3,null,1]
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
// 
//
// 
//
// 提示： 
//
// 
//
// 
// 树的节点数在 [1, 10⁴] 范围内 
// 0 <= Node.val <= 10⁴ 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1166 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution337 {
    /**
     * 参考 198 和 213, 单链路的打家劫舍很好做, 这道题的难点在于要求多链路之和最佳（从根节点到叶子节点为一条链路)
     * 原来是每个节点只依赖它的前一个节点, 现在是至多两个节点会依赖同一个父节点
     * 自顶向下单链路的最优解可能对总体的最优解有副作用, 所以自底向上归一到 root 节点可能会好点
     * 所以可以选择后续遍历
     *
     * 状态: 偷到了哪个节点, 该节点偷不偷
     *
     * 选择
     * 基本思路, 对任意一棵树 f(node, 0) 不偷 node; f(node, 1) 偷 node
     * 1. 如果偷 root 节点, 那么只能在它的左右子树下去偷
     *      f(root, 1) = f(node.left, 0) + f(node.right, 0) + root.val
     * 2. 如果不偷 root 节点, 那么允许偷它的左右孩子  (可偷可不偷)
     *      f(root, 0) = Math.max{f(root.left, 0), f(root.left, 1)}
     *                  + Math.max{f(root.right, 0), f(root.right, 1)}
     * 最终 1 和 2 是全部的两种情况, 谁大取谁
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int[] ret = toRob(root);
        return Math.max(ret[0], ret[1]);
    }

    public int[] toRob(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};
        if (root.left == null && root.right == null)
            return new int[]{0, root.val};

        int[] left = toRob(root.left);
        int[] right = toRob(root.right);

        int[] ret = new int[2];
        ret[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        ret[1] = left[0] + right[0] + root.val;
        return ret;
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
