//在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。注意:
//
// 
// 北方向 是y轴的正方向。 
// 南方向 是y轴的负方向。 
// 东方向 是x轴的正方向。 
// 西方向 是x轴的负方向。 
// 
//
// 机器人可以接受下列三条指令之一： 
//
// 
// "G"：直走 1 个单位 
// "L"：左转 90 度 
// "R"：右转 90 度 
// 
//
// 机器人按顺序执行指令 instructions，并一直重复它们。 
//
// 只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：instructions = "GGLLGG"
//输出：true
//解释：机器人最初在(0,0)处，面向北方。
//“G”:移动一步。位置:(0,1)方向:北。
//“G”:移动一步。位置:(0,2).方向:北。
//“L”:逆时针旋转90度。位置:(0,2).方向:西。
//“L”:逆时针旋转90度。位置:(0,2)方向:南。
//“G”:移动一步。位置:(0,1)方向:南。
//“G”:移动一步。位置:(0,0)方向:南。
//重复指令，机器人进入循环:(0,0)——>(0,1)——>(0,2)——>(0,1)——>(0,0)。
//在此基础上，我们返回true。
// 
//
// 示例 2： 
//
// 
//输入：instructions = "GG"
//输出：false
//解释：机器人最初在(0,0)处，面向北方。
//“G”:移动一步。位置:(0,1)方向:北。
//“G”:移动一步。位置:(0,2).方向:北。
//重复这些指示，继续朝北前进，不会进入循环。
//在此基础上，返回false。
// 
//
// 示例 3： 
//
// 
//输入：instructions = "GL"
//输出：true
//解释：机器人最初在(0,0)处，面向北方。
//“G”:移动一步。位置:(0,1)方向:北。
//“L”:逆时针旋转90度。位置:(0,1).方向:西。
//“G”:移动一步。位置:(- 1,1)方向:西。
//“L”:逆时针旋转90度。位置:(- 1,1)方向:南。
//“G”:移动一步。位置:(- 1,0)方向:南。
//“L”:逆时针旋转90度。位置:(- 1,0)方向:东方。
//“G”:移动一步。位置:(0,0)方向:东方。
//“L”:逆时针旋转90度。位置:(0,0)方向:北。
//重复指令，机器人进入循环:(0,0)——>(0,1)——>(- 1,1)——>(- 1,0)——>(0,0)。
//在此基础上，我们返回true。 
//
// 
//
// 提示： 
//
// 
// 1 <= instructions.length <= 100 
// instructions[i] 仅包含 'G', 'L', 'R' 
// 
//
// Related Topics 数学 字符串 模拟 👍 150 👎 0


import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1041 {

    /* 困于环中的机器人: https://leetcode.cn/problems/robot-bounded-in-circle/
     *
     * "GGLLG" 这样就无法走出，返回 true
     *   因为两个一组将返回起点: GGLLG GGLLG
     * "GGLLGRR" 这样就可以走出，每一组将向前推荐 1 个单位
     *
     * 没有思路..
     *
     * 以一组作为一个单位, 每次结束后要么方向不变，要么方向改变，每次变动都是固定的
     *   我们总可以确保在 0/4/2 组后方向和初始状态一致 (第一次和初始方向相同的时候就是最后一组)
     *   若此时回到原点，则可认为机器人永远无法离开
     *
     * 也不用棋盘，仅需要两组值:
     *   一个方向向量 (0,1) L(1,0) R(-1,0) (0,-1)
     *   一个二维坐标: (x,y)
     *
     * 方向转换怎么计算:
     *   R:
     *   A = ( 0,1)
     *       (-1,0)
     *     (0,1)*A = (-1,0)
     *     (-1,0)*A = (0,-1)
     *     (0,-1)*A = (1,0)
     *   L:
     *   A = (0,-1)
     *       (1, 0)
     *
     */
    public boolean isRobotBounded(String instructions) {
        char[] chars = instructions.toCharArray();
        int x = 0, y = 0;
        int[] p = new int[]{0,1};

        do {
            for (char c: chars) {
                if (c == 'G') {
                    x += p[0];
                    y += p[1];
                } else {
                    rotate(p, c);
                }
            }
        } while (p[0] != 0 || p[1] != 1);

        return x == 0 && y == 0;
    }

    private void rotate(int[] p, char c) {
        int i = p[0], j = p[1];
        if (c == 'L') {
            p[0] = j;
            p[1] = -i;
        } else {
            p[0] = -j;
            p[1] = i;
        }
    }

    public static void main(String[] args) {
        String instructions = "GGLLGRR";
        Solution1041 solu = new Solution1041();
        boolean r = solu.isRobotBounded(instructions);
        System.out.println(r);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
