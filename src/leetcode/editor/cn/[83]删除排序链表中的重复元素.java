//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。 
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,1,2]
//输出：[1,2]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序排列 
// 
// Related Topics 链表 👍 658 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 */
class DoublePoint83 {
    /**
     * 双指针
     * 参考 26 题
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        for (ListNode fast = head; fast != null; fast = fast.next) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
        }
        // 如果最后几个都是重复的, 则必须截断链表
        slow.next = null;
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
