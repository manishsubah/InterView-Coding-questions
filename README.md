### InterViewCoding — Java DSA Practice

This repository contains Java implementations and practice problems to prepare for coding interviews, progressing from easy to medium difficulty.

---

## Contents
- **`Essential_Data_Structures.md`**: ⭐ **START HERE!** Priority guide to the most important DS (Arrays, HashMap, Strings, Stack, Queue, Linked List) with practice checklist.
- **`100_Array_Problems_Easy_to_Medium.md`**: 📋 Complete list of 100 array problems from easy to medium with descriptions and practice plan.
- `DSA_Interview_Learning_Guide.md`: Step-by-step study plan, examples, and a 1-week schedule.
- Java source files (e.g., `ReverseArr.java`, `Palindrome.java`, etc.).
- `.gitignore` configured to avoid committing compiled artifacts (e.g., `*.class`).

---

## Prerequisites
- Java 8+ (JDK). Verify with:

```bash
java -version
javac -version
```

---

## Running Code
Compile and run a single Java file from the project root:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac Palindrome.java
java Palindrome
```

Tip: Re-compile after changes. The `.class` files are ignored by Git.

---

## Study Guide
Start with the structured path in `DSA_Interview_Learning_Guide.md`:
- Foundations: Arrays, Strings, Big-O
- Two Pointers, Sliding Window
- Stack/Queue, Hashing, Linked Lists
- Binary Search, Sorting, Trees, Graphs, Heaps
- Intro to Dynamic Programming

Each section includes brief explanations and Java examples you can copy and run.

---

## Git Hygiene
Compiled outputs are ignored by `.gitignore` (e.g., `*.class`, `bin/`, `out/`). If you accidentally committed compiled files earlier, untrack them:

```bash
git rm -r --cached *.class bin/ out/ target/ build/
git commit -m "chore: stop tracking compiled outputs"
```

---

## Suggested Workflow
1. Pick a topic from the guide.
2. Create a new `ProblemName.java` with a `main` method.
3. Implement, compile, run, and print results.
4. Write 2–3 sentences explaining your approach.
5. Revisit after 2–3 days for a faster second attempt.

---

## Questions / Next Steps
If you want starter templates for the first exercises (`ReverseStringEasy`, `PalindromeEasy`, `MaxInArray`, `TwoSum`), ask and they’ll be added here.


