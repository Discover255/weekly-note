package algs.emma.learn;

import edu.princeton.cs.algs4.*;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.val = value;
            this.size = size;
        }
    }
        //基本的定义

    public BST() {
    }
    //这里的初始化，啥都不干

    public boolean isEmpty() {
        return size() == 0;
    }
    //禁止了直接访问size，开了一个函数来访问
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("called get() with a null key");
        if (x == null) return null;//为什么不用empty?因为这个函数不仅仅用于root节点
        int cmp = key.compareTo(x.key);
        if     (cmp > 0) return get(x.right, key);//key比x的key大，进入右节点
        else if(cmp < 0) return get(x.left, key);
        //如果key不在树中，会return null
        else             return x.val;
    }

    public void put(Key key, Value value) {
        if (key   == null) throw new IllegalArgumentException("argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }
        // if (contains(key)) {
            //书中没有实现这个情况，看起来不需要？
        // }
        root = put(root, key, value);
        assert check();
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp > 0) x.right = put(x.right, key, val);//这是key大于x的key的情况，要进入右节点
        else if (cmp < 0) x.left  = put(x.left, key, val);//这两段递归的实现非常精妙
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);//这里有点迷，为什么不size(x)+1
        //这是第一个操作size的函数，我们也没有自动计算size的工具，这一行是每一次增加新节点，它的父节点都会执行的
        return x;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");//underflow是下溢的意思
        root = deleteMin(root);//这本书的example特别喜欢这样写
        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;//确定不是减一？可是根本没有执行删除操作啊？
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with a null key");
        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = delete(x.right, key);
        else if (cmp < 0) x.left = delete(x.left, key);
        else {
            //删除非叶结点怎么操作？
            //这段我理解不了
            if (x.left  == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;//我知道了，加一是加上节点自己的大小，所以这个是用来重新计算节点大小的
        return x;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }
    //返回最大的小于等于key的key
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        Node tmp = floor(x.right, key);
        if (tmp != null) return tmp;
        else             return x;
    }
    //返回最小的大于等于key的key
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;//因为此时x.key不存在
        else           return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;//这种情况是相当有限的
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return floor(x.right, key);
        if (cmp == 0) return x;
        Node tmp = floor(x.left, key);
        if (tmp == null) return x;
        else             return tmp;
    }

    public Key select(int k) {
        if (k < 0 || k > size()) {
            throw new IllegalArgumentException("called select() with invalid argument" + k);
        }
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);//这个就是将node下一分为2
        if      (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);//减一是因为要从0开始数，如果不减一，第一个就是1
        else            return x;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (key == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return size(x.left) + rank(x.right, key) + 1;
        else if (cmp < 0) return rank(x.left, key);
        else return size(x.left);
    }
    //这里要实现keys的迭代器方法

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;//queue是iterator?
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmphi = hi.compareTo(x.key);
        int cmplo = lo.compareTo(x.key);
        if (cmphi < 0) keys(x.left, queue, lo, hi);
        if (cmphi >= 0 && cmplo <= 0) queue.enqueue(x.key);//这里不懂，java迭代器不明白
        if (cmplo > 0) keys(x.right, queue, lo, hi);
    }


    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;//为什么要返回-1？如果root是一个叶结点，那么高度是0？难道不应该是1
        return 1 + Math.max(height(x.left), height(x.right));//此处遍历了整个树
    }



    private boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");//子树计数不一致？
        if (!isRankConsistent()) StdOut.println("Rank not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;//检查完毕
        if (min != null && x.key.compareTo(min) <= 0) return false;//=0的情况也是不合法的，因为只能允许有一个key存在
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (size(x) != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isRankConsistent() {
        //要实现rank()和select()才行
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;//检查索引
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

}