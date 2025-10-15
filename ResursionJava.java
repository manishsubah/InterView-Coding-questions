public class ResursionJava {
    public static void printNum(int n) {
        if(n < 1) {
            return; // Base case: if n is negative, stop recursion
        }
        System.out.println(n);
        printNum(n - 1);
    }

    public static void main(String[] args) {
        printNum(5);
    }
}
