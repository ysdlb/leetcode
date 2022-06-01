//给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。 
//
// 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。 
//
// 返回使每个结点上只有一枚硬币所需的移动次数。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[3,0,0]
//输出：2
//解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
// 
//
// 示例 2： 
//
// 
//
// 输入：[0,3,0]
//输出：3
//解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。
// 
//
// 示例 3： 
//
// 
//
// 输入：[1,0,2]
//输出：2
// 
//
// 示例 4： 
//
// 
//
// 输入：[1,0,0,null,3]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1<= N <= 100 
// 0 <= node.val <= N 
// 
// Related Topics 树 深度优先搜索 二叉树 👍 304 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 */
class Solution979 {
    /**
     * 题目没有说最少移动次数, 所以感觉不管怎么操作, 移动次数是固定的
     *
     * f(node) 表示 node 这棵树需要从这棵树移出硬币的数量
     * 如果是负数, 则代表移进, 比如对任意一个非空节点, 总需要一枚硬币, 所以它天然需要 +(-1)
     *
     * 对任意的 root 节点, 左子树, 右子树
     * root.val >= 0
     * f(root.left), 以及 f(root.right)
     *
     *   1
     * 0    0
     *
     * 1 + (-1) + (-1) - 1 = -2
     *
     *  0
     * 2  2
     * 0 + (1) + (1) - 1 = 1
     *
     * f(root) = root.val + f(left) + f(right) - 1
     *
     * 因为题目保证 N 个节点总是有 N 枚硬币, 所以 f(root) == 0;
     * 这棵树中每条边对应的孩子节点的 f(child) 绝对值之和, 就是我们需要的最终值
     */
    public int distributeCoins(TreeNode root) {
        return distribute(root)[1];
    }

    /**
     * @return [a, b]
     * a 表示 f(root) 的值, b root 这棵树需要移动的硬币数量之和
     *
     * 这里的 b 只会依赖子任务 a 的值, 所以 b 可以用一个全局变量, 累加过程值也可
     */
    public int[] distribute(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] left = distribute(root.left);
        int[] right = distribute(root.right);

        int a = root.val + left[0] + right[0] - 1;
        int b = Math.abs(left[0]) + Math.abs(right[0]) + left[1] + right[1];
        return new int[]{a, b};
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
