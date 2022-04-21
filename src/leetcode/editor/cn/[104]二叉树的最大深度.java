//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1204 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution104 {
    /**
     * 两种方案:
     * 1. BFS, 分层式的层序遍历, 看看一共有多少层
     * 2. DFS, 后续优先遍历, 一颗树的树高为 max(leftH, rightH) + 1
     *
     * 同题 剑指Offer55-1
     * 类似的题目: 二叉树的最小深度 <a href="https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/">leetcode-111</a>
     * 深度的应用参考: 543
     * 变种应用参考: 124
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return Math.max(
                maxDepth(root.left),
                maxDepth(root.right)
        ) + 1;
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
