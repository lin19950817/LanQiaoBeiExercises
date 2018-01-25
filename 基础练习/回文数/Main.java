import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        for (int i = 10000; i < 1000000; i++) {
            int a = i % 10, b = i / 10 % 10;
            if (i < 100000) {
                int right = a * 10 + b, left = i / 1000;
                if (right == left) {
                    int c = i / 100 % 10;
                    if (n == 2 * (a + b) + c)
                        System.out.println(i);
                }
            } else {
                int c = i / 100 % 10, right = a * 100 + b * 10 + c, left = i / 1000;
                if (right == left) {
                    if (n == 2 * (a + b + c))
                        System.out.println(i);
                }
            }
        }
    }
}
