//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
//根节点的引用。 
//
// 一般来说，删除节点可分为两个步骤： 
//
// 
// 首先找到需要删除的节点； 
// 如果找到了，删除它。 
// 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：root = [5,3,6,2,4,null,7], key = 3
//输出：[5,4,6,2,null,null,7]
//解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
//
// 
//
// 示例 2: 
//
// 
//输入: root = [5,3,6,2,4,null,7], key = 0
//输出: [5,3,6,2,4,null,7]
//解释: 二叉树不包含值为 0 的节点
// 
//
// 示例 3: 
//
// 
//输入: root = [], key = 0
//输出: [] 
//
// 
//
// 提示: 
//
// 
// 节点数的范围 [0, 10⁴]. 
// -10⁵ <= Node.val <= 10⁵ 
// 节点值唯一 
// root 是合法的二叉搜索树 
// -10⁵ <= key <= 10⁵ 
// 
//
// 
//
// 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。 
// Related Topics 树 二叉搜索树 二叉树 👍 552 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution450 {
    /**
     * 非常好的递归思想, 以及对二叉搜索树性质的总结
     * 1. 找到需要删除的节点 A
     * 2. 如果找到了, 删除 A
     *    a. 如果 A 无左子树, 则 A 的右子树替代其位置: A = A.right, 结束
     *    b. 如果 A 无右子树, 则 A 的左子树替代其位置: A = A.left, 结束
     *    c. 如果上述两种情况都不满足, 则 A 的右子树的最左节点是大于 A 的最小值, A 的左节点是小于 A 的最大值
     *      ① A 的左子树作为 A 右子树最左节点的左子树
     *      ② A = A.right
     *
     * 方法描述：
     *      在当前树中删除节点 key, 并返回删除后的根节点
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) // 小于, 去左子树删
            root.left = deleteNode(root.left, key);

        else if (key > root.val) // 大于, 去右子树删
            root.right = deleteNode(root.right, key);

        else { // 等于, 就是当前节点
            if (root.left == null) root = root.right;       // a
            else if (root.right == null) root = root.left;  // b
            else {                                          // c
                TreeNode rMaxLeft = root.right;
                while (rMaxLeft.left != null)
                    rMaxLeft = rMaxLeft.left;
                rMaxLeft.left = root.left;

                root = root.right;
            }
        }
        return root;
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
