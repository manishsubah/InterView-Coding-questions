/**
 * Linked List Visual Explanation
 * 
 * This program helps you visualize how references (pointers) work
 * in a linked list by showing step-by-step what happens in memory.
 */
class ListNode {
    int value;
    ListNode next;
    
    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class LinkedListVisualExplanation {
    
    /**
     * Visual demonstration of how references work
     */
    public static void demonstrateReferences() {
        System.out.println("=== Understanding References (Pointers) ===\n");
        
        // Step 1: Create nodes
        System.out.println("Step 1: Creating individual nodes");
        ListNode node1 = new ListNode(10);
        ListNode node2 = new ListNode(20);
        ListNode node3 = new ListNode(30);
        
        System.out.println("node1.value = " + node1.value);
        System.out.println("node2.value = " + node2.value);
        System.out.println("node3.value = " + node3.value);
        System.out.println("At this point, nodes are NOT connected\n");
        
        // Visual representation
        System.out.println("Memory representation:");
        System.out.println("node1 -> [10 | null]");
        System.out.println("node2 -> [20 | null]");
        System.out.println("node3 -> [30 | null]");
        System.out.println("(Each node exists independently)\n");
        
        // Step 2: Connect nodes using references
        System.out.println("Step 2: Connecting nodes using references");
        node1.next = node2;  // node1's 'next' now points to node2
        node2.next = node3;  // node2's 'next' now points to node3
        
        System.out.println("After connecting:");
        System.out.println("node1 -> [10 | next] -> node2");
        System.out.println("node2 -> [20 | next] -> node3");
        System.out.println("node3 -> [30 | null]");
        System.out.println("(Now they form a linked list!)\n");
        
        // Step 3: Traverse using references
        System.out.println("Step 3: Traversing the list using references");
        System.out.println("Starting from node1, following the 'next' references:\n");
        
        ListNode current = node1;  // Start from first node
        int step = 1;
        
        while (current != null) {
            System.out.println("Step " + step + ":");
            System.out.println("  current points to node with value: " + current.value);
            System.out.println("  current.next points to: " + 
                (current.next != null ? "node with value " + current.next.value : "null"));
            
            current = current.next;  // Move to next node by following the reference
            step++;
            System.out.println();
        }
        
        System.out.println("Reached the end (null)!\n");
    }
    
    /**
     * Demonstrate how to insert a node in the middle
     */
    public static void demonstrateInsertion() {
        System.out.println("=== Inserting a Node in the Middle ===\n");
        
        // Create a list: 10 -> 20 -> 30
        ListNode node1 = new ListNode(10);
        ListNode node2 = new ListNode(20);
        ListNode node3 = new ListNode(30);
        node1.next = node2;
        node2.next = node3;
        
        System.out.println("Original list: 10 -> 20 -> 30");
        System.out.println("\nWe want to insert 15 between 10 and 20\n");
        
        // Create new node
        ListNode newNode = new ListNode(15);
        System.out.println("Step 1: Created new node with value 15");
        System.out.println("newNode -> [15 | null]\n");
        
        // Insert between node1 and node2
        System.out.println("Step 2: Making newNode point to node2");
        newNode.next = node2;  // newNode.next = node1.next
        System.out.println("newNode -> [15 | next] -> node2 (value 20)\n");
        
        System.out.println("Step 3: Making node1 point to newNode");
        node1.next = newNode;
        System.out.println("node1 -> [10 | next] -> newNode (value 15)\n");
        
        System.out.println("Final list: 10 -> 15 -> 20 -> 30");
        System.out.println("\nVisual representation:");
        System.out.println("node1 -> [10 | next] ->");
        System.out.println("newNode -> [15 | next] ->");
        System.out.println("node2 -> [20 | next] ->");
        System.out.println("node3 -> [30 | null]");
    }
    
    /**
     * Demonstrate how to delete a node
     */
    public static void demonstrateDeletion() {
        System.out.println("\n=== Deleting a Node ===\n");
        
        // Create a list: 10 -> 20 -> 30
        ListNode node1 = new ListNode(10);
        ListNode node2 = new ListNode(20);
        ListNode node3 = new ListNode(30);
        node1.next = node2;
        node2.next = node3;
        
        System.out.println("Original list: 10 -> 20 -> 30");
        System.out.println("We want to delete node with value 20\n");
        
        System.out.println("Step 1: Find the node to delete and its previous node");
        ListNode current = node1;
        ListNode previous = null;
        
        while (current != null && current.value != 20) {
            previous = current;
            current = current.next;
        }
        
        System.out.println("Found:");
        System.out.println("  previous points to node with value: " + previous.value);
        System.out.println("  current points to node with value: " + current.value + " (to delete)\n");
        
        System.out.println("Step 2: Make previous point to current's next");
        System.out.println("  previous.next = current.next");
        previous.next = current.next;
        
        System.out.println("\nResult: 10 -> 30");
        System.out.println("(node with value 20 is no longer in the list)");
        System.out.println("\nVisual representation:");
        System.out.println("node1 -> [10 | next] ->");
        System.out.println("node3 -> [30 | null]");
        System.out.println("(node2 is no longer accessible)");
    }
    
    /**
     * Show the difference between copying reference vs copying value
     */
    public static void demonstrateReferenceVsValue() {
        System.out.println("\n=== Reference vs Value Copy ===\n");
        
        ListNode node1 = new ListNode(100);
        ListNode node2 = new ListNode(200);
        node1.next = node2;
        
        System.out.println("Original:");
        System.out.println("node1 -> [100 | next] -> node2");
        System.out.println("node2 -> [200 | null]\n");
        
        System.out.println("Case 1: Copying the reference");
        ListNode node3 = node1;  // node3 now points to the same object as node1
        System.out.println("ListNode node3 = node1;");
        System.out.println("node3 and node1 point to the SAME object!\n");
        
        System.out.println("If we change node3.value:");
        node3.value = 999;
        System.out.println("node3.value = 999;");
        System.out.println("node1.value is also " + node1.value + " (same object!)\n");
        
        System.out.println("Case 2: Following the reference");
        ListNode node4 = node1.next;  // node4 points to what node1.next points to
        System.out.println("ListNode node4 = node1.next;");
        System.out.println("node4 points to node2");
        System.out.println("node4.value = " + node4.value);
    }
    
    public static void main(String[] args) {
        demonstrateReferences();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demonstrateInsertion();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demonstrateDeletion();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demonstrateReferenceVsValue();
        
        System.out.println("\n=== Key Concepts ===");
        System.out.println("1. A reference (like 'next') stores the address of an object");
        System.out.println("2. When you assign 'node1.next = node2', you're making");
        System.out.println("   node1's 'next' reference point to node2");
        System.out.println("3. 'null' means the reference doesn't point to anything");
        System.out.println("4. Following references: 'current = current.next' moves");
        System.out.println("   to the next node in the list");
        System.out.println("5. To delete a node, make the previous node skip it by");
        System.out.println("   pointing directly to the next node");
    }
}




