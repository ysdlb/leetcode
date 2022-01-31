//输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈
//的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。 
//
// 
//
// 示例 1： 
//
// 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//输出：true
//解释：我们可以按以下顺序执行：
//push(1), push(2), push(3), push(4), pop() -> 4,
//push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
// 
//
// 示例 2： 
//
// 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//输出：false
//解释：1 不能在 2 之前弹出。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= pushed.length == popped.length <= 1000 
// 0 <= pushed[i], popped[i] < 1000 
// pushed 是 popped 的排列。 
// 
//
// 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/ 
// Related Topics 栈 数组 模拟 👍 273 👎 0


import java.sql.SQLSyntaxErrorException;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer31 {
    /**
     * 先进后出特性
     * 开一个辅助栈, 对 popped 数组中对每一个元素:
     *     如果栈顶元素为该元素, 直接出栈
     *     如果栈顶元素不为该元素, 将 pushed 入栈, 直到满足上一个条件或者 pushed 已全部入栈
     * 注意: pushed 全部入栈后依次出栈, 直到全部校验通过, 栈空; 或者出现对不上对情况, 返回 false
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = -1, len = pushed.length;
        int[] stack = new int[len];
//        for (int j = 0, k = 0; j < len && k < len; j++) {
        for (int j = 0, k = 0; j < len; j++) {
            while (k < len && (i < 0 || stack[i] != popped[j])) {
                stack[++i] = pushed[k++];
            }
            if (stack[i] == popped[j]) {
                i--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] pushed = new int[]{1,2,3,4,5};
        int[] popped = new int[]{4,3,5,1,2};
        boolean ret = new SolutionOffer31().validateStackSequences(pushed, popped);
        System.out.println(ret);
    }
}

class SolutionOffer31_2 {
    /**
     * 更清晰的写法
     * 开一个辅助栈, 对 pushed 的每个元素
     *     先入栈
     *     尝试按 popped 顺序出栈, 直到栈为空, 或栈顶元素不为 popped 顺位元素为止
     * 最后如果 popped 是 pushed 的弹出序列, 则栈一定为空
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = -1, len = pushed.length;
        int[] stack = new int[len];

        int p = 0;
        for (int push: pushed) {
            stack[++i] = push;
            while (i >= 0 && stack[i] == popped[p]) {
                p++;
                i--;
            }
        }
        return i == -1;
    }

    public static void main(String[] args) {
        int[] pushed = new int[]{1,2,3,4,5};
        int[] popped = new int[]{4,3,5,1,2};
        boolean ret = new SolutionOffer31_2().validateStackSequences(pushed, popped);
        System.out.println(ret);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
