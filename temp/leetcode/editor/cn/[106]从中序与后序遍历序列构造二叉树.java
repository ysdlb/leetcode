//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 590 👎 0


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
class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        return build(inorder, 0, postorder, 0, inorder.length);
    }

    /**
     * 描述: 通过中序遍历和后续遍历数组构建二叉树
     *
     * 后序遍历的最后一个节点即为当前数组的跟节点, 通过中序遍历可以找出根节点的左右子树在两种遍历数组中的长度即位置
     * 继续两个数据分片构建二叉树
     */
    private TreeNode build(int[] inorder, int i_l, int[] postorder, int p_l, int length) {
        // 后序遍历的最后一个节点为 root
        int r = postorder[p_l + length - 1];
        TreeNode root = new TreeNode(r);
        if (length == 1) return root;


        int i_h = i_l + length - 1;
        // 根节点值在中序遍历数组中的位置
        int r_index = i_l;
        for (int i = i_l + 1; i <= i_h; i++) {
            if (inorder[i] == r) r_index = i;
        }

        // 对左右子树的遍历数组重复上述结果
        int left_length = r_index - i_l;
        if (left_length > 0)
            root.left = build(inorder, i_l, postorder, p_l, left_length);

        int right_length = i_h - r_index;
        if (right_length > 0)
            root.right = build(inorder, r_index + 1, postorder, p_l + left_length, right_length);

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
