package gfg.week9.strings;

public class practiceProblems {

	public static void main(String[] args) {
	}

	static boolean search(String pat, String txt) {
		return txt.contains(pat);
	}

	static boolean searchAlter(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		int i = 0;
		while (i <= N - M) {
			int j;
			for (j = 0; j < M; j++)
				if (txt.charAt(i + j) != pat.charAt(j))
					break;
			if (j == M) {
				return true;
			} else if (j == 0)
				i = i + 1;
			else
				i = i + j;
		}
		return false;
	}

	public static boolean isAnagram(String s1, String s2) {
		int CHAR = 256;
		if (s1.length() != s2.length())
			return false;
		int[] count = new int[CHAR];
		for (int i = 0; i < s1.length(); i++) {
			count[s1.charAt(i)]++;
			count[s2.charAt(i)]--;
		}

		for (int i = 0; i < CHAR; i++) {
			if (count[i] != 0)
				return false;
		}
		return true;
	}

}
