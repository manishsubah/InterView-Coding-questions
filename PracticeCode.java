import java.lang.reflect.Array;
import java.util.Arrays;

public class PracticeCode {

    public static void main(String[] args) {
        String original = "Sanskriti University";

        StringBuffer revStr = new StringBuffer(original);
        System.out.println(revStr.reverse());

        String reversed = "";
        for (int i=original.length()-1; i>=0; i--) {
            reversed += original.charAt(i);
        }
        System.out.println(reversed);
    }
}
