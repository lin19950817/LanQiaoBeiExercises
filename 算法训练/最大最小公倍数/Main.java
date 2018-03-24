package myTest;

import java.math.BigInteger;
import java.util.*;


public class Main {
    // 有错误
    public static void main(String[] args) {
        // Input Start
        int n = new Scanner(System.in).nextInt();
        // Input End
        
        Main m = new Main();
        // 最大的最小公倍数为最大偶数和两个最大奇数
        boolean isEven = false;
        int second = 0, third = 0;
        if (0 == n % 2) {
            isEven = true;
        }
        for (int i = n - 1; i > 0; i--) {
            if (0 == second) {
                if (!isEven || i % 2 != 0) {
                    second = i;
                }
            } else {
                if (i % 2 != 0) {
                    third = i;
                    break;
                }
            }
        }
        BigInteger a = new BigInteger(n + ""), b = new BigInteger(second + ""), c = new BigInteger(third + "");
        a = a.multiply(b);
        a = a.multiply(c);
        System.out.println(a.toString());
    }
} 