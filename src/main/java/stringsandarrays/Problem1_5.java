package stringsandarrays;

import utils.Z;

public class Problem1_5 {

	public static void main(String[] args) {
		Z.print(new Problem1_5().strCompression("aabcccccaaa"));
	}

	/**
	 * U must assure the edge case in a for loop!
	 *
	 * @param s
	 * @return
	 */
	public String strCompression(String s) {
		if (s == null || s.length() == 1) return s;
		StringBuilder sb = new StringBuilder();
		sb.append(s.charAt(0));
		int count = 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(i - 1)) {
				sb.append(count);
				sb.append(s.charAt(i));
				count = 1;
			} else count++;
		}
		sb.append(count);
		return sb.length() < s.length() ? sb.toString() : s;
	}
}
