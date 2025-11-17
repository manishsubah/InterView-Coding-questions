/**
 * O(1) - Constant Time Complexity Examples
 * 
 * These operations take the same amount of time regardless of input size.
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
import java.util.*;

public class ConstantTime {
    
    // Example 1: Accessing array element by index
    public static int getFirstItem(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        return arr[0];  // O(1) - Direct index access
    }
    
    // Example 2: Getting last element
    public static int getLastItem(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        return arr[arr.length - 1];  // O(1) - Direct calculation
    }
    
    // Example 3: HashMap get operation (average case)
    public static Integer getValueFromMap(HashMap<String, Integer> map, String key) {
        return map.get(key);  // O(1) average case - Hash-based access
    }
    
    // Example 4: HashSet contains operation
    public static boolean containsValue(HashSet<Integer> set, int value) {
        return set.contains(value);  // O(1) average case
    }
    
    // Example 5: Stack operations
    public static void demonstrateStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);    // O(1) - Add to top
        stack.push(20);
        stack.push(30);
        
        int top = stack.peek();  // O(1) - View top without removing
        System.out.println("Top element: " + top);
        
        int popped = stack.pop();  // O(1) - Remove from top
        System.out.println("Popped element: " + popped);
    }
    
    // Example 6: Queue operations
    public static void demonstrateQueue() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(10);   // O(1) - Add to end
        queue.offer(20);
        queue.offer(30);
        
        int front = queue.peek();  // O(1) - View front without removing
        System.out.println("Front element: " + front);
        
        int removed = queue.poll();  // O(1) - Remove from front
        System.out.println("Removed element: " + removed);
    }
    
    // Example 7: Basic arithmetic operations
    public static int add(int a, int b) {
        return a + b;  // O(1) - Constant time operation
    }
    
    // Example 8: Checking if number is even
    public static boolean isEven(int n) {
        return n % 2 == 0;  // O(1) - Single modulo operation
    }
    
    // Example 9: Swapping two array elements
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];  // O(1)
        arr[i] = arr[j];    // O(1)
        arr[j] = temp;      // O(1)
    }
    
    // Example 10: Getting size of collection
    public static int getSize(ArrayList<Integer> list) {
        return list.size();  // O(1) - Size is stored, not calculated
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println("=== O(1) Constant Time Examples ===\n");
        
        // Test 1: Array access
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("1. First element: " + getFirstItem(arr));
        System.out.println("   Last element: " + getLastItem(arr));
        
        // Test 2: HashMap operations
        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 5);
        map.put("banana", 3);
        map.put("cherry", 8);
        System.out.println("\n2. HashMap get('apple'): " + getValueFromMap(map, "apple"));
        
        // Test 3: HashSet operations
        HashSet<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        System.out.println("\n3. HashSet contains(20): " + containsValue(set, 20));
        System.out.println("   HashSet contains(99): " + containsValue(set, 99));
        
        // Test 4: Stack operations
        System.out.println("\n4. Stack operations:");
        demonstrateStack();
        
        // Test 5: Queue operations
        System.out.println("\n5. Queue operations:");
        demonstrateQueue();
        
        // Test 6: Basic operations
        System.out.println("\n6. Add(15, 27): " + add(15, 27));
        System.out.println("   Is 42 even? " + isEven(42));
        System.out.println("   Is 43 even? " + isEven(43));
        
        // Test 7: Swap operation
        int[] swapArr = {10, 20, 30, 40};
        System.out.println("\n7. Before swap: " + Arrays.toString(swapArr));
        swap(swapArr, 0, 3);
        System.out.println("   After swap: " + Arrays.toString(swapArr));
        
        // Test 8: Size operation
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("\n8. List size: " + getSize(list));
        
        System.out.println("\n=== All operations completed in O(1) time! ===");
    }
}

