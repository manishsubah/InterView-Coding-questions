/**
 * O(n!) - Factorial Time Complexity Examples
 * 
 * These operations generate all permutations or try all possible arrangements.
 * Time Complexity: O(n!)
 * 
 * THE SLOWEST! Should be avoided at all costs. Only use for very small inputs.
 */
import java.util.*;

public class FactorialTime {
    
    // Example 1: Generating all permutations
    public static List<List<Integer>> generatePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        generatePermutationsHelper(nums, new ArrayList<>(), used, result);
        return result;
    }
    
    private static void generatePermutationsHelper(int[] nums, 
                                                  List<Integer> current,
                                                  boolean[] used,
                                                  List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));  // n! permutations
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums[i]);
                generatePermutationsHelper(nums, current, used, result);
                current.remove(current.size() - 1);  // Backtrack
                used[i] = false;
            }
        }
    }
    
    // Example 2: Traveling Salesman Problem (brute force)
    public static int tspBruteForce(int[][] graph, int start) {
        int n = graph.length;
        if (n <= 1) return 0;
        
        List<Integer> cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i != start) cities.add(i);
        }
        
        int minCost = Integer.MAX_VALUE;
        List<List<Integer>> allPermutations = generatePermutations(
            cities.stream().mapToInt(i -> i).toArray()
        );
        
        for (List<Integer> perm : allPermutations) {
            int cost = graph[start][perm.get(0)];
            for (int i = 0; i < perm.size() - 1; i++) {
                cost += graph[perm.get(i)][perm.get(i + 1)];
            }
            cost += graph[perm.get(perm.size() - 1)][start];
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }
    
    // Example 3: Generating all arrangements (anagrams)
    public static List<String> generateAnagrams(String str) {
        List<String> result = new ArrayList<>();
        char[] chars = str.toCharArray();
        generateAnagramsHelper(chars, 0, result);
        return result;
    }
    
    private static void generateAnagramsHelper(char[] chars, int index, 
                                             List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));  // n! arrangements
            return;
        }
        
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            generateAnagramsHelper(chars, index + 1, result);
            swap(chars, index, i);  // Backtrack
        }
    }
    
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    
    // Example 4: Finding all valid parentheses combinations (catalan number related)
    public static List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesesHelper("", 0, 0, n, result);
        return result;
    }
    
    private static void generateParenthesesHelper(String current, int open, int close, 
                                                 int n, List<String> result) {
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }
        
        if (open < n) {
            generateParenthesesHelper(current + "(", open + 1, close, n, result);
        }
        if (close < open) {
            generateParenthesesHelper(current + ")", open, close + 1, n, result);
        }
    }
    
    // Example 5: N-Queens problem (brute force - checking all arrangements)
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];
        solveNQueensHelper(queens, 0, result);
        return result;
    }
    
    private static void solveNQueensHelper(int[] queens, int row, List<List<String>> result) {
        int n = queens.length;
        if (row == n) {
            // Check if valid solution
            if (isValid(queens)) {
                result.add(formatSolution(queens));
            }
            return;
        }
        
        for (int col = 0; col < n; col++) {
            queens[row] = col;
            solveNQueensHelper(queens, row + 1, result);
        }
    }
    
    private static boolean isValid(int[] queens) {
        int n = queens.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (queens[i] == queens[j] || 
                    Math.abs(queens[i] - queens[j]) == Math.abs(i - j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static List<String> formatSolution(int[] queens) {
        List<String> solution = new ArrayList<>();
        int n = queens.length;
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            solution.add(new String(row));
        }
        return solution;
    }
    
    // Example 6: Finding all paths in a grid (brute force)
    public static int countPaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        // This is actually O(2^(m+n)), but demonstrates factorial-like growth
        return countPaths(m - 1, n) + countPaths(m, n - 1);
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println("=== O(n!) Factorial Time Examples ===\n");
        System.out.println("WARNING: These algorithms are EXTREMELY SLOW!");
        System.out.println("Only use for very small inputs (n <= 5-6)\n");
        
        // Test 1: Generate permutations (small input only!)
        System.out.println("1. Generate All Permutations:");
        int[] nums = {1, 2, 3};
        System.out.println("   Input: " + Arrays.toString(nums));
        List<List<Integer>> permutations = generatePermutations(nums);
        System.out.println("   Number of permutations: " + permutations.size() + " (3! = 6)");
        System.out.println("   Permutations:");
        for (List<Integer> perm : permutations) {
            System.out.println("     " + perm);
        }
        System.out.println("   Note: For n=10, there would be 3,628,800 permutations!");
        
        // Test 2: Anagrams
        System.out.println("\n2. Generate All Anagrams:");
        String word = "ABC";
        List<String> anagrams = generateAnagrams(word);
        System.out.println("   Word: " + word);
        System.out.println("   Number of anagrams: " + anagrams.size() + " (3! = 6)");
        System.out.println("   Anagrams: " + anagrams);
        
        // Test 3: Traveling Salesman Problem (very small!)
        System.out.println("\n3. Traveling Salesman Problem (Brute Force):");
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        System.out.println("   Cities: 4");
        System.out.println("   Calculating minimum cost...");
        long start = System.currentTimeMillis();
        int minCost = tspBruteForce(graph, 0);
        long end = System.currentTimeMillis();
        System.out.println("   Minimum cost: " + minCost);
        System.out.println("   Time taken: " + (end - start) + " ms");
        System.out.println("   Note: For 10 cities, this would take hours!");
        
        // Test 4: Generate parentheses
        System.out.println("\n4. Generate Valid Parentheses:");
        int n = 3;
        List<String> parentheses = generateParentheses(n);
        System.out.println("   n = " + n);
        System.out.println("   Number of combinations: " + parentheses.size());
        System.out.println("   Combinations: " + parentheses);
        System.out.println("   Note: This is actually Catalan number, not exactly n!");
        
        // Test 5: N-Queens (small only!)
        System.out.println("\n5. N-Queens Problem (Brute Force):");
        int queens = 4;
        System.out.println("   n = " + queens);
        System.out.println("   Finding solutions...");
        start = System.currentTimeMillis();
        List<List<String>> solutions = solveNQueens(queens);
        end = System.currentTimeMillis();
        System.out.println("   Number of solutions: " + solutions.size());
        System.out.println("   Time taken: " + (end - start) + " ms");
        if (solutions.size() > 0) {
            System.out.println("   First solution:");
            for (String row : solutions.get(0)) {
                System.out.println("     " + row);
            }
        }
        System.out.println("   Note: For n=8, this would be extremely slow!");
        
        // Performance warning
        System.out.println("\n=== Performance Warning ===");
        System.out.println("Input Size | Operations | Time Estimate");
        System.out.println("-----------|------------|---------------");
        System.out.println("n=3        | 6          | < 1 ms");
        System.out.println("n=4        | 24         | < 1 ms");
        System.out.println("n=5        | 120        | ~1 ms");
        System.out.println("n=6        | 720        | ~10 ms");
        System.out.println("n=7        | 5,040      | ~100 ms");
        System.out.println("n=8        | 40,320     | ~1 second");
        System.out.println("n=9        | 362,880    | ~10 seconds");
        System.out.println("n=10       | 3,628,800  | ~2 minutes");
        System.out.println("n=11       | 39,916,800 | ~20 minutes");
        System.out.println("n=12       | 479,001,600| ~4 hours");
        
        System.out.println("\n=== Key Takeaways ===");
        System.out.println("1. Factorial algorithms are THE SLOWEST");
        System.out.println("2. Only use for very small inputs (n <= 5-6)");
        System.out.println("3. Consider approximation algorithms or heuristics");
        System.out.println("4. Many problems with O(n!) brute force have better solutions");
        System.out.println("5. Examples: TSP has dynamic programming solution O(n²2ⁿ)");
        System.out.println("6. N-Queens can be solved with backtracking (much faster)");
        
        System.out.println("\n=== All operations demonstrate O(n!) complexity! ===");
        System.out.println("Avoid these algorithms for production code!");
    }
}

