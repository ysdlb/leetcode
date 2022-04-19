//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 624 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * Definition for a binary tree node.
 */
class Solution103 {
    /**
     * 同题 剑指Offer32-3
     * BFS 的 while for size 实现
     * 类似题参考 103, 107
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);

        boolean isReverse = true;

        while (!deque.isEmpty()) {
            int size = deque.size();
            LinkedList<Integer> item = new LinkedList<>();
            isReverse = !isReverse;

            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();

                if (isReverse)
                    item.addFirst(node.val);
                else
                    item.addLast(node.val);

                Optional.ofNullable(node.left)
                        .ifPresent(deque::addLast);
                Optional.ofNullable(node.right)
                        .ifPresent(deque::addLast);
            }
            ret.add(item);
        }
        return ret;
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
