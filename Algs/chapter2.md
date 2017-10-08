# 排序
## 初级排序算法
### 选择排序
#### 过程
1. 找到数组中最小的数
2. 将它与第一个数交换位置
3. 在剩下元素中寻找最小元素
4. 与第二个数交换位置
5. 循环直到整个数组排序
#### 特点
* 对于长度为N的数组，大约需要N^2/2次比较，N次交换
* 运行时间和输入无关
* 数据移动是最少的，为N次
### 插入排序
#### 过程
将每一张牌插入到左边的有序组合中，同时还要考虑右移以腾出空间
#### 特点
* 平均情况做N^2/4次比较和N^2/4次交换，最坏情况做N^2/2比较和N^2/2次交换
* 部分有序数组效率高
    * 数组中每个元素距离它的最终位置不远
    * 有序大数组接一个小数组
    * 数组只有几个元素位置不正确
### 希尔排序
#### 过程
1. 比较h距离的元素，使间隔h距离的元素有序
2. 减小h，再次排序
3. 直到h=1，比较相邻元素
#### 特点
* 冲破二次时间屏障的第一批算法之一
* 对于中等大小的数组，运行时间可接受。代码量很小，且不需要额外的内存空间。如果需要解决一个排序问题而又没有系统排序函数可用，可以先用希尔排序，然后再考虑是否值得将它替换为更复杂的算法。
## 归并排序
> 递归实现的归并排序是算法设计中分治思想的典型应用。
### 原地归并
#### 过程
1. 复制原数组
2. 分成两个数组
3. 对两个数组分别排序
4. 对两数组第一位进行比较，取较前的
5. 下一次比较的是，未取的数组的第一位，已取的数组的第二位
6. 以此类推
7. 如果界限不在中间，则小的数组比较完之后，大的数组的尾部直接赋给结果的尾部
![原地归并](原地归并.png)
### 自顶向下的归并排序
```Java
public class Merge
{
    private static Comparable[] aux;
    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {   //将数组a[lo...hi]排序
        if(hi <= lo) return;
        int mid = lo +(hi - lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }
}
```
#### 性质
1. 进行1/2NlgN到NlgN次比较
2. 最多需要访问6NlgN次数组
3. 由1和2可知归并排序所需的时间与NlgN成正比
#### 继续缩短时间
1. 对小规模子数组使用插入排序
2. 测试数组是否已经有序
3. 不将元素复制到辅助数组
## 快速排序
### 特点
1. 实现简单
2. 原地排序，只需要一个很小的辅助栈
3. 将长度为N的数组排序所需的时间和NlgN
4. 主要缺点是十分脆弱，在实现时需要非常小心才能避免低劣的性能