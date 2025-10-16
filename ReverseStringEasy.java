public class ReverseStringEasy {
	public static String reverse(String input) {
		if (input == null || input.length() <= 1) return input;
		char[] characters = input.toCharArray();
		int leftIndex = 0;
		int rightIndex = characters.length - 1;
		while (leftIndex < rightIndex) {
			char temp = characters[leftIndex];
			characters[leftIndex] = characters[rightIndex];
			characters[rightIndex] = temp;
			leftIndex++;
			rightIndex--;
		}
		return new String(characters);
	}

	public static void main(String[] args) {
		String[] samples = {"hello", "racecar", "", "a", "Interview"};
		for (String s : samples) {
			System.out.println("reverse(\"" + s + "\") => " + reverse(s));
		}
	}
}


