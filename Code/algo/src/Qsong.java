import java.util.Scanner;

public class Qsong {
    private int L, L1, L2, S1, S2;

    public int getSolv() {
        long ans = 0;
        int i = L/L1;
        if (i > S1) i = S1;
        for (;i >= 0;i--) {
            int tail = L - L1*i;
            int j = tail/L2;
            if (j > S2 || tail%L2 != 0) {}
            else {
                ans+=C(i, S1) * C(j, S2);
            }
        }
        int res = (int) ans%1000000007;
        return res;
    }

    private long C(int n, int m) {
        long res = 1;
        for (int i = m - n + 1; i <= m; i++) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Qsong qs = new Qsong();
        qs.L  = sc.nextInt();
        qs.L1 = sc.nextInt();
        qs.S1 = sc.nextInt();
        qs.L2 = sc.nextInt();
        qs.S2 = sc.nextInt();
        System.out.print(qs.getSolv());
    }
}
