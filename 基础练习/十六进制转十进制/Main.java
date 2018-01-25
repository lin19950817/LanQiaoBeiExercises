import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        System.out.println(new Main().Decimal(new Scanner(System.in).nextLine()));

    }

    private long Decimal(String hex) {
        long l = 0;
        while (!hex.equals("")) {
            l *= 16;
            char flag = hex.charAt(0);
            switch (flag) {
            case 'A':
                l += 10;
                break;
            case 'B':
                l += 11;
                break;
            case 'C':
                l += 12;
                break;
            case 'D':
                l += 13;
                break;
            case 'E':
                l += 14;
                break;
            case 'F':
                l += 15;
                break;
            default:
                l+=(int)flag-48;
            }
            hex=hex.substring(1,hex.length());
        }
        return l;
    }
}
