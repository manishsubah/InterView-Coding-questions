# Time Complexity Explained in Simple Words

input size and running time:
if size is n => n (linear)
if size is increased by 2 times => 4 times
if size is increased by 3 times => 9 times

time complexity directly proportional to input n (linear way)

-> Best case = omega(1)
-> Average case = thita
-> Worst case = big O

## What is Time Complexity?

**Time complexity** is a way to describe how long an algorithm takes to run as the size of the input grows. Think of it as answering the question: "If I give this program more data, how much slower will it become?"

It helps us understand and compare different algorithms to choose the most efficient one for our needs.

## Why Does It Matter?

Imagine you have two ways to find a book in a library:
- **Method 1**: Check every book one by one from the first shelf to the last
- **Method 2**: Use the library's catalog system to find the exact shelf

As the library gets bigger (more books), Method 1 becomes much slower, while Method 2 stays fast. Time complexity helps us predict this behavior.

## Big O Notation

We use **Big O notation** (written as O(...)) to describe time complexity. The "O" stands for "Order of" - it tells us the worst-case scenario of how an algorithm performs.

### Common Time Complexities (from fastest to slowest)

#### O(1) - Constant Time
**What it means**: The algorithm takes the same amount of time no matter how big the input is.

**Example**: Getting the first item from an array

**Python:**
```python
def get_first_item(arr):
    return arr[0]  # Always takes the same time, regardless of array size
```

**Java:**
```java
public class ConstantTime {
    // O(1) - Accessing array element by index
    public static int getFirstItem(int[] arr) {
        return arr[0];  // Always takes the same time, regardless of array size
    }
    
    // O(1) - Adding to end of ArrayList (amortized)
    public static void addToEnd(ArrayList<Integer> list, int value) {
        list.add(value);  // Usually O(1), occasionally O(n) when resizing
    }
    
    // O(1) - HashMap get operation (average case)
    public static Integer getValue(HashMap<String, Integer> map, String key) {
        return map.get(key);  // Direct hash-based access
    }
    
    // O(1) - Stack/Queue operations
    public static void stackOperations() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);    // O(1)
        stack.pop();       // O(1)
        stack.peek();      // O(1)
    }
}
```

**Real-world analogy**: Opening a door - it takes the same time whether you're the only person or one of a thousand people.

---

#### O(log n) - Logarithmic Time
**What it means**: The time grows slowly as the input size increases. Each step eliminates half of the remaining possibilities.

**Example**: Binary search (finding an item in a sorted list)

**Python:**
```python
def binary_search(arr, target):
    left, right = 0, len(arr) - 1
    
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1
```

**Java:**
```java
public class LogarithmicTime {
    // O(log n) - Binary search in sorted array
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;  // Prevents overflow
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;  // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return -1;  // Not found
    }
    
    // O(log n) - Finding element in balanced Binary Search Tree
    // Note: TreeNode is a custom class typically defined as:
    // class TreeNode { int val; TreeNode left; TreeNode right; }
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST(root.left, val);   // Eliminate right half
        } else {
            return searchBST(root.right, val);  // Eliminate left half
        }
    }
    
    // O(log n) - Finding power using binary exponentiation
    public static long power(long base, long exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        
        long half = power(base, exponent / 2);
        if (exponent % 2 == 0) {
            return half * half;  // x^8 = (x^4)^2
        } else {
            return half * half * base;  // x^9 = (x^4)^2 * x
        }
    }
}
```

**Real-world analogy**: Finding a word in a dictionary. You open to the middle, see if your word comes before or after, then eliminate half the book. Repeat until found.

**Why it's fast**: 
- 10 items → ~3-4 steps
- 100 items → ~7 steps
- 1,000 items → ~10 steps
- 1,000,000 items → ~20 steps

---

#### O(n) - Linear Time
**What it means**: The time grows proportionally with the input size. If you double the input, you double the time.

**Example**: Finding the maximum value in an unsorted array

**Python:**
```python
def find_max(arr):
    max_val = arr[0]
    for num in arr:  # Must check every element once
        if num > max_val:
            max_val = num
    return max_val
```

