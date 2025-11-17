/**
 * O(n log n) - Linearithmic Time Complexity Examples
 * 
 * These operations combine O(n) and O(log n) complexities.
 * Common in efficient sorting algorithms.
 * Time Complexity: O(n log n)
 * 
 * Examples: Merge Sort, Quick Sort (average case), Heap Sort
 */
import java.util.*;

public class LinearithmicTime {
    
    // Example 1: Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // Divide: O(log n) recursive calls
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            // Conquer: O(n) merge at each level
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Copy data to temp arrays - O(n)
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        // Merge temp arrays - O(n)
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        
        // Copy remaining elements
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }
    
    // Example 2: Quick Sort (average case O(n log n), worst case O(n²))
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition: O(n)
            int pivotIndex = partition(arr, low, high);
            
            // Recursively sort: O(log n) levels
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Example 3: Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Build heap - O(n)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extract elements from heap - O(n log n)
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);  // O(log n) per extraction
        }
    }
    
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
    
    // Example 4: Using Arrays.sort() - O(n log n)
    public static void sortWithBuiltIn(int[] arr) {
        Arrays.sort(arr);  // Uses TimSort (hybrid of merge and insertion sort)
    }
    
    // Example 5: Finding intersection of two sorted arrays
    public static List<Integer> findIntersection(int[] arr1, int[] arr2) {
        // First, sort both arrays if not sorted: O(n log n) + O(m log m)
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        
        // Then find intersection: O(n + m)
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                result.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
    
    // Example 6: Finding kth largest element using sorting
    public static int findKthLargest(int[] arr, int k) {
        Arrays.sort(arr);  // O(n log n)
        return arr[arr.length - k];  // O(1)
    }
    
    // Example 7: Counting inversions (using merge sort approach)
    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);
        
        int i = 0, j = 0, k = left, inversions = 0;
        
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                inversions += (mid + 1) - (left + i);
            }
        }
        
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
        
        return inversions;
    }
    
    public static int countInversions(int[] arr, int left, int right) {
        int inversions = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            inversions += countInversions(arr, left, mid);
            inversions += countInversions(arr, mid + 1, right);
            inversions += mergeAndCount(arr, left, mid, right);
        }
        return inversions;
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println("=== O(n log n) Linearithmic Time Examples ===\n");
        
        // Test 1: Merge Sort
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("1. Merge Sort:");
        System.out.println("   Before: " + Arrays.toString(arr1));
        mergeSort(arr1, 0, arr1.length - 1);
        System.out.println("   After: " + Arrays.toString(arr1));
        System.out.println("   Complexity: O(n log n) - divide and conquer");
        
        // Test 2: Quick Sort
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("\n2. Quick Sort:");
        System.out.println("   Before: " + Arrays.toString(arr2));
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println("   After: " + Arrays.toString(arr2));
        System.out.println("   Complexity: O(n log n) average, O(n²) worst case");
        
        // Test 3: Heap Sort
        int[] arr3 = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("\n3. Heap Sort:");
        System.out.println("   Before: " + Arrays.toString(arr3));
        heapSort(arr3);
        System.out.println("   After: " + Arrays.toString(arr3));
        System.out.println("   Complexity: O(n log n) guaranteed");
        
        // Test 4: Built-in sort
        int[] arr4 = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("\n4. Arrays.sort() (TimSort):");
        System.out.println("   Before: " + Arrays.toString(arr4));
        sortWithBuiltIn(arr4);
        System.out.println("   After: " + Arrays.toString(arr4));
        
        // Test 5: Find intersection
        System.out.println("\n5. Find Intersection of Two Arrays:");
        int[] arr5 = {1, 2, 3, 4, 5};
        int[] arr6 = {3, 4, 5, 6, 7};
        System.out.println("   Array 1: " + Arrays.toString(arr5));
        System.out.println("   Array 2: " + Arrays.toString(arr6));
        List<Integer> intersection = findIntersection(arr5, arr6);
        System.out.println("   Intersection: " + intersection);
        
        // Test 6: Find kth largest
        int[] arr7 = {3, 2, 1, 5, 6, 4};
        System.out.println("\n6. Find Kth Largest Element:");
        System.out.println("   Array: " + Arrays.toString(arr7));
        int k = 2;
        int kth = findKthLargest(arr7, k);
        System.out.println("   " + k + "nd largest: " + kth);
        
        // Test 7: Count inversions
        int[] arr8 = {2, 4, 1, 3, 5};
        System.out.println("\n7. Count Inversions:");
        System.out.println("   Array: " + Arrays.toString(arr8));
        int[] arr8Copy = arr8.clone();
        int inversions = countInversions(arr8Copy, 0, arr8Copy.length - 1);
        System.out.println("   Number of inversions: " + inversions);
        System.out.println("   (Inversions: (2,1), (4,1), (4,3))");
        
        // Performance comparison
        System.out.println("\n=== Performance Insight ===");
        System.out.println("For array of size 10: ~33 operations");
        System.out.println("For array of size 100: ~664 operations");
        System.out.println("For array of size 1000: ~9,966 operations");
        System.out.println("Much better than O(n²) but slower than O(n)");
        
        System.out.println("\n=== All operations completed in O(n log n) time! ===");
        System.out.println("This is the best possible complexity for comparison-based sorting!");
    }
}

