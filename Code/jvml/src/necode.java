import java.util.ArrayList;
import java.util.TreeMap;

public class necode {
    public static void main(String[] args) {

    }
    public int countCars(int[][] carArray) {
        int ans = 0, carNum = carArray.length/2;
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int j = 0; j < carNum; j++) {
            map.put(carArray[j][0], carArray[j][1]);
        }
        for (int i = 1; i <= 12; i++) {
            int k = 0;
            for (int j = 0; j < i; j++) {

                if (map.get())
            }
            ans = Math.max(ans, k);
        }
        return ans; // 返回计算结果
    }
}