**Java:**
```java
public class LinearTime {
    // O(n) - Finding maximum in unsorted array
    public static int findMax(int[] arr) {
        int maxVal = arr[0];
        for (int num : arr) {  // Must check every element once
            if (num > maxVal) {
                maxVal = num;
            }
        }
        return maxVal;
    }
    
    // O(n) - Linear search in unsorted array
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;  // Found at index i
            }
        }
        return -1;  // Not found
    }
    
    // O(n) - Summing all elements
    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;  // Visit each element once
        }
        return sum;
    }
    
    // O(n) - Traversing a linked list
    // Note: ListNode is a custom class typically defined as:
    // class ListNode { int val; ListNode next; }
    public static void traverseLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;  // Visit each node once
        }
    }
    
    // O(n) - Copying an array
    public static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];  // Copy each element once
        }
        return copy;
    }
}
```

**Real-world analogy**: Reading every page of a book. If the book has 100 pages, you read 100 pages. If it has 200 pages, you read 200 pages.

**Why it's reasonable**:
- 10 items → 10 operations
- 100 items → 100 operations
- 1,000 items → 1,000 operations

---

#### O(n log n) - Linearithmic Time
**What it means**: Slightly slower than linear time, but still quite efficient. Common in efficient sorting algorithms.

**Example**: Merge sort, Quick sort (average case)

**Python:**
```python
def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])      # O(log n) divisions
    right = merge_sort(arr[mid:])
    
    return merge(left, right)         # O(n) merging at each level
```

**Java:**
```java
public class LinearithmicTime {
    // O(n log n) - Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSort(arr, left, mid);      // O(log n) divisions
            mergeSort(arr, mid + 1, right);
            
            merge(arr, left, mid, right);   // O(n) merging at each level
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Copy data to temp arrays - O(n)
        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = arr[mid + 1 + j];
        
        // Merge temp arrays - O(n)
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }
    
    // O(n log n) - Quick Sort (average case)
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);   // O(log n) divisions
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {  // O(n) partitioning
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
    
    // O(n log n) - Heap Sort
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
        
        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;
        
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
}
```

**Real-world analogy**: Sorting a deck of cards by dividing it in half repeatedly, sorting each half, then merging them back together.

**Why it's still good**:
- 10 items → ~33 operations
- 100 items → ~664 operations
- 1,000 items → ~9,966 operations

---

#### O(n²) - Quadratic Time
**What it means**: The time grows with the square of the input size. If you double the input, the time quadruples!

**Example**: Checking all pairs in an array (nested loops)

**Python:**
```python
def find_duplicates(arr):
    duplicates = []
    for i in range(len(arr)):           # Outer loop: n iterations
        for j in range(i + 1, len(arr)): # Inner loop: n iterations
            if arr[i] == arr[j]:
                duplicates.append(arr[i])
    return duplicates
```

**Java:**
```java
public class QuadraticTime {
    // O(n²) - Finding duplicates using nested loops
    public static List<Integer> findDuplicates(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {           // Outer loop: n iterations
            for (int j = i + 1; j < arr.length; j++) {   // Inner loop: n iterations
                if (arr[i] == arr[j]) {
                    duplicates.add(arr[i]);
                }
            }
        }
        return duplicates;
    }
    
    // O(n²) - Bubble Sort
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
    
    // O(n²) - Selection Sort
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
    
    // O(n²) - Insertion Sort
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
    
    // O(n²) - Finding all pairs that sum to target
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
    
    // O(n²) - Matrix multiplication
    public static int[][] matrixMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        
        for (int i = 0; i < n; i++) {                  // Outer loop: n iterations
            for (int j = 0; j < n; j++) {              // Middle loop: n iterations
                for (int k = 0; k < n; k++) {          // Inner loop: n iterations
                    result[i][j] += A[i][k] * B[k][j]; // Actually O(n³)!
                }
            }
        }
        return result;
    }
}
```

**Real-world analogy**: Comparing every person in a room with every other person to find people with the same birthday. With 10 people, you make 45 comparisons. With 100 people, you make 4,950 comparisons!

