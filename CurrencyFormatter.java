import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class CurrencyFormatter {
    
    /**
     * Formats currency for US locale using NumberFormat
     */
    private static String formatUSCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(amount);
    }
    
    /**
     * Formats currency for India with "Rs." prefix
     * Note: India doesn't have a built-in Locale, so we use custom formatting
     */
    private static String formatIndiaCurrency(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        return "Rs." + formatter.format(amount);
    }
    
    /**
     * Formats currency for China using NumberFormat
     */
    private static String formatChinaCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return formatter.format(amount);
    }
    
    /**
     * Formats currency for France with euro symbol at end
     * French format: space as thousands separator, comma as decimal separator
     */
    private static String formatFranceCurrency(double amount) {
        // Get euro symbol from Currency class to ensure correct encoding
        java.util.Currency currency = java.util.Currency.getInstance(Locale.FRANCE);
        String euroSymbol = currency.getSymbol(Locale.FRANCE);
        
        // Format number with comma as thousands separator
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        String formatted = formatter.format(amount);
        // Replace comma with space for thousands separator
        // Replace dot with comma for decimal separator
        formatted = formatted.replace(",", " ").replace(".", ",");
        // Add euro symbol at the end
        return formatted + " " + euroSymbol;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();
        
        // Format currencies for different locales
        String us = formatUSCurrency(payment);
        String india = formatIndiaCurrency(payment);
        String china = formatChinaCurrency(payment);
        String france = formatFranceCurrency(payment);
        
        // Print formatted currencies
        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("China: " + china);
        System.out.println("France: " + france);
    }
}
