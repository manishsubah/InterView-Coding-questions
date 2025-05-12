public class ReverseString {
    public static void main(String[] args) {
        String original = "Hello, World!";

        //using string builder
        StringBuilder reversedBuilder = new StringBuilder(original).reverse();
        System.out.println("Reversed String: " + reversedBuilder);

        //using StringBuffer
        StringBuffer reversedBuffer = new StringBuffer(original).reverse();
        System.out.println("Reversed String: " + reversedBuffer);

        //using char array
        char[] chars = original.toCharArray();

        for(int i = 0, j = chars.length - 1; i<j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        //convert back to string
        String reversedString = new String(chars);
        System.out.println("Reversed String: " + reversedString);

        //using simple loop
        String reversed = "";
        
        // Loop through the string in reverse order
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);
        }
        
        System.out.println("Reversed String: " + reversed);
    }

}
