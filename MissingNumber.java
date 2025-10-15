public class MissingNumber {
    public static int findMissingNumber(int[] arr) {
        int n = arr.length + 1;
        System.out.println("Length of array: " + n);
        // Calculate the expected sum of numbers from 1 to n
        int totalSum = n*(n+1)/2; // sum of n natural numbers
        int arraySum = 0;
        // Calculate the sum of the elements in the array
        // Using enhanced for loop
        for(int num:arr) {
            arraySum += num;
        }
        //for (int i = 0; i < arr.length; i++) {
        //    arraySum += arr[i];
        //}
        System.out.println("Sum of array: " + arraySum);
        return totalSum - arraySum;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6, 7, 8, 9, 10};
        System.out.println("Missing number: " + findMissingNumber(arr));
    }
}