**Why it can be slow**:
- 10 items → 100 operations
- 100 items → 10,000 operations
- 1,000 items → 1,000,000 operations

---

#### O(2ⁿ) - Exponential Time
**What it means**: The time doubles with each additional input element. This is VERY slow and should be avoided when possible.

**Example**: Recursive Fibonacci (naive version)

**Python:**
```python
def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n - 1) + fibonacci(n - 2)  # Calls itself twice
```

**Java:**
```java
public class ExponentialTime {
    // O(2ⁿ) - Naive recursive Fibonacci
    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);  // Calls itself twice
    }
    
    // O(2ⁿ) - Generating all subsets of an array
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
    
    // O(2ⁿ) - Tower of Hanoi
    public static void towerOfHanoi(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
            return;
        }
        towerOfHanoi(n - 1, from, aux, to);   // First recursive call
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        towerOfHanoi(n - 1, aux, to, from);   // Second recursive call
    }
    
    // O(2ⁿ) - Recursive power calculation (naive)
    public static long powerNaive(long base, long exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        return base * powerNaive(base, exponent - 1);  // Not efficient!
    }
}
```

**Real-world analogy**: A rumor that spreads exponentially. Each person tells 2 people, who each tell 2 more people, and so on.

**Why it's problematic**:
- 10 items → ~1,024 operations
- 20 items → ~1,048,576 operations
- 30 items → ~1,073,741,824 operations

---

#### O(n!) - Factorial Time
**What it means**: The time grows factorially with input size. This is the slowest and should be avoided at all costs.

**Example**: Generating all permutations of a list

**Python:**
```python
def generate_permutations(arr):
    if len(arr) <= 1:
        return [arr]
    
    result = []
    for i in range(len(arr)):
        rest = arr[:i] + arr[i+1:]
        for perm in generate_permutations(rest):
            result.append([arr[i]] + perm)
    return result
```

**Java:**
```java
public class FactorialTime {
    // O(n!) - Generating all permutations
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
    
    // O(n!) - Traveling Salesman Problem (brute force)
    public static int tspBruteForce(int[][] graph, int start) {
        int n = graph.length;
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
    
    // O(n!) - Generating all arrangements (anagrams)
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
}
```

**Real-world analogy**: Trying every possible order of arranging books on a shelf. With 5 books, there are 120 ways. With 10 books, there are 3,628,800 ways!

---

## Visual Comparison

Here's how different complexities compare as input size grows:

| Input Size | O(1) | O(log n) | O(n) | O(n log n) | O(n²) | O(2ⁿ) |
|------------|------|----------|------|------------|-------|-------|
| 10         | 1    | 3        | 10   | 33         | 100   | 1,024 |
| 100        | 1    | 7        | 100  | 664        | 10K   | 10³⁰  |
| 1,000      | 1    | 10       | 1K   | 10K        | 1M    | 10³⁰¹ |

## How to Identify Time Complexity

### Step 1: Count the loops
- **No loops or fixed iterations** → O(1)
- **One loop** → O(n)
- **Nested loops** → O(n²) or O(n × m)
- **Loop that halves the input** → O(log n)

### Step 2: Look for recursion
- **Recursive calls that divide input in half** → O(log n) or O(n log n)
- **Recursive calls that reduce by 1** → O(n)
- **Recursive calls that branch multiple times** → O(2ⁿ) or worse

### Step 3: Consider the operations inside
- Simple operations (addition, comparison) don't change complexity
- Function calls inside loops multiply the complexity

## Java Examples with Detailed Analysis

### Example 1: Simple Loop - O(n)
**Java:**
```java
public static void printNumbers(int n) {
    for (int i = 0; i < n; i++) {
        System.out.println(i);
    }
}
```
**Analysis**: One loop that runs `n` times → **O(n)**
- Each iteration does constant work (printing)
- Total operations: n
- Time grows linearly with input size

### Example 2: Nested Loops - O(n²)
**Java:**
```java
public static void printPairs(int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            System.out.println(i + ", " + j);
        }
    }
}
```
**Analysis**: Outer loop runs `n` times, inner loop runs `n` times for each outer iteration → **O(n²)**
- Total iterations: n × n = n²
- Time grows quadratically with input size

