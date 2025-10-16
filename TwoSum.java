import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public static int[] twoSum(int[] numbers, int target) {
		if (numbers == null) return null;
		Map<Integer, Integer> valueToIndex = new HashMap<>();
		for (int i = 0; i < numbers.length; i++) {
			int complement = target - numbers[i];
			if (valueToIndex.containsKey(complement)) {
				return new int[] { valueToIndex.get(complement), i };
			}
			valueToIndex.put(numbers[i], i);
		}
		return null; // no solution
	}

	private static String formatPair(int[] pair) {
		if (pair == null) return "null";
		return "[" + pair[0] + ", " + pair[1] + "]";
	}

	public static void main(String[] args) {
		int[] nums1 = {2, 7, 11, 15};
		int[] nums2 = {3, 2, 4};
		int[] nums3 = {3, 3};

		System.out.println("twoSum(nums1, 9) => " + formatPair(twoSum(nums1, 9))); // [0,1]
		System.out.println("twoSum(nums2, 6) => " + formatPair(twoSum(nums2, 6))); // [1,2]
		System.out.println("twoSum(nums3, 6) => " + formatPair(twoSum(nums3, 6))); // [0,1]
	}
}


