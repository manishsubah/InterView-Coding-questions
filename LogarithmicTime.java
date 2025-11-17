/**
 * O(log n) - Logarithmic Time Complexity Examples
 * 
 * These operations eliminate half of the remaining possibilities at each step.
 * Time Complexity: O(log n)
 * 
 * Why log n? Because we repeatedly divide the problem size by a constant factor.
 */
import java.util.*;

public class LogarithmicTime {
    
    // Example 1: Binary Search in sorted array
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;  // Prevents overflow
            
            if (arr[mid] == target) {
                return mid;  // Found!
            } else if (arr[mid] < target) {
                left = mid + 1;   // Search right half - eliminate left half
            } else {
                right = mid - 1;  // Search left half - eliminate right half
            }
        }
        return -1;  // Not found
    }
    
    // Example 2: Binary Search (Recursive)
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;  // Not found
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);  // Right half
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);   // Left half
        }
    }
    
    // Example 3: Finding power using binary exponentiation
    public static long power(long base, long exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        
        long half = power(base, exponent / 2);
        if (exponent % 2 == 0) {
            return half * half;  // x^8 = (x^4)^2
        } else {
            return half * half * base;  // x^9 = (x^4)^2 * x
        }
    }
    
    // Example 4: Finding square root using binary search
    public static int sqrt(int x) {
        if (x < 2) {
            return x;
        }
        
        int left = 1;
        int right = x / 2;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            
            if (square == x) {
                return mid;
            } else if (square < x) {
                left = mid + 1;  // Search right half
            } else {
                right = mid - 1;  // Search left half
            }
        }
        return right;  // Return floor of square root
    }
    
    // Example 5: Finding first occurrence in sorted array with duplicates
    public static int findFirstOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1;  // Continue searching left for first occurrence
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    // Example 6: Finding peak element in array
    public static int findPeak(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[mid + 1]) {
                right = mid;  // Peak is in left half
            } else {
                left = mid + 1;  // Peak is in right half
            }
        }
        return left;
    }
    
    // Example 7: Counting how many times we can divide by 2
    public static int countDivisions(int n) {
        int count = 0;
        while (n > 0) {
            n = n / 2;  // Halve the number each time
            count++;
        }
        return count;  // This is approximately log₂(n)
    }
    
    // Example 8: Finding minimum in rotated sorted array
    public static int findMinInRotatedArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[right]) {
                left = mid + 1;  // Minimum is in right half
            } else {
                right = mid;  // Minimum is in left half (including mid)
            }
        }
        return arr[left];
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println("=== O(log n) Logarithmic Time Examples ===\n");
        
        // Test 1: Binary Search
        int[] sortedArr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25};
        int target = 13;
        System.out.println("1. Binary Search:");
        System.out.println("   Array: " + Arrays.toString(sortedArr));
        System.out.println("   Searching for: " + target);
        int index = binarySearch(sortedArr, target);
        System.out.println("   Found at index: " + index);
        System.out.println("   Steps taken: ~" + (int)(Math.log(sortedArr.length) / Math.log(2)) + " (log₂(" + sortedArr.length + "))");
        
        // Test 2: Binary Search Recursive
        System.out.println("\n2. Binary Search (Recursive):");
        int index2 = binarySearchRecursive(sortedArr, 7, 0, sortedArr.length - 1);
        System.out.println("   Found 7 at index: " + index2);
        
        // Test 3: Power calculation
        System.out.println("\n3. Binary Exponentiation:");
        long base = 2;
        long exponent = 10;
        long result = power(base, exponent);
        System.out.println("   " + base + "^" + exponent + " = " + result);
        System.out.println("   Steps: ~" + (int)(Math.log(exponent) / Math.log(2)) + " (log₂(" + exponent + "))");
        
        // Test 4: Square root
        System.out.println("\n4. Square Root using Binary Search:");
        int[] numbers = {16, 25, 36, 49, 100};
        for (int num : numbers) {
            int sqrt = sqrt(num);
            System.out.println("   √" + num + " = " + sqrt);
        }
        
        // Test 5: First occurrence
        System.out.println("\n5. Find First Occurrence:");
        int[] arrWithDupes = {1, 2, 2, 2, 3, 3, 4, 4, 4, 4, 5};
        System.out.println("   Array: " + Arrays.toString(arrWithDupes));
        int first = findFirstOccurrence(arrWithDupes, 4);
        System.out.println("   First occurrence of 4: index " + first);
        
        // Test 6: Peak element
        System.out.println("\n6. Find Peak Element:");
        int[] peakArr = {1, 3, 5, 7, 9, 8, 6, 4, 2};
        System.out.println("   Array: " + Arrays.toString(peakArr));
        int peak = findPeak(peakArr);
        System.out.println("   Peak element: " + peakArr[peak] + " at index " + peak);
        
        // Test 7: Count divisions
        System.out.println("\n7. Count Divisions by 2:");
        int[] testNums = {8, 16, 32, 64, 128, 1000};
        for (int n : testNums) {
            int count = countDivisions(n);
            System.out.println("   " + n + " can be divided " + count + " times (log₂(" + n + ") ≈ " + 
                             (int)(Math.log(n) / Math.log(2)) + ")");
        }
        
        // Test 8: Minimum in rotated array
        System.out.println("\n8. Find Minimum in Rotated Sorted Array:");
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("   Array: " + Arrays.toString(rotated));
        int min = findMinInRotatedArray(rotated);
        System.out.println("   Minimum: " + min);
        
        System.out.println("\n=== All operations completed in O(log n) time! ===");
        System.out.println("Key insight: Each step eliminates half the search space!");
    }
}

