import java.util.Scanner;

public class tcbs {
    public int getSolv(int n, int m) {
        if (n%(2*m) != 0) return 0;
        if (n == 0) return 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if ((int)Math.ceil(i/m)%2 == 0) {//除m向上取整是偶数
                ans -= i+1;
            } else {
                ans += i+1;
            }
        }
        return ans;
    }

    private int[] genInts(int n) {
        int[] serial = new int[n];
        for (int i = 0; i < n; i++) {
            serial[i] = i+1;
        }
        return serial;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        tcbs res = new tcbs();
        System.out.println(res.getSolv(n, m));
    }
}
