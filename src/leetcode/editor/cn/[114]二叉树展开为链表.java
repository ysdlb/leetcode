//给你二叉树的根结点 root ，请你将它展开为一个单链表： 
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 940 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution114 {
    public void flatten(TreeNode root) {
        flat(root);
    }

    /**
     *
     * @param root
     * @return
     * 返回 root 为根节点的子树下拉成链表的最后一个元素
     * <ol>
     *     <li>返回值为 null, 其子树为空</li>
     *     <li>该子树拉成链表后的最后一个元素</li>
     * </ol>
     */
    private TreeNode flat(TreeNode root) {
        if (root == null ||
                (root.left == null && root.right == null)) return root;


        TreeNode mid = flat(root.left);
        TreeNode end = flat(root.right);

        // 如果左子树为 null, 则无需对根节点进行设置
        if (mid != null) {
            mid.right = root.right;
            mid.left = null;

            root.right = root.left;
            root.left = null;
        }

        return end == null ? mid : end;
    }

    /**
     * <ol>
     *     <li>将 root 节点的左右子树拉平 </li>
     *     <li>将右子树接到左子树下方, 然后左子树接到 root 节点右孩子上</li>
     * </ol>
     * @param root
     */
    public void flattenV2(TreeNode root) {
        if (root == null) return;

        TreeNode l = root.left;
        TreeNode r = root.right;

        flattenV2(l);
        flattenV2(r);

        // 将左子树接到 root 节点右孩子上
        root.right = l;
        root.left = null;

        // 将右子树接到左子树下方, 考虑原来左子树的为 null 的情况, 从 root 开始遍历
        TreeNode p = root;
        while (p.right != null)
            p = p.right;
        p.right = r;
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
