//输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。 
//
// 
//
// 示例 1: 
//
// 给定二叉树 [3,9,20,null,null,15,7] 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回 true 。 
// 
//示例 2: 
//
// 给定二叉树 [1,2,2,3,3,null,null,4,4] 
//
// 
//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
// 
//
// 返回 false 。 
//
// 
//
// 限制： 
//
// 
// 0 <= 树的结点个数 <= 10000 
// 
//
// 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/ 
//
// 
// Related Topics 树 深度优先搜索 二叉树 👍 229 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class SolutionOffer55_2 {
    /**
     * <a href="https://leetcode-cn.com/problems/balanced-binary-tree/">leetcode110</a>
     * <p>
     * <a href="https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/">leetcode-offer-55-2</a>
     * <p>
     *
     * 如何判断平衡二叉树:
     * 1. 左右子树都为平衡二叉树
     * 2. 左右子树的高度差 <= 1
     * 换种说法: 每个节点的左右子树的深度相差 <= 1
     * 自下而上遍历不会重复计算, 只遍历一次
     */
    private boolean isB = true;
    public boolean isBalanced(TreeNode root) {
        depth(root);
        return isB;
    }

    /**
     * 证明它不是平衡二叉树
     */
    public int depth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        if (isB) {
            int left = depth(root.left);
            int right = depth(root.right);
            if (Math.abs(left - right) > 1)
                isB = false;
            return Math.max(left, right) + 1;
        }
        return -1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
