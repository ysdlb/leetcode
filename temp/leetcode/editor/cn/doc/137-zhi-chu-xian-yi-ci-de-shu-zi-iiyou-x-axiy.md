按有限状态机写的

用两个数a,b来表示状态。 列出写出状态表：

S(i) ——> S(i+1)
a b x ——> a b
0 0 1 ——> 0 1
0 1 1 ——> 1 0
1 0 1 ——> 0 0
0 0 0 ——> 0 0
0 1 0 ——> 0 1
1 0 0 ——> 1 0
![未命名文件(2).jpg](https://pic.leetcode-cn.com/1617795576-yZxLQt-%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6\(2\).jpg)


写出状态转移方程
a = (a & ~b & ~x) + (~a & b & x);
b = ~a& (b ^ x); (注意 a, b 是同时更新,所以用temp暂存更新前的a)

希望能看懂。。。

```
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int a =0;
        int b =0;
        int temp = 0;
        for(int i = 0; i < nums.size(); i++){
            temp = a;
            a = (a & ~b & ~nums[i]) + (~a & b & nums[i]);
            b = ~temp & (b ^ nums[i]);
        }
        return b; 
    }
};
```

