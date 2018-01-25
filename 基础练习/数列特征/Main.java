import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] a =new int[n];
        for(int i=0;i<n;i++)
            a[i]=sc.nextInt();
        int max=-10001,min=10001,sum=0;
        while(n>0){
            if(a[--n]>max)
                max=a[n];
            if(a[n]<min)
                min=a[n];
            sum+=a[n];
        }
        System.out.print(max+"\n"+min+"\n"+sum);
                       
    }
}
