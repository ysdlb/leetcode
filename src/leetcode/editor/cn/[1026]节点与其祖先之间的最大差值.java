//给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。 
//
//
// （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先） 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
//输出：7
//解释： 
//我们有大量的节点与其祖先的差值，其中一些如下：
//|8 - 3| = 5
//|3 - 7| = 4
//|8 - 1| = 7
//|10 - 13| = 3
//在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,null,2,null,0,3]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在 2 到 5000 之间。 
// 0 <= Node.val <= 10⁵ 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 203 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution1026 {
    /* 节点与其祖先之间的最大差值: https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor/
     * 相似题:
     *   236. 二叉树的最近公共祖先: https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
     *
     * 思路: 首先如何体现父子关系
     *   一条 DFS 链路天然具备父子关系, 我们从根节点到叶子节点的过程中，不断维护它的最大值和最小值 (参数透传下去)
     *   最终在叶子节点计算这条父子链的最大差值，然后当前链路到左右子树父子链的最大差值汇总 (后续遍历)
     */
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        return maxAncestorDiff(root, root.val, root.val);
    }
    private int maxAncestorDiff(TreeNode root, int max, int min) {
        if (root == null)
            return Math.abs(max - min);
        max = Math.max(root.val, max);
        min = Math.min(root.val, min);

        int left = maxAncestorDiff(root.left, max, min);
        int right = maxAncestorDiff(root.right, max, min);
        return Math.max(left, right);
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
