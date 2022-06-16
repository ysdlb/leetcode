//给出二叉树的根节点 root，树上每个节点都有一个不同的值。 
//
// 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。 
//
// 返回森林中的每棵树。你可以按任意顺序组织答案。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
//输出：[[1,2,null,4],[6],[7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,4,null,3], to_delete = [3]
//输出：[[1,2,4]]
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数最大为 1000。 
// 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。 
// to_delete.length <= 1000 
// to_delete 包含一些从 1 到 1000、各不相同的值。 
// 
// Related Topics 树 深度优先搜索 二叉树 👍 181 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;
import java.util.stream.Collectors;

/**
 * Definition for a binary tree node.
 */
class Solution1110 {
    /**
     * 删除一个节点之后,
     *  其左右子树独立出来;
     *  其父节点与该节点的连接断开
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null) return null;
        if (to_delete == null) return Collections.singletonList(root);

        Set<Integer> set = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        List<TreeNode> ret = new ArrayList<>();
        // 根节点特殊处理
        if (!set.contains(root.val))
            ret.add(root);

        recurse(root, set, ret);
        return ret;
    }

    /**
     *  如果我们站在其父节点的角度, 每次考虑 root, left, right 三个节点
     *      如果 root 应该被删除: left 和 right 将独立, 加入返回列表里
     *      如果 left 应该被删除: 断开 root 和 left 的连接
     *      right 同 left
     *
     *  真 root 节点要在递归的外层额外处理
     *
     *  上述思路的问题
     *  root 被删除的时候, left 或 right 也可能被删除
     *      如果 root 应该被删除, left 不应该被删除: left 将独立, 加入返回列表里
     *      如果 root 不该被删除, left 应该被删除: 断开 root 和 left 的连接
     *      其它情况不处理
     *
     *      right 同 left
     */
    private void recurse(TreeNode root, Set<Integer> set, List<TreeNode> ret) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        boolean containsRoot = set.contains(root.val);
        if (left != null) {
            boolean containsLeft = set.contains(left.val);
            if (containsRoot && !containsLeft)
                ret.add(left);
            else if (!containsRoot && containsLeft)
                root.left = null;
        }
        if (right != null) {
            boolean containsRight = set.contains(right.val);
            if (containsRoot && !containsRight)
                ret.add(right);
            else if (!containsRoot && containsRight)
                root.right = null;
        }


        recurse(left, set, ret);
        recurse(right, set, ret);
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
