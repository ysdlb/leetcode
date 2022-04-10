//给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1339 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
class Solution94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        // inorderRecursion(root, ret);
        inorderIteration(root, ret);
        return ret;
    }

    /**
     * 中序遍历迭代实现
     * 递归实现中维护了一个隐式的入栈操作 - 总是左链入栈
     * 这里用 stack 将其模拟出来
     */
    private void inorderIteration(TreeNode root, List<Integer> ret) {
        if (root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        // root == null && stack.isEmpty() 的时候才遍历完
        // root != null 防止了重复入栈的问题
        while (root != null || !stack.isEmpty()) {
            // 左链入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 出栈, 处理 root 节点
            root = stack.pop();
            ret.add(root.val);

            // 遍历右子树
            root = root.right;
        }
    }

    /**
     * 中序遍历递归实现
     */
    private void inorderRecursion(TreeNode root, List<Integer> ret) {
        if (root == null) return;
        inorderRecursion(root.left, ret);
        ret.add(root.val);
        inorderRecursion(root.right, ret);
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
