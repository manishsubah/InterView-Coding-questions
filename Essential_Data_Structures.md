# Essential Data Structures: Start Here! 🎯

**Priority order: Learn these first, master them, then move to advanced topics.**

---

## 🥇 Tier 1: MUST KNOW (Start Here - 80% of Easy Problems)

### 1. **Arrays** 
**Why:** Foundation of everything. Used in 90% of problems.

**What it is:** Fixed-size container holding elements in order.
```java
int[] arr = {1, 2, 3, 4, 5};
```

**Key Operations:**
- Access: `arr[i]` → O(1)
- Search: Loop through → O(n)
- Insert/Delete: Requires shifting → O(n)

**Practice Problems (Easy):**
- ✅ Find max/min element
- ✅ Reverse array
- ✅ Two Sum (find pair that adds to target)
- ✅ Remove duplicates
- ✅ Rotate array

**Time to Master:** 2-3 days

---

### 2. **HashMap / HashSet** 
**Why:** Solves "find/check if exists" problems instantly. Used in 60% of problems.

**What it is:** Stores key-value pairs. Lookup is O(1) average.

```java
HashMap<String, Integer> map = new HashMap<>();
map.put("apple", 5);        // O(1)
map.get("apple");           // O(1) - returns 5
map.containsKey("apple");   // O(1) - returns true

HashSet<Integer> set = new HashSet<>();
set.add(10);                // O(1)
set.contains(10);           // O(1) - returns true
```

**When to Use:**
- Counting frequencies
- Tracking "seen" items
- Fast lookups (avoid nested loops!)

**Practice Problems (Easy):**
- ✅ Two Sum (use HashMap instead of nested loops)
- ✅ Check if array has duplicates
- ✅ First non-repeating character
- ✅ Anagram check
- ✅ Group anagrams

**Time to Master:** 2-3 days

---

### 3. **Strings** 
**Why:** Often treated as arrays of characters. Very common in interviews.

**What it is:** Immutable sequence of characters in Java.

```java
String s = "hello";
char[] chars = s.toCharArray();  // Convert to array
StringBuilder sb = new StringBuilder();  // For many concatenations
```

**Key Operations:**
- Access: `s.charAt(i)` → O(1)
- Substring: `s.substring(start, end)` → O(n)
- Concatenation: Use `StringBuilder` for efficiency

**Practice Problems (Easy):**
- ✅ Reverse string
- ✅ Check palindrome
- ✅ Valid parentheses
- ✅ Longest common prefix
- ✅ Reverse words in string

**Time to Master:** 2-3 days

---

## 🥈 Tier 2: VERY IMPORTANT (Learn After Tier 1)

### 4. **Stack** 
**Why:** Perfect for "matching pairs" and "undo" problems. Simple concept.

**What it is:** Last-In-First-Out (LIFO) - like a stack of plates.

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(10);    // Add to top
stack.pop();       // Remove from top → O(1)
stack.peek();      // Look at top without removing
```

**When to Use:**
- Matching brackets/parentheses
- Valid parentheses
- Next greater element
- Evaluate expressions
- Undo/redo operations

**Practice Problems (Easy):**
- ✅ Valid parentheses
- ✅ Min stack
- ✅ Daily temperatures
- ✅ Next greater element

**Time to Master:** 1-2 days

---

### 5. **Queue** 
**Why:** Essential for BFS (breadth-first search) later. Simple FIFO concept.

**What it is:** First-In-First-Out (FIFO) - like a line at a store.

```java
Queue<Integer> queue = new LinkedList<>();
queue.offer(10);   // Add to back
queue.poll();       // Remove from front → O(1)
queue.peek();       // Look at front without removing
```

**When to Use:**
- BFS traversal (trees, graphs)
- Level-order traversal
- Sliding window (sometimes)

**Practice Problems (Easy):**
- ✅ Implement queue using stacks
- ✅ First unique character in stream

**Time to Master:** 1 day (basic), more when learning BFS

---

### 6. **Linked List** 
**Why:** Teaches pointer manipulation. Common interview topic.

**What it is:** Nodes connected by pointers. Dynamic size.

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
```

**Key Operations:**
- Insert/Delete: O(1) if you have the node
- Search: O(n) - must traverse

**When to Use:**
- When size is unknown
- Frequent insertions/deletions
- Reversing lists
- Cycle detection

