//给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。 
//
// 如果树中有不止一个众数，可以按 任意顺序 返回。 
//
// 假定 BST 满足如下定义： 
//
// 
// 结点左子树中所含节点的值 小于等于 当前节点的值 
// 结点右子树中所含节点的值 大于等于 当前节点的值 
// 左子树和右子树都是二叉搜索树 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,2]
//输出：[2]
// 
//
// 示例 2： 
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
// 树中节点的数目在范围 [1, 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// 
//
// 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内） 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 445 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

/**
 */
class Solution501 {
    /**
     * 二叉搜索树的中序遍历是有序的, 这题等价于求有序数组中的众数
     *
     * 众数数求出现频率最高的几个元素, 如果用 O(1) 的时间完成这个目的, 这是一个难点
     * 1. maxTimes 记录当前保存的众数的频率
     * 2. curTimes 记录当前遍历到的数子的出现频率,
     *      若 < maxTimes 不做处理;
     *      若 = maxTimes 加入结果集;
     *      若 > maxTimes 清空结果集, 然后加入结果集
     */
    public int[] findMode(TreeNode root) {
        ret = new ArrayList<>();
        findModeRecursion(root);
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    int maxTimes = 0;
    List<Integer> ret;
    int count = 1;
    // 初始值是一个树中不存在的数字
    int pre = Integer.MIN_VALUE;

    private void findModeRecursion(TreeNode root) {
        if (root == null) return;

        findModeRecursion(root.left);
        if (pre == root.val)
            ++count;
        else
            count = 1;

        if (count == maxTimes) {
            ret.add(root.val);
        } else if (count > maxTimes) {
            ret.clear();
            ret.add(root.val);
            maxTimes = count;
        }
        pre = root.val;

        findModeRecursion(root.right);
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
