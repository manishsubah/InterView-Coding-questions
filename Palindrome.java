public class Palindrome {
    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        System.out.println("Length of the string = " + str.length());
        System.out.println("left = " + left + ", right = " + right);
        // Check if the
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[] args) {
        String str = "college"; 
        System.out.println("Is the string a palindrome? = " + isPalindrome(str));
        str = "madam"; 
        System.out.println("Is the string a palindrome? = " + isPalindrome(str));
        str = "cool"; 
        System.out.println("Is the string a palindrome? = " + isPalindrome(str));
    }
}