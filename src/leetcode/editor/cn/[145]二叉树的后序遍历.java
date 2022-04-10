//给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[3,2,1]
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
// 树中节点的数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 👍 793 👎 0


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
class Solution145 {
    /**
     * 前序遍历 144
     * 中序遍历 94
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        // postorderRecursion(root, ret);
        // postorderIterationTrick(root, ret);
        postorderIteration(root, ret);
        return ret;
    }

    /**
     * 后续遍历迭代实现小技巧, 用栈模拟后续递归遍历太难了, 但模拟前序递归遍历很简单
     *
     * 后序遍历和前序遍历的关系:
     * 后续: 左、右、中; 前序: 中、左、右; 稍微调整下前序: 中、右、左
     * 恰好是后序的倒序
     */
    private void postorderIterationTrick(TreeNode root, List<Integer> ret) {
        if (root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> reverseRet = new ArrayDeque<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            reverseRet.push(cur.val);
            // 要保证中、右、左的顺序就要先放左节点
            if (cur.left != null)
                stack.push(cur.left);
            if (cur.right != null)
                stack.push(cur.right);
        }
        ret.addAll(reverseRet);
    }


    /**
     * 后续遍历迭代实现
     * 模拟递归的栈帧调用过程, 先左链入栈, 出栈后右节点入栈 ( 注意这里不是右链入栈 )
     *
     * 问题: 如何保证节点不重复入栈
     *
     * 后序只是在中序的基础上多了一些操作
     * 与中序的不同在于:
     * 中序遍历中, 从栈中弹出的节点, 其左子树是访问完了, 可以直接访问该节点, 然后接下来右子树
     * 后序遍历中, 从栈中弹出的节点, 我们只能确定其左子树是访问完了, 但是无法右子树是否访问过.
     *
     * 因此在后序遍历中, 需要引入一个 prev 来记录历史访问的记录 (后序遍历中, 任意一个节点一定紧接着其右孩子被访问)
     * 1. 当访问完一颗子树的时候, 我们用 prev 指向该节点.
     * 2. 这样, 在回溯到父节点的时候, 我们可以根据 prev 是指向左孩子还是右孩子, 来判断父节点的访问情况
     */
    private void postorderIteration(TreeNode root, List<Integer> ret) {
        if (root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.peek();
            // 根据 prev 判断右子树是否访问过了
            if (root.right == null || root.right == prev) {
                // 如果右子树没有或者访问过了
                // 那么将该节点出栈, 并处理这个元素
                prev = stack.pop();  // prev = root;
                ret.add(prev.val);
                // root 设置为 null, 避免重复入栈
                root = null;
            } else {
                // 如果右子树没有被访问过
                // 该节点不出栈, 继续访问其右子树
                root = root.right;
            }
        }
    }


    /**
     * 后序遍历递归实现
     */
    private void postorderRecursion(TreeNode root, List<Integer> ret) {
        if (root == null) return;
        postorderRecursion(root.left, ret);
        postorderRecursion(root.right, ret);
        ret.add(root.val);
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
