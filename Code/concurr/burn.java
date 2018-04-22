import java.util.concurrent.*;

public class burn {
    private static final int ThreadNum = 8;

    public class Burn_Single implements Runnable {
        private int a = 0;
        public void run() {
            for (;;){
                a = a ^ 1;
            }
        }
    }

    public static void main(String[] args) {
        burn burner = new burn();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < ThreadNum; i++) {
            exec.execute(burner.new Burn_Single());
        }
        exec.shutdown();
    }
}