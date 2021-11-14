//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，
//pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。 
//
// 说明：不允许修改给定的链表。 
//
// 进阶： 
//
// 
// 你是否可以使用 O(1) 空间解决此题？ 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
// Related Topics 哈希表 链表 双指针 👍 1213 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class TreePoint142 {
    /**
     * 每当慢指针 slow 前进一步，快指针 fast 就前进两步。
     * 如果 fast 最终遇到空指针，说明链表中没有环；
     * 如果 fast 最终和 slow 相遇，那肯定是 fast 超过了 slow 一圈，说明链表中含有环。
     *
     * 设非环部分长度为 m, 环长 n, p1, p2 相交位置与入口的距离为 a
     * 已知 p1 走的步长为 p2 的两倍
     * m + kn + a = 2(m + a) => m = kn - a;
     * 假如此时一个新节点从 head 开始走 m 步, 同时 p2 也从交点走 m 步 (k 环少 a 个节点)
     * 则它们此时正好在入口相交
     * m 未知, 新节点与 p2 协同走可以
     */
    public ListNode detectCycle(ListNode head) {
        ListNode p1 = head, p2 = head, pNew = head;
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2) {
                while (p2 != pNew) {
                    p2 = p2.next;
                    pNew = pNew.next;
                }
                return p2;
            }
        }
        return null;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