### Example 3: Loop with Halving - O(log n)
**Java:**
```java
public static int countDown(int n) {
    int count = 0;
    while (n > 0) {
        n = n / 2;
        count++;
    }
    return count;
}
```
**Analysis**: Each iteration halves `n` → **O(log n)**
- Number of iterations: log₂(n)
- Very efficient even for large inputs

### Example 4: Multiple Loops (Sequential) - O(n)
**Java:**
```java
public static void processArray(int[] arr) {
    // First loop
    for (int item : arr) {
        System.out.println(item);
    }
    
    // Second loop
    for (int item : arr) {
        System.out.println(item * 2);
    }
}
```
**Analysis**: Two separate loops, each O(n) → **O(n)** (not O(2n), which simplifies to O(n))
- Sequential operations add up: O(n) + O(n) = O(2n) = O(n)
- Constants are dropped in Big O notation

### Example 5: Mixed Complexity - O(n²)
**Java:**
```java
public static void complexFunction(int[] arr) {
    // O(1) - Constant time operation
    int first = arr[0];
    
    // O(n) - Linear time operation
    for (int item : arr) {
        System.out.println(item);
    }
    
    // O(n²) - Quadratic time operation
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length; j++) {
            System.out.println(arr[i] + ", " + arr[j]);
        }
    }
}
```
**Analysis**: O(1) + O(n) + O(n²) → **O(n²)** (we take the dominant term)
- The slowest operation determines overall complexity
- O(1) and O(n) are dominated by O(n²)

### Example 6: Recursive Function - O(n)
**Java:**
```java
public static int factorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);  // One recursive call, reduces by 1
}
```
**Analysis**: Makes `n` recursive calls, each doing constant work → **O(n)**
- Recursion depth: n
- Each level does O(1) work

### Example 7: Nested Loop with Different Bounds - O(n × m)
**Java:**
```java
public static void processMatrix(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    
    for (int i = 0; i < rows; i++) {        // n iterations
        for (int j = 0; j < cols; j++) {    // m iterations
            System.out.println(matrix[i][j]);
        }
    }
}
```
**Analysis**: Outer loop runs `n` times, inner loop runs `m` times → **O(n × m)**
- If n = m, this becomes O(n²)
- If one dimension is constant, it's O(n)

### Example 8: Loop with Inner Operation - O(n log n)
**Java:**
```java
public static void nestedComplexity(int[] arr) {
    for (int i = 0; i < arr.length; i++) {           // O(n) iterations
        // Binary search inside loop - O(log n)
        int index = Arrays.binarySearch(arr, arr[i]);
        System.out.println("Found at: " + index);
    }
}
```
**Analysis**: O(n) loop with O(log n) operation inside → **O(n log n)**
- Multiply complexities when one is nested inside another
- Common in efficient sorting algorithms

## Java Data Structures Time Complexity Cheat Sheet

Understanding the time complexity of common Java operations helps you choose the right data structure:

### ArrayList
```java
ArrayList<Integer> list = new ArrayList<>();
```
| Operation | Time Complexity | Notes |
|-----------|-----------------|-------|
| `get(index)` | O(1) | Direct index access |
| `add(value)` | O(1) amortized | O(n) when resizing |
| `add(index, value)` | O(n) | Must shift elements |
| `remove(index)` | O(n) | Must shift elements |
| `contains(value)` | O(n) | Linear search |
| `indexOf(value)` | O(n) | Linear search |

### LinkedList
```java
LinkedList<Integer> list = new LinkedList<>();
```
| Operation | Time Complexity | Notes |
|-----------|-----------------|-------|
| `get(index)` | O(n) | Must traverse to index |
| `add(value)` | O(1) | Add to end |
| `add(index, value)` | O(n) | Must traverse to index |
| `remove(index)` | O(n) | Must traverse to index |
| `removeFirst()` / `removeLast()` | O(1) | Direct access |
| `contains(value)` | O(n) | Linear search |

