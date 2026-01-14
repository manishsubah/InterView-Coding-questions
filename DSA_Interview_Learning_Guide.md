### DSA Interview Learning Guide (Easy → Medium)

A simple, structured path to build skills for coding interviews. Learn concepts, then apply them with tiny, satisfying problems. Examples use Java so you can practice in this project.

---

## How to Use This Guide
- **Tiny wins**: 20–40 min focused sessions; each ends with 1–3 short problems.
- **Spaced practice**: Revisit topics with 1–2 quick review problems after 2–3 days.
- **Gamify**: Track streaks, time your solutions, and beat prior attempts.
- **Explain out loud**: After every problem, explain your idea in 2–3 sentences.

---

## Roadmap (Easy → Medium)
1. Foundations: Complexity (Big-O), arrays, strings
2. Patterns: Two Pointers, Sliding Window
3. Stack & Queue basics
4. Hashing (HashMap/Set)
5. Linked Lists
6. Binary Search (on arrays and on answers)
7. Sorting essentials
8. Recursion & Backtracking
9. Trees (BST basics) and BFS/DFS
10. Heaps & Priority problems
11. Graphs (BFS/DFS, visited tracking)
12. Dynamic Programming (1D, 2D intro)

Use the Daily Template below for each session.

---

## Daily Template (30–60 minutes)
- 5 min: Review flash-notes (definitions, patterns).
- 15–30 min: Learn + 1 worked example.
- 10–20 min: Solve 1–2 problems (from the topic).
- 5 min: Write a 2–3 sentence solution summary.

---

## Lesson 1 — Foundations: Arrays, Strings, Big-O, and First Patterns

### Big-O (Quick Primer)
- **Time**: How work grows with input `n`.
  - `O(1)`: constant, `O(n)`: single loop, `O(n log n)`: sort, `O(n^2)`: nested loops.
- **Space**: Extra memory used beyond input.

Prefer linear `O(n)` over quadratic `O(n^2)` when possible.

### Arrays vs Strings in Java
- Arrays are fixed-size containers: `int[] nums = {1,2,3};`
- Strings are immutable; for many concatenations use `StringBuilder`.

### Pattern: Two Pointers (Easy Intro)
When data is sorted or you can sweep from both ends.

- Typical uses:
  - Reverse array/string in-place
  - Check palindrome
  - Find pairs meeting a condition (often with sorted arrays)

Example: Reverse string in-place using char array

```java
public class ReverseStringEasy {
	public static String reverse(String s) {
		char[] arr = s.toCharArray();
		int i = 0, j = arr.length - 1;
		while (i < j) {
			char tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
			i++;
			j--;
		}
		return new String(arr);
	}

	public static void main(String[] args) {
		System.out.println(reverse("hello")); // "olle
	}
}
```

Complexity: Time `O(n)`, Space `O(n)` for char array (or `O(1)` if mutable array).

### Sliding Window (Preview)
Great for “subarray/substring of size K” or “longest with constraint”. Full treatment in Lesson 3.

### Practice (Do Now)
- Reverse an array in-place
- Check if a string is a palindrome (ignore case and non-alphanumeric)
- Find max element in an array

Example: Palindrome (ignore non-alphanumeric)

```java
public class PalindromeEasy {
	private static boolean isAlphaNum(char c) {
		return Character.isLetterOrDigit(c);
	}

	public static boolean isPalindrome(String s) {
		int i = 0, j = s.length() - 1;
		while (i < j) {
			char left = s.charAt(i);
			char right = s.charAt(j);
			if (!isAlphaNum(left)) { i++; continue; }
			if (!isAlphaNum(right)) { j--; continue; }
			if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
				return false;
			}
			i++; j--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPalindrome("A man, a plan, a canal: Panama")); // true
		System.out.println(isPalindrome("race a car")); // false
	}
}
```

Example: Max in array

```java
public class MaxInArray {
	public static int max(int[] nums) {
		int best = Integer.MIN_VALUE;
		for (int x : nums) {
			if (x > best) best = x;
		}
		return best;
	}
}
```

---

## Lesson 2 — Arrays & Strings: Frequency and Hashing (Easy)

When the problem cares about counts or membership, use `HashMap` / `HashSet`.

Common tasks:
- Count chars: anagram check
- Track seen items: duplicate detection

Example: Check anagram

