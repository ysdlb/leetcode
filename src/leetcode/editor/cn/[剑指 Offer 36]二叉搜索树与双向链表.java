//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。 
//
// 
//
// 为了让您更好地理解问题，以下面的二叉搜索树为例： 
//
// 
//
// 
//
// 
//
// 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是
//第一个节点。 
//
// 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。 
//
// 
//
// 
//
// 
//
// 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。 
//
// 
//
// 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-
//to-sorted-doubly-linked-list/ 
//
// 注意：此题对比原题有改动。 
// Related Topics 栈 树 深度优先搜索 二叉搜索树 链表 二叉树 双向链表 👍 390 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
*/
class SolutionOffer36 {
    // 利用中序遍历的性质找到头节点和尾节点
    /**
     * 返回二叉搜索树转换为链表后的头节点
     * 因为头节点要连接尾节点, 所以我们要找到头尾节点
     * 这其实是后续遍历，这个方法是错的
     */
    Node tail;
    public Node treeToDoublyList(Node root) {
        Node head = dfs(root);
        if (head != null && tail != null) {
            head.left = tail;
            tail.right = head;
        }
        return head;
    }

    /**
     * 这个做法是错的, 最后一次 tail 赋值落到第二层的右节点上
     * @param root
     * @return
     */
    public Node dfs(Node root) {
        if (root == null || (root.left == null && root.right == null))
            return root;

        Node left = dfs(root.left);
        if (left != null) {
            left.right = root;
            root.left = left;
        }

        Node right = dfs(root.right);
        if (right != null) {
            root.right = right;
            right.left = root;
            tail = right;
        }
        return left != null ? left : root;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}

class SolutionOffer36_2 {
    // 利用中序遍历的性质找到头节点和尾节点
    /**
     * 返回二叉搜索树转换为链表后的头节点
     * 因为头节点要连接尾节点, 所以我们要找到头尾节点
     */
    Node pre, head;

    public Node treeToDoublyList(Node root) {
        dfs(root);
        if (head != null && pre != null) {
            head.left = pre;
            pre.right = head;
        }
        return head;
    }

    public void dfs(Node root) {
        if (root == null) return;

        dfs(root.left);

        if (pre != null) {
            pre.right = root;
            root.left = pre;
        } else {  // 如果没有 pre, 说明它是头节点
            head = root;
        }
        // 中序遍历 pre。第一次赋值时候是 head，中序遍历完成（最后一次赋值）是 tail
        pre = root;
        dfs(root.right);
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
