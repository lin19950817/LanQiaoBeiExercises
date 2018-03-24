package myTest;

import java.math.BigInteger;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Input Start
        int n = new Scanner(System.in).nextInt();
        // Input End
        
        Main m = new Main();
        
        /// 最大的最小公倍数为最大偶数和两个最大奇数（互质）
        // 先找出并存放最大的偶数，和两个奇数
        int evenFirst = 0, evenSecond = 0, evenThird = 0;
        // 先找出并存放最大的奇数，再找出偶数和奇数
        int oddFirst = 0, oddSecond = 0, oddThird = 0;
        if (0 == n % 2) {
            // 相差为1，一定互质
            evenFirst = n--;
            evenSecond = n;
            oddFirst = n--;
            oddSecond = n;
        } else {
            oddFirst = n--;
            oddSecond = n;
            evenFirst = n--;
            evenSecond = n;
        }
        for (int i = n; i > 0; i--) {
            if (i % 2 != 0) {
                if (0 == evenThird && 1 == m.commondDivisor(evenFirst, i) && 1 == m.commondDivisor(evenSecond, i)) {
                    evenThird = i;
                }
                if (0 == oddThird && 1 == m.commondDivisor(oddFirst, i) && 1 == m.commondDivisor(oddSecond, i)) {
                    oddThird = i;
                }
                if (0 == evenThird && 0 == oddThird) {
                    break;
                }
                
            }
        }
        // 比较两组数据乘积的大小，两组数据中有相同值，所以只要比较另外两个数的乘积
        BigInteger evenProduct, oddProduct;
        evenProduct = new BigInteger(String.valueOf(evenFirst)).multiply(new BigInteger(String.valueOf(evenSecond)).multiply(new BigInteger(String.valueOf(evenThird))));
        oddProduct = new BigInteger(String.valueOf(oddFirst)).multiply(new BigInteger(String.valueOf(oddSecond)).multiply(new BigInteger(String.valueOf(oddThird))));
        if (evenProduct.compareTo(oddProduct) > 0) {
            System.out.println(evenProduct.toString());
        } else {
            System.out.println(oddProduct.toString());
        }
    }   
    
    private int commondDivisor(int a, int b) {
        return 0 == b ? a : commondDivisor(b, a % b);
    }
} 