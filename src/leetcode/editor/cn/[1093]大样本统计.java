//我们对 0 到 255 之间的整数进行采样，并将结果存储在数组 count 中：count[k] 就是整数 k 在样本中出现的次数。 
//
// 计算以下统计数据: 
//
// 
// minimum ：样本中的最小元素。 
// maximum ：样品中的最大元素。 
// mean ：样本的平均值，计算为所有元素的总和除以元素总数。 
// median ： 
// 
// 如果样本的元素个数是奇数，那么一旦样本排序后，中位数 median 就是中间的元素。 
// 如果样本中有偶数个元素，那么中位数median 就是样本排序后中间两个元素的平均值。 
// 
// mode ：样本中出现次数最多的数字。保众数是 唯一 的。 
// 
//
// 以浮点数数组的形式返回样本的统计信息 [minimum, maximum, mean, median, mode] 。与真实答案误差在 10⁻⁵ 内的答案
//都可以通过。 
//
// 
//
// 示例 1： 
//
// 
//输入：count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
//输出：[1.00000,3.00000,2.37500,2.50000,3.00000]
//解释：用count表示的样本为[1,2,2,2,3,3,3,3]。
//最小值和最大值分别为1和3。
//均值是(1+2+2+2+3+3+3+3) / 8 = 19 / 8 = 2.375。
//因为样本的大小是偶数，所以中位数是中间两个元素2和3的平均值，也就是2.5。
//众数为3，因为它在样本中出现的次数最多。 
//
// 示例 2： 
//
// 
//输入：count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
//输出：[1.00000,4.00000,2.18182,2.00000,1.00000]
//解释：用count表示的样本为[1,1,1,1,2,2,3,3,3,4,4]。
//最小值为1，最大值为4。
//平均数是(1+1+1+1+2+2+2+3+3+4+4)/ 11 = 24 / 11 = 2.18181818…(为了显示，输出显示了整数2.18182)。
//因为样本的大小是奇数，所以中值是中间元素2。
//众数为1，因为它在样本中出现的次数最多。
// 
//
// 
//
// 提示： 
//
// 
// count.length == 256 
// 0 <= count[i] <= 10⁹ 
// 1 <= sum(count) <= 10⁹ 
// count 的众数是 唯一 的 
// 
//
// Related Topics 数组 数学 概率与统计 👍 67 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1093 {

    /* 1093.大样本统计: https://leetcode.cn/problems/statistics-from-a-large-sample/
     *
     * 差不多算简单题目，就是题目难懂
     *
     * count[i] 表示数字 i 在一个样本中出现的次数
     * 求:
     * 最小值: 第一个不为 0 的 count[i]
     * 最大值: 最后一个不为 0 的 count[i]
     * 平均值: sum(count[i]*i) 0<=i<n / sum(count[i])
     * 中位数: index = sum(count[i])/2
     * 众数: max(count[i]) 之 i
     *
     * 难点在于求中位数(需要分奇偶来讨论)
     * 但是在计算机中:
     * 无论奇偶，中位数的左边和右边的长度一定相等，都等于 (n−1)/2
     * 可以用这个特性来求中位数
     */
    public double[] sampleStats(int[] count) {
        int min = -1, max = 0;
        // count[mode] O(1) 访问, 所以无须一个额外的数来比较众数的数量
        int n = 0, mode = 0;
        long sum = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] <= 0) continue;

            n += count[i];
            sum += (long) count[i] *i;
            if (count[i] > count[mode])
                mode = i;

            if (min == -1) min = i;
            max = i;
        }
        double mean = ((double) sum) / n;
        int x = 0, y = count.length-1;
        int del = (n-1)/2;
        for (int t = del; t >= 0; t -= count[x++]);
        for (int t = del; t >= 0; t -= count[y--]);
        double median = (x+y)/2.0;
        return new double[]{min, max, mean, median, mode};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
