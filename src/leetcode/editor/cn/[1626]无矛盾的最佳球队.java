//假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。 
//
// 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间
//不会发生矛盾。 
//
// 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队
//中得分最高那支的分数 。 
//
// 
//
// 示例 1： 
//
// 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
//输出：34
//解释：你可以选中所有球员。 
//
// 示例 2： 
//
// 输入：scores = [4,5,6,5], ages = [2,1,2,1]
//输出：16
//解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
// 
//
// 示例 3： 
//
// 输入：scores = [1,2,3,5], ages = [8,9,10,1]
//输出：6
//解释：最佳的选择是前 3 名球员。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= scores.length, ages.length <= 1000 
// scores.length == ages.length 
// 1 <= scores[i] <= 10⁶ 
// 1 <= ages[i] <= 1000 
// 
//
// Related Topics 数组 动态规划 排序 👍 83 👎 0


import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//leetcode submit region begin(Prohibit modification and deletion)
//
@SuppressWarnings("Duplicated")
class Solution1626 {
    /**
     * 是否可以转换成: 求连续子序列的最大和
     * 1. 先按分数排序，按分数索引序给 ages 排序
     * 2. 计算连续子序列的分数和，碰到 age 下降，分数清零
     *
     * 不一定是连续子序列，把中间的大龄球员剔除掉可以让相加的子序列长度更长
     * 所以说 age 下降，可能需要剔除这个强的球员，也可能只剔除前面的高龄球员
     *
     * 5 5 5 5 5 6
     * 1 2 3 4 7 5
     *
     * 5 5 5 5 6 5
     * 1 2 3 4 5 7
     *
     * 因为要求是不产生矛盾，是不是按年龄排序更好一些
     * 然后求不下降的连续子序列和
     *
     * bad case
     * [1,2,3,5]
     * [8,9,10,1]
     * 原因: 未添加 preScore = score
     *
     * bad case
     * [319776,611683,835240,602298,430007,574,142444,858606,734364,896074]
     * [1,1,1,1,1,1,1,1,1,1]
     *
     * [5,4,3,2,1]
     * [1,1,1,1,1]
     * 原因: score 应该从小到大排序
     *
     * bad case
     * [4,5,6,5]
     * [2,1,2,1]
     *
     * [5,5,4,4,4,4,4,6]
     * [1,1,2,2,2,2,2,2]
     * 原因: score 的次序保证了 age 符合题目硬性条件，我们要做的就是求最大不下降子序列，不要求连续
     * 碰到 4 的时候，我们只有一个选择, 跳过 4
     * s[n] >= s[n-1]
     *   f(n) = f(n-1) + s[n]
     * s[n] < s[n-1]
     *   f(n) = max{f(n-1), s[n]}
     *
     * 这个题目已经做崩了，记录一下，正确解答看下面
     */
    public int bestTeamScore_wrong(int[] scores, int[] ages) {
        if (scores == null || scores.length == 0
                || ages == null || ages.length == 0)
            return 0;


        int[][] arr = new int[scores.length][2];
        for (int i = 0; i < scores.length; i++) {
            arr[i][0] = ages[i];
            arr[i][1] = scores[i];
        }

        // 按 age 从小到大排序, age 相同，按 score 从小到大排序
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int preScore = arr[0][1];
        int sum = preScore;
        int ret = sum;
        for (int i = 1; i < arr.length; i++) {
            int score = arr[i][1];
            if (score < preScore) {
                sum = 0;
            }
            sum += score;
            ret = Math.max(sum, ret);
            preScore = score;
        }
        return ret;
    }

