//给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下： 
//
// 
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//} 
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。 
//
// 初始状态下，所有 next 指针都被设置为 NULL。 
//
// 
//
// 进阶： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
// 
//
// 
//
// 示例： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,6,7]
//输出：[1,#,2,3,#,4,5,6,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 
//next 指针连接，'#' 标志着每一层的结束。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数量少于 4096 
// -1000 <= node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 562 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution116 {
    /**
     * 递归, 有重复计算
     */
    public Node connect(Node root) {
        if (root == null) return null;
        connectTwoNodes(root.left, root.right);
        return root;
    }

    /**
     * 节点间存在非直系联系, 所以我们要跨一代来处理 next 指针
     */
    private void connectTwoNodes(Node l, Node r) {
        if (l == null) return;
        l.next = r;
        connectTwoNodes(l.left, l.right);
        connectTwoNodes(r.left, r.right);
        connectTwoNodes(l.right, r.left);
    }

    /**
     * 上述递归的循环实现, 一种 O(1) 空间复杂度的层序遍历
     *
     * 节点间存在非直系联系, 所以我们要跨一代来处理 next 指针
     * 当我们在第 N 层建立 next 指针的时候, 处于第 N-1 层.
     * 当第 N 层节点的 next 指针全部建立完成后, 移至第 N 层, 建立第 N+1 层节点的 next 指针
     */
    public Node connect_v2(Node root) {
        if (root == null) return null;

        Node specLevelNode = root;
        while (specLevelNode != null) {
            Node node = specLevelNode;
            while (node != null) {
                // 完美二叉树, right != null -> left != null
                if (node.right != null) {
                    node.left.next = node.right;

                    if (node.next != null) {
                        node.right.next = node.next.left;
                    }
                }

                node = node.next;
            }

            specLevelNode = specLevelNode.left;
        }

        return root;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
