//给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。 
//
// 数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。例如，arr = [1,1,0,1] 表示数字 (-2)^3 +
// (-2)^2 + (-2)^0 = -3。数组形式 中的数字 arr 也同样不含前导零：即 arr == [0] 或 arr[0] == 1。 
//
// 返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1]
//输出：[1,0,0,0,0]
//解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
// 
//
// 
// 
//
// 示例 2： 
//
// 
//输入：arr1 = [0], arr2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：arr1 = [0], arr2 = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
// 
//
// 
// 1 <= arr1.length, arr2.length <= 1000 
// arr1[i] 和 arr2[i] 都是 0 或 1 
// arr1 和 arr2 都没有前导0 
// 
//
// Related Topics 数组 数学 👍 117 👎 0


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1073 {
    /* 1073.负二进制数相加: https://leetcode.cn/problems/adding-two-negabinary-numbers/
     * 相似题:
     *  1017.负二进制转换: https://leetcode.cn/problems/convert-to-base-2/
     *
     * 转化思路:
     * 负二进制的难点在于偶数位与奇数位表现不一样
     * 我们将两个负二进制转变成正常数后，相加，再转回负二进制数
     * 直接利用 1017 的代码
     * 目前过不去了, 数组长度最长可达 1000, 只能过 257/267 个用例
     *
     * 模拟思路:
     *  x 逢2进 -1
     *  x == -1, 进 1
     */
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1, j = arr2.length - 1;
        List<Integer> ans = new ArrayList<>();
        for (int c = 0; i >= 0 || j >= 0 || c != 0; --i, --j) {
            int a = i < 0 ? 0 : arr1[i];
            int b = j < 0 ? 0 : arr2[j];
            int x = a + b + c;
            if (x >= 2) {
                x -= 2;
                c = -1;
            } else if (x == -1) {
                x = 1;
                c = 1;
            }
            ans.add(x);
        }
        while (ans.size() > 1 && ans.get(ans.size() - 1) == 0) {
            ans.remove(ans.size() - 1);
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(x -> x).toArray();
    }

    /* 1073.负二进制数相加: https://leetcode.cn/problems/adding-two-negabinary-numbers/
     * 相似题:
     *  1017.负二进制转换: https://leetcode.cn/problems/convert-to-base-2/
     *
     * 转化思路:
     * 负二进制的难点在于偶数位与奇数位表现不一样
     * 我们将两个负二进制转变成正常数后，相加，再转回负二进制数
     * 直接利用 1017 的代码
     * 目前过不去了, 数组长度最长可达 1000, 只能过 257/267 个用例
     */
    public int[] addNegabinaryWrong(int[] arr1, int[] arr2) {
        BigInteger a1 = this.fromBaseNeg2(arr1);
        BigInteger a2 = this.fromBaseNeg2(arr2);
        return this.baseNeg2(a1.add(a2));
    }

    private BigInteger fromBaseNeg2(int[] arr) {
        int base = -2;

        int a = 1;
        BigInteger ans = BigInteger.valueOf(0);
        for (int k = arr.length - 1; k >= 0; k--) {
            if (arr[k] == 1) ans = ans.add(BigInteger.valueOf(a));
            a *= base;
        }
        return ans;
    }

    /* 若我们将一个数按 基2 的展现形式从低位到高位依次转换为 基-2, 未转换化的部分始终属于 基2 表示，这部分可以走正常的运算
     * 那么仅需要处理奇数位为 1 的一种情况: 2^i --> (-2)^(i+1) + (-2)^i
     *   若 基2 形式数 x 的某奇数位为 1, 如果我们将 基-2 形式的该位也设置为 1，原值 x 需要自增到 N 才可以保持值不变
     *   设高位 (包含当前奇数位) 表示的值大小为 C, i 为奇数位. 比如 arr = "10111000", C = "10111", i == 3
     *     C * 2^i = (-2)^i + N
     *     ==> N = C * 2^i + 2^i = (C+1) * 2^i
     * 仅需要将高位值 C 自增 1 即可
     * C+1 对结果有影响，
     * C-1 对结果无影响; 若该位 base2 为 1, (C-1)/2 == C/2
     *
     * 若 C < 0,
     * 可以等价与负的正整数 -C, 其二进制表示和正数相同，但每位的意义表示的是 -(2^i)
     * 若 i 为奇数, 则无须处理 -(2^i) == (-2)^i
     * 若 i 为偶数, -(2^i) --> (-2)^(i+1) + (-2)^i; 此时需要在奇数位上加 1
     *   -C * 2^i = 2^i - N
     *   ==> N = (C+1) * 2^i
     */
    public int[] baseNeg2(BigInteger C) {
        if (C.compareTo(BigInteger.ZERO) == 0) return new int[]{0};

        int k = C.compareTo(BigInteger.ZERO) > 0 ? -1 : 1;
        C = C.compareTo(BigInteger.ZERO) > 0 ? C : C.abs();

        StringBuilder ret = new StringBuilder();
        while (C.compareTo(BigInteger.ZERO) != 0) {
            if (C.mod(BigInteger.TWO).intValue() != 0) {
                ret.append('1');
                // 若 C > 0
                // 奇数位 == 1 的话一定会 +1, 根据等式 C * 2^i = (-2)^i + (C+1) * 2^i 来保持值一直相等
                // 偶数位 == 1 的话，一定会 -1，但减不减这个值对结果无影响，因为后面会 /2，这样可以省去一个 if 判断
                // C < 0 则反过来
                C = C.add(BigInteger.valueOf(k));
            } else {
                ret.append('0');
            }
            k = -k;
            C = C.divide(BigInteger.TWO);
        }

        int[] ans = new int[ret.length()];
        for (int i = ret.length()-1, j=0; i >= 0; i--) {
            ans[j++] = ret.charAt(i) - '0';
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution1073 so = new Solution1073();
        //System.out.println(Arrays.toString(so.baseNeg2(BigInteger.valueOf(-2))));
        //System.out.println(Arrays.toString(so.baseNeg2(BigInteger.valueOf(16))));
        int[] ints = so.addNegabinary(new int[]{0}, new int[]{1, 0});
        System.out.println(Arrays.toString(ints));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
