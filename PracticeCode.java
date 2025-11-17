

public class PracticeCode {

    public static void main(String[] args) {
        PointerDemo ref = new PointerDemo();
        PointerDemo refCopy = ref;
        System.out.println(refCopy.num);
        refCopy.num = 77;
        System.out.println(refCopy.num);
    }
}
class PointerDemo {
        int num = 25;
}
