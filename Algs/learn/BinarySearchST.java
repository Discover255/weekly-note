package algs.emma.learn;

//import edu.princeton.cs.algs4.*;

import org.omg.CORBA.Object;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;//干什么用的?
    private Key[] keys;
    private Value[] values;
    private int n = 0;//这是大小?

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys   = (Key[])   new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys   = tempk;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
//        if (keys == null) return true;
//        else              return false;
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null ) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) return null;
        int i = rank(key);//这里实现了对rank的复用
        if (i < n && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        int lo = 0, hi = n - 1;
        while (lo >= hi) {
            int mid = lo + (hi - lo) / 2;//为什么不用(hi + lo) / 2
            int cmp = key.compareTo(keys[mid]);//有点眼熟
            if (cmp > 0) lo = mid + 1;//为什么要-1?
            else if (cmp < 0) hi = mid - 1;
            else return mid;
        }
        //可以看出二分的查找是比较容易的,之前实现的归并比较难理解
        return lo;//hi也可以
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("argument to put() is null");
        //我觉得的步骤:找到目标位置的序号,然后把大于等于该序号的keys和values都右移,然后插入
        //我的实现
//        int lo = 0, hi = n - 1;
//        int shouldbe = rank(key) + 1;//
//        n = n + 1;
//        for (int i = n - 1; i >= shouldbe; i--) {
//            keys[i] = keys[i - 1];
//        }
//        values[shouldbe] = value;
        //我这段代码存在的问题:1. 没有实现足够的分支情况考虑,效率不够高 2. 没有resize操作,数组会溢出

    }

    //rank就是核心的函数,check要怎么实现呢?
}