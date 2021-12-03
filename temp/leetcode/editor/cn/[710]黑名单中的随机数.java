//给定一个包含 [0，n) 中不重复整数的黑名单 blacklist ，写一个函数从 [0, n) 中返回一个不在 blacklist 中的随机整数。 
//
// 对它进行优化使其尽量少调用系统方法 Math.random() 。 
//
// 提示: 
//
// 
// 1 <= n <= 1000000000 
// 0 <= blacklist.length < min(100000, N) 
// [0, n) 不包含 n ，详细参见 interval notation 。 
// 
//
// 示例 1： 
//
// 
//输入：
//["Solution","pick","pick","pick"]
//[[1,[]],[],[],[]]
//输出：[null,0,0,0]
// 
//
// 示例 2： 
//
// 
//输入：
//["Solution","pick","pick","pick"]
//[[2,[]],[],[],[]]
//输出：[null,1,1,1]
// 
//
// 示例 3： 
//
// 
//输入：
//["Solution","pick","pick","pick"]
//[[3,[1]],[],[],[]]
//输出：[null,0,0,2]
// 
//
// 示例 4： 
//
// 
//输入： 
//["Solution","pick","pick","pick"]
//[[4,[2]],[],[],[]]
//输出：[null,1,3,1]
// 
//
// 输入语法说明： 
//
// 输入是两个列表：调用成员函数名和调用的参数。Solution的构造函数有两个参数，n 和黑名单 blacklist。pick 没有参数，输入参数是一个列表
//，即使参数为空，也会输入一个 [] 空列表。 
// Related Topics 哈希表 数学 二分查找 排序 随机化 👍 82 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * 设 boundary = n - blacklist.size()
 * 则对 [0, boundary) 区间内每一个在黑名单内的数, 映射到 [boundary, n) 区间内非名单上的数上
 *
 * pick 时, 在 [0, boundary) random 一个数
 * 如果该数不在映射表里, 直接返回; 否则, 返回映射之后的数
 */
class Solution710 {

    private final Map<Integer, Integer> map;
    private final Random random;
    private final int boundary;

    public Solution710(int n, int[] blacklist) {
        map = new HashMap<>();

        int boundary = n - blacklist.length;
        random = new Random(System.currentTimeMillis());
        this.boundary = boundary;

        if (blacklist.length == 0) return;

        // 这里假设 blacklist 从小到大排列
        for (int l = 0, r = blacklist.length - 1; l < blacklist.length && blacklist[l] < boundary; ) {
            // 从后开始跳过黑名单中的数
            // 只要 [boundary, n) 不全是黑名单, 这里 r 就肯定不用减到 -1 ( blacklist[l] < boundary 保证)
            while (blacklist[r] == n - 1) {
                r--;
                n--;
            }
            // 建立黑名单数与非黑名单数的映射关系
            // 如果黑名单全在 [0, boundary) 需要防止 l 超出右边界 ( l < blacklist.length 保证 )
            map.put(blacklist[l], n - 1);
            l++;
            n--;
        }
    }
    
    public int pick() {
        int i = random.nextInt(boundary);
        Integer res = map.get(i);
        return res == null ? i : res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
//leetcode submit region end(Prohibit modification and deletion)

class SolutionNoOrder710 {

    private final Map<Integer, Integer> map;
    private final Random random;
    private final int boundary;

    public SolutionNoOrder710(int n, int[] blacklist) {
        map = new HashMap<>();

        random = new Random(System.currentTimeMillis());

        boundary = n - blacklist.length;

        if (blacklist.length == 0) return;

        // 这里假设 blacklist 无序
        Set<Integer> blackSet = new HashSet<>();
        for (int black : blacklist) {
            if (black >= boundary) blackSet.add(black);
        }

        int substitute = n - 1;
        for (int black : blacklist) {
            if (black < boundary) {
                while (blackSet.contains(substitute))
                    substitute--;
                map.put(black, substitute);
                // 这个替代者用过了换下一个
                substitute--;
            }
        }
    }

    public int pick() {
        int i = random.nextInt(boundary);
        Integer res = map.get(i);
        return res == null ? i : res;
    }
}
