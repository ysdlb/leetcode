//给定一棵二叉搜索树，请找出其中第 k 大的节点的值。 
//
// 
//
// 示例 1: 
//
// 
//输入: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//输出: 4 
//
// 示例 2: 
//
// 
//输入: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//输出: 4 
//
// 
//
// 限制： 
//
// 
// 1 ≤ k ≤ 二叉搜索树元素个数 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 246 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class SolutionOffer54 {
    private int count = 0;
    private int num = -1;
    public int kthLargest(TreeNode root, int k) {
        kth(root, k);
        return num;
    }

    private void kth(TreeNode root, int k) {
        if (root == null) return;
        if (count == k) return;

        kth(root.right, k);
        if (++count == k) {
            num = root.val;
        }
        kth(root.left, k);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
