//输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。 
//
// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 
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
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 655 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class SolutionOffer7 {
    /**
     * 道理都懂, 问题是代码怎么写
     * 二叉树大部分跟递归有关系, 由一个模式的大问题分解成同模式的两个子问题
     *
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeRecurse(preorder, 0, inorder, 0, preorder.length);
    }

    /**
     * 返回由两个数组表示的二叉树根节点
     * 两数组长度相同
     */
    private TreeNode buildTreeRecurse(int[] preorder, int preL,
                               int[] inorder, int inL, int len) {
        if (len == 0) return null;

        int headVal = preorder[preL];
        TreeNode head = new TreeNode(headVal);

        // 明确 head 节点在中序遍历列表中的位置, 计算左右子树的长度
        int headIndex = inL;
        for (; headIndex < inL + len; headIndex++) {
            if (inorder[headIndex] == headVal)
                break;
        }
        int leftLen = headIndex - inL;
        int rightLen = len - leftLen - 1;

        // 开始位置: preorder 跨过头节点; inorder 不变
        head.left = buildTreeRecurse(preorder, preL + 1, inorder, inL, leftLen);
        // 开始位置: preorder 跨过头节点, 再跨过左子树; inorder 跨过左子树, 再跨过头节点
        head.right = buildTreeRecurse(preorder, preL + 1 + leftLen, inorder, inL + leftLen + 1, rightLen);

        return head;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

class SolutionOffer7_Leaf {
    /**
     * 道理都懂, 问题是代码怎么写
     * 二叉树大部分跟递归有关系, 由一个模式的大问题分解成同模式的两个子问题
     *
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeRecurse(preorder, 0, inorder, 0, preorder.length);
    }

    /**
     * 返回由两个数组表示的二叉树根节点
     * 两数组长度相同
     */
    private TreeNode buildTreeRecurse(int[] preorder, int preL,
                                      int[] inorder, int inL, int len) {
        if (len == 0) return null;

        int headVal = preorder[preL];
        TreeNode head = new TreeNode(headVal);
        if (len == 1) return head;

        // 明确 head 节点在中序遍历列表中的位置, 计算左右子树的长度
        int headIndex = inL;
        for (; headIndex < inL + len; headIndex++) {
            if (inorder[headIndex] == headVal)
                break;
        }
        int leftLen = headIndex - inL;
        int rightLen = len - leftLen - 1;

        // 开始位置: preorder 跨过头节点; inorder 不变
        if (leftLen > 0)
            head.left = buildTreeRecurse(preorder, preL + 1, inorder, inL, leftLen);
        // 开始位置: preorder 跨过头节点, 再跨过左子树; inorder 跨过左子树, 再跨过头节点
        if (rightLen > 0)
            head.right = buildTreeRecurse(preorder, preL + 1 + leftLen, inorder, inL + leftLen + 1, rightLen);

        return head;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
