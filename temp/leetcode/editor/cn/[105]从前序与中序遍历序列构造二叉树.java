//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均无重复元素 
// inorder 均出现在 preorder 
// preorder 保证为二叉树的前序遍历序列 
// inorder 保证为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1245 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return build(preorder, 0, inorder, 0, preorder.length);
    }

    /**
     * 描述: 通过前序遍历和后续遍历数组构建二叉树
     *
     * 前序遍历的第一个节点即为当前数组的跟节点, 通过中序遍历可以找出根节点的左右子树在两种遍历数组中的长度即位置
     * 继续两个数据分片构建二叉树
     */
    private TreeNode build(int[] preorder, int p_l, int[] inorder, int i_l, int length) {
        int r = preorder[p_l];
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
            root.left = build(preorder, p_l + 1, inorder, i_l, left_length);

        int right_length = i_h - r_index;
        if (right_length > 0)
            root.right = build(preorder, p_l + left_length + 1, inorder, r_index + 1, right_length);

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
