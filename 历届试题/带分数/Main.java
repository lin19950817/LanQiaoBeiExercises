import java.util.*;

public class Main {
    int sum = 0;

    public static void main(String[] args) {
         int[] a=new int[9],book=new int[9];
         Main m=new Main();
         m.digui(a, book, 0, new Scanner(System.in).nextInt());
         System.out.println(m.sum);

    }

    private void digui(int[] a, int[] book, int step, int target) {
        if (9 == step) {
            int j=7;
            while(j>3){
                int zero=1,i=1;
                int Denominator=0;
                for(int flag=8;flag>j;flag--){
                    Denominator+=a[flag]*zero;
                    zero*=10;
                }
                while(i<j){
                    zero=1;
                    int Integer=0,Fractional=0;
                    for(int flag=i-1;flag>=0;flag--){
                        Integer+=a[flag]*zero;
                        zero*=10;
                    }
                    zero=1;
                    for(int flag=j;flag>=i;flag--){
                        Fractional+=a[flag]*zero;
                        zero*=10;
                        
                    }
                    if(target==Integer+Fractional*1.0/Denominator)
                        sum++;
                    i++;
                }
                j--;
            }
        } else {
            for (int i = 0; i < 9; i++) {
                if (0 == book[i]) {
                    book[i] = 1;
                    a[step] = i + 1;
                    digui(a, book, step + 1, target);
                    book[i] = 0;
                }
            }
        }
    }
}
