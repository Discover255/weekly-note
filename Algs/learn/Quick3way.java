package algs.emma.learn;

import edu.princeton.cs.algs4.*;

public class Quick3way {

    private Quick3way() {   }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;//为什么是>=,相邻的情况行不行?
        // 相邻当然不行了,因为他们也是需要比较的
        int lt = lo;
        int gt = hi;
        int i = lt;
        Comparable t = a[lo];
        while (i <= gt) {
            int compare = a[i].compareTo(t);
            if      (compare > 0) exch(a, gt--, i);
            else if (compare < 0) exch(a, i++, lt++);
            else    i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);//为什么要加一减一?
        //递归有没有求得最后情况的技巧?
        assert isSorted(a, lo, hi);
    }

    public static boolean less(Comparable[] a, int i, int j) {
        if (a[i].compareTo(a[j]) < 0) return true;
        else return false;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) return false;
        }
        return true;
    }
}