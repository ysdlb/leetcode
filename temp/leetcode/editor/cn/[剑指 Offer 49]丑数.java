//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 282 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer49 {
    /**
     * <a href="https://leetcode-cn.com/problems/chou-shu-lcof/">leetcode-offer-49</a>
     * <a href="https://leetcode-cn.com/problems/ugly-number-ii/">leetcode-264 (同 offer 49)</a>
     *
     * 设已有最大丑数为 M, 那么下一个丑数必定是 M 及其以前的某个数 *2, *3 or *5 得来的, 我们只需要找到最小值便可得到下一个丑数
     * 丑数为一个数组, 为数组添加下一个丑数时, 肯定数当前数组的3个元素各自乘 2,3,5 得到的 3 个数中最小的一个
     * 对应倍数(2,3,5)的那个游标向后移动一位, 以便进行下一次比较(添加下一个丑数)
     *
     * 类比: 三个有序数组不重复合并
     * 特点: 结果数组的新增元素总是为三个有序数组中比结果元素最大值还要大的最小元素
     *      三个有序数组的新增元素分别为结果数组新增元素 *2, *3, *5 的结果
     */
    public int nthUglyNumber(int n) {
        int[] ret = new int[n];

        // 1 是丑数
        ret[0] = 1;
        // 三个虚拟有序数组
        int[][] m = new int[2][3];
        // m[0] = m[1] = m[2] = 0;
        m[1][0] = 2; m[1][1] = 3; m[1][2] = 5;

        // 3 个虚拟数组去重, 取最大的那一个
        for (int i = 1; i < n; i++) {
            // 因为是不断取最小的, 所以最多只会有一个重复
            // int r2 = ret[m2]*2, r3 = ret[m3]*3, r5 = ret[m5]*5;
            // if (r2 == ret[i-1]) m2++;
            // if (r3 == ret[i-1]) m3++;
            // if (r5 == ret[i-1]) m5++;

            // 取最小的那一个, 并将指针后移一位
            int min = ret[m[0][0]]*m[1][0], minK = 0;
            for (int k = 1; k < 3; k++) {
                int t = ret[m[0][k]] * m[1][k];
                if (min > t) {
                    min = t;
                    minK = k;
                } else if (min == t) {
                    m[0][k]++; // 去重复
                }
            }

            ret[i] = min;
            m[0][minK]++;
        }

        return ret[n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
