import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class CurrencyFormatterMedium {
    
    // Method to format currency for a given locale
    private static String formatCurrency(double amount, Locale locale) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(amount);
    }
    
    // Method to get locale for India (since it doesn't have built-in locale)
    private static Locale getIndiaLocale() {
        return new Locale("en", "IN");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();
        
        // Format currencies using helper methods
        String us = formatCurrency(payment, Locale.US);
        String india = formatCurrency(payment, getIndiaLocale());
        String china = formatCurrency(payment, Locale.CHINA);
        String france = formatCurrency(payment, Locale.FRANCE);
        
        // Print formatted currencies
        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("China: " + china);
        System.out.println("France: " + france);
    }
}
