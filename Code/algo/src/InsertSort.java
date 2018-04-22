import java.util.Collection;

public class InsertSort {
    public <E extends Comparable<E>> E[] sort(E[] x) {
        int n = x.length;
        if (n <= 1) return x;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int cmp = x[i].compareTo(x[j]);
                if (cmp <= 0) {
                    E tmp = x[i];
                    for (int k = i; k > j; k--) {
                        x[k] = x[k - 1];
                    }
                    x[j] = tmp;
                    break;
                }
            }
        }
        return x;
    }

//    class mytest<E> implements Collection<E> {
//
//    }

    public static void main(String[] args) {
        InsertSort insert = new InsertSort();
        Integer[] test = {3, 2, 1};
        Integer[] res  = insert.sort(test);
        for (int i = 0; i < res.length; i++) {
            System.out.println((int)res[i]);
        }
        int[] ta = new int[]{0, 0, };
        System.out.println(ta);
    }
}
