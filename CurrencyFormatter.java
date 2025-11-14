import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class CurrencyFormatter {
    
    private static String formatUSCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(amount);
    }
    
    private static String formatIndiaCurrency(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        return "Rs." + formatter.format(amount);
    }
    
    private static String formatChinaCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return formatter.format(amount);
    }
    

    private static String formatFranceCurrency(double amount) {
        
        java.util.Currency currency = java.util.Currency.getInstance(Locale.FRANCE);
        String euroSymbol = currency.getSymbol(Locale.FRANCE);
        
        
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        String formatted = formatter.format(amount);
        
        formatted = formatted.replace(",", " ").replace(".", ",");
        
        return formatted + " " + euroSymbol;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();
        
        
        String us = formatUSCurrency(payment);
        String india = formatIndiaCurrency(payment);
        String china = formatChinaCurrency(payment);
        String france = formatFranceCurrency(payment);
        
    
        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("China: " + china);
        System.out.println("France: " + france);
    }
}
