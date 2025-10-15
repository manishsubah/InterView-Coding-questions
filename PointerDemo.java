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
