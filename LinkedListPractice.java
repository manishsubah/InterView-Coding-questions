/**
 * Linked List Practice - Try It Yourself!
 * 
 * This file contains practice problems for you to solve.
 * Uncomment and implement each method.
 */
class PracticeNode {
    int data;
    PracticeNode next;
    
    PracticeNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListPractice {
    PracticeNode head;
    
    LinkedListPractice() {
        head = null;
    }
    
    // Helper method to add at end (for testing)
    public void add(int data) {
        PracticeNode newNode = new PracticeNode(data);
        if (head == null) {
            head = newNode;
            return;
        }
        PracticeNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    
    // Helper method to display
    public void display() {
        PracticeNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    /**
     * PRACTICE 1: Find the length of the linked list
     * 
     * Hint: Traverse the list and count nodes
     */
    public int findLength() {
        // TODO: Implement this method
        // Start from head, count each node until you reach null
        return 0;  // Replace with your implementation
    }
    
    /**
     * PRACTICE 2: Find the maximum value in the list
     * 
     * Hint: Traverse and keep track of maximum
     */
    public int findMax() {
        // TODO: Implement this method
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        // Traverse and find maximum
        return 0;  // Replace with your implementation
    }
    
    /**
     * PRACTICE 3: Reverse the linked list
     * 
     * Hint: You need three references: previous, current, next
     */
    public void reverse() {
        // TODO: Implement this method
        // Use three pointers: previous, current, next
        // For each node, make current.next point to previous
    }
    
    /**
     * PRACTICE 4: Check if the list contains a cycle
     * 
     * Hint: Use two pointers - slow and fast (Floyd's cycle detection)
     */
    public boolean hasCycle() {
        // TODO: Implement this method
        // Use slow pointer (moves 1 step) and fast pointer (moves 2 steps)
        // If they meet, there's a cycle
        return false;  // Replace with your implementation
    }
    
    /**
     * PRACTICE 5: Find the middle node
     * 
     * Hint: Use two pointers - one moves twice as fast
     */
    public int findMiddle() {
        // TODO: Implement this method
        // Use slow and fast pointers
        // When fast reaches end, slow is at middle
        return -1;  // Replace with your implementation
    }
    
    /**
     * PRACTICE 6: Remove duplicates from sorted list
     * 
     * Hint: Compare current node with next node
     */
    public void removeDuplicates() {
        // TODO: Implement this method
        // If current.data == current.next.data, skip next node
    }
    
    /**
     * PRACTICE 7: Merge two sorted linked lists
     * 
     * Hint: Compare values and link nodes accordingly
     */
    public static PracticeNode mergeSortedLists(PracticeNode list1, PracticeNode list2) {
        // TODO: Implement this method
        // Create a dummy node to start
        // Compare and merge
        return null;  // Replace with your implementation
    }
    
    // Test your implementations
    public static void main(String[] args) {
        System.out.println("=== Linked List Practice Problems ===\n");
        
        // Test Practice 1: Find Length
        System.out.println("Practice 1: Find Length");
        LinkedListPractice list1 = new LinkedListPractice();
        list1.add(10);
        list1.add(20);
        list1.add(30);
        list1.display();
        System.out.println("Length: " + list1.findLength());
        System.out.println("Expected: 3\n");
        
        // Test Practice 2: Find Max
        System.out.println("Practice 2: Find Maximum");
        LinkedListPractice list2 = new LinkedListPractice();
        list2.add(5);
        list2.add(15);
        list2.add(10);
        list2.add(25);
        list2.display();
        System.out.println("Maximum: " + list2.findMax());
        System.out.println("Expected: 25\n");
        
        // Test Practice 3: Reverse
        System.out.println("Practice 3: Reverse List");
        LinkedListPractice list3 = new LinkedListPractice();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        System.out.println("Before: ");
        list3.display();
        list3.reverse();
        System.out.println("After: ");
        list3.display();
        System.out.println("Expected: 3 -> 2 -> 1 -> null\n");
        
        // Add more tests for other practices...
        
        System.out.println("Implement the methods above and test them!");
    }
}

