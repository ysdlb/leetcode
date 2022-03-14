//给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。 
//
// 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。 
//
// 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎
//，则可以在之后的操作中 重复使用 这枚鸡蛋。 
//
// 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？ 
// 
//
// 示例 1： 
//
// 
//输入：k = 1, n = 2
//输出：2
//解释：
//鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。 
//否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。 
//如果它没碎，那么肯定能得出 f = 2 。 
//因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。 
// 
//
// 示例 2： 
//
// 
//输入：k = 2, n = 6
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：k = 3, n = 14
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= 100 
// 1 <= n <= 10⁴ 
// 
// Related Topics 数学 二分查找 动态规划 👍 756 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution887 {
    /*
     * 状态：当前在第 i 层，手里剩下几枚鸡蛋（k），
     * 选择：本次鸡蛋碎没碎（y/n）这不算状态，和偷不偷一样，这里将客观选择抽象为主观选择（因为需要最差情况，哪个需要次数最多我们选哪个)
     *
     * 说明：我们在第 i 层丢了鸡蛋之后，可能出现两种情况：
     * 	a. 鸡蛋碎了：k—；楼层区间从 【1…n】  -> 【1…i-1】
     * 	b. 鸡蛋没碎：k 不变，楼层区间从 【1…n】->【i+1…n】
     *
     * 关于最差情况下的最小操作次数：我们总是从下往上找，那么最上面那层楼就是就是最差情况？（不是这么解释）
     *
     *
     * 我们在任意一层丢鸡蛋，总有碎没碎两种情况，从而进一步将总区间确定为该层的上区间或者下区间其一。这里有两个主要的问题。
     *
     * 1. 因为求最差情况下的最少次数，最差情况下我们肯定是区间缩小到 1 的时候的区间，但楼层可能是任意一个。
     * 2. 鸡蛋数量是有限的，当鸡蛋无限时候，我们总可以使用二分在 「lgn」的整数上界次数内将区间缩小到 1；但假如鸡蛋只有一个，只要碎了就没有的情况下，只能从最下一层一层往上试，最差情况是第 n 层也没碎，最终结果也是n，最少需要次数为 n
     *
     * 将题目翻译一下就是 k 枚 鸡蛋在 windows size 区间范围内通过碎没碎这个指标在最差情况下将 windowsize 这个值缩小到 1 所需的最小次数。
     * 1. 当 windowsize == 1 的时候，我们需要 1 次 (windowsize == 0 时,  已经找到了最小值, 故需要 0 次)
     * 2. 当 k = 1 的时候，我们需要 windowsize 次
     *
     * 将上诉逻辑描述为 f(win, k)
     * 我们设区间大小 win 这些楼层的范围为 [0+1, 0+win]（我们只关心楼层范围大小），我们在任意一层 i 丢鸡蛋，基于鸡蛋碎或者不碎两种“选择”，会产生两个结果
     * 碎了，区间范围变为 i 之下的区间：f(i-1，k-1)
     * 没碎，区间范围变为 i 之上的区间：f(win-i, k)
     * 因为求最差情况，我们最终的取值为 max = max{ f(i-1, k-1), f(win-i, k)} （作出任何一个抉择，结果都在最坏的情况中）
     *
     * 又因为求最差情况下的最少次数，我们可以在区间内的任意 i 层丢鸡蛋，所以
     * f(win, k) = foreach i min[ max{ f(i-1, k-1), f(win-i, k)} ],   i 属于 [0+1, 0+win]
     * ps: 假如第 i 层没碎（恰好结果是第 i 层, 那么当在 i+1 层 windowsize == 1 时, 通过鸡蛋碎没碎， 可以确认结果是 i 还是 i+1
     *     所以第 i 层试了, 上下区间都不用包含 i 层 ( 求最少次数 )
     *
     * 上述状态选择有误, 经过中间说明纠正后
     * 状态：当前在剩余 n 层，手里剩下几枚鸡蛋（k），
     * 选择：
     * 1. 本次鸡蛋碎没碎（y/n）这不算状态，和偷不偷一样，这里将客观选择抽象为主观选择（因为需要最差情况，哪个需要次数最多我们选哪个)
     * 2. 鸡蛋在这 n 层里的哪一层丢 ( 因为需要最少次数, 哪个次数最少我们选哪个)
     *
     * ************************************* 时间复杂度 ************************************
     * 上述方法的时间复杂度为 o(k*n^2)
     * 在寻找最优解的时候, 我们用了这样一个公式:
     * f(win, k) = foreach i min[ max{ f(i-1, k-1), f(win-i, k)} ],   i 属于 [0+1, 0+win]
     * 从直觉上讲, 在 win, k 固定的情况下:
     *      f(i-1, k-1) 随 i 单调递增, 类似 y1 = i-1;
     *      f(win - i, k) 随 i 单调减, 类似 y2 = win-i;
     *
     * ************************************* 错误的推论 **********************************
     * 如果 y1, y2 是连续线段, 那么二者较大值中的最小值就是它们的交点,
     * 在 i 散列的情况下, 目标值肯定是离中心最近的左右两个点上, 下面举两个例子:
     *      win == 3: (1,2,3) 中心唯一是 2; lowmiddle == highmiddle = (3+1)/2
     *      win == 4: (1,2,3,4) 中心有两个, (2,3), lowmiddle = 4/2; highmiddle = lowmiddle+1
     * 我们可以固定 middle = (win+1)/2, 最优值肯定在 middle 和 middle + 1 之间 ( 奇数情况下 middle 肯定比 middle + 1 更优
     *
     *
     * ************************************* 纠正 **********************************
     *      f(i-1, k-1) 随 i 单调递增, 类似 y1 = i-1;
     *      f(win - i, k) 随 i 单调减, 类似 y2 = win-i;
     * 只是类似, 单调递增的可能永远比单调递减的大; 也可能永远比之小; 也可能有交点
     *
     * 这里要用二分, 我们用 left 与 right 两个端点, 在 middle 位置,
     * 如果递减序列比递增序列大, 那么两序列最接近的位置肯定在 middle 右边, middle 变成 left;
     * 如果递减序列比递增序列小, 那么两序列最接近的位置肯定在 middle 左边, middle 变成 right;
     * 相等的话, 恰好就是终点, middle 变成 left 和 right
     * 终止条件, right - left <= 1
     *
     * 有交点的情况下: 这里 left 直接取下降趋势线就是最坏情况, right 直接取上升趋势线也是最坏情况, 然后取最优解
     * 上升序列永远比下降序列大, left 和 right  都在最优解的右边, 最优解是 left 点的上升序列
     * 上升序列永远比下降序列小, left 和 right  都在最优解的左边, 最优解是 right 点的下降序列
     * 所以还是比较四个值, 一力降十会
     *
     */
    public int superEggDrop(int k, int n) {
        if (k < 1 || n < 1)
            throw new RuntimeException("Param Error!");

        // 初始状态
        int[][] dp = new int[n+1][k+1];
        // windowSize == 1
        for (int i = 1; i <= k; i++) {
            dp[1][i] = 1;
        }
        // k == 1
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }

        for (int win = 2; win <= n; win++) {
            for (int K = 2; K <= k; K++) {
                dp[win][K] = Integer.MAX_VALUE;
                for (int i = 1; i <= win; i++)
                    dp[win][K] = Math.min(
                            dp[win][K],
                            Math.max(dp[i-1][K-1], dp[win-i][K]) + 1
                            );
            }
        }
        return dp[n][k];
    }

    /**
     * 从直觉上讲, 在 win, k 固定的情况下:
     *      f(i-1, k-1) 随 i 单调递增, 类似 y1 = i-1;
     *      f(win - i, k) 随 i 单调减, 类似 y2 = win-i;
     * 如果 y1, y2 是连续线段, 那么二者较大值中的最小值就是它们的交点,
     * 在 i 散列的情况下, 目标值肯定是离中心最近的左右两个点上, 下面举两个例子:
     *      win == 3: (1,2,3) 中心唯一是 2; lowMiddle == highMiddle = (3+1)/2
     *      win == 4: (1,2,3,4) 中心有两个, (2,3), lowMiddle = 4/2; highMiddle = lowMiddle+1
     * 我们可以固定 middle = (win+1)/2, 最优值肯定在 middle 和 middle + 1 之间 ( 奇数情况下 middle 肯定比 middle + 1 更优
     *
     *      f(i-1, k-1) 随 i 单调递增, 类似 y1 = i-1;
     *      f(win - i, k) 随 i 单调减, 类似 y2 = win-i;
     * 只是类似, 单调递增的可能永远比单调递减的大; 也可能永远比之小; 也可能有交点
     */
    public int superEggDropV2(int k, int n) {
        if (k < 1 || n < 1)
            throw new RuntimeException("Param Error!");

        // 初始状态
        int[][] dp = new int[n+1][k+1];
        // windowSize == 1
        for (int i = 1; i <= k; i++) {
            dp[1][i] = 1;
        }
        // k == 1
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }

        for (int win = 2; win <= n; win++) {
            for (int K = 2; K <= k; K++) {
                int middle = (win+1) / 2;
                dp[win][K] = Math.min(
                        Math.max(dp[middle-1][K-1], dp[win-middle][K]) + 1,
                        Math.max(dp[middle][K-1], dp[win-middle-1][K]) + 1
                );
            }
        }
        return dp[n][k];
    }

    public int superEggDropV3(int k, int n) {
        if (k < 1 || n < 1)
            throw new RuntimeException("Param Error!");

        // 初始状态
        int[][] dp = new int[n+1][k+1];
        // windowSize == 1
        for (int i = 1; i <= k; i++) {
            dp[1][i] = 1;
        }
        // k == 1
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }

        for (int win = 2; win <= n; win++) {
            for (int K = 2; K <= k; K++) {
                int left = 1, right = win, middle = (left + right) / 2;
                while (right - left > 1) {
                    if (dp[win - middle][K] > dp[middle-1][K-1]) // 下降趋势线比上升趋势线大
                        left = middle;
                    else if (dp[win - middle][K] < dp[middle-1][K-1]) // 下降趋势线比上升趋势线小
                        right = middle;
                    else { // 相等
                        left = middle;
                        right = middle;
                    }
                    middle = (left + right) / 2;
                }
                // 有交点的情况下: 这里 left 直接取下降趋势线就是最坏情况, right 直接取上升趋势线也是最坏情况, 然后取最优解
                // 上升序列永远比下降序列大, left 和 right  都在最优解的右边, 最优解是 left 点的上升序列
                // 上升序列永远比下降序列小, left 和 right  都在最优解的左边, 最优解是 right 点的下降序列
                // 所以还是比较四个值, 一力降十会
                int leftV = Math.max(dp[left-1][K-1], dp[win-left][K]);
                int rightV = Math.max(dp[right-1][K-1], dp[win-right][K]);
                dp[win][K] = Math.min(leftV, rightV) + 1;
            }
        }
        return dp[n][k];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
