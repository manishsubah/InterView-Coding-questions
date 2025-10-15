import java.util.*;

public class JavaString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        sc.close();

        System.out.println(A.length() + B.length()); // Print the total length of both strings

        System.out.println(A.compareTo(B) > 0 ? "Yes" : "No"); // Compare the strings lexicographically

        System.out.println(
            String.valueOf(A.charAt(0)).toUpperCase() + A.substring(1) + " " +
            String.valueOf(B.charAt(0)).toUpperCase() + B.substring(1)
        ); // Concatenate the first character of each string with the rest of the string, capitalizing the first character

    }

    private static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
}
