//请实现两个函数，分别用来序列化和反序列化二叉树。 
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。 
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。 
//
// 
//
// 示例： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-
//binary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 262 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.Deque;
/**
 * <a href="https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/">leetcode</a>
 * <p>
 * <a href="https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/submissions/">leetcode 297</a>
 * <p>
 * 1. 先序遍历加后续遍历的形式
 *      a. 二叉树中不能有重复的节点
 *      b.  只有当两个序列中所有的数据都读出后才能开始反序列化。
 * <p>
 * 2. 构建满二叉树, 不足的用特殊字符补齐
 *      链表形式的二叉树浪费空间多
 * <p>
 * 3. 对所有 nullptr 做特殊处理
 *      普通形式二叉树浪费空间多
 */
class CodecOffer37 {

    private final static String SEP = ",";
    private final static String NULL = "#";

    // Encodes a tree to a single string.

    /**
     * 循环做先序遍历要利用栈
     * 而且要先入右孩子，再入左孩子
     */
    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder builder = new StringBuilder();
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode NPE = new TreeNode(-1);
        queue.offerFirst(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            builder.append(node == NPE ? NULL : String.valueOf(node.val));
            builder.append(SEP);

            if (node == NPE) continue;
            if (node.right == null) queue.offerFirst(NPE);
            else queue.offerFirst(node.right);

            if (node.left == null) queue.offerFirst(NPE);
            else queue.offerFirst(node.left);
        }
        return builder.toString();
    }


    private int start = 0;
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        return deserialize(data.split(SEP));
    }

    /**
     * 先序遍历
     */
    public TreeNode deserialize(String[] data) {
        // if (start >= data.length) return null;
        String str = data[start++];
        // 递归迭代对终点总为空, 所以上面对 start 对判断大可不必
        if (NULL.equals(str)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = deserialize(data);
        node.right = deserialize(data);
        return node;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
