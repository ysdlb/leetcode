//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。 
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树 深度优先搜索 二叉树 👍 997 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution543 {
    /**
     * 两结点之间的路径长度是以它们之间边的数目表示。
     * 后序遍历, 二叉树的直径为某颗子树中左子树和右子树的最大深度之和;
     * 求深度参考 104, 111
     * 相似参考: 124
     */
    int ret = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depthOfTree(root);
        return ret;
    }

    /**
     * 后续遍历求最大深度
     * 顺便更新最大直径
     */
    private int depthOfTree(TreeNode root) {
        if (root == null) return 0;
        int left = depthOfTree(root.left);
        int right = depthOfTree(root.right);

        ret = Math.max(left + right, ret);
        return Math.max(left, right) + 1;
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
