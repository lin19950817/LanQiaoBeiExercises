package myTest;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Input start
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        // Input end
        System.out.println(new Main().theNumberOfTheContinuousInterval(n, a));
    }
    
    /**
     * 计算连号区间的个数
     * */
    private int theNumberOfTheContinuousInterval(int n, int[] a) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += statisticalContinuousInterval(a, i);
        }
        return sum;
    }
    
    /** 
     * 统计从start开始的array所有连续区间个数
     * 参数：array：被统计的连续区间个数的数组，start：统计开始的下标
     * 返回连续区间的个数
     * */
    private int statisticalContinuousInterval(int[] array, int start) {
        int length = array.length;
        if (0 == length) {
            return 0;
        }
        int biggest = array[start], smallest = array[start], sum = 0;
        for (int i = start; i < length; i++) {
            if (array[i] < smallest) {
                smallest = array[i];
            }
            if (array[i] > biggest) {
                biggest = array[i];
            }
            // array范围1~N，长度为N，所以但最大最小值的差等于个数则为连续区间
            if (biggest - smallest == i - start) {
                sum++;
            }
        }
        return sum;
    }
}