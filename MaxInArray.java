public class MaxInArray {
	public static int max(int[] numbers) {
		if (numbers == null || numbers.length == 0) throw new IllegalArgumentException("Array must not be null or empty");
		int best = Integer.MIN_VALUE;
		for (int value : numbers) {
			if (value > best) best = value;
		}
		return best;
	}

	public static void main(String[] args) {
		int[][] samples = {
			{1, 2, 3, 4, 5},
			{-3, -1, -7, -2},
			{42},
			{5, 5, 5}
		};
		for (int[] arr : samples) {
			System.out.println("max => " + max(arr));
		}
	}
}


