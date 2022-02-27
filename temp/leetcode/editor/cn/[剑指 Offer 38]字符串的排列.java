//输入一个字符串，打印出该字符串中字符的所有排列。 
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 字符串 回溯 👍 492 👎 0


import java.lang.reflect.Array;
import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer38 {
    /**
     * 经典回溯法
     * 不重复的前提是保证一个坑位上一个字符只出现一次
     * 即保证在某一层递归回溯时, 填入的值不重复（只需要保证同父节点的所有可能空间不重复即可）
     */
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        List<String> list = new ArrayList<>();
        this.backTrack(chars, list, 0);
        return list.toArray(new String[0]);
    }

    /**
     * 同父同层级节点去重
     */
    public void backTrack(char[] chars, List<String> ret, int deep) {
        if (chars.length == 0) return;

        if (deep == chars.length - 1) {
            ret.add(new String(chars));
            return;
        }

        Set<Character> set = new HashSet<>();
        for (int i = deep; i < chars.length; i++) {
            if (set.contains(chars[i]))
                continue;
            set.add(chars[i]);
            // 在当前坑位填入不重复的一个值
            this.swap(chars, deep, i);
            // 继续填入下一个节点
            this.backTrack(chars, ret, deep+1);
            // 恢复现场
            this.swap(chars, deep, i);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
