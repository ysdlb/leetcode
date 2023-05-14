//给定三个整数 x 、 y 和 bound ，返回 值小于或等于 bound 的所有 强整数 组成的列表 。 
//
// 如果某一整数可以表示为 xⁱ + yʲ ，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个 强整数 。 
//
// 你可以按 任何顺序 返回答案。在你的回答中，每个值 最多 出现一次。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2, y = 3, bound = 10
//输出：[2,3,4,5,7,9,10]
//解释： 
//2 = 2⁰ + 3⁰
//3 = 2¹ + 3⁰
//4 = 2⁰ + 3¹
//5 = 2¹ + 3¹
//7 = 2² + 3¹
//9 = 2³ + 3⁰
//10 = 2⁰ + 3² 
//
// 示例 2： 
//
// 
//输入：x = 3, y = 5, bound = 15
//输出：[2,4,6,8,10,14]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= x, y <= 100 
// 0 <= bound <= 10⁶ 
// 
//
// Related Topics 哈希表 数学 👍 103 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution970 {
    /* 970.强整数: https://leetcode.cn/problems/powerful-integers/
     *
     * 思路: 暴力枚举 i,j
     * 时间复杂度 O(log(bound))^2
     */
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        int iEnd = bound, jEnd = bound;
        // 1 自然数幂, 其值都为 1
        if (x == 1) iEnd = 1;
        if (y == 1) jEnd = 1;

        List<Integer> ans = new ArrayList<>();
        for (int i = 0, a = 1; i < iEnd && a < bound; i++) {
            if (i > 0) a *= x;
            for (int j = 0, b = 1; j < jEnd && b < bound; j++) {
                if (j > 0) b *= y;
                int sum = a+b;
                if (sum <= bound) {
                    ans.add(sum);
                } else {
                    break;
                }
            }
        }
        return ans.stream().distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Solution970 so = new Solution970();
        List<Integer> integers = so.powerfulIntegers(2, 3, 10);
        System.out.println(integers);
        integers = so.powerfulIntegers(2, 3, 2);
        System.out.println(integers);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
