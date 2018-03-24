package myTest;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Input Start
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] start = new int[m], end = new int[m], target = new int[m];
        for (int i = 0; i < m; i++) {
            start[i] = sc.nextInt();
            end[i] = sc.nextInt();
            target[i] = sc.nextInt();
        }
        // Input End
        
        Main ma = new Main();
        for (int i = 0; i < m; i++) {
            int[] arr = ma.cutArray(a, start[i], end[i]);
            ma.quickSort(arr, 0, arr.length - 1);
            System.out.println(arr[arr.length - target[i]]);
        }
    }
    private void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start, right = end, flag = a[start];
        while (left != right) {
            while (a[right] >= flag && left < right) {
                right--;
            }
            while (a[left] <= flag && left < right) {
                left++;
            }
            if (left < right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
            }
        }
        a[start] = a[right];
        a[right] = flag;
        quickSort(a, right + 1, end);
        quickSort(a, start, left - 1);
    }
    /**
     * 数组截取
     * 参数：a:截取的数组，start：截取开始index，end：截取结束index
     * 返回：截取的新数组
     * */
    private int[] cutArray(int[] a, int start, int end) {
        int[] arr = new int[end + 1 - start];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = a[start - 1 + i];
        }
        return arr;
    }
} 