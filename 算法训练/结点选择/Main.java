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
//        
//        int n1 = 10;
//        int[] a1 = {41, 982, 686, 796, 781, 801, 63, 52, 60, 748};
//        boolean[][] matrix1 = new boolean[n1][n1];
//        matrix1[2][3] = true;matrix1[3][2] = true;
//        matrix1[7][4] = true;matrix1[4][7] = true;
//        matrix1[0][8] = true;matrix1[8][0] = true;
//        matrix1[9][6] = true;matrix1[6][9] = true;
//        matrix1[1][2] = true;matrix1[2][1] = true;
//        matrix1[0][5] = true;matrix1[5][0] = true;
//        matrix1[0][2] = true;matrix1[2][0] = true;
//        matrix1[1][6] = true;matrix1[6][1] = true;
//        matrix1[3][4] = true;matrix1[4][3] = true;
//        
//        int n2 = 20;
//        int[] a2 = {341, 697, 481, 697, 210, 129, 172, 214, 13, 801, 733, 445, 701, 731, 11, 946, 145, 717, 753, 618};
//        boolean[][] matrix2 = new boolean[n2][n2];
//        matrix2[16][18] = true;matrix2[18][16] = true;
//        matrix2[10][8] = true;matrix2[8][10] = true;
//        matrix2[16][19] = true;matrix2[19][16] = true;
//        matrix2[0][8] = true;matrix2[8][0] = true;
//        matrix2[18][12] = true;matrix2[12][18] = true;
//        matrix2[15][6] = true;matrix2[6][15] = true;
//        matrix2[3][16] = true;matrix2[16][3] = true;
//        matrix2[13][16] = true;matrix2[16][13] = true;
//        matrix2[6][4] = true;matrix2[4][6] = true;
//        matrix2[1][18] = true;matrix2[18][1] = true;
//        matrix2[18][4] = true;matrix2[4][18] = true;
//        matrix2[0][12] = true;matrix2[12][0] = true;
//        matrix2[17][16] = true;matrix2[16][17] = true;
//        matrix2[6][14] = true;matrix2[14][6] = true;
//        matrix2[9][6] = true;matrix2[6][9] = true;
//        matrix2[2][14] = true;matrix2[14][2] = true;
//        matrix2[12][5] = true;matrix2[5][12] = true;
//        matrix2[7][19] = true;matrix2[19][7] = true;
//        matrix2[9][11] = true;matrix2[11][9] = true;
//        
//        System.out.println(new Main(n, a, matrix).maxValue());
//        System.out.println(new Main(n1, a1, matrix1).maxValue());
//        System.out.println(new Main(n2, a2, matrix2).maxValue());
        
        
    }
    public Main(int n, int[] a, boolean[][] matrix) {
        this.n = n;
        this.a = a;
        this.matrix = matrix;
        maxSum = 0;
    }
    private int maxValue() {
//        search(new boolean[n], 0, n);
        
        search2(new boolean[n], 0, 0);
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
    private void search2(boolean[] cannotBeSelected, int sum, int step) {
        if (n == step) {
            if (sum > maxSum) {
                maxSum = sum;
            }
            return;
        }
        if (!cannotBeSelected[step]) {
            for (int i = 0; i < 2; i++) {
                boolean[] newBook = cannotBeSelectedClone(cannotBeSelected);
                // 标记
                if (0 == i) {
                    // 选择点后，与其相连的点都不能被选择
                    newBook[step] = true;
                    for (int j =0; j < n; j++) {
                        if (matrix[step][j] && !cannotBeSelected[j]) {
                            newBook[j] = true;
                        }
                    } 
                }
                search2(newBook, newBook[step] ? sum + a[step] : sum, step + 1);
            }
        } else {
            search2(cannotBeSelected, sum, step + 1);
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
