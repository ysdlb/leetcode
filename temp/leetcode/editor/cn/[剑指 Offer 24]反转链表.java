//定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。 
//
// 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 206 题相同：https://leetcode-cn.com/problems/reverse-linked-list/ 
// Related Topics 递归 链表 👍 362 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * @see <a href="https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/">leetcode-Offer-24</a>
 * @see <a href="https://leetcode-cn.com/problems/reverse-linked-list/">leetcode-206</a>
 */
class SolutionOffer24 {

    /**
     * 优雅递归 多指针循环
     * 优雅递归的实现 (后续遍历)
     */
    public ListNode reverseList(ListNode head) {
        if (head  == null || head.next == null)
            return head;

        ListNode rHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rHead;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
