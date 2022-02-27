//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉树中。 
// 
//
// 注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-
//a-binary-tree/ 
// Related Topics 树 深度优先搜索 二叉树 👍 379 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class SolutionOffer68_2 {
    /**
     * 后续遍历 ( 前提: 保证 p q 都在这颗🌲中 )
     * 结束条件: 如果碰到 p,q 中任意一个, 返回 该节点, 当遍历到叶子节点之下时, 返回 null
     * 后续处理条件:
     *   a. 当左右子树遍历完时, 它俩当返回值都不为空 ( 一定分别碰到了 p,q ), 返回 root 节点
     *   b. 当左右子树只有一个不为空时, 返回不为空当那个 ( 在找到公共祖先之前, 返回的是 p 或则 q; 找到公共祖先之后, 返回的是公共祖先; 当p,q有直系关系, 返回辈分最高当那个)
     *   c. 当左右子树都为空的时候, 返回空
     *
     *
     * 另一种方法:
     * 1. 后续遍历 找到 p->root 以及 q->root 的路径（先序遍历+回溯找 root->p 以及 root->q 也可以)
     * 2. 寻找双链表的第一个公共节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null)
            return root;

        return left == null ? right : left;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
