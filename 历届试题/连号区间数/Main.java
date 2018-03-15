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
        
//        // 测试
//        int[] case1Array = { 3, 2, 4, 1};   // 7
//        int[] case2Array = { 3, 4, 2, 5, 1};    // 9
//        int[] case3Array = { 1, 3, 6, 5, 4, 7, 2};  // 15
//        Main m = new Main();
//        System.out.println(m.theNumberOfTheContinuousInterval(case1Array.length, case1Array));
//        System.out.println(m.theNumberOfTheContinuousInterval(case2Array.length, case2Array));
//        System.out.println(m.theNumberOfTheContinuousInterval(case3Array.length, case3Array));
    }
    
    /**
     * 计算连号区间的个数
     * */
    private int theNumberOfTheContinuousInterval(int n, int[] a) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += statisticalContinuousInterval(a, i);
        }
        
        
//        /**
//         * 找出每个区间，排序，判断是否连续
//         * 结果：时间超时 
//         * */
//        // 记录连号区间个数，每个个数都是区间，所以至少n个
//        int sum = n;
//        for (int i = 0; i < n-1; i++) {
//            for (int j = i + 1; j< n; j++) {
//                int[] array = new int[j - i + 1];
//                for (int k = i; k <= j; k++) {
//                    array[k - i] = a[k];
//                }
//                boolean bl = isContinuousArray(array);
//                if (bl) {
//                    sum++;
//                }
//            }
//        }
        
        
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
    
    private void quickSort(int[] a, int first, int last) {
        if (last > first) {
            int flagFirst = first, flagLast = last, flag = a[first];
            while (flagFirst != flagLast) {
                while (a[flagLast] >= flag && flagLast > flagFirst) {
                    flagLast--;
                }
                while (a[flagFirst] <= flag && flagLast > flagFirst) {
                    flagFirst++;
                }
                if (flagLast > flagFirst) {
                    int temp = a[flagFirst];
                    a[flagFirst] = a[flagLast];
                    a[flagLast] = temp;
                }
            }
            a[first] = a[flagFirst];
            a[flagFirst] = flag;
            quickSort(a, flagLast + 1, last);
            quickSort(a, first, flagLast - 1);
        }
    }

    private int[] intArrayClone(int[] targetArray) {
        int targetArrayLength = targetArray.length;
        int[] newArray = new int[targetArrayLength];
        for (int i = 0; i < targetArrayLength; i++) {
            newArray[i] = targetArray[i];
        }
        return newArray;
    }
    
    /**
     * 是否连续数列
     * */
    private boolean isContinuousArray(int[] array) {
        if (0 == array.length) {
            return false;
        }
        int arrayLength = array.length;
        int[] tempArray = intArrayClone(array);
        quickSort(tempArray, 0, arrayLength - 1);
        int flag = tempArray[0] + 1;
        for (int i = 1; i < arrayLength; i++) {
            if (flag++ != tempArray[i]) {
                return false;
            }
        }
        return true;
    }
}