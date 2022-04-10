//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。 
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。 
//
// 示例 1： 
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
// 
//
// 下面是两个重复的子树： 
//
//       2
//     /
//    4
// 
//
// 和 
//
//     4
// 
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 318 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
class Solution652 {
    // 代表空节点
    private static final String NULL = "#";
    // 序列化结果的分隔符
    private static final String SEP = ",";
    // 记录这样的树出现了几次
    private final Map<String, Integer> map = new HashMap<>();
    // 记录树的跟节点
    private final Map<String, TreeNode> mapNode = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // 制作两个 map
        serialize(root);

        List<String> dup = new ArrayList<>();
        map.forEach((tree, num) -> {
            if (num > 1)
                dup.add(tree);
        });

        List<TreeNode> res = new ArrayList<>();
        dup.forEach(tree -> res.add(mapNode.get(tree)));
        return res;
    }

    /**
     * 序列化一棵树
     */
    private String serialize(TreeNode root) {
        if (root == null) return NULL;

        String res = new StringBuilder()
                .append(root.val)
                .append(SEP).append(serialize(root.left))
                .append(SEP).append(serialize(root.right))
                .toString();
        map.compute(res, (key, oldV) -> oldV == null ? 0 : oldV + 1);
        mapNode.put(res, root);
        return res;
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
