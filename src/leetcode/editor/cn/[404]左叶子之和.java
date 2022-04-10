//计算给定二叉树的所有左叶子之和。 
//
// 示例： 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 355 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        TreeNode leaf = root.left;
        if (leaf != null && leaf.left == null && leaf.right == null)
            return leaf.val + sumOfLeftLeaves(root.right);
        else
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    /**
     * 简化版本
     */
    public int sumOfLeftLeavesEnhance(TreeNode root) {
        if (root == null) return 0;

        TreeNode leaf = root.left;
        return sumOfLeftLeaves(root.left) +
                sumOfLeftLeaves(root.right) +
                ((leaf != null && leaf.left == null && leaf.right == null) ? leaf.val : 0);
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
