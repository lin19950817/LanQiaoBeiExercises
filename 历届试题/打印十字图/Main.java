import java.util.*;

public class Main {
    int sum = 0;

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        new Main().ShiZiTu(n);
    }
    private void ShiZiTu(int n){
        int length=3+2*n;
        char[][] a = new char[length][length];
        // 第一行
        a[0][1] = '.';
        a[0][0] = '.';
        for (int i = 2; i < length; i++)
            a[0][i] = '$';
        // 第二行
        a[1][0] = '.';
        a[1][1] = '.';
        a[1][2] = '$';
        for (int i = 3; i < length; i++)
            a[1][i] = '.';
        // 第三行
        for (int i = 0; i < length; i++)
            a[2][i] = '$';
        a[2][3] = '.';

        for (int i = 0; i < 2 * n; i++) {
          //单数行
            if (0 == i % 2) {
                for(int j=0;j<length;j++)
                    a[i+3][j]='.';
                for(int j=0;j<=i;j+=2)
                    a[i+3][j]='$';
                a[i+3][i+4]='$';
            }
            //双行数
            if (1 == i % 2) {
                for(int j=0;j<length;j++)
                    a[i+3][j]='$';
                for(int j=1;j<=i;j+=2)
                    a[i+3][j]='.';
                if(i!=2*n-1)
                    a[i+3][i+4]='.';
            }
        }
        //打印
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++)
                System.out.print(a[i][j]);
            for(int j=length-1;j>=0;j--)
                System.out.print(a[i][j]);
            System.out.println();
        }for(int i=length-1;i>=0;i--){
            for(int j=0;j<length;j++)
                System.out.print(a[i][j]);
            for(int j=length-1;j>=0;j--)
                System.out.print(a[i][j]);
            System.out.println();
        }
    }
}
