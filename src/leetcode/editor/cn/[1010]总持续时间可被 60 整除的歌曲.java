//在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。 
//
// 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望下标数字 i 和 j 满足 i < j 且有 (time[i] + 
//time[j]) % 60 == 0。 
//
// 
//
// 示例 1： 
//
// 
//输入：time = [30,20,150,100,40]
//输出：3
//解释：这三对的总持续时间可被 60 整除：
//(time[0] = 30, time[2] = 150): 总持续时间 180
//(time[1] = 20, time[3] = 100): 总持续时间 120
//(time[1] = 20, time[4] = 40): 总持续时间 60
// 
//
// 示例 2： 
//
// 
//输入：time = [60,60,60]
//输出：3
//解释：所有三对的总持续时间都是 120，可以被 60 整除。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= time.length <= 6 * 10⁴ 
// 1 <= time[i] <= 500 
// 
//
// Related Topics 数组 哈希表 计数 👍 271 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1010 {

    /* 总持续时间可被 60 整除的歌曲: https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60/
     * 相似题:
     *  1.两数之和: https://leetcode.cn/problems/two-sum/
     *  560.和为 K 的子数组: https://leetcode.cn/problems/subarray-sum-equals-k/
     *
     * 高阶两数和, 甚至不如 560 连续子数组和复杂
     * 对每个元素取模, 然后判断取模之后的是否存在两数和
     *
     * 空间复杂度 O(n), 时间复杂度 O(1)
     */
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        int[] map = new int[60];
        for (int t: time) {
            int mod = t % 60;
            count += map[(60-mod)%60];
            map[mod]++;
        }
        return count;
    }

    public static void main(String[] args) {
        // int[] arg = new int[]{30,20,150,100,40};
        int[] arg = new int[]{60,60,60};
        Solution1010 solution = new Solution1010();
        int i = solution.numPairsDivisibleBy60(arg);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
