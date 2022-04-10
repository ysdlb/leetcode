//输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。 
//
// 例如： 
//
// 给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 10000 
// 
//
// 注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-
//tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 164 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */

class SolutionOffer55_1 {
    /**
     * <a href="https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/">leetcode104</a>
     * <p>
     * <a href="https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/">leetcode-offer-55-1</a>
     * <p>
     * 两种解法
     * 1. dfs 先序遍历, 把每一条路径走一遍, 递归回溯
     * 2. 递归思想
     *      a. 如果一棵树的根节点是null, 则树高是0,
     *      b. 如果一棵树的跟节点没有孩子节点, 那么它的树高树1,
     *      c. 否则该树的高度为最大的子树高度 + 1
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
