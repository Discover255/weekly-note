package algs.emma.learn;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;//定义红和黑

    private Node root;
    private class Node {
        private Key   key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key key, Value val, boolean color, int size) {
            this.key   = key;
            this.val   = val;
            this.color = color;
            this.size  = size;
        }
    }

    public RedBlackBST() {
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;//为什么不用size实现？
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
//        if (x == null) return null;
//        int cmp = key.compareTo(x.key);
//        if (cmp > 0) return get(x.right, key);
//        else if (cmp < 0) return get(x.left, key);
//        else return x.val;
        //我这里使用之前的递归实现，这次的源码是用循环实现的
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp > 0) x = x.right;
            else if (cmp < 0) x = x.left;
            else            return x.val;
        }
        return null;
    }

    public boolean contains(Key key) {
//        if (isEmpty()) throw new NoSuchElementException("called contains() with empty symboll table");
//        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        //这个检测在get()中已经做过了
        return get(key) != null;
    }
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED, 1);

        //常规插入的过程
        int cmp = key.compareTo(h.key);//我一直想知道这里用int类型不会浪费吗，用一个字节就够了啊
        if      (cmp < 0) h.left  = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else                h.val = val;

        //修复红黑二叉树
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.size = size(h.right) + size(h.left) + 1;

        return h;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;//这两波操作没看懂，为什么要手动切换root的颜色？
    }

    private Node deleteMin(Node h) {
        if (h.left == null) return null;

        if (!isRed(h.left) && !isRed(h.right)) h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);//标记一下，待会回来看
    }

    //deleteMax()大同小异我就照抄了
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    //这中间还有一个delete()函数，我想写完后面的基础函数之后再来实现

    private Node rotateRight(Node h) {
        Node x = h.left;
        //assert (h != null) && (isRed(x))
//        Node temp = new Node(x.key, x.val, h.color, size(x));
//        temp.left = x.left;
//        temp.right = h;
//        temp.right.left = x.right;
//        temp.right.right = h.right;
        //比较low的写法
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;//更新h的size
        return x;
    }

    private Node rotateLeft(Node h) {
        //assert (h != null) && (isRed(h.right))
        Node x = h.right;
        x.left = h;
        h.right = x.left;//和上一行交换顺序有没有影响？
        x.color = x.left.color;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        //这是翻转颜色的意思
        //而且根据源码来看，仅仅是作颜色的修改，不作任何其他的动作
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node moveRedLeft(Node h) {
        //isRed(h.left) && isRed(h.left.left)
//        Node x = h.left;
//        x.right = h;
//        h.left = h.left.right;
//        x.color = BLACK;
//        x.right.color = BLACK;
//        x.left.color = BLACK;
//        x.size = h.size;
//        h.size = size(h.left) + size(h.right) + 1;
//        return x;
        //可以复用前面的方法
        flipColors(h);

    }
}