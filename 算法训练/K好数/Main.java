package myTest;

import java.math.BigInteger;
import java.util.*;

public class Main {
    private static final int MODULO = 1000000007;
    public static void main(String[] args) {
        // Start of input
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt(), l = sc.nextInt();
		sc.close();
        // End of input
        
//        // 测试
//        int k1 = 4, l1 = 2;
//        int k2 = 100, l2 = 3;
//        int k3 = 50, l3 = 50;
//        
//        Main m = new Main();
//        System.out.println(m.goodKNumber(k1, l1));
//        System.out.println(m.goodKNumber(k2, l2));
//        System.out.println(m.goodKNumber(k3, l3));
        
        System.out.println(new Main().goodKNumber(k, l));
    }
    private int goodKNumber(int k, int l){
        // 存放 K 进制每个基数的非相邻的数目
        int[] a = new int[k];
        // 初始化
        for (int i = 0; i < k; i++) {
            a[i] = 1;
        }
        int flag = l;
        while (--flag != 0) {
            a = nextBasicNumber(a, k);
        }
        
        int sum = 0;
        for (int i = 1; i < k; i++) {
            sum += a[i];
            if (sum > MODULO) {
                sum -= MODULO;
            }
        }
        return sum;
    }
    /** 
     * 计算下一个基数的每个基数的非相邻的数目
     * 参数：a：上一个基数的每个基数的非相邻的数目，k：要求的进制
     * 返回：int[]：下一个基数的每个基数的非相邻的数目
     * */
    private int[] nextBasicNumber(int[] a, int k) {
        int[] newArray = new int[k];
        int half = (k + 1) / 2, maxSubscript = k - 1;
        
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < k; j++) {
                if (j != i - 1 && j != i + 1) {
                    newArray[i] += a[j];
                    // 求模
                    if (newArray[i] > MODULO) {
                        newArray[i] -= MODULO;
                    }
                }
            }
            // 数组头尾值相同
            newArray[maxSubscript - i] = newArray[i];
        }
        return newArray;
    }
} 