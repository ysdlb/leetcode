//有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。 
//
// 下标为 i 处的奶酪被吃掉的得分为： 
//
// 
// 如果第一只老鼠吃掉，则得分为 reward1[i] 。 
// 如果第二只老鼠吃掉，则得分为 reward2[i] 。 
// 
//
// 给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。 
//
// 请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。 
//
// 
//
// 示例 1： 
//
// 
//输入：reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
//输出：15
//解释：这个例子中，第一只老鼠吃掉第 2 和 3 块奶酪（下标从 0 开始），第二只老鼠吃掉第 0 和 1 块奶酪。
//总得分为 4 + 4 + 3 + 4 = 15 。
//15 是最高得分。
// 
//
// 示例 2： 
//
// 
//输入：reward1 = [1,1], reward2 = [1,1], k = 2
//输出：2
//解释：这个例子中，第一只老鼠吃掉第 0 和 1 块奶酪（下标从 0 开始），第二只老鼠不吃任何奶酪。
//总得分为 1 + 1 = 2 。
//2 是最高得分。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n == reward1.length == reward2.length <= 10⁵ 
// 1 <= reward1[i], reward2[i] <= 1000 
// 0 <= k <= n 
// 
//
// Related Topics 贪心 数组 排序 堆（优先队列） 👍 17 👎 0


import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2611 {

    /* 2611.老鼠和奶酪: https://leetcode.cn/problems/mice-and-cheese/
     * 相似题:
     *  1029.两地调度: https://leetcode.cn/problems/two-city-scheduling/
     *
     * 贪心:
     * 第一只老鼠吃掉 reward1[i]-reward2[i] 差值最大的k个元素
     * 时间复杂度 O(n*lgn) 空间复杂度 O(n)
     *
     * 同样是贪心，因为我们只需要最大的 k 个。所以使用 size 为 k 的小根堆更适合
     * 时间复杂度 O(n*lgk) 空间复杂度 O(k)
     * 以后有时间写一写
     */
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        Integer[] array = IntStream.range(0, reward1.length).boxed().toArray(Integer[]::new);
        // reward1[i] - reward2[i] 大到小排序
        Arrays.sort(array, Comparator.comparingInt(e -> reward2[e]-reward1[e]));

        int ans = 0;
        for (int i = 0; i < array.length; i++) {
            int index = array[i];
            ans += i < k ? reward1[index] : reward2[index];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
