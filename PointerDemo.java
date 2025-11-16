public class PointerDemo {
    public static void main(String[] args) {
        InnerPointerDemo demo = new InnerPointerDemo();
        InnerPointerDemo demo2 = demo; // demo2 is a reference to the same object as demo
        System.out.println(demo2.x);
        demo2.x = 20;
        System.out.println(demo.x);
    }
}
class InnerPointerDemo {
    int x = 10;
}



/*
 A pointer is a variable that stores the memory address of another variable or object.

👉 Instead of storing the value directly, it stores where the value is located in memory.

Think of it like:

A pointer = the address of a house

The actual data = the house

You can reach the data (house) only through the address (pointer)
 */
