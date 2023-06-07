//给你一个数组 time ，其中 time[i] 表示第 i 辆公交车完成 一趟旅途 所需要花费的时间。 
//
// 每辆公交车可以 连续 完成多趟旅途，也就是说，一辆公交车当前旅途完成后，可以 立马开始 下一趟旅途。每辆公交车 独立 运行，也就是说可以同时有多辆公交车在
//运行且互不影响。 
//
// 给你一个整数 totalTrips ，表示所有公交车 总共 需要完成的旅途数目。请你返回完成 至少 totalTrips 趟旅途需要花费的 最少 时间。 
//
//
// 
//
// 示例 1： 
//
// 输入：time = [1,2,3], totalTrips = 5
//输出：3
//解释：
//- 时刻 t = 1 ，每辆公交车完成的旅途数分别为 [1,0,0] 。
//  已完成的总旅途数为 1 + 0 + 0 = 1 。
//- 时刻 t = 2 ，每辆公交车完成的旅途数分别为 [2,1,0] 。
//  已完成的总旅途数为 2 + 1 + 0 = 3 。
//- 时刻 t = 3 ，每辆公交车完成的旅途数分别为 [3,1,1] 。
//  已完成的总旅途数为 3 + 1 + 1 = 5 。
//所以总共完成至少 5 趟旅途的最少时间为 3 。
// 
//
// 示例 2： 
//
// 输入：time = [2], totalTrips = 1
//输出：2
//解释：
//只有一辆公交车，它将在时刻 t = 2 完成第一趟旅途。
//所以完成 1 趟旅途的最少时间为 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= time.length <= 10⁵ 
// 1 <= time[i], totalTrips <= 10⁷ 
// 
//
// Related Topics 数组 二分查找 👍 51 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution2187 {
    /* 2187.完成旅途的最少时间: https://leetcode.cn/problems/minimum-time-to-complete-trips/
     * 相似题:
     *  875.爱吃香蕉的珂珂: https://leetcode.cn/problems/koko-eating-bananas/
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *
     * count(x) 表示时间为 x 时，至多可以完成的旅途数量
     * 显然 count(x) 单调增
     * ans 为满足 count(x) >= totalTrips 的第一个值
     *
     * x 最小值为 1, 最大值为 max{time[0:-1]}*totalTrip
     * 第 i 辆公交车在时间 x 内，可完成的旅途数量为 x/time[i]
     *
     * 时间复杂度 O(n*lgC)
     *
     */
    public long minimumTime(int[] time, int totalTrips) {
        long l = 1, r = 0;
        for (int t: time) r = Math.max(r, t);
        r *= totalTrips;

        while (l < r) {
            long mid = (l+r) >> 1;
            if (count(time, mid) >= totalTrips) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private long count(int[] time, long x) {
        long ans = 0;
        for (int t: time) {
            ans += x/t;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
