package myTest;

import java.util.*;

//有错误
public class Main {
    int[][] path = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    int minstep = 99, sum = 0;

    public static void main(String[] args) {
        //录入数据
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        int[][] a=new int[n][m];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                a[i][j]=sc.nextInt();
//        int[][] a ={{10,1,52},{20,30,1},{1,2,3}}; //测试
        Main ms = new Main();
        System.out.println(ms.min(a));
        
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < a[0].length; j++)
//                System.out.print(a[i][j]+"  \t");
//            System.out.println();
//        }
    }

    private int min(int[][] a) {
        //深度函数参数
        int target = 0, sum = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                target += a[i][j];
        if (0 == target % 2) {
            int[][] re_a = new int[a.length][a[0].length], book=new int[a.length][a[0].length];
            book[0][0]=1;this.sum=a[0][0];
            
            //找出目标数组re_a，统计个数并返回
            minlength(a, book, 0, 0, 0, target / 2, re_a);
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
//                    System.out.print(re_a[i][j]); 
                    if (1 == re_a[i][j])
                        sum++;
                }
//                System.out.println();
            }
            return sum;
        } else
            return 0;
    }

    //深度
    private void minlength(int[][] a, int[][] book, int step, int x, int y, int target, int[][] re_a) {
        if (sum < target) {
            for (int i = 0; i < 4; i++) {
                int _x = x + path[i][0], _y = y + path[i][1];
                if (_x < a.length && _y < a[0].length && _x >= 0 && _y >= 0) {
                    if (0 == book[_x][_y]) {
                        book[_x][_y] = 1;
                        sum += a[_x][_y];
                        minlength(a, book, step + 1, _x, _y, target, re_a);
                        book[_x][_y] = 0;
                        sum -= a[_x][_y];
                    }
                }
            }
        } else {
            if (sum == target) {
                //计算长度
                int len = 0;
                for (int i = 0; i < a.length; i++)
                    for (int j = 0; j < a[0].length; j++)
                        if (1 == book[i][j])
                            len++;
                //是否最小长度
                if (len < minstep) {
                    int[][] _a = new int[a.length][a[0].length];
                    for (int i = 0; i < a.length; i++)
                        for (int j = 0; j < a[0].length; j++)
                            if (1 == book[i][j])
                                re_a[i][j] = 1;
                    re_a = _a;
                }
            }
        }
    }
}
