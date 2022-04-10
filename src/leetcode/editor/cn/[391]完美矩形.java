//给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是
// (xi, yi) ，右上顶点是 (ai, bi) 。 
//
// 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。 
// 
//
// 示例 1： 
//
// 
//输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
//输出：true
//解释：5 个矩形一起可以精确地覆盖一个矩形区域。 
// 
//
// 示例 2： 
//
// 
//输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
//输出：false
//解释：两个矩形之间有间隔，无法覆盖成一个矩形。 
//
// 示例 3： 
//
// 
//输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
//输出：false
//解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。 
//
// 
//
// 提示： 
//
// 
// 1 <= rectangles.length <= 2 * 10⁴ 
// rectangles[i].length == 4 
// -10⁵ <= xi, yi, ai, bi <= 10⁵ 
// 
// Related Topics 数组 扫描线 👍 212 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution391 {
    /**
     * 完美矩形, 从面积和顶点两个方面来判断
     * 1. 最左下, 最左上, 最右下, 最右上 的四个点只出现一次, 其它点成对出现 (准确的说是出现 2 次或者 4 次)
     * 2. 四个点围成的矩形面积 = 小矩形的面积之和
     *
     * 可以使用消除点的方式, 完美矩形最后肯定会剩下 4 个点
     */
    public boolean isRectangleCover(int[][] rectangles) {
        Set<Point> set = new HashSet<>();
        int area = 0;
        for (int[] rec: rectangles) {
            int x1 = rec[0], y1 = rec[1], x2 = rec[2], y2 = rec[3];
            area += (x2 - x1) * (y2 - y1);
            Point p;
            // 取差集
            boolean ignore;
            ignore = set.contains(p = Point.of(x1, y1)) ? set.remove(p) : set.add(p);
            ignore = set.contains(p = Point.of(x1, y2)) ? set.remove(p) : set.add(p);
            ignore = set.contains(p = Point.of(x2, y1)) ? set.remove(p) : set.add(p);
            ignore = set.contains(p = Point.of(x2, y2)) ? set.remove(p) : set.add(p);
        }

        if (set.size() != 4)
            return false;

        // 完美矩形的左下角和右上角
        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
        for (Point p: set) {
            x1 = Math.min(x1, p.x);
            y1 = Math.min(y1, p.y);

            x2 = Math.max(x2, p.x);
            y2 = Math.max(y2, p.y);
        }
        return (y2 - y1) * (x2 - x1) == area;
    }

    private static class Point {
        int x;
        int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point point) {
                return point.x == x && point.y == y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return x ^ y;
        }

        public static Point of(int x, int y) {
            return new Point(x, y);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
