package myTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 超时。。
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
//        System.out.println(new Main(n, a, matrix).maxValue());
//        
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
//        System.out.println(new Main(n1, a1, matrix1).maxValue());
//        
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
//        System.out.println(new Main(n2, a2, matrix2).maxValue());
        
        
    }
    public Main(int n, int[] a, boolean[][] matrix) {
        this.n = n;
        this.a = a;
        this.matrix = matrix;
        maxSum = 0;
    }
    private int maxValue() {
        // 将a数组放进notes
        Point[] notes = new Point[n];
        for (int i = 0; i < n; i++) {
            notes[i] = new Point(i, a[i]);
        }
        
        int maxValueOfSelected = notes[0].getMaxValueOfSelected(matrix, new boolean[n], notes), maxValueOfUnselected = notes[0].getMaxValueOfUnselected(matrix, new boolean[n], notes);
        this.maxSum = maxValueOfSelected > maxValueOfUnselected ? maxValueOfSelected : maxValueOfUnselected;
        
        return maxSum;
    }
}
class Point{
    private int subscript;
    private int value;
    private int maxValueOfSelected;
    private int maxValueOfUnselected;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getMaxValueOfSelected(boolean[][] matrix, boolean[] book, Point[] notes) {
        if (maxValueOfSelected != -1) {
            return maxValueOfSelected;
        }
        
        book[subscript] = true;
        setMaxValueOfSelected(matrix, book, notes);
        return maxValueOfSelected;
    }
    /**
     * 被选择最大值为所有连接点的不能被选择最大值的和
     * */
    public void setMaxValueOfSelected(boolean[][] matrix, boolean[] book, Point[] notes) {
        int sum = 0, length = matrix.length;
        for (int i = 0; i < length; i++) {
            if (!book[i] && matrix[subscript][i]) {
                sum += notes[i].getMaxValueOfUnselected(matrix, book, notes);
            }
        }
        this.maxValueOfSelected = sum + value;
    }
    public int getMaxValueOfUnselected(boolean[][] matrix, boolean[] book, Point[] notes) {
        if (maxValueOfUnselected != -1) {
            return maxValueOfUnselected;
        }
        
        book[subscript] = true;
        setMaxValueOfUnselected(matrix, book, notes);
        return maxValueOfUnselected;
    }
    /**
     * 不能被选择最大值为所有连接点的被选择最大值和不能被选择最大值之间最大数的和
     * */
    public void setMaxValueOfUnselected(boolean[][] matrix, boolean[] book, Point[] notes) {
        int sum = 0, length = matrix.length;
        boolean[] anotherBook = bookClone(book);
        for (int i = 0; i < length; i++) {
            if (!book[i] && matrix[subscript][i]) {
                int nextMaxValueOfSelected = notes[i].getMaxValueOfSelected(matrix, book, notes), nextMaxValueOfUnselected = notes[i].getMaxValueOfUnselected(matrix, anotherBook, notes);
                sum += nextMaxValueOfSelected > nextMaxValueOfUnselected ? nextMaxValueOfSelected : nextMaxValueOfUnselected;
            }
            
        }
        this.maxValueOfUnselected = sum;
    }
    public Point(int subscript, int value) {
        this.subscript = subscript;
        this.value = value;
        maxValueOfSelected = -1;
        maxValueOfUnselected = -1;
    }
    
    private boolean[] bookClone(boolean[] book) {
        int length = book.length;
        boolean[] newBook = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (book[i]) {
                newBook[i] = true;
            }
        }
        return newBook;
    }
}
