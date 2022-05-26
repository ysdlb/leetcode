//给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。 
//
// 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。 
//
// 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。 
//
// 返回一个由上述 k 部分组成的数组。 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3], k = 5
//输出：[[1],[2],[3],[],[]]
//解释：
//第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
//最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
//输出：[[1,2,3,4],[5,6,7],[8,9,10]]
//解释：
//输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 1000] 
// 0 <= Node.val <= 1000 
// 1 <= k <= 50 
// 
// Related Topics 链表 👍 254 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.*;

/**
 * Definition for singly-linked list.
 */
class Solution725 {
    /**
     * 简单题目
     * len 除以 k 的商作为 base, 模作为 add
     * 前 add 个链表的长度为 base+1, 后面长度为 base
     *
     * 本身很简单，但边界条件巨恶心
     * 还有 三元运算符的优先级比 + 低
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        for (ListNode cur = head; cur != null; cur = cur.next)
            len++;
        int base = len / k, add = len % k;

        ListNode[] ret = new ListNode[k];
        int i = 0;
        for (ListNode cur = head; cur != null;) {
            ret[i] = cur;
            int count = base + (i < add ? 1 : 0);
            i++;

            while (cur != null && count > 0) {
                ListNode pre = cur;
                cur = cur.next;
                if (count == 1)
                    pre.next = null;
                count--;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        Solution725 solution725 = new Solution725();
        solution725.splitListToParts(node1, 2);
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
