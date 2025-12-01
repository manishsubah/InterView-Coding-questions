
class Node {
    int data;        // Data stored in the node
    Node next;       // Reference (pointer) to the next node
    
    // Constructor to create a new node
    Node(int data) {
        this.data = data;
        this.next = null;  // Initially, no next node
    }
}

public class SimpleLinkedList {
    Node head;  // Reference (pointer) to the first node
    
    // Constructor
    SimpleLinkedList() {
        this.head = null;  // Initially, list is empty
    }
    
    /**
     * Method 1: Add a node at the beginning
     * 
     * Think of it like:
     * 1. Create a new node
     * 2. Make the new node point to the current head
     * 3. Make head point to the new node
     */
    public void addAtBeginning(int data) {
        // Step 1: Create a new node
        Node newNode = new Node(data);
        
        // Step 2: Make new node point to current head
        newNode.next = head;
        
        // Step 3: Make head point to new node
        head = newNode;
        
        System.out.println("Added " + data + " at the beginning");
    }
    
    /**
     * Method 2: Add a node at the end
     * 
     * Steps:
     * 1. Create a new node
     * 2. If list is empty, make head point to new node
     * 3. Otherwise, traverse to the last node
     * 4. Make last node point to new node
     */
    public void addAtEnd(int data) {
        Node newNode = new Node(data);
        
        // If list is empty, new node becomes head
        if (head == null) {
            head = newNode;
            System.out.println("Added " + data + " at the end (first node)");
            return;
        }
        
        // Traverse to the last node
        Node current = head;  // Start from head
        while (current.next != null) {  // Keep moving until we find the last node
            current = current.next;  // Move to next node
        }
        
        // Now current points to the last node
        // Make it point to the new node
        current.next = newNode;
        
        System.out.println("Added " + data + " at the end");
    }
    
    /**
     * Method 3: Display the entire list
     * 
     * Steps:
     * 1. Start from head
     * 2. Print each node's data
     * 3. Move to next node using the reference
     * 4. Stop when we reach null (end of list)
     */
    public void display() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        
        System.out.print("List: ");
        Node current = head;  // Start from head
        
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");  // Arrow shows the link
            }
            current = current.next;  // Move to next node using reference
        }
        System.out.println(" -> null");
    }
    
    /**
     * Method 4: Search for a value
     * 
     * Steps:
     * 1. Start from head
     * 2. Check each node's data
     * 3. If found, return true
     * 4. If not found after checking all nodes, return false
     */
    public boolean search(int data) {
        Node current = head;
        
        while (current != null) {
            if (current.data == data) {
                return true;  // Found!
            }
            current = current.next;  // Move to next node
        }
        
        return false;  // Not found
    }
    
    /**
     * Method 5: Delete a node by value
     * 
     * Steps:
     * 1. If list is empty, return
     * 2. If head node has the value, delete head
     * 3. Otherwise, find the node and its previous node
     * 4. Make previous node point to next node (skip the node to delete)
     */
    public void delete(int data) {
        if (head == null) {
            System.out.println("List is empty, nothing to delete");
            return;
        }
        
        // If head node has the value to delete
        if (head.data == data) {
            head = head.next;  // Make head point to next node
            System.out.println("Deleted " + data);
            return;
        }
        
        // Find the node to delete and its previous node
        Node current = head;
        Node previous = null;
        
        while (current != null && current.data != data) {
            previous = current;        // Keep track of previous node
            current = current.next;    // Move to next node
        }
        
        // If node not found
        if (current == null) {
            System.out.println(data + " not found in the list");
            return;
        }
        
        // Skip the node to delete
        // Make previous node point to next node
        previous.next = current.next;
        
        System.out.println("Deleted " + data);
    }
    
    /**
     * Method 6: Get the size of the list
     */
    public int size() {
        int count = 0;
        Node current = head;
        
        while (current != null) {
            count++;
            current = current.next;
        }
        
        return count;
    }
    
    /**
     * Method 7: Add at a specific position
     */
    public void addAtPosition(int data, int position) {
        if (position < 1) {
            System.out.println("Invalid position");
            return;
        }
        
        if (position == 1) {
            addAtBeginning(data);
            return;
        }
        
        Node newNode = new Node(data);
        Node current = head;
        
        // Move to the node before the position
        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        
        if (current == null) {
            System.out.println("Position out of range");
            return;
        }
        
        // Insert the new node
        newNode.next = current.next;
        current.next = newNode;
        
        System.out.println("Added " + data + " at position " + position);
    }
    
    // Main method to test the linked list
    public static void main(String[] args) {
        System.out.println("=== Simple Linked List Program ===\n");
        System.out.println("Understanding References (Pointers in Java)\n");
        
        // Create a new linked list
        SimpleLinkedList list = new SimpleLinkedList();
        
        // Display empty list
        System.out.println("1. Creating an empty list:");
        list.display();
        System.out.println("   Size: " + list.size() + "\n");
        
        // Add nodes at the end
        System.out.println("2. Adding nodes at the end:");
        list.addAtEnd(10);
        list.addAtEnd(20);
        list.addAtEnd(30);
        list.display();
        System.out.println("   Size: " + list.size() + "\n");
        
        // Add node at the beginning
        System.out.println("3. Adding node at the beginning:");
        list.addAtBeginning(5);
        list.display();
        System.out.println("   Size: " + list.size() + "\n");
        
        // Add more nodes
        System.out.println("4. Adding more nodes:");
        list.addAtEnd(40);
        list.addAtEnd(50);
        list.display();
        System.out.println("   Size: " + list.size() + "\n");
        
        // Search for values
        System.out.println("5. Searching for values:");
        System.out.println("   Is 20 in the list? " + list.search(20));
        System.out.println("   Is 100 in the list? " + list.search(100));
        System.out.println();
        
        // Delete a node
        System.out.println("6. Deleting a node:");
        list.display();
        list.delete(30);
        list.display();
        System.out.println("   Size: " + list.size() + "\n");
        
        // Delete head node
        System.out.println("7. Deleting head node:");
        list.display();
        list.delete(5);
        list.display();
        System.out.println("   Size: " + list.size() + "\n");
        
        // Add at specific position
        System.out.println("8. Adding at specific position:");
        list.display();
        list.addAtPosition(25, 2);
        list.display();
        System.out.println("   Size: " + list.size() + "\n");
        
        // Final list
        System.out.println("9. Final list:");
        list.display();
        System.out.println("   Size: " + list.size() + "\n");
        
        System.out.println("=== Understanding References ===");
        System.out.println("In Java:");
        System.out.println("- 'Node next' is a reference (like a pointer)");
        System.out.println("- It stores the memory address of the next node");
        System.out.println("- 'head' is a reference to the first node");
        System.out.println("- When we do 'current = current.next', we're");
        System.out.println("  following the reference to the next node");
        System.out.println("- 'null' means the reference points to nothing");
    }
}




