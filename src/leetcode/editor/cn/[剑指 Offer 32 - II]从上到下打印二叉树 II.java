//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。 
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
//  [9,20],
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
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-
//traversal/ 
// Related Topics 树 广度优先搜索 二叉树 👍 174 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 */
class SolutionOffer32_TWO {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null) return ret;

        List<TreeNode> levelArray = new ArrayList<>();
        levelArray.add(root);
        while (!levelArray.isEmpty()) {
            levelArray = levelOrder(levelArray, ret);
        }
        return ret;
    }

    private List<TreeNode> levelOrder(List<TreeNode> levelArray, List<List<Integer>> out) {
        List<TreeNode> ret = new ArrayList<>();
        List<Integer> levelNum = new ArrayList<>();
        for (TreeNode node: levelArray) {
            levelNum.add(node.val);
            if (node.left != null) {
                ret.add(node.left);
            }
            if (node.right != null) {
                ret.add(node.right);
            }
        }
        if (levelNum.size() > 0) {
            out.add(levelNum);
        }
        return ret;
    }

    /**
     * while 内嵌 for 循环
     * for 循环记录初始队列长度, 一次打印一层
     */
    public List<List<Integer>> levelOrder_FOR(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null) return ret;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int i = deque.size(); i > 0; i--) {
                TreeNode node = deque.pollFirst();
                level.add(node.val);
                if (node.left != null)
                    deque.offerLast(node.left);
                if (node.right != null)
                    deque.offerLast(node.right);
            }
            ret.add(level);
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
