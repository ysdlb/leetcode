//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。 
//
// 
//
// 示例 1: 
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi" 
//
// 
//
// 提示： 
//
// 
// 0 <= num < 2³¹ 
// 
// Related Topics 字符串 动态规划 👍 371 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer46 {
    /**
     * 设 f(n) 为 num 前 n 的翻译方法数量
     * f(n+1) 的值由两部分组成
     *     f(n), (n+1)位对应一个码
     *     f(n-1)     * 设 f(n) 为 num 前 n 的翻译方法数量
     *      * f(n+1) 的值由两部分组成
     *      *     f(n), (n+1)位对应一个码
     *      *     f(n-1), 如果 nums[n]nums[n+1] <= 25
     */
    public int translateNum(int num) {
        int[] nums = this.translate(num);
        int[] dp = new int[nums.length + 1];
        dp[0] = 1; dp[1] = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] == 1 || (nums[i-1] == 2 && nums[i] <= 5))
                dp[i+1] = dp[i] + dp[i-1];
            else
                dp[i+1] = dp[i];
        }
        return dp[nums.length];
    }

    public int[] translate(int num) {
        if (num == 0) return new int[]{0};
        // 25 转换位数组 [5,2]
        int[] nums = new int[20];
        int i = 0;
        while (num > 0) {
            nums[i++] = num % 10;
            num /= 10;
        }

        int[] ret = new int[i];
        for (int j = 0; i > 0;)
            ret[--i] = nums[j++];

        return ret;
    }

    public static void main(String[] args) {
        new SolutionOffer46().translateNum(0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
