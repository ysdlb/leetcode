//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 👍 1601 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Stack;

/**
 * <a href="https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/">leetcode-19</a>
 */
class Solution19 {
    /**
     * 先寻找倒数第 n 个节点
     * 全部入栈, 然后出栈第 n 个就是符合条件的节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }

        ListNode remove = null;
        for (int i = 0; i < n && !stack.isEmpty(); i++)
            remove = stack.pop();
        // 没有倒数第 n 个, 返回头节点
        if (remove == null)
            return head;

        ListNode preRe = stack.isEmpty() ? null : stack.pop();
        // 倒数第 n 是头节点
        if (preRe == null)
            return remove.next;
        else
            preRe.next = remove.next;

        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class DoublePoint19 {
    /**
     * 先寻找倒数第 n 个节点 (假设链表有 m 个元素)
     * 双指针:
     * p1 先走 n 步 (此时 p1 指向正数第 n+1 倒数第 m-n),
     * p2 指向 head, 伴随 p1 走到链表终点, 走到终点需要 m-n-1 步, 走到 null 需要多一步, m-n,
     *
     * p1 只需要走到终点就可以, 走 m-n-1 步; p2 指向了 第 m-n 个, 倒数 第m - ( m-n-1) = n+1个
     *
     * p2.next 就是应该被删除的节点, p2.next = p2.next.next 删除就好了
     *
     * 改一改更优雅
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head, p2 = head;
        // p1 先走 n 步
        for (int i = 0; i < n; i++) {
            p1 = p1.next;
            // 如果不设置虚拟头节点, 要加这个判断
            // 如果 p1 走了 n 步是 null, 说明一共就 n 个元素, 删除倒数第 n 个元素, 就是头节点
            if (p1 == null)
                return head.next;
        }

        // p1 走到终点
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        // 如果待删除的节点存在
        p2.next = p2.next.next;
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
