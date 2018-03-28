package myTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 超时
public class Main {
    private int maxSum;
    private int n;
    private int[] a;
    private boolean[][] matrix;
    public static void main(String[] arg) {
        // Start of input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        boolean[][] matrix = new boolean[n][n];
        int temp = n - 1;
        for (int i = 0; i < temp; i++) {
            int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
            matrix[x][y] = true;
            matrix[y][x] = matrix[x][y];
        }
        // End of input
        
        System.out.println(new Main(n, a, matrix).maxValue());
        
        
//        // 测试
//        int n = 5;
//        int[] a = {1, 2, 3, 4, 5};
//        boolean[][] matrix = new boolean[n][n];
//        matrix[0][1] = true;matrix[1][0] = true;
//        matrix[0][2] = true;matrix[2][0] = true;
//        matrix[1][3] = true;matrix[3][1] = true;
//        matrix[1][4] = true;matrix[4][1] = true;
//        Main m = new Main(n, a, matrix);
//        System.out.println(m.maxValue());
        
    }
    public Main(int n, int[] a, boolean[][] matrix) {
        this.n = n;
        this.a = a;
        this.matrix = matrix;
        maxSum = 0;
    }
    private int maxValue() {
        search(new boolean[n], 0, n);
        return maxSum;
    }
    /** 
     * 暴力枚举
     * 参数：cannotBeSelected：标记不能被选择的节点，sum：当前值总和，canSelectCount：剩余能被选择的节点数
     * */
    private void search(boolean[] cannotBeSelected, int sum, int canSelectCount) {
        if (0 == canSelectCount) {
            if (sum > maxSum) {
                maxSum = sum;
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!cannotBeSelected[i]) {
                boolean[] newBook = cannotBeSelectedClone(cannotBeSelected);
                int next = nextCannotBeSelectedCount(newBook, i);
                
                search(newBook, sum + a[i], canSelectCount - next);
                
                
            }
        }
    }
    /** 
     * 克隆cannotBeSelectedClone
     * */
    private boolean[] cannotBeSelectedClone(boolean[] cannotBeSelected) {
        boolean[] newArray = new boolean[n];
        for (int i = 0; i < n; i++) {
            newArray[i] = cannotBeSelected[i];
        }
        return newArray;
    }
    /**
     * 计算下一个不能被选择的节点的数量
     * 参数：cannotBeSelected：标记不能被选择的节点（下个不能被选择的节点会被标记），selectedPoint：当前选择的点
     * */
    private int nextCannotBeSelectedCount(boolean[] cannotBeSelected, int selectedPoint) {
        // 当前选择的点标记上
        int sum = 1;
        cannotBeSelected[selectedPoint] = true;
        for (int i = 0; i < n; i++) {
            if (matrix[selectedPoint][i] && !cannotBeSelected[i]) {
                cannotBeSelected[i] = true;
                sum++;
            }
        }
        return sum;
    }
}
