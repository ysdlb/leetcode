//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 173 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 */
class SolutionOffer32THREE {
    /**
     * while + for 循环
     * 替代队列, 正取反取
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        boolean order = true;
        while (!deque.isEmpty()) {
            List<Integer> levelNums = new ArrayList<>();
            Deque<TreeNode> dequeSubstitute = new LinkedList<>();

            for (TreeNode node: deque) {
                if (node.left != null)
                    dequeSubstitute.offerLast(node.left);
                if (node.right != null)
                    dequeSubstitute.offerLast(node.right);
            }

            for (int i = deque.size(); i > 0; i--) {
                TreeNode node = order ? deque.pollFirst() : deque.pollLast();
                levelNums.add(node.val);
            }

            order = !order;
            deque = dequeSubstitute;
            ret.add(levelNums);
        }
        return ret;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
