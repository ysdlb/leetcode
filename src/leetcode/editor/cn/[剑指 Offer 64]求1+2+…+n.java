//求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。 
//
// 
//
// 示例 1： 
//
// 输入: n = 3
//输出: 6
// 
//
// 示例 2： 
//
// 输入: n = 9
//输出: 45
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10000 
// 
// Related Topics 位运算 递归 脑筋急转弯 👍 439 👎 0


import java.util.stream.IntStream;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer64 {
    /**
     * 开放性题目, C 系语言可以声明一个 n*n+1 的数组, 然后求数组的 size >> 1
     */
    public int sumNums(int n) {
        return IntStream.range(1, n+1).sum();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
