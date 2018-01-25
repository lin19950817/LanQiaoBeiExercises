package myTest;

import java.util.*;

public class Main {
    public static void main(String[] args) {
         Main m=new Main();
         Scanner sc=new Scanner(System.in);
         int i=Integer.parseInt(sc.next());
         System.out.println((m.Fn(i)));

    }

    public int Fn(int n) {
        if (n < 3) 
            return 1;
        int t1=1,t2=1;
        for(int i=3;i<=n;i++){
            int _t=t2;
            t2=(t1+t2)%10007;
            t1=_t;
        }
        return t2;
    }
}
