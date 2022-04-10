// 给定一颗二叉树和其中的一个节点, 如何找出中序遍历的下一个节点?
// 树中的节点除了有两个分别指向左、右子节点的指针, 还有一个指向父节点的指针

class SolutionOffer8 {

    /**
     * 中序遍历: 先左子树, 再 head, 在右子树
     * 1. 如果一个节点有右子树, 那么它的下一个节点就是它右子树中序遍历的第一个节点 (最左节点)
     * 2. 否则, 下一个节点和父节点有关
     *      a. 如果该节点是其父节点的左孩子, 那么下一个节点就是其父节点
     *      b. 否则 (只能是其父节点的左孩子或者没有父节点) 沿父系一直找到一个是左孩子的祖先节点
     */
    public TreeNode getNext(TreeNode pNode) {
        if (pNode == null)
            return null;

        TreeNode pNext = null;
        if (pNode.right != null) {
            TreeNode pRight = pNode.right;
            while (pRight.left != null)
                pRight = pRight.left;
            pNext = pRight;

        } else if (pNode.parent != null) {
            TreeNode pCurrent = pNode;
            TreeNode pParent = pNode.parent;
            while (pParent != null && pCurrent != pParent.left) {
                pCurrent = pParent;
                pParent = pParent.parent;
            }
            pNext = pParent;
        }
        return pNext;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
