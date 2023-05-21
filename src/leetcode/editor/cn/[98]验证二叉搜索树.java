//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。 
//
// 有效 二叉搜索树定义如下： 
//
// 
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,1,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在[1, 10⁴] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1242 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution98 {
    /* 98.验证二叉搜索树: https://leetcode.cn/problems/validate-binary-search-tree/
     * 后续遍历相似题目:
     *  1373.二叉搜索子树的最大键值和: https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/
     *
     * BST 中序遍历:
     *  700.二叉搜索树中的搜索: https://leetcode.cn/problems/search-in-a-binary-search-tree/
     *  530.二叉搜索树的最小绝对差: https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
     *  501.二叉搜索树中的众数: https://leetcode.cn/problems/find-mode-in-binary-search-tree/
     *  230.二叉搜索树中第K小的元素: https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
     *
     * 这个题解后续遍历做法
     * 下面还有一个类用的中序遍历做法
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        return isValidBST(root.left, Long.MIN_VALUE, root.val)
                && isValidBST(root.right, root.val, Long.MAX_VALUE);
    }

    /*
     * 方法描述
     *     1. 判断一棵树是否为二叉搜索树
     *     2. 判断这棵树的值是否在 min 和 max 之内
     * 对于一个根节点, 其值一定是其左子树的最大值, 右子树的最小值
     * 左子树的范围 (-infinite, root.val)
     * 右子树的范围 (root.val, infinite)
     *
     * 递归
     * 往左子树走更新最大值
     * 往右子树走, 更新最小值
     *
     * [-2147483648,null,2147483647]
     * 为应对这种情况, 所以 min 和 max 设置为 null
     * @return
     */
    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) return true;

        // 节点的左子树只包含 小于 当前节点的数。
        // 节点的右子树只包含 大于 当前节点的数。
        // 所有左子树和右子树自身必须也是二叉搜索树
        if (root.val <= min || root.val >= max)
            return false;

        return isValidBST(root.left, min, root.val) &&
                isValidBST(root.right, root.val, max);
    }

    /*
     * 也可以用 null 来表示无穷小 和 无穷大
     * @return
     */
    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;

        // 节点的左子树只包含 小于 当前节点的数。
        // 节点的右子树只包含 大于 当前节点的数。
        // 所有左子树和右子树自身必须也是二叉搜索树
        if (min != null && root.val <= min.val ||
                max != null && root.val >= max.val)
            return false;

        return isValidBST(root.left, min, root) &&
                isValidBST(root.right, root, max);
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

class InorderTraversal98 {

    /*
     * 利用二叉搜索树 中序遍历 有序的性质来完成判断
     *
     * 即不断用中序遍历顺序的前一个节点与当前节点比对, 如果是 大于 关系, 则可判断不是二叉搜索树
     *
     */
    private TreeNode pre;
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        if (!isValidBST(root.left))
            return false;

        if (pre != null && pre.val > root.val)
            return false;
        pre = root;

        return isValidBST(root.right);
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
