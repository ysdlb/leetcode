//给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。 
//
// 注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出："110"
//解释：(-2)² + (-2)¹ = 2
// 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出："111"
//解释：(-2)² + (-2)¹ + (-2)⁰ = 3
// 
//
// 示例 3： 
//
// 
//输入：n = 4
//输出："100"
//解释：(-2)² = 4
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 10⁹ 
// 
//
// Related Topics 数学 👍 107 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1017 {
    /* 负二进制转换: https://leetcode.cn/problems/convert-to-base-2/
     * 相似题:
     *   1073.负二进制数相加: https://leetcode.cn/problems/adding-two-negabinary-numbers/
     *
     * 对 base N 的转换问题，一般都是对值先取模，在除以这个数
     * 任意一个数 % -2， 可能的值有 1,0,-1; 对于 -1 我们无法证明怎么处理
     *
     * 若二进制的奇偶位分开讨论，因为二进制位的 0 不会有任何贡献，所以我们只讨论二进制为 1 的情况
     *   偶数位(0,2,4,...): -2 的基等价于 基2
     *   奇数位(1,3,5,...): -2 的基等价于 基2 2^i --> (-2)^(i+1) + (-2)^i
     * 若最低位是奇数位, 则 1 --> 11
     *   问题是若高位偶数位原本就是 1，则 11 --> 21
     * 一个数天然可以是 基2 表示的数，我们按照上述规则将 基2 转化为 基-2 即可
     *
     * n & 0xaaaaaaaa 可抽出奇数位的值来
     * 下面将 基2 的数转化为 等价的基-2
     * 对于 "111"
     *   偶数位: "101" --> 基-2("101")
     *   奇数位: "010" --> 基-2("110")
     * 基-2和("211") --> 偶数位 "2" == "110" --> "11011"
     * 这种方式的转化需要一层一层去除偶数位的 2，但又引入奇数位的新变化，逻辑稍微混乱
     *
     * 上述方式的难点在于 基-2 的形式无法用正常的运算逻辑处理
     * 若我们将一个数按 基2 的展现形式从低位到高位依次转换为 基-2, 未转换化的部分始终属于 基2 表示，这部分可以走正常的运算
     * 那么仅需要处理奇数位为 1 的一种情况: 2^i --> (-2)^(i+1) + (-2)^i
     *   若 基2 形式数 x 的某奇数位为 1, 如果我们将 基-2 形式的该位也设置为 1，原值 x 需要自增到 N 才可以保持值不变
     *   设高位 (包含当前奇数位) 表示的值大小为 C, i 为奇数位. 比如 arr = "10111000", C = "10111", i == 3
     *     C * 2^i = (-2)^i + N
     *     ==> N = C * 2^i + 2^i = (C+1) * 2^i
     * 仅需要将高位值 C 自增 1 即可
     * C+1 对结果有影响，
     * C-1 对结果无影响; 若该位 base2 为 1, (C-1)/2 == C/2
     *
     * 若 C < 0, 可以等价与负的正整数 -C, 其二进制表示和正数相同，但每位的意义表示的是 -(2^i)
     * 若 i 为奇数, 则无须处理 -(2^i) == (-2)^i
     * 若 i 为偶数, -(2^i) --> (-2)^(i+1) + (-2)^i; 此时需要在奇数位上加 1
     *   -C * 2^i = 2^i - N
     *   ==> N = (C+1) * 2^i
     *
     * 优雅一点的转换方式:
     *   o. "111" % 2 == 1 --> (1, "11") /2
     *   j. "11" % 2 == 1  --> (1, "10") +1 /2
     *   o. "10" % 2 == 0 --> (0, "1") /2
     *   j. "1" % 2 == 1 --> (1, "1") +1 /2
     *   o. "1" % 2 == 1 --> (1, "0") /2
     */
    public String baseNeg2(int n) {
        if (n == 0) return "0";

        int k = n > 0 ? 1 : -1;
        n = n > 0 ? n : -n;
        StringBuilder ret = new StringBuilder();
        while (n != 0) {
            if (n % 2 != 0) {
                ret.append('1');
                // 奇数位 == 1 的话一定会 +1, 根据等式 C * 2^i = (-2)^i + (C+1) * 2^i 来保持值一直相等
                // 偶数位 == 1 的话，一定会 -1，但减不减这个值对结果无影响，因为后面会 /2，这样可以省去一个 if 判断
                n -= k;
            } else {
                ret.append('0');
            }
            k = -k;
            n >>>= 1;
        }

        return ret.reverse().toString();
    }

    public static void main(String[] args) {
        Solution1017 solution = new Solution1017();
        System.out.println(solution.baseNeg2(1));
        System.out.println(solution.baseNeg2(-2));
        System.out.println(solution.baseNeg2(3));
        System.out.println(solution.baseNeg2(4));
        System.out.println(solution.baseNeg2(5));
        System.out.println(solution.baseNeg2(6));
        System.out.println(solution.baseNeg2(7));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
