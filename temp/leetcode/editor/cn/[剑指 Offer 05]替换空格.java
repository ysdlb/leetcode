//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。 
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// Related Topics 字符串 👍 198 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer5 {
    /**
     * 每次 insert 会 移动后面的所有元素, 一次的时间复杂度是 O(n)
     * 总时间复杂度是 O(n^2)
     *
     * 所以 时间复杂度为 O(n) 应采用提前计算空间双指针的模式
     * 如果原字符数组的长度足够, 可以从后往前复制, 这样空间复杂度也能做到 O(1)
     */
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        int numSpace = 0;
        for (char c: chars) {
            if (c == ' ') numSpace++;
        }

        char[] res = new char[chars.length + 2*numSpace];
        for (int i = 0, j = 0; i < chars.length;) {
            if (chars[i] != ' ') {
                res[j++] = chars[i++];
            } else {
                res[j] = '%';
                res[j+1] = '2';
                res[j+2] = '0';
                i++;
                j += 3;
            }
        }
        return new String(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
