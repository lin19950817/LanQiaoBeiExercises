import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        for (int i=0;i<n;i++){
                for (int a=65+i;a>65&&65+i-a<m;a--)
                    System.out.print((char)a);
                for (int b=65;b-65<m-i;b++)
                    System.out.print((char)b);
                System.out.println();
        }
                       
    }
}
