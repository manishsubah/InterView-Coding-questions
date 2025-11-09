public class Smallest {

    public static int findSmallest(int[] arr) {
        int smallest = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        return smallest;
    }

    public static void main(String[] args) {
        int[] arr = {5, 15, 22, 1, 15, 24};
        int smallest = findSmallest(arr);
        System.out.println("Smallest so far: " + smallest);
    }
}
