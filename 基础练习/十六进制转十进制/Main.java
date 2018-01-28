import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().Decimal(new Scanner(System.in).nextLine()));
    }

    private long Decimal(String hex) {
        long l = 0;
        while (!hex.equals("")) {
            l *= 16;
            int flag = (int) hex.charAt(0);
            if (flag > 64)
                l += flag - 55;
            else
                l += flag - 48;
            hex = hex.substring(1, hex.length());
        }
        return l;
    }
}