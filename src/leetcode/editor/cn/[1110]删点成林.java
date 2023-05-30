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
    /* 1110.删点成林: https://leetcode.cn/problems/delete-nodes-and-return-forest/
     * 相似题目(后续遍历, 前后双方向携带标记):
     *  1080.根到叶路径上的不足节点: https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths/
     *
     * 删除一个节点之后,
     *  其左右子树独立出来;
     *  其父节点与该节点的连接断开
     *
     * 换个角度:
     *  1. 若一个节点没有父节点, 且它不该被删除, 则将其视为一颗独立的树
     *  2. 若它就是该被删除, 不管怎样, 都把这颗树删除 ( 这颗树已经在条件 1 的作用下, 被肢解过了 )
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (to_delete == null) return Collections.singletonList(root);

        Set<Integer> set = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, true, set, ans);
        return ans;
    }

    /**
     * @param root 一颗树
     * @param noParent 是否有父节点
     * @param toDelete 带删除的集合
     * @param ans 森林结果
     * @return 若这颗树需要删除, 返回 null; 否则返回 root
     * 特殊的策略: 如果当前节点不该被删除, 但它已经没有了父节点, 那它应该放进 ans 中
     * 如果当前节点应该被删除, 返回 null, 不论有无父节点都会被删除
     */
    private TreeNode dfs(TreeNode root, boolean noParent, Set<Integer> toDelete, List<TreeNode> ans) {
        if (root == null) return null;

        boolean isDelete = toDelete.contains(root.val);
        root.left = dfs(root.left, isDelete, toDelete, ans);
        root.right = dfs(root.right, isDelete, toDelete, ans);
        if (isDelete) return null;

        if (noParent) ans.add(root);
        return root;
    }


    // 2022-06-17
    public List<TreeNode> delNodes_old(TreeNode root, int[] to_delete) {
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
