package myTest;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Input Start
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        // Input End
        
        System.out.println(new Main().maxNumber(n, m));
        
//        // 测试
//        Main m = new Main();
//        System.out.println(m.maxNumber(3, 5));
//        System.out.println(m.maxNumber(4, 7));
    }
    
    /** 
     * 计算不能买到的最大数量
     * 参数：n，m：包装数量
     * */
    private int maxNumber(int n,int m){
        // 记录是否能够买到与下标相等的数量(n, m < 1000, 假设1000 * 1000内能找到不能买到最大数量)
        final int MAX_STACK_SIZE = 1000 * 1000;
        boolean[] blBook = new boolean[MAX_STACK_SIZE];
        blBook[n] = true;
        blBook[m] = true;
        blBook[0] = true;
        
        // record用来记录能够连续买到的次数
        int small = m > n ? n : m;
        int flag = m > n ? m : n, record = 0;
        while (++flag < MAX_STACK_SIZE) {
            int tempFlag = flag;
            while (--tempFlag > 0) {
                if (blBook[tempFlag] && blBook[flag - tempFlag]) {
                    blBook[flag] = true;
                    record++;
                    break;
                }
            }
            // 连续small次数能够购买则以后数量都能购买
            if (small == record) {
                return flag - small;
            }
            // 非连续能够买到
            if(!blBook[flag] && record != 0) {
                record = 0;
            }
        }
        
        // 没有找到最大数量
        return 0;
    }
}