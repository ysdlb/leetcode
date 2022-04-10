//给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。 
//
// 返回删除后的链表的头节点。 
//
// 注意：此题对比原题有改动 
//
// 示例 1: 
//
// 输入: head = [4,5,1,9], val = 5
//输出: [4,1,9]
//解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
// 
//
// 示例 2: 
//
// 输入: head = [4,5,1,9], val = 1
//输出: [4,5,9]
//解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
// 
//
// 
//
// 说明： 
//
// 
// 题目保证链表中节点的值互不相同 
// 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点 
// 
// Related Topics 链表 👍 187 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * @see <a href="https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/">leetcode</a>
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class SolutionOffer18 {

    /**
     * 双指针前后挨着一起走, 憨憨写法
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;

        if (head.val == val) return head.next;

        ListNode p1 = head.next;
        ListNode p2 = head;
        while (p1 != null) {
            if (p1.val == val) {
                p2.next = p1.next;
                break;
            }
            p2 = p1;
            p1 = p1.next;
        }
        return head;
    }

    /**
     * 单指针写法, 如果我们直接拿到被删除的节点时候, 时间复杂度为 O(1)
     * 如果被删除节点不为节点尾部, 不需要得到要删除节点的下一个节点
     * 尾部必须要用双指针写法
     */
    public ListNode deleteNodeSingleP(ListNode head, ListNode val) {
        return head;
    }

    /**
     * 另类单指针写法, 严格意义上不算
     */
    public ListNode deleteNodeSingleP(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;
        ListNode cur = head;
        while (cur.next != null && cur.next.val != val)
            cur = cur.next;
        if (cur.next != null)
            cur.next = cur.next.next;
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
