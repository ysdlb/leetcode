//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。 
//
// 
//
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 
//
// 
//
// 提示: 
//
// 
// 0 < nums.length <= 100 
// 
//
// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 贪心 字符串 排序 👍 380 👎 0


import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer45 {
    /**
     * 确定一个大小关系, 对数 m 和 n, 如果拼接后的结果
     * mn > nm ==> m > n
     * mn == nm ==> m == n
     * mn < nm ==> n < m
     */
    public String minNumber(int[] nums) {
        // 从小到大排列, 然后拼起来
        return Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((n1, n2) -> (n1 + n2).compareTo(n2 + n1))
                .collect(Collectors.joining());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
