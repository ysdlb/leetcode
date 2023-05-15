//我们从二叉树的根节点 root 开始进行深度优先搜索。 
//
// 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。
//根节点的深度为 0）。 
//
// 如果节点只有一个子节点，那么保证该子节点为左子节点。 
//
// 给出遍历输出 S，还原树并返回其根节点 root。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入："1-2--3--4-5--6--7"
//输出：[1,2,5,3,4,6,7]
// 
//
// 示例 2： 
//
// 
//
// 输入："1-2--3---4-5--6---7"
//输出：[1,2,5,3,null,6,null,4,null,7]
// 
//
// 示例 3： 
//
// 
//
// 输入："1-401--349---90--88"
//输出：[1,401,null,349,88,90]
// 
//
// 
//
// 提示： 
//
// 
// 原始树中的节点数介于 1 和 1000 之间。 
// 每个节点的值介于 1 和 10 ^ 9 之间。 
// 
//
// Related Topics 树 深度优先搜索 字符串 二叉树 👍 240 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1028 {

    /* 1028.从先序遍历还原二叉树: https://leetcode.cn/problems/recover-a-tree-from-preorder-traversal/
     * 序列化相似题目:
     *  105.从前序与中序遍历序列构造二叉树: https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     *  106.从中序与后序遍历序列构造二叉树: https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     *  297.二叉树的序列化与反序列化: https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
     *
     * 几种反序列化的方法:
     *  1. 单纯的先序遍历无法还原二叉树, 需要配合一个中序或者后续遍历才可以
     *  2. 先序遍历变种，将叶子节点的孩子 NULL 也缓存
     *
     * 题目也是先序遍历变种，但额外携带了节点深度信息
     * 我们写一个携带深度的递归方法，root 的 deep <- 0; 定义从 index 开始第一个开始的数字为当前节点，
     * deep 表示调用栈的深度，可以回退；index则 一直往前推进
     *
     * 其前面的 '-' 的长度 len 表示深度
     * 若 len == deep: 用当前数字 cur 构建一个节点
     *   &index++, deep+1 去构建 cur 的左子树; 然后同样的逻辑同样的值继续构建 cur 的右子树
     * 若 len < deep: 返回 null
     */
    public TreeNode recoverFromPreorder(String traversal) {
        return recoverFromPreorder(traversal.toCharArray(), 0, new int[]{0});
    }

    /* 先序遍历，后续组装左右子树
     */
    public TreeNode recoverFromPreorder(char[] chars, int deep, int[] index) {
        int len = 0;
        while (index[0]+len < chars.length && chars[index[0]+len] == '-')
            len++;

        if (len < deep) {
            return null;
        }


        // len == deep
        index[0] += len;
        int numL = 0;
        while (index[0]+numL < chars.length && chars[index[0]+numL] != '-')
            numL++;
        // 空字符串
        if (numL == 0) return null;

        TreeNode node = new TreeNode();
        node.val = Integer.parseInt(new String(chars, index[0], numL));
        index[0] += numL;

        node.left = recoverFromPreorder(chars, deep+1, index);
        node.right = recoverFromPreorder(chars, deep+1, index);
        return node;
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
        Solution1028 so = new Solution1028();
        so.recoverFromPreorder("1-2--3--4-5--6--7");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
