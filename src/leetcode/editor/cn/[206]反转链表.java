//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 👍 2015 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
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
class Recursion206 {
    /**
     * 优雅的递归
     * 方法描述: 输入一个节点 head，将「以 head 为起点」的链表反转，并返回反转之后的头结点。
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 反转子链表
        ListNode reverse = reverseList(head.next);
        // 原来紧跟 head 那个节点
        // 在 head.next 链表反转后, 变成了 最后一个节点
        // 但其 next 仍旧指向 原 head 的下下个节点
        // 现在让其指向 head
        head.next.next = head;
        // 断开 head 与子链表反转后尾节点的指向关系
        head.next = null;
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
//leetcode submit region end(Prohibit modification and deletion)
class Iteration206 {
    /**
     * 两个指针 加一个辅助指针 循环
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
