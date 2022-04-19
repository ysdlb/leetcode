//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 10⁵] 内。 
// -10⁹ <= Node.val <= 10⁹ 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 深度优先搜索 二叉树 👍 1323 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution236 {
    /**
     * 递归定义, 非常优雅的递归解法
     * 函数描述: 给函数输入三个参数 root, p, q, 它会返回一个节点
     * @return
     * <ol>
     *     <li>如果 p 和 q 都在以 root 为根的树中, 函数返回的即为 p 和 q 的最近公共祖先节点</li>
     *     <li>如果 p 和 q 都不在以 root 为根的树中, 返回 null</li>
     *     <li>如果 p 和 q 只有一个节点在以 root 为根的树中, 返回那个节点</li>
     * </ol>
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 题目假设了整颗树中 p 和 q 必定出现且各自仅出现一次, 以下是我们返回值的选取规则
        // 1. 如果左右子树各自找到的 p 和 q (left right 都不为空), 因为是后续遍历, 所以第一次相交的地方就是最近公共祖先
        if (left != null && right != null)
            return root;

        // 2. 如果仅找到其中一个
        //    a. p, q 都在该子树中, 该返回值就是最近公共祖先
        //    b. p, q 只有一个在该子树中, 另一个在除该子树节点之外的其它子树中, 继续返回该返回值
        // 3. 如果一个没找到, 返回 null
        return left != null ? left : right;
    }
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
