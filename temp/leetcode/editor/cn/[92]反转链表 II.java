//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
// Related Topics 链表 👍 1047 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class DoublePoint92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        return null;
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

/**
 * 优雅递归
 */
class Recurse {
    // right + 1;
    ListNode successor = null;

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode curLeft = head, pre = null;
        for (int i = 1; i < left; i++) {
            pre  = curLeft;
            curLeft = curLeft.next;
        }
        ListNode reverse = this.recurse(curLeft, right - left + 1);
        if (pre != null)
            pre.next = reverse;
        else
            head = reverse;
        return head;
    }

    /**
     * 递归方法描述
     * 反转以 head 为起点的 n 个节点, 并返回反转后的头节点
     */
    private ListNode recurse(ListNode head, int n) {
        if (head == null || head.next == null) return head;
        if (n == 1) {
            successor = head.next;
            return head;
        }

        ListNode reverse = recurse(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return reverse;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
