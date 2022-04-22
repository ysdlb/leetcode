//给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。 
//
// 差值是一个正数，其数值等于两值之差的绝对值。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：root = [4,2,6,1,3]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：root = [1,0,48,null,null,12,49]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目范围是 [2, 100] 
// 0 <= Node.val <= 10⁵ 
// 
//
// 
//
// 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-
//bst/ 相同 
// 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 二叉树 👍 216 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution783 {

    /**
     * 中序遍历有序, 最先差只能在连续的两个数之间出
     * 同题 530
     */
    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return (int) minV;
    }
    long pre = Integer.MIN_VALUE;
    long minV = Integer.MAX_VALUE;

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        minV = Math.min(root.val - pre, minV);
        pre = root.val;

        inorder(root.right);
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
