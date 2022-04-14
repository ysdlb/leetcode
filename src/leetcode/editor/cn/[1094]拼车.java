//车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向） 
//
// 给定整数 capacity 和一个数组 trips , trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有
// numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。 
//
// 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：trips = [[2,1,5],[3,3,7]], capacity = 4
//输出：false
// 
//
// 示例 2： 
//
// 
//输入：trips = [[2,1,5],[3,3,7]], capacity = 5
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= trips.length <= 1000 
// trips[i].length == 3 
// 1 <= numPassengersi <= 100 
// 0 <= fromi < toi <= 1000 
// 1 <= capacity <= 10⁵ 
// 
// Related Topics 数组 前缀和 排序 模拟 堆（优先队列） 👍 161 👎 0


import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1094 {
    /**
     * 实际就是统计车在每个站点的人数, 最开始每个站点的人数都是 0
     * 假设 trip 表示有 n 个人从 from 站点到 to 站点, 则需要给站点数组的 [from, to] 区间每个元素加 n
     *
     * 遍历 trips 里的所有 trip, 对站点人数进行增删操作, 如果全程人数不超过 capacity, 则可以接送所有乘客
     *
     * 因为所有站点数量不好确定, 所以我们用一个 treeMap 来表示站点数组, 编号小的数组排前面
     * 而且 from 到 to 的操作为 O(n) 量级, 这里可以用差分数组表示这个区间内的元素增减, from+, to-, O(1) 量级
     *
     * 参考 370, 1109
     */
    public boolean carPooling(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] trip: trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            if (map.getOrDefault(from, 0) + passengers > capacity)
                return false;
            map.compute(from, (key, oldV) -> oldV == null ? passengers : oldV + passengers);
            map.compute(to, (key, oldV) -> oldV == null ? -passengers : oldV - passengers);
        }

        int passengers = 0;
        for (Integer p: map.values()) {
            passengers += p;
            if (passengers > capacity)
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
