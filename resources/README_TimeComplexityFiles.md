# Time Complexity Java Examples

This directory contains practical Java examples demonstrating different time complexities. Each file focuses on a specific complexity class with multiple examples and detailed explanations.

## Files Overview

### 1. **ConstantTime.java** - O(1) Examples
- Array access operations
- HashMap/HashSet operations
- Stack/Queue operations
- Basic arithmetic operations

**Run:**
```bash
javac ConstantTime.java
java ConstantTime
```

### 2. **LogarithmicTime.java** - O(log n) Examples
- Binary search (iterative and recursive)
- Binary exponentiation
- Square root using binary search
- Finding peak elements
- BST operations

**Run:**
```bash
javac LogarithmicTime.java
java LogarithmicTime
```

### 3. **LinearTime.java** - O(n) Examples
- Finding max/min in array
- Linear search
- Summing array elements
- Array reversal
- Linked list traversal
- Counting occurrences

**Run:**
```bash
javac LinearTime.java
java LinearTime
```

### 4. **LinearithmicTime.java** - O(n log n) Examples
- Merge Sort (complete implementation)
- Quick Sort (average case)
- Heap Sort
- Finding intersections
- Counting inversions

**Run:**
```bash
javac LinearithmicTime.java
java LinearithmicTime
```

### 5. **QuadraticTime.java** - O(n²) Examples
- Bubble Sort
- Selection Sort
- Insertion Sort
- Finding duplicates (naive)
- Finding pairs
- Matrix operations

**Run:**
```bash
javac QuadraticTime.java
java QuadraticTime
```

### 6. **ExponentialTime.java** - O(2ⁿ) Examples
- Naive recursive Fibonacci
- Generating all subsets
- Tower of Hanoi
- Subset sum problem
- **WARNING:** Very slow for large inputs!

**Run:**
```bash
javac ExponentialTime.java
java ExponentialTime
```

### 7. **FactorialTime.java** - O(n!) Examples
- Generating all permutations
- Traveling Salesman Problem (brute force)
- Generating anagrams
- N-Queens problem
- **WARNING:** Extremely slow! Only for very small inputs (n ≤ 5-6)!

**Run:**
```bash
javac FactorialTime.java
java FactorialTime
```

### 8. **TimeComplexityDemo.java** - Performance Comparison
- Compares all time complexities with actual timing
- Shows how performance scales with input size
- Demonstrates the difference between complexities

**Run:**
```bash
javac TimeComplexityDemo.java
java TimeComplexityDemo
```

## Quick Start Guide

### Step 1: Compile a File
```bash
javac ConstantTime.java
```

### Step 2: Run the Compiled Class
```bash
java ConstantTime
```

### Step 3: Or Compile and Run in One Command
```bash
javac ConstantTime.java && java ConstantTime
```

## Recommended Learning Path

### Beginner Level
1. Start with **ConstantTime.java** - Understand O(1)
2. Then **LinearTime.java** - Understand O(n)
3. Then **LogarithmicTime.java** - Understand O(log n)

### Intermediate Level
4. **LinearithmicTime.java** - See efficient sorting algorithms
5. **QuadraticTime.java** - Understand why nested loops are slow

### Advanced Level
6. **ExponentialTime.java** - See exponential growth (with optimizations)
7. **FactorialTime.java** - See factorial growth
8. **TimeComplexityDemo.java** - Compare all complexities side-by-side

## Key Features

- ✅ **Well-commented code** - Every example has detailed explanations
- ✅ **Multiple examples per complexity** - See different use cases
- ✅ **Runnable code** - All examples can be executed directly
- ✅ **Performance comparisons** - See actual timing differences
- ✅ **Optimization examples** - Compare naive vs optimized solutions
- ✅ **Real-world problems** - Practical examples you'll encounter

## Tips for Learning

1. **Read the comments** - Each file has extensive documentation
2. **Run the examples** - See the code in action
3. **Modify the code** - Try changing inputs to see how it affects performance
4. **Compare approaches** - Notice the difference between naive and optimized solutions
5. **Use TimeComplexityDemo** - See actual performance differences

## Performance Warnings

⚠️ **ExponentialTime.java**: 
- Very slow for n > 30
- Use small inputs (n ≤ 20) for testing

⚠️ **FactorialTime.java**: 
- Extremely slow for n > 6
- Only test with very small inputs (n ≤ 5)

## Example Output

When you run `TimeComplexityDemo.java`, you'll see output like:

```
=== Time Complexity Performance Comparison ===

1. O(1) - Constant Time (Array Access):
   Size     10:      123 ns (always fast!)
   Size    100:      145 ns (always fast!)
   Size   1000:      156 ns (always fast!)

2. O(log n) - Logarithmic Time (Binary Search):
   Size     10:      234 ns (very fast!)
   Size    100:      456 ns (very fast!)
   Size   1000:      678 ns (very fast!)

...
```

## Notes

- All examples are self-contained and can be run independently
- Some examples use helper classes (ListNode, TreeNode) defined inline
- Performance may vary based on your system
- The code follows Java best practices and is production-ready

## Next Steps

After understanding these examples:
1. Try solving coding problems on LeetCode, HackerRank
2. Analyze time complexity of your own code
3. Learn about space complexity
4. Study advanced data structures (Trie, Segment Tree, etc.)
5. Practice optimizing algorithms

Happy Learning! 🚀

