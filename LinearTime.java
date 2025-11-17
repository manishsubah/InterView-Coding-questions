/**
 * O(n) - Linear Time Complexity Examples
 * 
 * These operations process each element exactly once.
 * Time Complexity: O(n) where n is the input size
 * 
 * If input doubles, time doubles. Directly proportional relationship.
 */
import java.util.*;

public class LinearTime {
    
    // Example 1: Finding maximum in unsorted array
    public static int findMax(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int maxVal = arr[0];
        for (int num : arr) {  // Visit each element once: O(n)
            if (num > maxVal) {
                maxVal = num;
            }
        }
        return maxVal;
    }
    
    // Example 2: Finding minimum
    public static int findMin(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int minVal = arr[0];
        for (int num : arr) {  // O(n)
            if (num < minVal) {
                minVal = num;
            }
        }
        return minVal;
    }
    
    // Example 3: Linear search in unsorted array
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {  // O(n)
            if (arr[i] == target) {
                return i;  // Found at index i
            }
        }
        return -1;  // Not found
    }
    
    // Example 4: Summing all elements
    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int num : arr) {  // O(n)
            sum += num;
        }
        return sum;
    }
    
    // Example 5: Calculating average
    public static double calculateAverage(int[] arr) {
        if (arr.length == 0) return 0;
        
        int sum = sumArray(arr);  // O(n)
        return (double) sum / arr.length;  // O(1)
    }
    
    // Example 6: Counting occurrences
    public static int countOccurrences(int[] arr, int target) {
        int count = 0;
        for (int num : arr) {  // O(n)
            if (num == target) {
                count++;
            }
        }
        return count;
    }
    
    // Example 7: Copying an array
    public static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {  // O(n)
            copy[i] = arr[i];
        }
        return copy;
    }
    
    // Example 8: Reversing an array
    public static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {  // O(n/2) = O(n)
            // Swap
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            
            left++;
            right--;
        }
    }
    
    // Example 9: Finding all even numbers
    public static List<Integer> findEvenNumbers(int[] arr) {
        List<Integer> evens = new ArrayList<>();
        for (int num : arr) {  // O(n)
            if (num % 2 == 0) {
                evens.add(num);
            }
        }
        return evens;
    }
    
    // Example 10: Traversing a linked list
    public static void traverseLinkedList(ListNode head) {
        ListNode current = head;
        int count = 0;
        while (current != null) {  // O(n) where n is number of nodes
            System.out.print(current.val + " -> ");
            current = current.next;
            count++;
        }
        System.out.println("null");
        System.out.println("Total nodes: " + count);
    }
    
    // Example 11: Converting array to string
    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {  // O(n)
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    // Example 12: Finding sum of two largest numbers
    public static int sumOfTwoLargest(int[] arr) {
        if (arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least 2 elements");
        }
        
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        
        for (int num : arr) {  // O(n)
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second) {
                second = num;
            }
        }
        return first + second;
    }
    
    // Helper class for linked list example
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println("=== O(n) Linear Time Examples ===\n");
        
        int[] arr = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        System.out.println("Test array: " + Arrays.toString(arr));
        
        // Test 1: Find max and min
        System.out.println("\n1. Finding Maximum and Minimum:");
        System.out.println("   Maximum: " + findMax(arr));
        System.out.println("   Minimum: " + findMin(arr));
        System.out.println("   Operations: O(n) - visited each element once");
        
        // Test 2: Linear search
        System.out.println("\n2. Linear Search:");
        int target = 7;
        int index = linearSearch(arr, target);
        System.out.println("   Searching for: " + target);
        System.out.println("   Found at index: " + index);
        System.out.println("   Worst case: O(n) if element is at the end");
        
        // Test 3: Sum and average
        System.out.println("\n3. Sum and Average:");
        int sum = sumArray(arr);
        double avg = calculateAverage(arr);
        System.out.println("   Sum: " + sum);
        System.out.println("   Average: " + String.format("%.2f", avg));
        
        // Test 4: Count occurrences
        int[] arrWithDupes = {1, 2, 3, 2, 4, 2, 5, 2};
        System.out.println("\n4. Count Occurrences:");
        System.out.println("   Array: " + Arrays.toString(arrWithDupes));
        System.out.println("   Count of 2: " + countOccurrences(arrWithDupes, 2));
        
        // Test 5: Copy array
        System.out.println("\n5. Copy Array:");
        int[] copy = copyArray(arr);
        System.out.println("   Original: " + Arrays.toString(arr));
        System.out.println("   Copy: " + Arrays.toString(copy));
        
        // Test 6: Reverse array
        System.out.println("\n6. Reverse Array:");
        int[] reverseArr = copyArray(arr);
        System.out.println("   Before: " + Arrays.toString(reverseArr));
        reverseArray(reverseArr);
        System.out.println("   After: " + Arrays.toString(reverseArr));
        
        // Test 7: Find even numbers
        System.out.println("\n7. Find Even Numbers:");
        List<Integer> evens = findEvenNumbers(arr);
        System.out.println("   Even numbers: " + evens);
        
        // Test 8: Linked list traversal
        System.out.println("\n8. Linked List Traversal:");
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        traverseLinkedList(head);
        
        // Test 9: Array to string
        System.out.println("\n9. Array to String:");
        String str = arrayToString(arr);
        System.out.println("   " + str);
        
        // Test 10: Sum of two largest
        System.out.println("\n10. Sum of Two Largest:");
        System.out.println("   Array: " + Arrays.toString(arr));
        System.out.println("   Sum of two largest: " + sumOfTwoLargest(arr));
        
        // Performance comparison
        System.out.println("\n=== Performance Insight ===");
        System.out.println("For array of size 10: ~10 operations");
        System.out.println("For array of size 100: ~100 operations");
        System.out.println("For array of size 1000: ~1000 operations");
        System.out.println("Time grows linearly with input size!");
        
        System.out.println("\n=== All operations completed in O(n) time! ===");
    }
}

