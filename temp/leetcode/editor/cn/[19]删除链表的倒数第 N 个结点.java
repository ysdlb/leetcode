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
 * Definition for singly-linked list.
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
     * 双指针, 第 1 个指针先走 n 步 (此时第一个指针指向倒数第 m - n 个),
     * 第二个指针指向 head, 伴随第一个指针走到链表终点,
     * 第二个指针指向了 第 m-n 个, 即倒数 第 m - ( m - n) = n 个
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head, p2 = head, preP2 = null;

        for (int i = 0; p1 != null && i < n; i++) {
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            preP2 = p2;
            p2 = p2.next;
        }

        // 如果待删除的节点存在
        if (p2 != null) {
            if (preP2 == null)
                head = p2.next;
            else
                preP2.next = p2.next;
        }
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
