//给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。 
//
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
//
// 
//
// 注意：本题与主站 206 题相同： https://leetcode-cn.com/problems/reverse-linked-list/ 
// Related Topics 递归 链表 👍 51 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * <a href="https://leetcode-cn.com/problems/UHnkqh/">leetcode-offer-024</a>
 */
class SolutionOffer024 {
    /**
     * 优雅递归:
     * reverseList(node) 表示反转 node 这个链表
     * base case: 如果 node.next == null, 表示链表只有一个元素, 无需返回
     *
     * 对于链表 1->2->3->4->5
     * 经过 reverseList(2) 后
     * 1->2<-3<-4<-5
     *
     * 只需要 1.next.next = 1
     * 1.next = null
     * 就完成了反转
     *
     *
     * 循环反转:
     * 见 Offer24, 206
     * 两种做法
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode reversed = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return reversed;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