### HashMap / HashSet
```java
HashMap<String, Integer> map = new HashMap<>();
HashSet<Integer> set = new HashSet<>();
```
| Operation | Time Complexity | Notes |
|-----------|-----------------|-------|
| `put(key, value)` / `add(value)` | O(1) average | O(n) worst case (collisions) |
| `get(key)` / `contains(value)` | O(1) average | O(n) worst case |
| `remove(key)` | O(1) average | O(n) worst case |
| `containsKey(key)` | O(1) average | O(n) worst case |
| `keySet()` / `values()` | O(n) | Must iterate all elements |

### TreeMap / TreeSet
```java
TreeMap<String, Integer> map = new TreeMap<>();
TreeSet<Integer> set = new TreeSet<>();
```
| Operation | Time Complexity | Notes |
|-----------|-----------------|-------|
| `put(key, value)` / `add(value)` | O(log n) | Balanced BST |
| `get(key)` / `contains(value)` | O(log n) | Binary search |
| `remove(key)` | O(log n) | Tree deletion |
| `first()` / `last()` | O(log n) | Find min/max |
| `lower(key)` / `higher(key)` | O(log n) | Find predecessor/successor |

### PriorityQueue (Heap)
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```
| Operation | Time Complexity | Notes |
|-----------|-----------------|-------|
| `add(value)` / `offer(value)` | O(log n) | Heap insertion |
| `poll()` / `remove()` | O(log n) | Extract min/max |
| `peek()` | O(1) | View min/max without removing |
| `contains(value)` | O(n) | Linear search |
| `remove(value)` | O(n) | Linear search + O(log n) removal |

### Stack / Queue (using LinkedList)
```java
Stack<Integer> stack = new Stack<>();
Queue<Integer> queue = new LinkedList<>();
```
| Operation | Time Complexity | Notes |
|-----------|-----------------|-------|
| `push()` / `add()` | O(1) | Add to top/end |
| `pop()` / `poll()` | O(1) | Remove from top/front |
| `peek()` | O(1) | View without removing |
| `contains(value)` | O(n) | Linear search |

## Practical Java Optimization Examples

### Example 1: Choosing the Right Data Structure

**Problem**: Check if an array contains duplicates

**❌ Inefficient - O(n²)**
```java
public static boolean hasDuplicatesSlow(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[i] == arr[j]) {
                return true;
            }
        }
    }
    return false;
}
```

**✅ Efficient - O(n)**
```java
public static boolean hasDuplicatesFast(int[] arr) {
    HashSet<Integer> seen = new HashSet<>();
    for (int num : arr) {
        if (seen.contains(num)) {  // O(1) lookup
            return true;
        }
        seen.add(num);  // O(1) insertion
    }
    return false;
}
```

### Example 2: Optimizing String Operations

**Problem**: Check if two strings are anagrams

**❌ Inefficient - O(n log n)**
```java
public static boolean areAnagramsSlow(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    
    char[] arr1 = s1.toCharArray();
    char[] arr2 = s2.toCharArray();
    Arrays.sort(arr1);  // O(n log n)
    Arrays.sort(arr2);    // O(n log n)
    
    return Arrays.equals(arr1, arr2);
}
```

**✅ Efficient - O(n)**
```java
public static boolean areAnagramsFast(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    
    int[] count = new int[26];  // For lowercase letters
    
    for (int i = 0; i < s1.length(); i++) {  // O(n)
        count[s1.charAt(i) - 'a']++;
        count[s2.charAt(i) - 'a']--;
    }
    
    for (int c : count) {  // O(26) = O(1)
        if (c != 0) return false;
    }
    return true;
}
```

### Example 3: Memoization for Exponential Algorithms

**Problem**: Calculate Fibonacci numbers

**❌ Inefficient - O(2ⁿ)**
```java
public static long fibonacciSlow(int n) {
    if (n <= 1) return n;
    return fibonacciSlow(n - 1) + fibonacciSlow(n - 2);
}
```

**✅ Efficient - O(n) using Memoization**
```java
public static long fibonacciFast(int n) {
    long[] memo = new long[n + 1];
    return fibonacciHelper(n, memo);
}

