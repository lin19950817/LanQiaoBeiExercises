package myTest;

import java.math.BigInteger;
import java.util.*;

// 超时。。。
public class Main {
    public int sum = 0;
    public static void main(String[] args) {
        // Start of input
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt(), l = sc.nextInt();
        // End of input
        
//        // 测试
//        int k = 4, l = 2;
        
        boolean[] disuseBook = new boolean[k];
        // 第一项不能为 0 
        disuseBook[0] = true;
        int[] a = new int[l];
        Main m = new Main();
        m.search(k, l, a, disuseBook, 0);
        System.out.println(m.sum);
    }
    private void search(int k, int l, int[] a, boolean[] disuseBook, int step) {
        if (step == l) {
            sum++;
            if (1000000007 == sum) {
                sum = 0;
            }
            return;
        }
        
        for (int i = 0; i < k; i++) {
            if (!disuseBook[i]) {
                a[step] = i;
                boolean[] book = new boolean[k];
                int small = i - 1, big = i + 1;
                if (small >= 0) {
                    book[small] = true;
                }
                if (big < k) {
                    book[big] = true;
                }
                search(k, l, a, book, step + 1);
            }
        }
    }
} 