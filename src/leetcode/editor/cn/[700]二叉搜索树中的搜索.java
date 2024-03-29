//给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。 
//
// 例如， 
//
// 
//给定二叉搜索树:
//
//        4
//       / \
//      2   7
//     / \
//    1   3
//
//和值: 2
// 
//
// 你应该返回如下子树: 
//
// 
//      2     
//     / \   
//    1   3
// 
//
// 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。 
// Related Topics 树 二叉搜索树 二叉树 👍 155 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution700 {
    /*
     * 二叉搜索树的性质
     * 基础题，类似简单题：98
     *  98.验证二叉搜索树: https://leetcode.cn/problems/validate-binary-search-tree/
     *  1373.二叉搜索子树的最大键值和: https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;

        if (val == root.val)
            return root;

        if (val < root.val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);
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