```java
import java.util.HashMap;

public class AnagramCheck {
	public static boolean isAnagram(String a, String b) {
		if (a.length() != b.length()) return false;
		HashMap<Character, Integer> count = new HashMap<>();
		for (char c : a.toCharArray()) {
			count.put(c, count.getOrDefault(c, 0) + 1);
		}
		for (char c : b.toCharArray()) {
			int newCount = count.getOrDefault(c, 0) - 1;
			if (newCount < 0) return false;
			count.put(c, newCount);
		}
		return true;
	}
}
```

Example: First non-repeating character index

```java
import java.util.*;

public class FirstUniqueChar {
	public static int firstUniqChar(String s) {
		int[] freq = new int[26];
		for (char c : s.toCharArray()) {
			if (c >= 'a' && c <= 'z') freq[c - 'a']++;
		}
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z' && freq[c - 'a'] == 1) return i;
		}
		return -1;
	}
}
```

Try next:
- Check if array contains duplicates
- Return indices of two numbers that sum to target (Two Sum via `HashMap`)

---

## Lesson 3 — Sliding Window (Easy → Low-Medium)

Use when you need:
- Longest/shortest substring with constraints
- Fixed-size subarray properties

Example: Fixed-size window: max sum of subarray size k

```java
public class MaxSumSubarrayK {
	public static int maxSumK(int[] nums, int k) {
		int windowSum = 0, best = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			windowSum += nums[i];
			if (i >= k) windowSum -= nums[i - k];
			if (i >= k - 1) best = Math.max(best, windowSum);
		}
		return best;
	}
}
```

Example: Variable-size window: longest substring without repeating characters

```java
import java.util.*;

public class LongestUniqueSubstring {
	public static int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> lastIndex = new HashMap<>();
		int left = 0, best = 0;
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			if (lastIndex.containsKey(c) && lastIndex.get(c) >= left) {
				left = lastIndex.get(c) + 1;
			}
			lastIndex.put(c, right);
			best = Math.max(best, right - left + 1);
		}
		return best;
	}
}
```

---

## Lesson 4 — Stack & Queue (Easy)

- Stack: last-in, first-out (undo, matching brackets)
- Queue: first-in, first-out (BFS later)

Example: Valid parentheses

```java
import java.util.*;

public class ValidParentheses {
	public static boolean isValid(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			if (c == '(' || c == '{' || c == '[') stack.push(c);
			else {
				if (stack.isEmpty()) return false;
				char open = stack.pop();
				if ((open == '(' && c != ')') ||
					(open == '{' && c != '}') ||
					(open == '[' && c != ']')) return false;
			}
		}
		return stack.isEmpty();
	}
}
```

---

## Lesson 5 — Linked Lists (Easy → Medium)

Practice reversing a linked list, detecting cycle (Floyd’s tortoise-hare), merging two sorted lists.

Example: Reverse singly linked list (iterative)

```java
class ListNode {
	int val;
	ListNode next;
	ListNode(int v) { val = v; }
}

public class ReverseLinkedList {
	public static ListNode reverse(ListNode head) {
		ListNode prev = null, curr = head;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
}
```

---

## What’s Next After These Lessons
- Binary Search patterns (boundaries, first/last occurrence)
- Sorting and when to rely on library vs custom
- Trees and traversal (BFS/DFS)
- Graphs with adjacency lists and visited sets
- Intro to DP: 1D (climbing stairs, house robber), 2D (grid paths)

---

## First Week Plan (Short and Fun)
- Day 1–2: Lesson 1 + 2 (arrays/strings, maps). 4–6 small problems.
- Day 3: Sliding Window. 2–3 problems.
- Day 4: Stack basics. 2–3 problems.
- Day 5: Linked lists. 2–3 problems.
- Day 6: Timed review: redo 4 earlier problems faster.
- Day 7: Rest/light review; explain 3 solutions out loud.

--

## Quick Checkpoint (Do These Now)
- Reverse an array in-place
- Palindrome (ignore non-alphanumeric)
- Two Sum (return indices)

For each, write a 2–3 sentence explanation of your approach.

---

## How to Practice in This Repo
- Create a new Java file per exercise (e.g., `TwoSum.java`).
- Add a `main` with sample inputs; print outputs to verify quickly.
- Aim for: clear variable names, `O(n)` where reasonable, and brief post-solution notes.

If you want this as a printable PDF, let me know and I’ll export it.