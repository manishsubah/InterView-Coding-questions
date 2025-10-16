public class PalindromeEasy {
	private static boolean isAlphaNumeric(char c) {
		return Character.isLetterOrDigit(c);
	}

	public static boolean isPalindrome(String s) {
		if (s == null) return false;
		int leftIndex = 0;
		int rightIndex = s.length() - 1;
		while (leftIndex < rightIndex) {
			char left = s.charAt(leftIndex);
			char right = s.charAt(rightIndex);
			if (!isAlphaNumeric(left)) { leftIndex++; continue; }
			if (!isAlphaNumeric(right)) { rightIndex--; continue; }
			if (Character.toLowerCase(left) != Character.toLowerCase(right)) return false;
			leftIndex++;
			rightIndex--;
		}
		return true;
	}

	public static void main(String[] args) {
		String[] tests = {
			"A man, a plan, a canal: Panama",
			"race a car",
			"",
			"a",
			"No 'x' in Nixon"
		};
		for (String t : tests) {
			System.out.println('"' + t + '"' + " => " + isPalindrome(t));
		}
	}
}


