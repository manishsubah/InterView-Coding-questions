/**
 * Time Complexity Demo - Comparing Different Complexities
 * 
 * This program demonstrates the performance difference between
 * different time complexities with practical examples.
 */
import java.util.*;

public class TimeComplexityDemo {
    
    // O(1) - Constant time
    public static int getFirst(int[] arr) {
        return arr[0];
    }
    
    // O(log n) - Logarithmic time
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
    
    // O(n) - Linear time
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }
    
    // O(n log n) - Linearithmic time
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = arr[mid + 1 + j];
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) arr[k++] = leftArr[i++];
            else arr[k++] = rightArr[j++];
        }
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }
    
    // O(n²) - Quadratic time
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // O(2ⁿ) - Exponential time
    public static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // Helper method to generate random array
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }
    
    // Helper method to generate sorted array
    public static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Time Complexity Performance Comparison ===\n");
        
        // Test different input sizes
        int[] sizes = {10, 100, 1000, 10000};
        
        System.out.println("1. O(1) - Constant Time (Array Access):");
        for (int size : sizes) {
            if (size <= 10000) {
                int[] arr = generateRandomArray(size);
                long start = System.nanoTime();
                getFirst(arr);
                long end = System.nanoTime();
                System.out.printf("   Size %6d: %8d ns (always fast!)\n", size, (end - start));
            }
        }
        
        System.out.println("\n2. O(log n) - Logarithmic Time (Binary Search):");
        for (int size : sizes) {
            if (size <= 10000) {
                int[] arr = generateSortedArray(size);
                int target = size / 2;
                long start = System.nanoTime();
                binarySearch(arr, target);
                long end = System.nanoTime();
                System.out.printf("   Size %6d: %8d ns (very fast!)\n", size, (end - start));
            }
        }
        
        System.out.println("\n3. O(n) - Linear Time (Linear Search):");
        for (int size : sizes) {
            if (size <= 10000) {
                int[] arr = generateRandomArray(size);
                int target = arr[size - 1]; // Worst case: last element
                long start = System.nanoTime();
                linearSearch(arr, target);
                long end = System.nanoTime();
                System.out.printf("   Size %6d: %8d ns (proportional to size)\n", size, (end - start));
            }
        }
        
        System.out.println("\n4. O(n log n) - Linearithmic Time (Merge Sort):");
        for (int size : sizes) {
            if (size <= 10000) {
                int[] arr = generateRandomArray(size);
                long start = System.nanoTime();
                mergeSort(arr, 0, arr.length - 1);
                long end = System.nanoTime();
                System.out.printf("   Size %6d: %8d ns (efficient sorting)\n", size, (end - start));
            }
        }
        
        System.out.println("\n5. O(n²) - Quadratic Time (Bubble Sort):");
        for (int size : sizes) {
            if (size <= 1000) { // Limit size for quadratic
                int[] arr = generateRandomArray(size);
                long start = System.nanoTime();
                bubbleSort(arr);
                long end = System.nanoTime();
                System.out.printf("   Size %6d: %8d ns (gets slow quickly)\n", size, (end - start));
            }
        }
        
        System.out.println("\n6. O(2ⁿ) - Exponential Time (Naive Fibonacci):");
        int[] fibSizes = {10, 20, 30, 40};
        for (int n : fibSizes) {
            if (n <= 40) {
                long start = System.currentTimeMillis();
                long result = fibonacci(n);
                long end = System.currentTimeMillis();
                System.out.printf("   n = %2d: %8d ms (fibonacci(%d) = %d)\n", n, (end - start), n, result);
                if (n >= 40) {
                    System.out.println("   Warning: n=40 takes several seconds!");
                    break;
                }
            }
        }
        
        System.out.println("\n=== Key Observations ===");
        System.out.println("• O(1) and O(log n): Stay fast even for large inputs");
        System.out.println("• O(n): Grows linearly - acceptable for most cases");
        System.out.println("• O(n log n): Good for sorting - scales well");
        System.out.println("• O(n²): Gets slow quickly - avoid for large inputs");
        System.out.println("• O(2ⁿ): Extremely slow - only for very small inputs");
        
        System.out.println("\n=== Complexity Growth Comparison ===");
        System.out.println("Input Size | O(1) | O(log n) | O(n) | O(n log n) | O(n²)");
        System.out.println("-----------|------|----------|------|-----------|------");
        System.out.println("10         | 1    | 3        | 10   | 33        | 100");
        System.out.println("100        | 1    | 7        | 100  | 664       | 10,000");
        System.out.println("1,000      | 1    | 10       | 1K   | 10K       | 1M");
        System.out.println("10,000     | 1    | 13       | 10K  | 133K      | 100M");
        
        System.out.println("\n=== Recommendations ===");
        System.out.println("✓ Use O(1) or O(log n) when possible");
        System.out.println("✓ O(n) is acceptable for most real-world problems");
        System.out.println("✓ O(n log n) is good for sorting");
        System.out.println("✗ Avoid O(n²) for large inputs");
        System.out.println("✗ Never use O(2ⁿ) or O(n!) in production code");
    }
}