**Practice Problems (Easy → Medium):**
- ✅ Reverse linked list
- ✅ Merge two sorted lists
- ✅ Detect cycle (Floyd's algorithm)
- ✅ Remove duplicates
- ✅ Middle of linked list

**Time to Master:** 2-3 days

---

## 🥉 Tier 3: IMPORTANT (Learn After Tier 2)

### 7. **Two Pointers Technique** 
**Why:** Not a DS, but a pattern that makes arrays/strings problems easy.

**What it is:** Use two indices moving through array/string.

**When to Use:**
- Sorted arrays
- Palindrome checks
- Finding pairs
- Removing duplicates

**Practice Problems:**
- ✅ Two Sum (sorted array)
- ✅ Three Sum
- ✅ Container with most water
- ✅ Trapping rainwater

**Time to Master:** 1-2 days

---

### 8. **Sliding Window** ⭐
**Why:** Pattern for substring/subarray problems. Very common.

**What it is:** Maintain a window that slides through array/string.

**When to Use:**
- Longest/shortest substring
- Subarray with sum K
- Maximum in sliding window

**Practice Problems:**
- ✅ Longest substring without repeating chars
- ✅ Maximum sum subarray of size K
- ✅ Minimum window substring

**Time to Master:** 2-3 days

---

## 📊 Learning Priority Summary

### Week 1: Foundation
1. **Arrays** (Days 1-2)
2. **HashMap/HashSet** (Days 3-4)
3. **Strings** (Days 5-6)
4. **Review & Practice** (Day 7)

### Week 2: Linear Structures
1. **Stack** (Days 1-2)
2. **Queue** (Day 3)
3. **Linked List** (Days 4-5)
4. **Two Pointers** (Day 6)
5. **Review** (Day 7)

### Week 3: Patterns
1. **Sliding Window** (Days 1-3)
2. **More practice** (Days 4-7)

---

## 🎯 Quick Decision Tree: Which DS to Use?

```
Problem asks to:
├─ Find/Check if exists → HashMap/HashSet
├─ Count frequencies → HashMap
├─ Matching pairs/brackets → Stack
├─ Reverse something → Two Pointers or Stack
├─ Substring/subarray → Sliding Window
├─ Sorted array, find pairs → Two Pointers
├─ Dynamic size, frequent insert/delete → Linked List
└─ Level-order traversal → Queue
```

---

## 💡 Pro Tips

1. **Start with Arrays + HashMap**: These solve 70% of easy problems.
2. **Master one at a time**: Don't jump around. Complete 5-10 problems per DS.
3. **Practice daily**: Even 20 minutes helps. Consistency > intensity.
4. **Explain out loud**: After solving, explain your approach. This helps retention.
5. **Time yourself**: After mastering, try solving faster each time.

---

## 📝 Practice Checklist

### Arrays
- [ ] Find max/min
- [ ] Reverse array
- [ ] Two Sum
- [ ] Remove duplicates
- [ ] Rotate array

### HashMap/HashSet
- [ ] Two Sum (HashMap version)
- [ ] Contains duplicate
- [ ] First unique character
- [ ] Anagram check
- [ ] Group anagrams

### Strings
- [ ] Reverse string
- [ ] Valid palindrome
- [ ] Valid parentheses
- [ ] Longest common prefix
- [ ] Reverse words

### Stack
- [ ] Valid parentheses
- [ ] Min stack
- [ ] Daily temperatures
- [ ] Next greater element

### Queue
- [ ] Implement queue using stacks
- [ ] First unique character in stream

### Linked List
- [ ] Reverse linked list
- [ ] Merge two sorted lists
- [ ] Detect cycle
- [ ] Middle of linked list
- [ ] Remove duplicates

### Two Pointers
- [ ] Two Sum (sorted)
- [ ] Three Sum
- [ ] Container with most water
- [ ] Trapping rainwater

### Sliding Window
- [ ] Longest substring without repeating chars
- [ ] Maximum sum subarray of size K
- [ ] Minimum window substring

---

## 🚀 Next Steps

After mastering Tier 1 & 2:
- Binary Search
- Trees (Binary Tree, BST)
- Graphs
- Heaps
- Dynamic Programming

**But first, master the basics!** These 6-8 data structures will get you through most easy and many medium problems.

---

**Remember:** Understanding > Memorization. Practice with real problems, not just theory! 🎓


