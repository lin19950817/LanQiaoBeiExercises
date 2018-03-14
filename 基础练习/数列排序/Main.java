package myTest;

import java.util.*;

public class Main {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         int[] a = new int[n];
         for (int i = 0; i < n; i++)
         a[i] = sc.nextInt();

        // // 冒泡
        // for (int i = 0; i < n; i++) {
        // for (int j = 1; j < n - i; j++) {
        // if (a[j - 1] > a[j]) {
        // int temp = a[j - 1];
        // a[j - 1] = a[j];
        // a[j] = temp;
        // }
        // }
        // }
        
        new Main().quicksort(a, 0, 4);

        for (int i = 0; i < n; i++)
            System.out.print(a[i] + " ");
    }

    private void quicksort(int[] a, int left, int right) {
        if (right > left) {
            int _left = left, _right = right;
            while (right > left) {
                while (a[right] >= a[_left] && right > left)
                    right--;

                while ((a[left] <= a[_left] && right > left)) {
                    left++;
                }
                if (right > left) {
                    int temp = a[right];
                    a[right] = a[left];
                    a[left] = temp;
                }
            }
            int temp = a[_left];
            a[_left] = a[right];
            a[right] = temp;

            quicksort(a, _left, right - 1);
            quicksort(a, right + 1, _right);
        }
        
        //快速排序2
        // if(left < right){
        // int i = left, j = right, pivot = a[left];
        // while(i < j){
        //
        // while(a[j] > pivot && i < j)
        // j --;
        // if(i < j){
        // a[i] = a[j];
        // i++;
        // }
        // while(a[i] < pivot && i < j)
        // i ++;
        // if(i < j){
        // a[j] = a[i];
        // j--;
        // }
        // }
        // a[j] = pivot;
        // quicksort(a, left, i-1);
        // quicksort(a, i+1, right);
        // }
    }
}
