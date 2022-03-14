//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。 
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
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
// Related Topics 链表 双指针 👍 728 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * <a href="https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/">leetcode82</a>
 */
class Solution82 {
    /**
     * 参考 26, 83
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 虚拟头部
        ListNode h = new ListNode(-1);
        h.next = head;
        ListNode cur = h;

        // 如果后面两个不为 null
        while (cur.next != null && cur.next.next != null) {
            // 判断后面两个是否为重复元素
            if (cur.next.val == cur.next.next.val) {
                int dup = cur.next.val;
                // 跳过所有的重复元素 dup
                while (cur.next != null && cur.next.val == dup)
                    cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return h.next;
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
