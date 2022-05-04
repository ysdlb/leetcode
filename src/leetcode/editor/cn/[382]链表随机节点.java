//给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。 
//
// 进阶: 
//如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？ 
//
// 示例: 
//
// 
//// 初始化一个单链表 [1,2,3].
//ListNode head = new ListNode(1);
//head.next = new ListNode(2);
//head.next.next = new ListNode(3);
//Solution solution = new Solution(head);
//
//// getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
//solution.getRandom();
// 
// Related Topics 水塘抽样 链表 数学 随机化 👍 172 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Random;

/**
 * 2022/05/04补充，类似题目：398
 * 710, 380
 */
class Solution382 {

    private final Random random = new Random(System.currentTimeMillis());

    private final ListNode head;

    public Solution382(ListNode head) {
        this.head = head;
    }

    /**
     * 水塘抽样算法简略版
     * 对任意第 K 个节点 有 1/k 的概率留下, 因为只有一个名额, 如果前面有被留下的, 此时会被顶掉
     * 那该元素最终被留下的概率 (n 未知)
     * 1/k * (1-1/(k+1)) * (1-1/(k+2)) * ... * (1-1/n)
     * = 1/k * k/k+1 * k+1/k+2 * ... * n-1/n
     * = 1/n
     *
     * 它们的概率都为 1/n , 机会均等
     */
    public int getRandom() {
        ListNode node = head, res = head;

        for (int i = 1; node != null; i++) {
            if (random.nextInt(i) == 0)
                res = node;
            node = node.next;
        }
        return res.val;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)
