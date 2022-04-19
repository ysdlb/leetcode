//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 10⁵] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 722 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution111 {
    /**
     * 两种方案:
     * 1. BFS, 分层式的层序遍历, 看看一共有多少满的层
     * 2. DFS, 后续优先遍历, 一颗树的树高为 min(leftH, rightH) + 1
     *
     * 类似的题目: 二叉树的最大深度 104
     * 剑指Offer55-1
     */
    public int minDepth(TreeNode root) {
        // DFS 这种实现, 叶子节点才算数
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        // 他俩不可能同时为 0
        if (left != 0 && right != 0)
            return Math.min(left, right) + 1;
        else if (left == 0)
            return right + 1;
        else // right == 0
            return left + 1;
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
