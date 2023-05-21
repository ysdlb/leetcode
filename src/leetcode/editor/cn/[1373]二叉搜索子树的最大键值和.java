//给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。 
//
// 二叉搜索树的定义如下： 
//
// 
// 任意节点的左子树中的键值都 小于 此节点的键值。 
// 任意节点的右子树中的键值都 大于 此节点的键值。 
// 任意节点的左子树和右子树都是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
//输出：20
//解释：键值为 3 的子树是和最大的二叉搜索树。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [4,3,null,1,2]
//输出：2
//解释：键值为 2 的单节点子树是和最大的二叉搜索树。
// 
//
// 示例 3： 
//
// 
//输入：root = [-4,-2,-5]
//输出：0
//解释：所有节点键值都为负数，和最大的二叉搜索树为空。
// 
//
// 示例 4： 
//
// 
//输入：root = [2,1,3]
//输出：6
// 
//
// 示例 5： 
//
// 
//输入：root = [5,4,8,3,null,6,3]
//输出：7
// 
//
// 
//
// 提示： 
//
// 
// 每棵树有 1 到 40000 个节点。 
// 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 动态规划 二叉树 👍 90 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
class Solution1373 {
    /* 1373.二叉搜索子树的最大键值和: https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/
     * 判断 BST 相似题目:
     *  98.验证二叉搜索树: https://leetcode.cn/problems/validate-binary-search-tree/
     *
     * BST 中序遍历:
     *  700.二叉搜索树中的搜索: https://leetcode.cn/problems/search-in-a-binary-search-tree/
     *  530.二叉搜索树的最小绝对差: https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
     *  501.二叉搜索树中的众数: https://leetcode.cn/problems/find-mode-in-binary-search-tree/
     *  230.二叉搜索树中第K小的元素: https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
     *
     * 后序遍历求二叉树的是否为 BST, 最大值, 最小值（判断是不是二叉搜索树）和二叉树的和
     * 记录期间的最大值
     * [是否为BST, minV, maxV, sum]
     * 判断是否为 BST, 中序遍历更简单, 但后续遍历可以顺便计算子树的和
     */
    public int maxSumBST(TreeNode root) {
        postorder(root);
        return maxSum;
    }

    private static final int NULL_MIN = Integer.MAX_VALUE;
    private static final int NULL_MAX = Integer.MIN_VALUE;
    private static final int[] NOT_BST = new int[]{0};

    int maxSum = 0;

    /*
     * int[0] 是否二叉树, 0否; 1是
     * int[1] root 为根树的数的最大值
     * int[2] root 为根树的数的最小值
     * int[3] root 为根树的和
     */
    private int[] postorder(TreeNode root) {
        if (root == null)
            return new int[]{1, NULL_MIN, NULL_MAX, 0};

        int[] left = postorder(root.left);
        int[] right = postorder(root.right);

        if (left[0] == 0 || right[0] == 0)
            return NOT_BST;

        // 后续逻辑
        int maxL = left[2], minR = right[1];
        int val = root.val;
        if ((maxL == NULL_MAX || maxL < val) && (minR == NULL_MIN || minR > val)) {
            int minL = left[1], maxR = right[2];
            // 用来判断左右子树是否为 null
            int min = Math.min(minL, val);
            int max = Math.max(maxR, val);
            int sum = left[3] + right[3] + val;
            maxSum = Math.max(maxSum, sum);
            return new int[]{1, min, max, sum};
        } else {
            return NOT_BST;
        }
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
