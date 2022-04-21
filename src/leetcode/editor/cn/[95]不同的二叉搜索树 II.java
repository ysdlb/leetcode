//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// 
// 
// Related Topics 树 二叉搜索树 动态规划 回溯 二叉树 👍 1002 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

class Solution95 {

    /**
     * 不同与 96 的解法
     * 利用二叉搜索树的性质
     */
    public List<TreeNode> generateTrees(int n) {
        NULL.add(null);
        return build(1, n);
    }
    private List<TreeNode> NULL = new ArrayList<>();

    private List<TreeNode> build(int min, int max) {
        if (min > max) return NULL;

        List<TreeNode> ret = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            List<TreeNode> left = build(min, i-1);
            List<TreeNode> right = build(i+1, max);
            for (TreeNode lo: left) {
                for (TreeNode hi: right) {
                    TreeNode node = new TreeNode(i);
                    node.left = lo;
                    node.right = hi;
                    ret.add(node);
                }
            }
        }
        return ret;
    }

    /**
     * 同 96 解法, 输出不同
     * 需要深拷贝, 不然只是输出来某些情况, 但不符合二叉搜索树的性质
     */
    public List<TreeNode> generateTrees_Error(int n) {
        List<List<TreeNode>> ret = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) ret.add(null);

        // ret[0] 为空
        List<TreeNode> zeroTrees = new ArrayList<>();
        zeroTrees.add(null);
        ret.set(0, zeroTrees);

        for (int i = 1; i <= n; i++) {
            List<TreeNode> item = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int left = j, right = i - j - 1;
                for (TreeNode lTree: ret.get(left)) {
                    for (TreeNode rTree: ret.get(right)) {
                        TreeNode node = new TreeNode(i);
                        node.left = lTree;
                        node.right = rTree;
                        item.add(node);
                    }
                }
            }
            ret.set(i, item);
        }

        return ret.get(n);
    }

    public static void main(String[] args) {
        Solution95 solution95 = new Solution95();
        solution95.generateTrees(3);
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
