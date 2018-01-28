import java.util.*;

public class Main {
    public static void main(String[] args) {
        int a = new Scanner(System.in).nextInt();
        System.out.println(new Main().Hexadecimal(a));
    }

    private String Hexadecimal(int a) {
        if (0 == a)
            return "0";
        StringBuilder decimal = new StringBuilder("");
        while (a != 0) {
            int remaind = a % 16;
            switch (remaind) {
            case 10:
                decimal.insert(0, "A");
                break;
            case 11:
                decimal.insert(0, "B");
                break;
            case 12:
                decimal.insert(0, "C");
                break;
            case 13:
                decimal.insert(0, "D");
                break;
            case 14:
                decimal.insert(0, "E");
                break;
            case 15:
                decimal.insert(0, "F");
                break;
            default:
                decimal.insert(0, String.valueOf(remaind));
                break;
            }
            a >>= 4;
        }
        return decimal.toString();
    }
}
