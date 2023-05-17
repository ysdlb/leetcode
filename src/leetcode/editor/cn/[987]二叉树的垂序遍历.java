//给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。 
//
// 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的
//根结点位于 (0, 0) 。 
//
// 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则
//按结点的值从小到大进行排序。 
//
// 返回二叉树的 垂序遍历 序列。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[9],[3,15],[20],[7]]
//解释：
//列 -1 ：只有结点 9 在此列中。
//列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
//列  1 ：只有结点 20 在此列中。
//列  2 ：只有结点 7 在此列中。 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,3,4,5,6,7]
//输出：[[4],[2],[1,5,6],[3],[7]]
//解释：
//列 -2 ：只有结点 4 在此列中。
//列 -1 ：只有结点 2 在此列中。
//列  0 ：结点 1 、5 和 6 都在此列中。
//          1 在上面，所以它出现在前面。
//          5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
//列  1 ：只有结点 3 在此列中。
//列  2 ：只有结点 7 在此列中。
// 
//
// 示例 3： 
// 
// 
//输入：root = [1,2,3,4,6,5,7]
//输出：[[4],[2],[1,5,6],[3],[7]]
//解释：
//这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
//因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。 
//
// 
//
// 提示： 
//
// 
// 树中结点数目总数在范围 [1, 1000] 内 
// 0 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 哈希表 二叉树 👍 241 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Definition for a binary tree node.
 */
class Solution987 {

    /* 987.二叉树的垂序遍历: https://leetcode.cn/problems/vertical-order-traversal-of-a-binary-tree/
     *
     * 一个不太光彩的思路:
     *  垂序遍历总是一列一列输出
     *  按先序遍历的顺序给每个节点打上列位置和深度的标记, 然后按 <列值, 深度> 排序
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // 标记
        List<int[]> tagList = new ArrayList<>();
        this.tagTree(root, tagList, 0, 0);

        // 按 <列值, 深度> 小到大排序
        Comparator<int[]> comparator = Comparator.<int[]>comparingInt(e -> e[0])
                .thenComparingInt(e -> e[1])
                .thenComparing(e -> e[2]);
        tagList.sort(comparator);

        Integer preCol = null;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> colAns = null;
        for (int[] tag: tagList) {
            if (preCol == null || preCol != tag[0]) {
                if (preCol != null)
                    ans.add(colAns);
                colAns = new ArrayList<>();
            }
            colAns.add(tag[2]);
            preCol = tag[0];
        }
        if (colAns != null) ans.add(colAns);
        return ans;
    }

    private void tagTree(TreeNode root, List<int[]> tagList, int col, int deep) {
        if (root == null) return;
        int[] tag = new int[]{col, deep, root.val};
        tagList.add(tag);

        tagTree(root.left, tagList, col-1, deep+1);
        tagTree(root.right, tagList, col+1, deep+1);
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

    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node3 = new TreeNode(3, node6, node7);
        TreeNode node1 = new TreeNode(1, node2, node3);

        Solution987 so = new Solution987();
        List<List<Integer>> lists = so.verticalTraversal(node1);
        System.out.println(lists);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
