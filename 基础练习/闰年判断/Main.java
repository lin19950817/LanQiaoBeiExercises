import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        if (0 == a % 4 && 0 != a % 100 || 0 == a % 400)
            System.out.print("yes");
        else
            System.out.print("no");
    }
}