private static long fibonacciHelper(int n, long[] memo) {
    if (n <= 1) return n;
    if (memo[n] != 0) return memo[n];  // Already computed
    
    memo[n] = fibonacciHelper(n - 1, memo) + fibonacciHelper(n - 2, memo);
    return memo[n];
}

// Even better - Iterative O(n) with O(1) space
public static long fibonacciIterative(int n) {
    if (n <= 1) return n;
    
    long prev = 0, curr = 1;
    for (int i = 2; i <= n; i++) {
        long next = prev + curr;
        prev = curr;
        curr = next;
    }
    return curr;
}
```

### Example 4: Two-Pointer Technique

**Problem**: Find two numbers in sorted array that sum to target

**❌ Inefficient - O(n²)**
```java
public static int[] twoSumSlow(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[i] + arr[j] == target) {
                return new int[]{i, j};
            }
        }
    }
    return new int[]{-1, -1};
}
```

**✅ Efficient - O(n) using Two Pointers**
```java
public static int[] twoSumFast(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left < right) {
        int sum = arr[left] + arr[right];
        if (sum == target) {
            return new int[]{left, right};
        } else if (sum < target) {
            left++;  // Need larger sum
        } else {
            right--; // Need smaller sum
        }
    }
    return new int[]{-1, -1};
}
```

### Example 5: Sliding Window Technique

**Problem**: Find maximum sum of k consecutive elements

**❌ Inefficient - O(n × k)**
```java
public static int maxSumSlow(int[] arr, int k) {
    int maxSum = Integer.MIN_VALUE;
    for (int i = 0; i <= arr.length - k; i++) {
        int sum = 0;
        for (int j = i; j < i + k; j++) {  // Recalculating sum
            sum += arr[j];
        }
        maxSum = Math.max(maxSum, sum);
    }
    return maxSum;
}
```

**✅ Efficient - O(n) using Sliding Window**
```java
public static int maxSumFast(int[] arr, int k) {
    int windowSum = 0;
    
    // Calculate sum of first window
    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }
    
    int maxSum = windowSum;
    
    // Slide the window
    for (int i = k; i < arr.length; i++) {
        windowSum = windowSum - arr[i - k] + arr[i];  // Remove left, add right
        maxSum = Math.max(maxSum, windowSum);
    }
    
    return maxSum;
}
```

## Key Takeaways

1. **Lower is better**: O(1) is best, O(n!) is worst
2. **Focus on the dominant term**: O(n + n²) = O(n²)
3. **Constants don't matter**: O(2n) = O(n), O(100n) = O(n)
4. **Worst case matters**: We usually describe the worst-case scenario
5. **Context is important**: Sometimes a "slower" algorithm is better for small inputs or specific use cases
6. **Choose the right data structure**: HashMap for O(1) lookups, TreeMap for sorted data, ArrayList for random access
7. **Use optimization techniques**: Memoization, two pointers, sliding window can dramatically improve performance

## Practice Questions

1. What's the time complexity of finding an item in an unsorted array?
   - **Answer**: O(n) - you might need to check every element

2. What's the time complexity of finding an item in a sorted array using binary search?
   - **Answer**: O(log n) - you eliminate half the possibilities each time

3. What's the time complexity of sorting an array using bubble sort?
   - **Answer**: O(n²) - nested loops compare all pairs

4. What's the time complexity of accessing an element in an array by index?
   - **Answer**: O(1) - direct access, same time regardless of array size

## Java-Specific Tips and Best Practices

### 1. When to Use Each Collection

**Use ArrayList when:**
- You need random access by index (O(1))
- You mostly add/remove at the end
- You don't need frequent insertions/deletions in the middle

**Use LinkedList when:**
- You frequently add/remove at the beginning or end
- You don't need random access
- You're implementing stacks/queues

**Use HashMap/HashSet when:**
- You need O(1) lookups, insertions, deletions
- Order doesn't matter
- You're checking for duplicates or membership

**Use TreeMap/TreeSet when:**
- You need sorted order
- You need range queries (lower, higher, floor, ceiling)
- O(log n) operations are acceptable

**Use PriorityQueue when:**
- You need to always access min/max element
- You're implementing algorithms like Dijkstra's, heap sort
- You need a heap data structure

### 2. Common Java Performance Pitfalls

**❌ Avoid unnecessary boxing/unboxing:**
```java
// Bad - creates Integer objects
List<Integer> list = new ArrayList<>();
for (int i = 0; i < 1000000; i++) {
    list.add(i);  // Autoboxing overhead
}

