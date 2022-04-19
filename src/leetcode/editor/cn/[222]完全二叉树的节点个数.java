//给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。 
//
// 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层
//为第 h 层，则该层包含 1~ 2ʰ 个节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,4,5,6]
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目范围是[0, 5 * 10⁴] 
// 0 <= Node.val <= 5 * 10⁴ 
// 题目数据保证输入的树是 完全二叉树 
// 
//
// 
//
// 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？ 
// Related Topics 树 深度优先搜索 二分查找 二叉树 👍 544 👎 0


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

/**
 * 不考虑完全二叉树的性质, 直接简单的递归遍历
 * 复杂度 O(n)
 */
class Simple222 {
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        /**
         * 左子树的数量 + 右子树的数量 + 自己
         */
        return countNodes(root.left) + 1
                + countNodes(root.right);
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

/**
 * 利用完全二叉树的性质
 * 1. 如果左右子树的最左高度和最右高度相同, 则直接根据高度计算
 * 2. 如果不相同, 按照上述步骤继续计算左子树和右子树
 *
 * 留存，同等时间复杂度的叶子结点二分解法
 */
class Complex222 {
    public int countNodes(TreeNode root) {
        TreeNode left = root, right = root;

        int lh = 0;
        while (left != null) {
            left = left.left;
            lh++;
        }

        int rh = 0;
        while (right != null) {
            right = right.right;
            rh ++;
        }

        if (lh == rh)
            return (int)Math.pow(2, lh) - 1;
        else {
            return countNodes(root.left) + 1
                    + countNodes(root.right);
        }

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
