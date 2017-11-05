package algs.emma.learn;
import edu.princeton.cs.algs4.*;
public class Parentheses {
    private static final char  LEFT1 = '(';
    private static final char  LEFT2 = '[';
    private static final char  LEFT3 = '{';
    private static final char RIGHT1 = ')';
    private static final char RIGHT2 = ']';
    private static final char RIGHT3 = '}';

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == LEFT1) stack.push(LEFT1);
            if (s.charAt(i) == LEFT2) stack.push(LEFT2);
            if (s.charAt(i) == LEFT3) stack.push(LEFT3);

            if (s.charAt(i) == RIGHT1) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != LEFT1) return false;
            }

            else if (s.charAt(i) == RIGHT2) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != LEFT2) return false;
            }

            else if (s.charAt(i) == RIGHT3) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != LEFT3) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = StdIn.readString();
        StdOut.println(isBalanced(s));
    }
}