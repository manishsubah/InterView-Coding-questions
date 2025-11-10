import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;

public class CurrencyFormatterAdvanced {
    
    // Enum for supported countries
    private enum Country {
        US(Locale.US, "US"),
        INDIA(new Locale("en", "IN"), "India"),
        CHINA(Locale.CHINA, "China"),
        FRANCE(Locale.FRANCE, "France");
        
        private final Locale locale;
        private final String displayName;
        
        Country(Locale locale, String displayName) {
            this.locale = locale;
            this.displayName = displayName;
        }
        
        public Locale getLocale() {
            return locale;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Method to format currency with validation
    private static String formatCurrency(double amount, Locale locale) {
        if (amount < 0 || amount > Math.pow(10, 9)) {
            throw new IllegalArgumentException("Amount must be between 0 and 10^9");
        }
        
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(amount);
    }
    
    // Method to format currency for all countries
    private static Map<String, String> formatAllCurrencies(double payment) {
        Map<String, String> formattedCurrencies = new LinkedHashMap<>();
        
        for (Country country : Country.values()) {
            try {
                String formatted = formatCurrency(payment, country.getLocale());
                formattedCurrencies.put(country.getDisplayName(), formatted);
            } catch (Exception e) {
                formattedCurrencies.put(country.getDisplayName(), "Error: " + e.getMessage());
            }
        }
        
        return formattedCurrencies;
    }
    
    // Method to print formatted currencies
    private static void printFormattedCurrencies(Map<String, String> currencies) {
        for (Map.Entry<String, String> entry : currencies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            double payment = scanner.nextDouble();
            
            // Validate input
            if (payment < 0 || payment > Math.pow(10, 9)) {
                System.err.println("Error: Payment must be between 0 and 10^9");
                return;
            }
            
            // Format and print all currencies
            Map<String, String> formattedCurrencies = formatAllCurrencies(payment);
            printFormattedCurrencies(formattedCurrencies);
            
        } catch (Exception e) {
            System.err.println("Error processing input: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
