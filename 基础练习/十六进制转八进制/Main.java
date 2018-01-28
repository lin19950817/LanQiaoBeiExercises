import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         int n=sc.nextInt();
         String[] a=new String[n];
         for(int i=0;i<n;i++)
         a[i]=sc.next();
         Main m=new Main();
         for(int i=0;i<n;i++)
         System.out.println(m.Octal(a[i]));
         
    }

    private String Octal(String hex) {
        StringBuilder sbBin = new StringBuilder(""), sbOct = new StringBuilder("");
        HashMap<String, String> hs = new HashMap<String, String>();

        hs.put("0", "0000");
        hs.put("1", "1000");
        hs.put("2", "0100");
        hs.put("3", "1100");
        hs.put("4", "0010");
        hs.put("5", "1010");
        hs.put("6", "0110");
        hs.put("7", "1110");
        hs.put("8", "0001");
        hs.put("9", "1001");
        hs.put("A", "0101");
        hs.put("B", "1101");
        hs.put("C", "0011");
        hs.put("D", "1011");
        hs.put("E", "0111");
        hs.put("F", "1111");

        hs.put("000", "0");
        hs.put("100", "1");
        hs.put("010", "2");
        hs.put("110", "3");
        hs.put("001", "4");
        hs.put("101", "5");
        hs.put("011", "6");
        hs.put("111", "7");

        for (int i = hex.length() - 1; i >= 0; i--) {
            sbBin.append(hs.get(String.valueOf(hex.charAt(i))));
        }
        while ('0' == sbBin.charAt(sbBin.length() - 1))
            sbBin.deleteCharAt(sbBin.length()-1);
        while(sbBin.length()%3!=0){
            sbBin.append("0"); 
        }
        for (int i = sbBin.length(); i > 0; i -= 3) {
            sbOct.append(hs.get(sbBin.substring(i - 3, i)));
        }
        return sbOct.toString();
    }

}
