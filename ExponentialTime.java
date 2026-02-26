/**
 * O(2ⁿ) - Exponential Time Complexity Examples
 * 
 * These operations double in time with each additional input element.
 * Time Complexity: O(2ⁿ) or O(cⁿ) where c > 1
 * 
 * VERY SLOW! Should be avoided when possible. Use memoization or dynamic programming.
 */
import java.util.*;

public class ExponentialTime {
    
    // Example 1: recursive Fibonacci
    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        // Each call branches into 2 calls: O(2ⁿ)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // Example 2: Generating all subsets of an array
    public static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsetsHelper(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateSubsetsHelper(int[] nums, int index, 
                                            List<Integer> current, 
                                            List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));  // 2ⁿ subsets
            return;
        }
        
        // Don't include current element
        generateSubsetsHelper(nums, index + 1, current, result);
        
        // Include current element
        current.add(nums[index]);
        generateSubsetsHelper(nums, index + 1, current, result);
        current.remove(current.size() - 1);  // Backtrack
    }
    
    // Example 3: Tower of Hanoi
    public static void towerOfHanoi(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
            return;
        }
        // Each call makes 2 recursive calls: O(2ⁿ)
        towerOfHanoi(n - 1, from, aux, to);   // First recursive call
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        towerOfHanoi(n - 1, aux, to, from);   // Second recursive call
    }
    
    // Example 4: Recursive power calculation (naive)
    public static long powerNaive(long base, long exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        // This is actually O(exponent), but demonstrates exponential growth
        return base * powerNaive(base, exponent - 1);
    }
    
    // Example 5: Finding all combinations of coins that sum to target
    public static int coinChangeCombinations(int[] coins, int target) {
        return coinChangeHelper(coins, target, 0);
    }
    
    private static int coinChangeHelper(int[] coins, int target, int index) {
        if (target == 0) {
            return 1;  // Found a valid combination
        }
        if (target < 0 || index >= coins.length) {
            return 0;  // Invalid
        }
        // Try including current coin and not including it
        return coinChangeHelper(coins, target - coins[index], index) +
               coinChangeHelper(coins, target, index + 1);
    }
    
    // Example 6: Checking if subset sum exists (naive)
    public static boolean subsetSum(int[] arr, int target) {
        return subsetSumHelper(arr, target, 0);
    }
    
    private static boolean subsetSumHelper(int[] arr, int target, int index) {
        if (target == 0) {
            return true;
        }
        if (index >= arr.length || target < 0) {
            return false;
        }
        // Try including current element or not
        return subsetSumHelper(arr, target - arr[index], index + 1) ||
               subsetSumHelper(arr, target, index + 1);
    }
    
    // Example 7: Counting ways to climb stairs (naive)
    public static int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        // Can take 1 or 2 steps: O(2ⁿ)
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    // Example 8: Generating all binary strings of length n
    public static List<String> generateBinaryStrings(int n) {
        List<String> result = new ArrayList<>();
        generateBinaryStringsHelper("", n, result);
        return result;
    }
    
    private static void generateBinaryStringsHelper(String current, int n, List<String> result) {
        if (current.length() == n) {
            result.add(current);  // 2ⁿ binary strings
            return;
        }
        generateBinaryStringsHelper(current + "0", n, result);
        generateBinaryStringsHelper(current + "1", n, result);
    }
    
    // OPTIMIZED VERSIONS FOR COMPARISON
    
    // Optimized Fibonacci using memoization: O(n)
    public static long fibonacciOptimized(int n) {
        long[] memo = new long[n + 1];
        return fibonacciMemo(n, memo);
    }
    
    private static long fibonacciMemo(int n, long[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        return memo[n];
    }
    
    // Optimized climb stairs: O(n)
    public static int climbStairsOptimized(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println("=== O(2ⁿ) Exponential Time Examples ===\n");
        System.out.println("WARNING: These algorithms are VERY SLOW for large inputs!\n");
        
        // Test 1: Fibonacci (small values only!)
        System.out.println("1. Naive Recursive Fibonacci:");
        System.out.println("   Calculating fibonacci(10)...");
        long start = System.currentTimeMillis();
        long fib10 = fibonacci(10);
        long end = System.currentTimeMillis();
        System.out.println("   fibonacci(10) = " + fib10);
        System.out.println("   Time taken: " + (end - start) + " ms");
        System.out.println("   Note: fibonacci(40) would take several seconds!");
        
        System.out.println("\n   Optimized Fibonacci (with memoization):");
        start = System.currentTimeMillis();
        long fib40 = fibonacciOptimized(40);
        end = System.currentTimeMillis();
        System.out.println("   fibonacci(40) = " + fib40);
        System.out.println("   Time taken: " + (end - start) + " ms");
        System.out.println("   Complexity: O(n) instead of O(2ⁿ)!");
        
        // Test 2: Generate subsets
        System.out.println("\n2. Generate All Subsets:");
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = generateSubsets(nums);
        System.out.println("   Array: " + Arrays.toString(nums));
        System.out.println("   Number of subsets: " + subsets.size() + " (2³ = 8)");
        System.out.println("   Subsets:");
        for (List<Integer> subset : subsets) {
            System.out.println("     " + subset);
        }
        
        // Test 3: Tower of Hanoi (small n)
        System.out.println("\n3. Tower of Hanoi (n=3):");
        System.out.println("   Steps to move 3 disks:");
        towerOfHanoi(3, 'A', 'C', 'B');
        System.out.println("   Total moves: " + (int)(Math.pow(2, 3) - 1) + " (2ⁿ - 1)");
        
        // Test 4: Coin change combinations
        System.out.println("\n4. Coin Change Combinations:");
        int[] coins = {1, 2, 5};
        int target = 5;
        int combinations = coinChangeCombinations(coins, target);
        System.out.println("   Coins: " + Arrays.toString(coins));
        System.out.println("   Target: " + target);
        System.out.println("   Combinations: " + combinations);
        
        // Test 5: Subset sum
        System.out.println("\n5. Subset Sum Problem:");
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        boolean exists = subsetSum(arr, sum);
        System.out.println("   Array: " + Arrays.toString(arr));
        System.out.println("   Target sum: " + sum);
        System.out.println("   Subset exists: " + exists);
        
        // Test 6: Climb stairs
        System.out.println("\n6. Climb Stairs (Naive vs Optimized):");
        int n = 10;
        System.out.println("   Naive approach (n=" + n + "):");
        start = System.currentTimeMillis();
        int ways1 = climbStairs(n);
        end = System.currentTimeMillis();
        System.out.println("   Ways: " + ways1 + ", Time: " + (end - start) + " ms");
        
        System.out.println("   Optimized approach (n=" + n + "):");
        start = System.currentTimeMillis();
        int ways2 = climbStairsOptimized(n);
        end = System.currentTimeMillis();
        System.out.println("   Ways: " + ways2 + ", Time: " + (end - start) + " ms");
        
        // Test 7: Binary strings
        System.out.println("\n7. Generate Binary Strings:");
        List<String> binaryStrings = generateBinaryStrings(3);
        System.out.println("   Length: 3");
        System.out.println("   Number of strings: " + binaryStrings.size() + " (2³ = 8)");
        System.out.println("   Strings: " + binaryStrings);
        
        // Performance comparison
        System.out.println("\n=== Performance Comparison ===");
        System.out.println("Input Size | Naive O(2ⁿ) | Optimized O(n)");
        System.out.println("-----------|--------------|----------------");
        System.out.println("n=10       | ~1,024 ops   | ~10 ops");
        System.out.println("n=20       | ~1M ops      | ~20 ops");
        System.out.println("n=30       | ~1B ops      | ~30 ops");
        System.out.println("n=40       | ~1T ops      | ~40 ops");
        
        System.out.println("\n=== Key Takeaways ===");
        System.out.println("1. Exponential algorithms are VERY slow");
        System.out.println("2. Use memoization or dynamic programming to optimize");
        System.out.println("3. Consider if the problem can be solved differently");
        System.out.println("4. For large inputs, exponential algorithms are impractical");
        
        System.out.println("\n=== All operations demonstrate O(2ⁿ) complexity! ===");
    }
}

