//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构) 
//
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。 
//
// 例如: 
//给定的树 A: 
//
// 3 
// / \ 
// 4 5 
// / \ 
// 1 2 
//给定的树 B： 
//
// 4 
// / 
// 1 
//返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。 
//
// 示例 1： 
//
// 输入：A = [1,2,3], B = [3,1]
//输出：false
// 
//
// 示例 2： 
//
// 输入：A = [3,4,5,1,2], B = [4,1]
//输出：true 
//
// 限制： 
//
// 0 <= 节点个数 <= 10000 
// Related Topics 树 深度优先搜索 二叉树 👍 442 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class SolutionOffer26 {

    /**
     * 找到 A B 的所有公共节点（遍历方式: 先序遍历)
     * 从公共节点开始, 判断 B 是否为 A 的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 最终应该都为 B == null, A 不为 null, B 为 null 时候题解为 false
        // if (B == null) return true;
        // B != null && A == null 时 B 一定不是 A 的子结构
        if (A == null || B == null) return false;

        return isSub(A, B)
                || isSubStructure(A.left, B)
                || isSubStructure(A.right, B);
    }

    /**
     * 判断 B 是否为 A 同根节点的子结构
     * 递归的终止条件 A == null 或者 B == null
     */
    private boolean isSub(TreeNode A, TreeNode B) {
        // 最终应该都为 B == null
        if (B == null) return true;
        // B != null && A == null 时 B 一定不是 A 的子结构
        if (A == null) return false;

        return A.val == B.val
                && isSub(A.left, B.left)
                && isSub(A.right, B.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
