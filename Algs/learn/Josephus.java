package algs.emma.learn;
import edu.princeton.cs.algs4.*;
public class Josephus {
    public static void main(String[] args) {
        int ALL = StdIn.readInt();
        int KILL = StdIn.readInt();

        //initialize
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i <= (ALL - 1); i++) {
            queue.enqueue(i);
        }

        //kill
        int counter = 0;
        while (!queue.isEmpty()) {
            for (int i = 1; i <= (KILL - 1); i++) {
                queue.enqueue(queue.dequeue());//a nice loop!
            }
            if (counter != (ALL-1))
                StdOut.println(queue.dequeue() + "is killed");
            else
                StdOut.println(queue.dequeue() + "is alive!");
            counter++;
        }
    }
}