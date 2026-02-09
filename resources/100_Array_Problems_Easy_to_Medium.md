# 100 Array Problems: Easy → Medium 🎯

**Master arrays step-by-step! Start with Easy, then progress to Medium.**

---

## 📋 How to Use This List

1. **Start with Easy problems** - Complete 20-30 before moving to Medium
2. **Practice daily** - Aim for 2-3 problems per day
3. **Time yourself** - Easy: 15-20 min, Medium: 25-35 min
4. **Review solutions** - After solving, check optimal approaches
5. **Revisit** - Redo problems after 1 week for retention

---

## 🟢 EASY PROBLEMS (1-60)

### Basic Operations (1-10)

1. **Find Maximum Element in Array**
   - Given array, return max value
   - Example: `[3, 5, 2, 8, 1]` → `8`

2. **Find Minimum Element in Array**
   - Given array, return min value
   - Example: `[3, 5, 2, 8, 1]` → `1`

3. **Find Second Largest Element**
   - Return second largest number
   - Example: `[3, 5, 2, 8, 1]` → `5`

4. **Sum of All Elements**
   - Calculate total sum
   - Example: `[1, 2, 3, 4]` → `10`

5. **Average of Array Elements**
   - Calculate average value
   - Example: `[1, 2, 3, 4, 5]` → `3.0`

6. **Count Even Numbers**
   - Count how many even numbers
   - Example: `[1, 2, 3, 4, 5]` → `2`

7. **Count Odd Numbers**
   - Count how many odd numbers
   - Example: `[1, 2, 3, 4, 5]` → `3`

8. **Find Index of Element**
   - Return index of target, or -1 if not found
   - Example: `[1, 2, 3, 4]`, target `3` → `2`

9. **Check if Element Exists**
   - Return true if target exists
   - Example: `[1, 2, 3]`, target `2` → `true`

10. **Count Occurrences of Element**
    - Count how many times target appears
    - Example: `[1, 2, 2, 3, 2]`, target `2` → `3`

---

### Reverse & Rotate (11-20)

11. **Reverse Array**
    - Reverse array in-place
    - Example: `[1, 2, 3, 4]` → `[4, 3, 2, 1]`

12. **Reverse Array (New Array)**
    - Return new reversed array
    - Example: `[1, 2, 3]` → `[3, 2, 1]`

13. **Rotate Array Left by K**
    - Rotate left by k positions
    - Example: `[1, 2, 3, 4, 5]`, k=2 → `[3, 4, 5, 1, 2]`

14. **Rotate Array Right by K**
    - Rotate right by k positions
    - Example: `[1, 2, 3, 4, 5]`, k=2 → `[4, 5, 1, 2, 3]`

15. **Rotate Array Right (In-Place)**
    - Rotate right without extra space
    - Example: `[1, 2, 3, 4, 5]`, k=2 → `[4, 5, 1, 2, 3]`

16. **Reverse First K Elements**
    - Reverse only first k elements
    - Example: `[1, 2, 3, 4, 5]`, k=3 → `[3, 2, 1, 4, 5]`

17. **Reverse Last K Elements**
    - Reverse only last k elements
    - Example: `[1, 2, 3, 4, 5]`, k=3 → `[1, 2, 5, 4, 3]`

18. **Reverse Array Between Indices**
    - Reverse subarray from index i to j
    - Example: `[1, 2, 3, 4, 5]`, i=1, j=3 → `[1, 4, 3, 2, 5]`

19. **Rotate Matrix 90 Degrees**
    - Rotate 2D array clockwise
    - Example: `[[1,2],[3,4]]` → `[[3,1],[4,2]]`

20. **Reverse Rows of Matrix**
    - Reverse each row of 2D array
    - Example: `[[1,2],[3,4]]` → `[[2,1],[4,3]]`

---

### Duplicates & Unique (21-30)

21. **Remove Duplicates from Sorted Array**
    - Remove duplicates in-place, return new length
    - Example: `[1, 1, 2, 2, 3]` → length `3`, array `[1, 2, 3, _, _]`

22. **Remove Duplicates from Unsorted Array**
    - Remove all duplicates
    - Example: `[1, 3, 2, 1, 3]` → `[1, 3, 2]`

23. **Find All Duplicates**
    - Return list of duplicate elements
    - Example: `[1, 2, 2, 3, 3, 3]` → `[2, 3]`

