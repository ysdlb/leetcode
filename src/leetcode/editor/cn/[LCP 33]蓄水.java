//给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 `i` 个水缸配备的水桶容量记作 `bucket[i]`。小扣有以下两种操作：
//- 升级水桶：选择任意一个水桶，使其容量增加为 `bucket[i]+1`
//- 蓄水：将全部水桶接满水，倒入各自对应的水缸
//
//每个水缸对应最低蓄水量记作 `vat[i]`，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。
//
//注意：实际蓄水量 **达到或超过** 最低蓄水量，即完成蓄水要求。
//
//**示例 1：**
//
//> 输入：`bucket = [1,3], vat = [6,8]`
//>
//> 输出：`4`
//>
//> 解释：
//> 第 1 次操作升级 bucket[0]；
//> 第 2 ~ 4 次操作均选择蓄水，即可完成蓄水要求。
//> ![vat1.gif](https://pic.leetcode-cn.com/1616122992-RkDxoL-vat1.gif)
//
//**示例 2：**
//
//> 输入：`bucket = [9,0,1], vat = [0,2,2]`
//>
//> 输出：`3`
//>
//> 解释：
//> 第 1 次操作均选择升级 bucket[1]
//> 第 2~3 次操作选择蓄水，即可完成蓄水要求。
//
//**提示：**
//- `1 <= bucket.length == vat.length <= 100`
//- `0 <= bucket[i], vat[i] <= 10^4`
//
// Related Topics 贪心 数组 堆（优先队列） 👍 164 👎 0


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionLCP33 {
    /* LCP 33.蓄水: https://leetcode.cn/problems/o8SXZn/
     *
     * 对于每个水桶的容量 a 和水缸的蓄水要求 b
     * 至少需要 (b+(a-1))/a 次操作才可以完成要求, 数学上需要 b/a 次
     *
     * 我们可以选择升级 x 次水桶, 此时需要 f(x) = x + b/(a+x) 次
     * f(x) 先降低后增大, 求每一对 a,b 所需的最小次数; 然后找出全局的最大值
     *
     * 或许特殊找二分时间复杂度更低, 但它太难写了
     *
     * 很容易出错的几个点:
     *  1. val[i] 为 0, 水缸需求为 0, 不需要考虑桶的容量
     *  2. 倒水的次数求最大值, 但增加的桶的容量次数是累计的
     * end Wrong
     *
     * 上面思路错了:
     * 其实是一个木桶，短板需要的次数最多，我们每次考虑是否升级短板，最终使倒水次数+升级次数最少
     * 这个趋势并不是先下降在上升，所以就先尝试 1000 次
     */
    public int storeWater(int[] bucket, int[] vat) {
        // <need, index>
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(Comparator.<int[]>comparingInt(e -> {
            // 缓存比较值
            if (e[0] != -1) return e[0];
            e[0] = need(bucket[e[1]], vat[e[1]]);
            return e[0];
        }).reversed());

        for (int i = 0; i < bucket.length; i++)
            maxHeap.offer(new int[]{-1, i});
        int min = Integer.MAX_VALUE;
        // 根本不知道什么时候是最小值
        // 枚举所有的蓄水次数, 每次增加短板的蓄水次数
        for (int x = 0, pre; x < min;) {
            int[] bad = maxHeap.poll();
            int index = bad[1];
            pre = (bad[0] == -1) ?  this.need(bucket[index], vat[index]) : bad[0];
            min = Math.min(min, pre+x);

            bucket[index]++;
            x++;
            maxHeap.offer(new int[]{-1, index});
        }
        return min;
    }

    private int need(int a, int b) {
        if (b == 0) return 0;
        if (a == 0) return Integer.MAX_VALUE/2;
        return (b+a-1)/a;
    }

    public int storeWaterWrong(int[] bucket, int[] vat) {
        int sum = 0;
        int max = 0;
        int minus = 0;
        for (int i = 0; i < bucket.length; i++) {
            int x = 0;
            int a = bucket[i], b = vat[i];
            if (b == 0) continue;

            if (a == 0) {
                a++;
                x++;
            }
            int pre = (b+a-1)/a + x;
            int now;
            while ((now = (b+a)/(a+1)+x+1) <= pre && now > max) {
                pre = now;
                a++;
                x++;
            }

            if (pre > max) {
                max = pre;
                minus = x;
            }
            sum += x;
        }
        return max + sum - minus;
    }

    public static void main(String[] args) {
        SolutionLCP33 so = new SolutionLCP33();
        int[] buckets = new int[]{9988,5017,5130,2445,9896,9151,3625,7801,608,3283,1386,979,5209,4182,8234,9870,8714,6435,3800,956,4006,5620,7474,1205,6993,3320,1201,7593,905,3816,4522,4560,8027,8219,6686,3779,2141,1240,6504,6612,6921,7329,8145,5745,7652,4340,7933,6246,5157,9447,107,9665,3653,2978,9832,4945,4312,2199,449,8432,3230,8163,800,6547,1110,1194,9384,632,3275,1229,7230,8643,7613,8256,5043,1288,3088,8997,4554,4755,7433,8146,9722,3469,8863,5831,7816,5058,4316,7946,8402,975,2450,4958,9811,9336,21,9309,8999,56};
        int[] val = new int[]{9991,6973,7192,9876,9910,9549,3700,8814,1308,9981,9234,7292,7732,8458,8441,9939,9621,7285,7452,2718,6589,7555,8788,3202,7832,4781,8798,9299,2112,9963,8755,7240,9217,8587,6782,9703,8954,3759,6907,7218,7333,8020,8323,5750,9510,8571,8664,8510,9363,9741,8643,9825,4227,8530,9961,8511,8949,7486,9086,9690,5316,9581,9314,8817,7234,8998,9485,5394,7365,1501,7984,9802,9778,8314,7482,7117,5117,9609,8732,9728,9330,8800,9775,6210,8966,7700,8802,7607,8950,9730,9855,1231,5228,5329,9982,9532,3230,9951,9034,8299};
        int i = so.storeWater(buckets, val);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
