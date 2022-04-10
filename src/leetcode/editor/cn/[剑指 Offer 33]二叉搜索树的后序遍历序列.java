//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。 
//
// 
//
// 参考以下这颗二叉搜索树： 
//
//      5
//    / \
//   2   6
//  / \
// 1   3 
//
// 示例 1： 
//
// 输入: [1,6,3,2,5]
//输出: false 
//
// 示例 2： 
//
// 输入: [1,3,2,6,5]
//输出: true 
//
// 
//
// 提示： 
//
// 
// 数组长度 <= 1000 
// 
// Related Topics 栈 树 二叉搜索树 递归 二叉树 单调栈 👍 417 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer33 {
    /**
     * 对于二叉搜索树对任意后续遍历序列, 其最后一个节点一定是根节点
     * 然后前面节点可以拆分为左右两个连续队列, 左边队列全部比根节点小, 右边节点全部比根节点大
     * 这两个连续队列又分别是两颗二叉搜索子树对后续遍历序列
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    public boolean verifyPostorder(int[] postorder, int left, int right) {
        if (right <= left)
            return true;

        int root = postorder[right];
        int mid = left;

        // 找到第一个不比 root 小的数
        while (postorder[mid] < root) {
            mid++;
        }
        // 验证后面都比 root 大
        for (int i = mid; i < right; i++) {
            if (postorder[i] < root)
                return false;
        }

        return verifyPostorder(postorder, left, mid - 1)
                && verifyPostorder(postorder, mid, right - 1);
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1,6,3,2,5};
        boolean b = new SolutionOffer33().verifyPostorder(ints);
        System.out.println(b);
    }
}

class SolutionOffer33_Stack {
    /**
     * 后续遍历: 左子树、右子树、根节点
     * 后续遍历反向遍历: 根节点、右子树、左子树
     * 阶段内递增且 阶段下降
     * 每个阶段的起步（最小的那个元素是根节点, 记为 root, 下一个单增节点开始是这个根节点的左孩子
     */
    public boolean verifyPostorder(int[] postorder) {
        // 一个栈
        int[] stack = new int[postorder.length];
        int k = -1;

        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            // 遍历的序列总是为 root 的左子树 (任意序列都是 Integer.MAX_VALUE节点 的左子树)
            // 换句话说, root 总是比当前元素大的且最相邻的祖先节点
            // 所以一旦存在元素比 root 大, 则一定不合法
            if (root < postorder[i])
                return false;
            // 反向后续遍历时, 每个根节点、右孩子的遍历都是单调递增的,
            // 如果当前元素比栈顶元素小, 则表明进入了新的左孩子, 下一步找到这个子树的 root 节点
            // root 节点: 栈中最后一个元素或者比当前元素大的最小的一个元素
            while (k >= 0 && postorder[i] < stack[k])
                root = stack[k--];
            stack[++k] = postorder[i];
        }
        return true;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 3, 2, 6, 5};
        boolean b = new SolutionOffer33_Stack().verifyPostorder(ints);
        System.out.println(b);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
