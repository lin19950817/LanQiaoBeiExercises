import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        System.out.print(new Main().Find(a, n, sc.nextInt()));

    }

    private int Find(int[] a, int n, int target) {
        for (int i = 0; i < n; i++) {
            if (target == a[i])
                return i + 1;
        }
        return -1;
    }
}