// Better - use primitive arrays when possible
int[] arr = new int[1000000];
for (int i = 0; i < 1000000; i++) {
    arr[i] = i;  // No boxing
}
```

**❌ Avoid string concatenation in loops:**
```java
// Bad - O(n²) due to string immutability
String result = "";
for (String s : strings) {
    result += s;  // Creates new string each time
}

// Better - O(n) using StringBuilder
StringBuilder sb = new StringBuilder();
for (String s : strings) {
    sb.append(s);
}
String result = sb.toString();
```

**❌ Avoid using contains() on ArrayList frequently:**
```java
// Bad - O(n) for each contains() call
ArrayList<Integer> list = new ArrayList<>();
for (int i = 0; i < n; i++) {
    if (list.contains(arr[i])) {  // O(n) each time
        // ...
    }
}

// Better - O(1) lookups with HashSet
HashSet<Integer> set = new HashSet<>();
for (int i = 0; i < n; i++) {
    if (set.contains(arr[i])) {  // O(1) each time
        // ...
    }
}
```

### 3. Quick Reference: Java Collections Complexity

| Data Structure | Insert | Delete | Search | Access | Notes |
|---------------|--------|--------|--------|--------|-------|
| **Array** | O(n) | O(n) | O(n) | O(1) | Fixed size |
| **ArrayList** | O(1)* | O(n) | O(n) | O(1) | *Amortized |
| **LinkedList** | O(1) | O(1) | O(n) | O(n) | If at ends |
| **HashMap** | O(1)* | O(1)* | O(1)* | N/A | *Average case |
| **TreeMap** | O(log n) | O(log n) | O(log n) | N/A | Sorted |
| **HashSet** | O(1)* | O(1)* | O(1)* | N/A | *Average case |
| **TreeSet** | O(log n) | O(log n) | O(log n) | N/A | Sorted |
| **PriorityQueue** | O(log n) | O(log n) | O(n) | O(1) | Min/Max access |

### 4. Algorithm Patterns and Their Complexities

| Pattern | Time Complexity | Use Cases |
|----------|----------------|-----------|
| **Brute Force** | O(n²) or worse | Small inputs, simple problems |
| **Two Pointers** | O(n) | Sorted arrays, palindromes, pairs |
| **Sliding Window** | O(n) | Subarrays, substrings, consecutive elements |
| **Binary Search** | O(log n) | Sorted arrays, search problems |
| **Divide & Conquer** | O(n log n) | Sorting, merge operations |
| **Dynamic Programming** | O(n) to O(n²) | Optimization problems, overlapping subproblems |
| **Backtracking** | O(2ⁿ) or O(n!) | Permutations, combinations, constraint satisfaction |
| **Greedy** | O(n log n) | Optimization with local choices |

## Conclusion

Time complexity helps us:
- **Choose the right algorithm** for our problem
- **Predict performance** as data grows
- **Write efficient code** that scales well
- **Compare solutions** objectively
- **Select appropriate data structures** in Java
- **Optimize existing code** by identifying bottlenecks

### Remember:
1. **Profile before optimizing** - Measure actual performance, don't guess
2. **Premature optimization is the root of all evil** - Make it work first, then optimize
3. **Readability matters** - Sometimes O(n²) is fine if n is small and code is clear
4. **Understand your constraints** - Time vs. space trade-offs
5. **Practice makes perfect** - Solve problems to build intuition

Understanding time complexity is like learning to read a map - it helps you navigate the world of algorithms and make better programming decisions!

### Next Steps:
- Practice implementing these algorithms in Java
- Solve coding problems on platforms like LeetCode, HackerRank
- Analyze time complexity of your own code
- Learn about space complexity (memory usage)
- Study advanced data structures (Trie, Segment Tree, etc.)

