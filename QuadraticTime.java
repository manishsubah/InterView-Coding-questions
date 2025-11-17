/**
 * O(n²) - Quadratic Time Complexity Examples
 * 
 * These operations have nested loops, processing all pairs of elements.
 * Time Complexity: O(n²)
 * 
 * If input doubles, time quadruples! Should be avoided for large inputs.
 */
import java.util.*;

public class QuadraticTime {
    
    // Example 1: Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {              // Outer loop: n iterations
            for (int j = 0; j < n - i - 1; j++) {     // Inner loop: n iterations
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // Example 2: Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {              // Outer loop: n iterations
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {          // Inner loop: n iterations
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    // Example 3: Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {                  // Outer loop: n iterations
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {          // Inner loop: up to n iterations
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    // Example 4: Finding duplicates using nested loops
    public static List<Integer> findDuplicates(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {           // Outer loop: n iterations
            for (int j = i + 1; j < arr.length; j++) {   // Inner loop: n iterations
                if (arr[i] == arr[j] && !duplicates.contains(arr[i])) {
                    duplicates.add(arr[i]);
                }
            }
        }
        return duplicates;
    }
    
    // Example 5: Finding all pairs that sum to target
    public static List<int[]> findPairs(int[] arr, int target) {
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    pairs.add(new int[]{arr[i], arr[j]});
                }
            }
        }
        return pairs;
    }
    
    // Example 6: Checking if array contains duplicates (naive)
    public static boolean hasDuplicates(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // Example 7: Finding maximum product of two numbers
    public static int maxProduct(int[] arr) {
        int maxProd = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int product = arr[i] * arr[j];
                if (product > maxProd) {
                    maxProd = product;
                }
            }
        }
        return maxProd;
    }
    
    // Example 8: Transposing a matrix
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }
    
    // Example 9: Finding longest common substring (naive)
    public static String longestCommonSubstring(String s1, String s2) {
        String longest = "";
        for (int i = 0; i < s1.length(); i++) {
            for (int j = i + 1; j <= s1.length(); j++) {
                String substring = s1.substring(i, j);
                if (s2.contains(substring) && substring.length() > longest.length()) {
                    longest = substring;
                }
            }
        }
        return longest;
    }
    
    // Example 10: Printing all subarrays
    public static void printAllSubarrays(int[] arr) {
        System.out.println("All subarrays:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                System.out.print("[");
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k]);
                    if (k < j) System.out.print(", ");
                }
                System.out.println("]");
            }
        }
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println("=== O(n²) Quadratic Time Examples ===\n");
        
        // Test 1: Bubble Sort
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("1. Bubble Sort:");
        System.out.println("   Before: " + Arrays.toString(arr1));
        bubbleSort(arr1);
        System.out.println("   After: " + Arrays.toString(arr1));
        System.out.println("   Complexity: O(n²) - nested loops");
        
        // Test 2: Selection Sort
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("\n2. Selection Sort:");
        System.out.println("   Before: " + Arrays.toString(arr2));
        selectionSort(arr2);
        System.out.println("   After: " + Arrays.toString(arr2));
        
        // Test 3: Insertion Sort
        int[] arr3 = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("\n3. Insertion Sort:");
        System.out.println("   Before: " + Arrays.toString(arr3));
        insertionSort(arr3);
        System.out.println("   After: " + Arrays.toString(arr3));
        
        // Test 4: Find duplicates
        int[] arr4 = {1, 2, 3, 2, 4, 5, 3, 6, 7, 3};
        System.out.println("\n4. Find Duplicates:");
        System.out.println("   Array: " + Arrays.toString(arr4));
        List<Integer> duplicates = findDuplicates(arr4);
        System.out.println("   Duplicates: " + duplicates);
        System.out.println("   Note: Better solution uses HashSet for O(n)");
        
        // Test 5: Find pairs
        int[] arr5 = {2, 7, 11, 15, 3, 6};
        System.out.println("\n5. Find Pairs that Sum to Target:");
        System.out.println("   Array: " + Arrays.toString(arr5));
        int target = 9;
        List<int[]> pairs = findPairs(arr5, target);
        System.out.println("   Pairs that sum to " + target + ":");
        for (int[] pair : pairs) {
            System.out.println("     [" + pair[0] + ", " + pair[1] + "]");
        }
        System.out.println("   Note: Better solution uses HashMap for O(n)");
        
        // Test 6: Has duplicates
        int[] arr6 = {1, 2, 3, 4, 5};
        int[] arr7 = {1, 2, 3, 2, 4};
        System.out.println("\n6. Check for Duplicates:");
        System.out.println("   Array: " + Arrays.toString(arr6));
        System.out.println("   Has duplicates: " + hasDuplicates(arr6));
        System.out.println("   Array: " + Arrays.toString(arr7));
        System.out.println("   Has duplicates: " + hasDuplicates(arr7));
        
        // Test 7: Max product
        int[] arr8 = {1, 2, 3, 4, 5};
        System.out.println("\n7. Maximum Product of Two Numbers:");
        System.out.println("   Array: " + Arrays.toString(arr8));
        System.out.println("   Max product: " + maxProduct(arr8));
        
        // Test 8: Transpose matrix
        System.out.println("\n8. Transpose Matrix:");
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("   Original:");
        for (int[] row : matrix) {
            System.out.println("     " + Arrays.toString(row));
        }
        int[][] transposed = transposeMatrix(matrix);
        System.out.println("   Transposed:");
        for (int[] row : transposed) {
            System.out.println("     " + Arrays.toString(row));
        }
        
        // Test 9: Longest common substring
        System.out.println("\n9. Longest Common Substring:");
        String s1 = "abcdef";
        String s2 = "abcfed";
        String lcs = longestCommonSubstring(s1, s2);
        System.out.println("   String 1: " + s1);
        System.out.println("   String 2: " + s2);
        System.out.println("   Longest common substring: " + lcs);
        
        // Test 10: Print subarrays (small example)
        System.out.println("\n10. Print All Subarrays (small example):");
        int[] arr10 = {1, 2, 3};
        printAllSubarrays(arr10);
        
        // Performance warning
        System.out.println("\n=== Performance Warning ===");
        System.out.println("For array of size 10: ~100 operations");
        System.out.println("For array of size 100: ~10,000 operations");
        System.out.println("For array of size 1000: ~1,000,000 operations");
        System.out.println("O(n²) algorithms become very slow for large inputs!");
        System.out.println("Consider optimizing to O(n log n) or O(n) when possible.");
        
        System.out.println("\n=== All operations completed in O(n²) time! ===");
    }
}

