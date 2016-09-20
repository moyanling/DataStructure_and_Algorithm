package stringsandarrays;

public class Problem1_2 {

	public static void main(String[] args) {
		System.out.println(new Problem1_2().reverseString("123456789"));
	}

	public String reverseString(String s) {
		if (s == null) return null;
		char[] charArr = s.toCharArray();
		for (int i = 0; i < charArr.length / 2; i++) {
			char temp = charArr[charArr.length - 1 - i];
			charArr[charArr.length - 1 - i] = charArr[i];
			charArr[i] = temp;
		}
		return String.valueOf(charArr);
	}
}
