package myTest;

import java.util.*;

//有错误
public class Main {
    //深度搜索的方向
    int[][] path = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    //minstep:最小步数，sum:搜索的点的值的总和
    int minstep = 999, sum = 0;

    public static void main(String[] args) {
        // //录入数据
        // Scanner sc=new Scanner(System.in);
        // int n=sc.nextInt(),m=sc.nextInt();
        // int[][] a=new int[n][m];
        // for (int i = 0; i < a.length; i++)
        // for (int j = 0; j < a[0].length; j++)
        // a[i][j]=sc.nextInt();
        int[][] a = { { 2, 2, 2 }, { 1, 7, 5 }, { 1, 1, 1 } }; // 测试
        Main ms = new Main();
        System.out.println(ms.min(a));

        // for (int i = 0; i < a.length; i++) {
        // for (int j = 0; j < a[0].length; j++)
        // System.out.print(a[i][j]+" \t");
        // System.out.println();
        // }

    }

    //返回最小格子数
    private int min(int[][] a) {
        // 深度函数参数
        int target = 0, _length = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                target += a[i][j];
        if (0 == target % 2) {
            int[][] re_a = new int[a.length][a[0].length], book = new int[a.length][a[0].length];
            book[0][0] = 1;
            sum = a[0][0];

            // 找出目标数组re_a，统计个数并返回（可以直接输出minstep）
            minlength(a, book, 1, 0, 0, target / 2, re_a);
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    // System.out.print(re_a[i][j]);
                    if (1 == re_a[i][j])
                        _length++;
                }
                // System.out.println();
            }
            //_length=minstep;
            return _length;
        } else
            return 0;
    }

    // 广度检查是否形成连续区域
    private boolean islianxvqvyv(int[][] a) {
        int x = 0, y = 0;
        // 找出没有标记的点
        boolean b = false;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                if (0 == a[i][j]) {
                    x = i;
                    y = j;
                    b = true;
                    break;
                }
            if (b)
                break;
        }

        List<point> list = new ArrayList<point>();
        // int[][] book=a.clone();
        // 克隆
        int[][] book = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                if (1 == a[i][j])
                    book[i][j] = a[i][j];

        book[x][y] = 1;
        list.add(new point(x, y));
        // 染色
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 4; j++) {
                int _x = list.get(i).x + path[j][0], _y = list.get(i).y + path[j][1];
                if (_x < a.length && _y < a[0].length && _x >= 0 && _y >= 0) {
                    if (0 == book[_x][_y]) {
                        book[_x][_y] = 1;
                        list.add(new point(_x, _y));
                    }
                }
            }
        }
        // 是否存在没染色的点
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                if (0 == book[i][j])
                    return false;
        return true;

    }

    // 深度
    ///a:要求录入的数组，book:深度优先搜索用来标记已用过，step：记录步数，x，y:数组坐标，target:要求两区域的值总和相等（target为所有点值总和的一半），re_a:存放符合条件的数组（非必须，但能监控过程）
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
                // 是否最小长度且连续区域
                if (step < minstep && islianxvqvyv(book)) {
                    for (int i = 0; i < a.length; i++)
                        for (int j = 0; j < a[0].length; j++)
                            re_a[i][j] = book[i][j];
                    //re_a用来查看过程，结果是minstep
                    minstep = step;
                }
            }
        }
    }
}

class point {
    public int x;
    public int y;

    public point(int a, int b) {
        x = a;
        y = b;
    }
}
