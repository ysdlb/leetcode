//把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。 
//
// 
//
// 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。 
//
// 
//
// 示例 1: 
//
// 输入: 1
//输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
// 
//
// 示例 2: 
//
// 输入: 2
//输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0
//.05556,0.02778] 
//
// 
//
// 限制： 
//
// 1 <= n <= 11 
// Related Topics 数学 动态规划 概率与统计 👍 361 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer60 {
    /**
     * <a href="https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/">leetcode-offer-60</a>
     * 暴力法:
     * 对 n 个骰子, 可能出现的点数为 [n, 6n]
     * 回溯递归穷举 6^n 中情况, 对每组和计数, 时间复杂度 O(6^n)
     *
     * 类似求 k 位不下降的数的方法:
     * 当有一个骰子时: 次数统计 1:1:1:1:1:1
     * 当再加入一个骰子时, 它自己可以贡献 [1,6] 点数, 所以对于任意 k 属于 [2,12], count(k) = sum(statistic[k-i]) i 从 1 到 6
     * 同理, 再加入一个也是
     *
     * 代码实现上可用 s1[0, 6*n-1], s2[0, 6*n-1] 两个数组交替计算
     * 注意每次 sum 的基值都是 0
     *
     * 从0开始
     * 最终对次数数组 [n-1, 6*n-1] 范围内的次数分别除以 6^n 算概率
     */
    private static final int MAX = 6;
    public double[] dicesProbability(int n) {
        int[] s1 = new int[MAX * n];
        int[] s2 = new int[MAX * n];

        // 先算第一个骰子
        int num = 1;
        for (int i = 0; i < MAX; i++)
            s1[i] = 1;

        while (num < n) {
            num++;
            // 新加入一个骰子, 产生新的范围, 对范围内对每个点数 i, sum[i-6, i-1]
            for (int i = num - 1; i < MAX*num; i++) {
                s2[i] = 0; // 这个位置可能被污染了
                for (int j = 1; j <= MAX; j++) {
                    int beforeI = i - j;
                    if (beforeI >= num - 2 && beforeI < MAX*(num-1))
                        s2[i] += s1[beforeI];
                }
            }
            int[] tmp = s1;
            s1 = s2;
            s2 = tmp;
        }

        // 统计概率
        double[] ret = new double[MAX*n - n +1];
        double total = Math.pow(MAX, n);
        for (int i = 0, j = n-1; i < ret.length; i++, j++) {
            ret[i] = s1[j] / total;
        }
        return ret;
    }

    public static void main(String[] args) {
        double[] ret = new SolutionOffer60().dicesProbability(2);
        System.out.println(Arrays.toString(ret));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
