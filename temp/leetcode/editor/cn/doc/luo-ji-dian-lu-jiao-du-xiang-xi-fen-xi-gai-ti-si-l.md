## 数值解法

```python []
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        return (3*sum(set(nums))-sum(nums))//2
```

- 这种做法感觉不太可取，有点取巧，使用`set()`感觉优化全交给编译器去做了。但可以作为一种思路。

## 逻辑位运算
### 思路分析

- 第一时间应该想到的是找到一种逻辑操作，可以满足 *1*1*1=0* 且 *0*1=1*0=1* ，其中 *** 为这种新逻辑操作符。根据这个，我们可以想到
  - 出现0次为0，出现1次为1，出现2次的值无所谓，出现3次就又回到0，也就是说，我们一共需要记录3种状态：0次，1次，2次，**之后次数都是这三种状态的循环。其实这也就是一个模三运算。**
  - 记录两个状态需要的是一位二进制0/1，那么记录三种状态需要的是至少两位二进制，可以是00, 01, 10, 11，这里我们只需要选其中**任意三个状态即可，例如：00，01，10，分别代表0次1次2次。**
  - 用00代表0次，01代表出现1次是因为刚好对应数字原本那位上0代表0次，1代表1次，**这样可以方便写程序**，不这么选也可以，但最后你自己要有个映射规则。至于2次的编码无所谓，10和11都可以，反正最后答案也不要它，只是个中间状态，辅助我们计算的。
  - 那么对于输入数字的每一位二进制位，都可以用这三种状态表示。如果再输入一个数字，对于每一位上，我们的操作可以化为：
    - 新输入的是0（即00），三种状态都维持不变，![00\rightarrow00,01\rightarrow01,10\rightarrow10 ](./p__00rightarrow00,01rightarrow01,10rightarrow10_.png) 
    - 新输入的是1（即01），![00\rightarrow01,01\rightarrow10,10\rightarrow00 ](./p__00rightarrow01,01rightarrow10,10rightarrow00_.png) 


### 求逻辑表达式

- 设当前状态为*XY*，输入为*Z*，那么我们可以分别为*X*和*Y*列出真值表

  |  #   | *XY* | *Z*  | *X_{new}* | *Y_{new}* |
  | :--: | :----: | :--: | :-------: | :-------: |
  |  1   |   00   |  0   |     0     |     0     |
  |  2   |   01   |  0   |     0     |     1     |
  |  3   |   10   |  0   |     1     |     0     |
  |  4   |   00   |  1   |     0     |     1     |
  |  5   |   01   |  1   |     1     |     0     |
  |  6   |   10   |  1   |     0     |     0     |

  

- 对于*Y*，转化为逻辑表达式就是（取所有*Y_{new}=1*的行的*X,Y,Z*的最小项，然后*OR*起来）

![Y_{new}=\overline{X}\cdot\overline{Y}\cdotZ+\overline{X}\cdotY\cdot\overline{Z}​ ](./p___Y_{new}=overline{X}cdotoverline{Y}cdot_Z+overline{X}cdot_Ycdotoverline{Z}​__.png) 

  化简完就是
  ![Y_{new}=\overline{X}\cdot(Y\oplusZ) ](./p_____Y_{new}=overline{X}cdot_Yoplus_Z_____.png) 
  

- 同理也可以得出*X*的逻辑表达式，但是这里有一个tricky的地方，不用再去求新的表达式。我们先更新完*Y*，然后把*Y_{new}*放到逻辑表中替换原来*Y*的值形成新的逻辑表，这个新的逻辑表对于*X*来说是跟求*Y*的时候的逻辑表是同构的。
- 同构这里很多人不理解，我补充一段，理解的人可以跳过此段。大家可以按照上一段所述把新的逻辑表（真值表）画出来，*XY*那一列的值现在就变成了*XY_{new}*，然后你再交换一下位置变成*Y_{new}X*，此时你对比一下现在的表和上面我画的表，每一行都是可以一一对应的，和原表完全相同。说出来有点乱，大家自己画出来看看吧
- 因为上述说的同构，所以先更新*Y*以后，拿*Y_{new}*去更新*X*时是可以直接套用刚才求*Y_{new}*的表达式的，也就是

![X_{new}=\overline{Y_{new}}\cdot(X\oplusZ) ](./p___X_{new}=overline{Y_{new}}cdot_Xoplus_Z___.png) 

- 这样就算做完了，把所有数以依次输入，然后不断更新这两个状态，最终，出现3次的位都成0（也就是00），出现1次的位都成了1（也就是01）。我们最后直接返回状态*Y*就是要的答案。（另外辅助验证，最后*X*应该为全0，因为最后所有位的状态要么是00，即出现3次的数的位，要么是01，即出现1次的数的位）


### 代码

- 这个写法已经无数人写过了，乍一看可能完全不知道为什么，只有了解背后的逻辑表达式才能明白。


```python []
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        X,Y=0,0
        for Z in nums:
            Y=Y^Z & ~X
            X=X^Z & ~Y
        return Y
```

- 而这个写法是上述介绍的直接用了*Y_{new}*去计算*X_{new}*，可能有人这里不太明白，其实我们也可以naive地推出用旧*Y*去计算*X_{new}*的表达式：
![X_{new}=X\cdot\overline{Y}\cdot\overline{Z}+\overline{X}\cdotY\cdotZ ](./p___X_{new}=Xcdotoverline{Y}cdotoverline{Z}+overline{X}cdot_Ycdot_Z__.png) 

使用这个表达式，我们可以写出以下程序，它和上面的代码是等价的：
```python []
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        X,Y=0,0
        for Z in nums:
            Y_new=Y^Z & ~X
            X=(X&~Y&~Z)|(~X&Y&Z)
            Y=Y_new
        return Y
```

### 推广

其实有了这个解法，所有关于数值状态转移的题都可以用类似的解法，列出真值表，求出逻辑表达式，即可很容易写出程序。

### 补充

有人问

![X_{new}=X\cdot\overline{Y}\cdot\overline{Z}+\overline{X}\cdotY\cdotZ ](./p___X_{new}=Xcdotoverline{Y}cdotoverline{Z}+overline{X}cdot_Ycdot_Z__.png) 

这个还能化简吗？是可以的，但没必要，下面是过程：

![X_{new}=X\cdot\overline{Y}\cdot\overline{Z}+\overline{X}\cdotY\cdotZ ](./p___X_{new}=Xcdotoverline{Y}cdotoverline{Z}+overline{X}cdot_Ycdot_Z__.png) 
![=X\cdot\overline{Y}\cdot\overline{Y\oplusZ}+\overline{X}\cdotY\cdot\overline{Y\oplusZ} ](./p___=Xcdot_overline{Y}_cdot_overline{Yoplus_Z}+_overline{X}cdot_Ycdotoverline{Yoplus_Z}___.png) 
![=(X\overline{Y}+\overline{X}Y)\cdot\overline{Y\oplusZ} ](./p___=_Xoverline{Y}+overline{X}Y_cdot_overline{Yoplus_Z}__.png) 
![=(X\oplusY)\cdot\overline{Y\oplusZ} ](./p___=_Xoplus_Y_cdotoverline{Yoplus_Z}__.png) 

PS: Leetcode这个公式支持太辣鸡了。