    /**
     * 题目的要求是无矛盾，即按年龄排序后，抽出一组子序列，这个子序列是单调递增的;
     * 那么我们求符合条件的和最大的一个子序列
     * <p>
     * 最大不下降子序列和
     * 序列 s[...]
     * d[...] 表示将当前元素加入子序列的最大和
     * 由于题目要求，只有当 s[i] >= s[j](j < i) 的时候，才可以将 s[i] 元素加入 d[j] 中
     * 推导得出:
     *   dS 为所有符合 s[i] >= s[j] 条件的 d[j] 的集合， d[i] = max{dS} + s[i]
     *   若 dS 集合为空, d[i] = s[i]
     * 最大不下降子序列和为 max{d[0], d[1] ... d[i-1]}
     * <p>
     * 时间复杂度 O(n^2)
     * <p>
     * 思路:
     * 状态: 当前位置 i
     * 选择: 计算包含当前位置的最大和 d[i]
     * <p></p>
     * 相似题目: <a href="https://leetcode.cn/problems/longest-increasing-subsequence/">300 最长递增子序列</a>
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        // 将题目转换为最大不下降子序列的和
        if (scores == null || scores.length == 0
                || ages == null || ages.length == 0)
            return 0;

        int[][] arr = new int[scores.length][2];
        for (int i = 0; i < scores.length; i++) {
            arr[i][0] = ages[i];
            arr[i][1] = scores[i];
        }
        // 按 age 从小到大排序, age 相同，按 score 从小到大排序
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        int[] d = new int[arr.length];
        d[0] = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            // 这一步表示从比当前 score 小的所有(后面还存在比它小的 score, 但值一定是 0) 里面找一个
            for (int j = 0; j < i; j++) {
                if (arr[i][1] >= arr[j][1]) {
                    d[i] = Math.max(d[j] + arr[i][1], d[i]);
                } else {
                    d[i] = Math.max(arr[i][1], d[i]);
                }
            }
        }

        int ret = 0;
        for (int x: d) {
            ret = Math.max(x, ret);
        }
        return ret;
    }

    /**
     * 值域的做法 (可进阶至线段树, 上面的解法相当于用索引做最大不下降子序和)
     * <p></p>
     * 因为 ages 的值域比较小，所有我们优先给 scores 排序
     * <p></p>
     * 由于 scores 单调递增, 设 d[age] 表示从左至右遍历过程中恰好最大年龄为 age 的任意序列中的最大和
     * (age\score 序列都单调递增)
     * <p></p>
     * 对遍历过程中任意状态下的 (score, age): 存在一个年龄集合 S, 所有的值都比 age 小
     *   d[age] = max{d[S]} + score
     * 初始状态 d[age] 为 0
     * <p></p>
     * max{d[age]} 就是我们要的结果
     */
    public int bestTeamScore_ValueRange(int[] scores, int[] ages) {
        Integer[] ids = new Integer[scores.length];
        int maxAge = 0;
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
            maxAge = Math.max(maxAge, ages[i]);
        }
        Arrays.sort(ids, (i, j) -> scores[i] != scores[j] ?
                Integer.compare(scores[i], scores[j]) : Integer.compare(ages[i], ages[j]));

        int[] d = new int[maxAge+1];
        int maxSum = 0;
        // 按 scores 从小到大遍历, ages 是无序的
        for (int i = 0; i < ids.length; i++) {
            int age = ages[ids[i]];
            for (int j = 0; j < age; j++) {
                d[age] = Math.max(d[age], d[j]);
            }
            d[age] += scores[ids[i]];
            maxSum = Math.max(maxSum, d[age]);
        }
        return maxSum;
    }

    /**
     * 树状数组优化
     * 接着上面的值域解法
     * <p></p>
     * 我们存在一个 d[maxAge+1] 数组, 想要更新任意一个单位 d[i]，
     * 都要遍历 d[0...i]，找到其中的最大值，然后才能更新 d[i], 时间复杂度为 O(n)
     * <p></p>
     * 此时是不是很像前缀和以及前缀和数组的场景
     * <p></p>
     * 如果我们套用前缀和的思路新建一个数组 maxValue, maxValue[i] = max{d[0...i]}
     * 就可以用 O(1) 的时间找到最大值，但维护 maxValue 又需要 O(n) 的时间复杂度
     * maxValue[i] 的更改会影响到 maxValue[i+]
     * <p></p>
     * 树状数组:
     *   可以在 n*log(n) 内找到 d[0...i] 的最大值;
     *   可以在 n*log(n) 内完成 d[i] 值的更新
     * <p></p>
     * 树状数组 tree 并不是值域做法中的数组 d, 它只是数组 d 的辅助数据结构
     */
    public int bestTeamScore_BIT(int[] scores, int[] ages) {
        Integer[] ids = new Integer[scores.length];
        int maxAge = 0;
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
            maxAge = Math.max(maxAge, ages[i]);
        }
        Arrays.sort(ids, (i, j) -> scores[i] != scores[j] ?
                Integer.compare(scores[i], scores[j]) : Integer.compare(ages[i], ages[j]));

        int[] tree = new int[maxAge+1];
        // 按 scores 从小到大遍历, ages 是无序的
        int maxSum, max = 0;
        for (Integer id : ids) {
            int age = ages[id];
            // 寻找 d[0...age] 中的最大值, 并更新它
            maxSum = query(tree, age) + scores[id];
            update(tree, age, maxSum);
            max = Math.max(maxSum, max);
        }
        return max;
    }

    /**
     * 这里不是用于求前缀和
     * 而是用于求区间最大值
     */
    private int query(int[] tree, int age) {
        int max = 0;
        // 为什么可以这么做，涉及到管辖区间的问题
        for(; age > 0; age -= (age & -age)) {
            max = Math.max(max, tree[age]);
        }
        return max;
    }

    /**
     * 从最底层不断 tree 的某个节点所管辖的最大值
     */
    private void update(int[] tree, int age, int maxV) {
        for (; age < tree.length; age += (age & -age)) {
            if (maxV > tree[age])
                tree[age] = maxV;
        }
    }

    /**
     * bad case:
     * [1,2,3,5]
     * [8,9,10,1]
     * <p></p>
     */
    public static void main(String[] args) {
        Solution1626 solution = new Solution1626();
        int[] scores = new int[]{1,2,3,5};
        int[] ages = new int[]{8,9,10,1};
        int ret = solution.bestTeamScore(scores, ages);
        System.out.println(ret);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