24. **Find First Duplicate**
    - Return first duplicate element
    - Example: `[1, 2, 3, 2, 1]` → `2`

25. **Find Missing Number (1 to n)**
    - Array contains n-1 numbers from 1 to n, find missing
    - Example: `[1, 2, 4, 5]` (n=5) → `3`

26. **Find All Missing Numbers**
    - Array contains numbers from 1 to n, find all missing
    - Example: `[1, 3, 4, 5]` (n=5) → `[2]`

27. **Find Duplicate Number (One Duplicate)**
    - Array of n+1 numbers from 1 to n, find duplicate
    - Example: `[1, 3, 4, 2, 2]` → `2`

28. **Remove Element (In-Place)**
    - Remove all instances of val, return new length
    - Example: `[3, 2, 2, 3]`, val=3 → length `2`, array `[2, 2, _, _]`

29. **Move Zeros to End**
    - Move all zeros to end, maintain order of non-zeros
    - Example: `[0, 1, 0, 3, 12]` → `[1, 3, 12, 0, 0]`

30. **Remove Duplicates Allowing At Most 2**
    - Remove duplicates but allow at most 2 occurrences
    - Example: `[1, 1, 1, 2, 2, 3]` → `[1, 1, 2, 2, 3]`

---

### Two Pointers & Pairs (31-40)

31. **Two Sum**
    - Find two numbers that add to target, return indices
    - Example: `[2, 7, 11, 15]`, target=9 → `[0, 1]`

32. **Two Sum (Sorted Array)**
    - Array is sorted, use two pointers
    - Example: `[2, 7, 11, 15]`, target=9 → `[0, 1]`

33. **Three Sum**
    - Find three numbers that add to zero
    - Example: `[-1, 0, 1, 2, -1, -4]` → `[[-1, -1, 2], [-1, 0, 1]]`

34. **Three Sum Closest**
    - Find three numbers with sum closest to target
    - Example: `[-1, 2, 1, -4]`, target=1 → sum `2` (from `[-1, 2, 1]`)

35. **Four Sum**
    - Find four numbers that add to target
    - Example: `[1, 0, -1, 0, -2, 2]`, target=0 → `[[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]`

36. **Container With Most Water**
    - Find two lines that form container with most water
    - Example: `[1, 8, 6, 2, 5, 4, 8, 3, 7]` → `49`

37. **Valid Mountain Array**
    - Check if array is valid mountain (strictly increasing then decreasing)
    - Example: `[0, 2, 3, 4, 5, 2, 1, 0]` → `true`

38. **Squares of Sorted Array**
    - Return squares of elements in sorted order
    - Example: `[-4, -1, 0, 3, 10]` → `[0, 1, 9, 16, 100]`

39. **Merge Sorted Arrays**
    - Merge two sorted arrays into one sorted array
    - Example: `[1, 2, 3]` + `[2, 5, 6]` → `[1, 2, 2, 3, 5, 6]`

40. **Intersection of Two Arrays**
    - Find common elements in two arrays
    - Example: `[1, 2, 2, 1]` + `[2, 2]` → `[2]`

---

### Sliding Window & Subarrays (41-50)

