public class fibonacchiRecursion {
    public static void fibPrint(int a, int b, int n) {
        if (n == 0) {
            return;
        }
        int c = a + b;
        System.out.println(c);
        fibPrint(b, c, n - 1);
    }
    public static void main(String[] args) {
        int a = 0, b = 1;
        System.out.println(a); // Print the first Fibonacci number
        System.out.println(b); // Print the second Fibonacci number
        int n = 5; // Change this value to print more or fewer Fibonacci numbers
        fibPrint(a, b, n - 2); // Start with the first two Fibonacci number
    }
}
