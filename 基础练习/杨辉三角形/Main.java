import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n>0)
            System.out.println(1);
        if(n>1)
            System.out.println(1+" "+1);
        int[] a={1,1};
        Main m=new Main();
        for(int i=2;i<n;){
            a=m.YanHui(a,i++);
            for(int j=0;j<i;j++)
                System.out.print(a[j]+" ");
            System.out.println();
        }

    }

    private int[] YanHui(int[] a, int n) {
        int[] x=new int[n+1];
        x[0]=1;
        x[n--]=1;
        for (int i = 0; i < n;) {
            x[++i]=a[i-1]+a[i];
        }
        return x;
    }
}
