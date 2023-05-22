//给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。 
//
// 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。 
//
// 叶子节点，就是没有子节点的节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
//输出：[1,2,3,4,null,null,7,8,9,null,14]
// 
//
// 示例 2： 
// 
// 
//输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
//输出：[5,4,8,11,null,17,4,7,null,null,null,5]
// 
//
// 示例 3： 
// 
// 
//输入：root = [1,2,-3,-5,null,4,null], limit = -1
//输出：[1,null,-3,4]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 5000] 内 
// -10⁵ <= Node.val <= 10⁵ 
// -10⁹ <= limit <= 10⁹ 
// 
//
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 112 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1080 {
    /* 1080.根到叶路径上的不足节点: https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths/
     * 相似题目:
     *  236.二叉树的最近公共祖先: https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
     *
     * 问题要求:
     *   如果通过节点 node 所有的 “根-叶“ 路径和小于 limit，那么称其为不足节点，应当删除
     *
     * 思路:
     * 同时存在先序遍历和后续遍历
     * 先序: 入参 prePathSum 表示真正的 root 到当前节点父节点的路径和，
     * 后序: 返回值 postSum 为当前节点到叶子节点的最大路径和
     *  如果左节点的 postSum + prePathSum + curNode.val < limit, 删除左节点
     *  同样的方式判断是否删除右节点
     * 这种方式可以保证树中不存在不足节点, 却不满足"删除"这个问题要求, 题目要求需要自上向下判断:
     *
     * 如果一条 "根-叶" 路径和 sum < limit, 那么删除这个叶子;
     * 如果该叶子的父节点只有一个叶子, 或另一个叶子也被删除, 那么这个父节点也应该被删除
     * 同样的递推逻辑推广到子树, 非叶子节点的左子树和右子树都没了, 这个节点也应该被删除
     *
     * 即找到所有应该被删除叶子节点的所有最近公共祖先删除掉.
     */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        // int maxSum = this.countPathSum(root, 0, limit);
        // return maxSum < limit ? null : root;
        if (root == null) return null;
        if (root.left == null && root.right == null) return root.val < limit ? null : root;

        root.left = sufficientSubset(root.left, limit - root.val);
        root.right = sufficientSubset(root.right, limit - root.val);

        return root.left == null && root.right == null ? null : root;
    }

    /**
     * @param root root 节点
     * @param prePathSum 真实 root 到当前节点父节点的路径和
     * @param limit 题目要求的 limit，不变量
     * @return 当前节点到叶子节点的路径和
     *
     * 思路:
     * 同时存在先序遍历和后续遍历
     * 先序: 入参 prePathSum 表示真正的 root 到当前节点父节点的路径和，
     * 后序: 返回值 postSum 为当前节点到叶子节点的最大路径和
     *  如果左节点的 postSum + prePathSum + curNode.val < limit, 删除左节点
     *  同样的方式判断是否删除右节点
     * 这种方式可以保证树中不存在不足节点, 却不满足"删除"这个问题要求, 题目要求需要自上向下判断:
     */
    private int countPathSum(TreeNode root, int prePathSum, int limit) {
        if (root == null) return 0;

        prePathSum += root.val;
        int left = countPathSum(root.left, prePathSum, limit);
        int right = countPathSum(root.right, prePathSum, limit);
        if (left + prePathSum < limit) {
            root.left = null;
            left = 0;
        }

        if (right + prePathSum < limit) {
            root.right = null;
            right = 0;
        }

        return Math.max(left, right) + root.val;
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