41. **Maximum Sum Subarray (Kadane's Algorithm)**
    - Find maximum sum of contiguous subarray
    - Example: `[-2, 1, -3, 4, -1, 2, 1, -5, 4]` → `6` (from `[4, -1, 2, 1]`)

42. **Maximum Sum Subarray of Size K**
    - Find max sum of subarray with exactly k elements
    - Example: `[2, 1, 5, 1, 3, 2]`, k=3 → `9` (from `[5, 1, 3]`)

43. **Minimum Sum Subarray of Size K**
    - Find min sum of subarray with exactly k elements
    - Example: `[2, 1, 5, 1, 3, 2]`, k=3 → `7` (from `[1, 5, 1]`)

44. **Average of Subarrays of Size K**
    - Find average of all subarrays of size k
    - Example: `[1, 3, 2, 6, -1, 4, 1, 8, 2]`, k=5 → `[2.2, 2.8, 2.2, 3.6, 2.8]`

45. **Longest Subarray with Sum K**
    - Find longest subarray with sum equal to k
    - Example: `[1, -1, 5, -2, 3]`, k=3 → length `4` (from `[-1, 5, -2, 3]`)

46. **Count Subarrays with Sum K**
    - Count number of subarrays with sum k
    - Example: `[1, 1, 1]`, k=2 → `2`

47. **Maximum Average Subarray**
    - Find subarray with maximum average
    - Example: `[1, 12, -5, -6, 50, 3]`, k=4 → `12.75` (from `[12, -5, -6, 50]`)

48. **Subarray Product Less Than K**
    - Count subarrays with product < k
    - Example: `[10, 5, 2, 6]`, k=100 → `8`

49. **Longest Substring Without Repeating Characters (Array Version)**
    - Find longest subarray with all unique elements
    - Example: `[1, 2, 3, 2, 1]` → length `3` (from `[1, 2, 3]`)

50. **Maximum in Sliding Window**
    - Find maximum in each sliding window of size k
    - Example: `[1, 3, -1, -3, 5, 3, 6, 7]`, k=3 → `[3, 3, 5, 5, 6, 7]`

---

### Sorting & Searching (51-60)

51. **Sort Array (Bubble Sort)**
    - Implement bubble sort
    - Example: `[64, 34, 25, 12, 22]` → `[12, 22, 25, 34, 64]`

52. **Sort Array (Selection Sort)**
    - Implement selection sort
    - Example: `[64, 25, 12, 22, 11]` → `[11, 12, 22, 25, 64]`

53. **Sort Array (Insertion Sort)**
    - Implement insertion sort
    - Example: `[12, 11, 13, 5, 6]` → `[5, 6, 11, 12, 13]`

54. **Find Kth Largest Element**
    - Find kth largest element (without sorting entire array)
    - Example: `[3, 2, 1, 5, 6, 4]`, k=2 → `5`

55. **Find Kth Smallest Element**
    - Find kth smallest element
    - Example: `[3, 2, 1, 5, 6, 4]`, k=3 → `3`

56. **Top K Frequent Elements**
    - Return k most frequent elements
    - Example: `[1, 1, 1, 2, 2, 3]`, k=2 → `[1, 2]`

57. **Sort Colors (Dutch National Flag)**
    - Sort array of 0s, 1s, 2s in-place
    - Example: `[2, 0, 2, 1, 1, 0]` → `[0, 0, 1, 1, 2, 2]`

58. **Wiggle Sort**
    - Rearrange so nums[0] < nums[1] > nums[2] < nums[3]...
    - Example: `[3, 5, 2, 1, 6, 4]` → `[3, 5, 1, 6, 2, 4]`

59. **Merge Intervals**
    - Merge overlapping intervals
    - Example: `[[1,3],[2,6],[8,10],[15,18]]` → `[[1,6],[8,10],[15,18]]`

60. **Insert Interval**
    - Insert new interval and merge if needed
    - Example: `[[1,3],[6,9]]` + `[2,5]` → `[[1,5],[6,9]]`

---

## 🟡 MEDIUM PROBLEMS (61-100)

### Advanced Two Pointers (61-70)

61. **Trapping Rain Water**
    - Calculate trapped rainwater between bars
    - Example: `[0,1,0,2,1,0,1,3,2,1,2,1]` → `6`

62. **Product of Array Except Self**
    - Return array where each element is product of all others
    - Example: `[1, 2, 3, 4]` → `[24, 12, 8, 6]` (without division)

63. **3Sum Smaller**
    - Count triplets with sum less than target
    - Example: `[-2, 0, 1, 3]`, target=2 → `2` (triplets: `[-2, 0, 1]`, `[-2, 0, 3]`)

64. **Boats to Save People**
    - Minimum boats needed (each boat capacity limit)
    - Example: `[1, 2]`, limit=3 → `1` boat

65. **Partition Labels**
    - Partition string into as many parts as possible
    - Example: `"ababcbacadefegdehijhklij"` → `[9, 7, 8]`

66. **Sort Array by Parity**
    - Move all even numbers before odd numbers
    - Example: `[3, 1, 2, 4]` → `[2, 4, 3, 1]`

67. **Remove Duplicates from Sorted Array II**
    - Remove duplicates allowing at most 2 occurrences
    - Example: `[1, 1, 1, 2, 2, 3]` → `[1, 1, 2, 2, 3]`

68. **Move All Zeros to End (Optimal)**
    - Move zeros maintaining relative order of non-zeros
    - Example: `[0, 1, 0, 3, 12]` → `[1, 3, 12, 0, 0]`

69. **Squares of Sorted Array (Optimal)**
    - Return squares in sorted order using two pointers
    - Example: `[-4, -1, 0, 3, 10]` → `[0, 1, 9, 16, 100]`

70. **Next Permutation**
    - Find next lexicographically greater permutation
    - Example: `[1, 2, 3]` → `[1, 3, 2]`

---

### Sliding Window Advanced (71-80)

71. **Longest Subarray with At Most K Distinct Elements**
    - Find longest subarray with ≤ k distinct elements
    - Example: `[1, 2, 1, 2, 3]`, k=2 → length `4` (from `[1, 2, 1, 2]`)

72. **Minimum Window Substring**
    - Find minimum window in string containing all characters of pattern
    - Example: `s="ADOBECODEBANC"`, `t="ABC"` → `"BANC"`

73. **Longest Repeating Character Replacement**
    - Longest substring with same letter after k replacements
    - Example: `"ABAB"`, k=2 → `4` (replace both A's or both B's)

74. **Fruit Into Baskets**
    - Maximum fruits you can pick (at most 2 types)
    - Example: `[1, 2, 1]` → `3`

75. **Subarray Sum Equals K**
    - Count subarrays with sum exactly k
    - Example: `[1, 1, 1]`, k=2 → `2`

76. **Continuous Subarray Sum**
    - Check if array has continuous subarray of size ≥ 2 with sum multiple of k
    - Example: `[23, 2, 4, 6, 7]`, k=6 → `true` (from `[2, 4]`)

77. **Maximum Points You Can Obtain from Cards**
    - Maximum points from k cards from ends
    - Example: `[1, 2, 3, 4, 5, 6, 1]`, k=3 → `12` (take `[1, 2, 1]`)

78. **Minimum Size Subarray Sum**
    - Find minimal length subarray with sum ≥ target
    - Example: `[2, 3, 1, 2, 4, 3]`, target=7 → `2` (from `[4, 3]`)

79. **Maximum Consecutive Ones III**
    - Maximum consecutive ones after flipping at most k zeros
    - Example: `[1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0]`, k=2 → `6`

80. **Grumpy Bookstore Owner**
    - Maximum customers satisfied (can be grumpy for X minutes)
    - Example: `customers=[1,0,1,2,1,1,7,5]`, `grumpy=[0,1,0,1,0,1,0,1]`, X=3 → `16`

---

### Matrix & 2D Arrays (81-90)

81. **Spiral Matrix**
    - Return elements in spiral order
    - Example: `[[1,2,3],[4,5,6],[7,8,9]]` → `[1,2,3,6,9,8,7,4,5]`

82. **Spiral Matrix II**
    - Generate n×n matrix filled with numbers 1 to n² in spiral order
    - Example: n=3 → `[[1,2,3],[8,9,4],[7,6,5]]`

83. **Set Matrix Zeroes**
    - If element is 0, set entire row and column to 0
    - Example: `[[1,1,1],[1,0,1],[1,1,1]]` → `[[1,0,1],[0,0,0],[1,0,1]]`

84. **Rotate Image**
    - Rotate n×n matrix 90 degrees clockwise (in-place)
    - Example: `[[1,2],[3,4]]` → `[[3,1],[4,2]]`

85. **Search a 2D Matrix**
    - Search target in sorted 2D matrix (each row sorted, first element of row > last element of previous row)
    - Example: `[[1,3,5,7],[10,11,16,20],[23,30,34,60]]`, target=3 → `true`

86. **Search a 2D Matrix II**
    - Search target in matrix (each row and column sorted)
    - Example: `[[1,4,7,11],[2,5,8,12],[3,6,9,16]]`, target=5 → `true`

87. **Game of Life**
    - Apply Conway's Game of Life rules (in-place)
    - Rules: Live cell with 2-3 neighbors survives, dead cell with 3 neighbors becomes alive

88. **Number of Islands**
    - Count number of islands (connected 1's)
    - Example: `[[1,1,0],[0,1,0],[0,0,1]]` → `2` islands

89. **Max Area of Island**
    - Find maximum area of island
    - Example: `[[0,0,1,0,0],[0,0,0,0,0],[0,1,1,0,1]]` → `2`

90. **Pacific Atlantic Water Flow**
    - Find cells that can flow to both Pacific and Atlantic
    - Example: Heights matrix, return coordinates

---

### Advanced Patterns (91-100)

91. **Jump Game**
    - Can you reach last index? (each element is max jump length)
    - Example: `[2, 3, 1, 1, 4]` → `true`

92. **Jump Game II**
    - Minimum jumps to reach last index
    - Example: `[2, 3, 1, 1, 4]` → `2` jumps

93. **Best Time to Buy and Sell Stock**
    - Maximum profit from one transaction
    - Example: `[7, 1, 5, 3, 6, 4]` → `5` (buy at 1, sell at 6)

94. **Best Time to Buy and Sell Stock II**
    - Maximum profit from multiple transactions
    - Example: `[7, 1, 5, 3, 6, 4]` → `7` (buy at 1 sell at 5, buy at 3 sell at 6)

95. **Best Time to Buy and Sell Stock with Cooldown**
    - Maximum profit with cooldown period after sell
    - Example: `[1, 2, 3, 0, 2]` → `3`

96. **House Robber**
    - Maximum money you can rob (can't rob two adjacent houses)
    - Example: `[2, 7, 9, 3, 1]` → `12` (rob houses 0, 2, 4)

97. **House Robber II**
    - Houses arranged in circle (first and last are adjacent)
    - Example: `[2, 3, 2]` → `3` (can't rob both first and last)

98. **Product of Array Except Self (Space Optimized)**
    - Solve without using extra space for result array
    - Example: `[1, 2, 3, 4]` → `[24, 12, 8, 6]`

99. **Find All Duplicates in Array**
    - Array contains numbers 1 to n, each appears once or twice, find all duplicates
    - Example: `[4, 3, 2, 7, 8, 2, 3, 1]` → `[2, 3]`

100. **First Missing Positive**
    - Find smallest positive integer not in array (O(n) time, O(1) space)
    - Example: `[3, 4, -1, 1]` → `2`

---

## 📊 Progress Tracker

### Easy Problems (1-60)
- [ ] 1-10: Basic Operations
- [ ] 11-20: Reverse & Rotate
- [ ] 21-30: Duplicates & Unique
- [ ] 31-40: Two Pointers & Pairs
- [ ] 41-50: Sliding Window & Subarrays
- [ ] 51-60: Sorting & Searching

### Medium Problems (61-100)
- [ ] 61-70: Advanced Two Pointers
- [ ] 71-80: Sliding Window Advanced
- [ ] 81-90: Matrix & 2D Arrays
- [ ] 91-100: Advanced Patterns

---

## 🎯 Recommended Practice Order

### Week 1: Foundation
- Problems 1-20 (Basic operations, reverse, rotate)
- Focus: Getting comfortable with array manipulation

### Week 2: Core Patterns
- Problems 21-40 (Duplicates, two pointers, pairs)
- Focus: Two pointers technique

### Week 3: Sliding Window
- Problems 41-50 (Sliding window basics)
- Focus: Window technique

### Week 4: Sorting & Searching
- Problems 51-60 (Sorting, searching)
- Focus: Understanding sorting algorithms

### Week 5-6: Medium Level
- Problems 61-80 (Advanced patterns)
- Focus: Optimizing solutions

### Week 7-8: Advanced Topics
- Problems 81-100 (Matrix, DP patterns)
- Focus: Complex problem-solving

---

## 💡 Tips for Success

1. **Start Easy**: Don't skip easy problems - they build foundation
2. **Understand Patterns**: Group similar problems together
3. **Time Complexity**: Always think about O(n) vs O(n²) solutions
4. **Space Complexity**: Try to optimize space when possible
5. **Edge Cases**: Always test with empty array, single element, duplicates
6. **Practice Daily**: Consistency beats intensity
7. **Review Solutions**: After solving, check optimal approaches
8. **Explain Out Loud**: Teaching helps retention

---

## 🔗 Where to Practice

- **LeetCode**: Most problems available (search by problem name)
- **HackerRank**: Good for basic array problems
- **Codeforces**: For competitive programming style
- **GeeksforGeeks**: Detailed explanations

---

## 📝 Notes

- Mark problems as ✅ when completed
- Note time taken and space complexity
- Write brief notes on approach used
- Revisit problems after 1 week for retention

**Good luck! Master these 100 problems and you'll be ready for array-based interview questions! 🚀**


