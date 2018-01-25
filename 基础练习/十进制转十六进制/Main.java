import java.util.*;

public class Main {
    public static void main(String[] args) {
        int a = new Scanner(System.in).nextInt();
        System.out.println(new Main().Hexadecimal(a));
     
    }
    private String Hexadecimal(int a){
        if(0==a)
            return "0";
        String decimal="";
        while (a != 0) {
            int remaind = a % 16;
            if (remaind > 9) {
                switch (remaind - 10) {
                case 0:
                    decimal = "A"+decimal;
                    break;
                case 1:
                    decimal = "B"+decimal;
                    break;
                case 2:
                    decimal = "C"+decimal;
                    break;
                case 3:
                    decimal = "D"+decimal;
                    break;
                case 4:
                    decimal = "E"+decimal;
                    break;
                case 5:
                    decimal = "F"+decimal;
                    break;
                default:
                    break;
                }
            }
            else
                decimal=remaind+decimal;
            a>>=4;
        }
        return decimal;
    }
}
