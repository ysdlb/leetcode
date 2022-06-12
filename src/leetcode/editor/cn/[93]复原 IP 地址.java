//有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
// 
//
// 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新
//排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯 👍 932 👎 0


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution93 {
    /**
     * 经典回溯解法
     */
    public List<String> restoreIpAddresses(String s) {
        Deque<String> path = new LinkedList<>();
        List<String> ret = new ArrayList<>();
        backtrack(s, 0, path, ret);
        return ret;
    }

    private void backtrack(String s, int i, Deque<String> path, List<String> ret) {
        if (i == s.length() || path.size() == 4) {
            if (i == s.length() && path.size() == 4)
                ret.add(String.join(".", path));
            return;
        }

        for (int j = i+1; j <= s.length() && j <= i+3; j++) {
            String item = s.substring(i, j);
            int e = Integer.parseInt(item);
            // 减枝干
            if (e > 255
                    || (item.charAt(0) == '0' && item.length() > 1))
                continue;

            path.addLast(item);
            backtrack(s, j, path, ret);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution93 solution93 = new Solution93();

    }
}
//leetcode submit region end(Prohibit modification and deletion)
