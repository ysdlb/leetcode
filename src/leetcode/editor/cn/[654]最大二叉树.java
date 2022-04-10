//给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下： 
//
// 
// 二叉树的根是数组 nums 中的最大元素。 
// 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。 
// 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。 
// 
//
// 返回有给定数组 nums 构建的 最大二叉树 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,2,1,6,0,5]
//输出：[6,3,5,null,2,0,null,null,1]
//解释：递归调用如下所示：
//- [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
//    - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
//        - 空数组，无子节点。
//        - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
//            - 空数组，无子节点。
//            - 只有一个元素，所以子节点是一个值为 1 的节点。
//    - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
//        - 只有一个元素，所以子节点是一个值为 0 的节点。
//        - 空数组，无子节点。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[3,null,2,null,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 1000 
// nums 中的所有整数 互不相同 
// 
// Related Topics 栈 树 数组 分治 二叉树 单调栈 👍 330 👎 0


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
class Solution654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) return null;
        return constructBinaryTree(nums, 0, nums.length - 1);
    }

    /**
     * 函数描述: 返回 [l, h] 数组内的 "最大二叉树
     *
     * 情况描述
     * <ol>
     *     <li>l == h, 以 l 构建 node 返回</li>
     *     <li>
     *         l < h, 以 [l, h] 内最大的数构建节点, 如果该数两边还有值的话, 继续构建左右子节点
     *         最后返回最大数构建的节点
     *     </li>
     * </ol>
     */
    private TreeNode constructBinaryTree(int[] nums, int l, int h) {
        if (l == h) return new TreeNode(nums[l]);

        int mid = maxIndex(nums, l, h);
        TreeNode root = new TreeNode(nums[mid]);
        if (l < mid) {
            root.left = constructBinaryTree(nums, l, mid - 1);
        }
        if (h > mid) {
            root.right = constructBinaryTree(nums, mid + 1, h);
        }
        return root;
    }

    private int maxIndex(int[] nums, int l, int h) {
        int index = l, max = nums[index];
        for (int i = l + 1; i <= h; i++) {
            if (nums[i] > index) {
                index = i;
                max = nums[index];
            }
        }
        return index;
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
