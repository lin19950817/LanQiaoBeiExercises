public class Main {
    public static void main(String[] args) {
        for (int i = 100; i < 1000; i++) {
            int a = i % 10, b = i / 10 % 10, c = i / 100 % 10;
            if (i == a * a * a + b * b * b + c * c * c)
                System.out.println(i);
        }
    }
}
