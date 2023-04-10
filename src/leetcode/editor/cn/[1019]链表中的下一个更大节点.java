//给定一个长度为 n 的链表 head 
//
// 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。 
//
// 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点
//，设置 answer[i] = 0 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [2,1,5]
//输出：[5,5,0]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [2,7,4,3,5]
//输出：[7,0,5,5,0]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数为 n 
// 1 <= n <= 10⁴ 
// 1 <= Node.val <= 10⁹ 
// 
//
// Related Topics 栈 数组 链表 单调栈 👍 263 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
class Solution1019 {

    /* 链表中的下一个更大节点: https://leetcode.cn/problems/next-greater-node-in-linked-list/
     * 相似题:
     *   496. 下一个更大元素 I: https://leetcode.cn/problems/next-greater-element-i/
     *   503. 下一个更大元素 II: https://leetcode.cn/problems/next-greater-element-ii/
     *   739. 每日温度: https://leetcode.cn/problems/daily-temperatures/
     *   1118. 一个月有多少天: https://leetcode.cn/problems/number-of-days-in-a-month/
     *
     * 依次遍历链表，维护一个非严格递减的单调栈
     * 我们的目标是下一个更大的值，
     *   所以栈顶需要弹出元素的时候，弹出元素的下一个更大的值恰好就是待压入栈顶的值
     *
     * 因为源数据是链表，目标数据是数组
     * 应该有两个同步单调栈，一个存值，一个存目标数组的索引
     * 另外再需要一个目标数组, 作为返回值
     */
    public int[] nextLargerNodes(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> index = new Stack<>();
        List<Integer> ret = new ArrayList<>();
        int i = 0;
        while (head != null) {
            while (!stack.isEmpty() && head.val > stack.peek()) {
                stack.pop();
                ret.set(index.pop(), head.val);
            }
            stack.push(head.val);
            index.push(i++);
            ret.add(0);
            head = head.next;
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Solution1019 solution = new Solution1019();
        ListNode node2 = new ListNode(2);
        ListNode node7 = new ListNode(7);
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        node2.next = node7;
        node7.next = node4;
        node4.next = node3;
        node3.next = node5;

        int[] ret = solution.nextLargerNodes(node2);
        System.out.println(Arrays.toString(ret));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
