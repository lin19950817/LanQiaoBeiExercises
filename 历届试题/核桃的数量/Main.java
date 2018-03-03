import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
        Main m=new Main();
        System.out.println(m.TheLeastCommonMultiple(m.TheLeastCommonMultiple(a, b), c));

    }
    private int TheLeastCommonMultiple(int a,int b){
        int[] pn = { 2, 3, 5, 7, 9, 11, 13, 17, 19, 23, 29 };
        int TheSmallestCommonDivisor = 1;
        boolean bl = true;
        while (bl) {
            bl = false;
            for (int i = 0; i < pn.length; i++)
                if (0 == a % pn[i] && 0 == b % pn[i]) {
                    bl = true;
                    TheSmallestCommonDivisor *= pn[i];
                    a /= pn[i];
                    b /= pn[i];
                }
        }
        return TheSmallestCommonDivisor*a*b;
    }
}
