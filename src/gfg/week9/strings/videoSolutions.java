package gfg.week9.strings;

import java.util.Arrays;

public class videoSolutions {
	public static final int CHAR = 256;
	public static final int d = 256;
	public static final int q = 101;

	public static void main(String[] args) {
	}

	public static void stringmethod() {
		String str = "geeksforgeeks";
		int[] count = new int[26];
		for (int i = 0; i < str.length(); i++) {
			count[str.charAt(i) - 'a']++;
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] > 0) {
				System.out.println((char) (i + 'a') + " " + count[i]);
			}
		}
	}

	static boolean areAnagram(String s1, String s2) {
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

	static int leftMost(String str) {
		int[] fIndex = new int[CHAR];
		Arrays.fill(fIndex, -1);
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < str.length(); i++) {
			int fi = fIndex[str.charAt(i)];
			if (fi == -1)
				fIndex[str.charAt(i)] = i;
			else
				res = Math.min(res, fi);
		}
		return (res == Integer.MAX_VALUE) ? -1 : res;
	}

	static int leftMostMostEfficient(String str) {
		boolean[] visited = new boolean[CHAR];
		int res = -1;
		for (int i = str.length() - 1; i >= 0; i--) {
			if (visited[str.charAt(i)])
				res = i;
			else
				visited[str.charAt(i)] = true;
		}
		return res;
	}

	static int nonRep(String str) {
		int[] fI = new int[CHAR];
		Arrays.fill(fI, -1);

		for (int i = 0; i < str.length(); i++) {
			if (fI[str.charAt(i)] == -1)
				fI[str.charAt(i)] = i;
			else
				fI[str.charAt(i)] = -2;
		}
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < CHAR; i++) {
			if (fI[i] >= 0)
				res = Math.min(res, fI[i]);
		}
		return (res == Integer.MAX_VALUE) ? -1 : res;
	}

	static void reverse(char str[], int low, int high) {
		while (low <= high) {
			// swap
			char temp = str[low];
			str[low] = str[high];
			str[high] = temp;
			//
			low++;
			high--;
		}
	}

	////////// Handles only single space scenarios
	static void reverseWords(char str[], int n) {
		int start = 0;
		for (int end = 0; end < n; end++) {
			if (str[end] == ' ') {
				reverse(str, start, end - 1);
				start = end + 1;
			}
		}
		reverse(str, start, n - 1);//// To Reverse the last word
		reverse(str, 0, n - 1);
	}

	static void search(String pat, String txt) {
		boolean flag;
		int patlen = pat.length(), txtlen = txt.length();
		for (int i = 0; i <= txtlen - patlen; i++) {
			flag = true;
			for (int j = 0; j < patlen; j++) {
				if (pat.charAt(j) != txt.charAt(i + j)) {
					flag = false;
				}
			}
			if (flag) {
				System.out.println("index : " + i);
			}
		}
	}

	//////// For Distinct characters only(In Pattern)
	static void patSearchinng(String txt, String pat) {
		int m = pat.length();
		int n = txt.length();
		for (int i = 0; i <= (n - m);) {
			int j;
			for (j = 0; j < m; j++)
				if (pat.charAt(j) != txt.charAt(i + j))
					break;

			if (j == m)
				System.out.print(i + " ");
			if (j == 0) {
				i++;
			} else {
				i = (i + j);
			}
		}
	}

	static void RBSearch(String pat, String txt, int M, int N) {
		// Compute (d^(M-1))%q
		int h = 1;
		for (int i = 1; i <= M - 1; i++)
			h = (h * d) % q;
		// Compute p and to
		int p = 0, t = 0;
		for (int i = 0; i < M; i++) {
			p = (p * d + pat.charAt(i)) % q;
			t = (t * d + txt.charAt(i)) % q;
		}
		for (int i = 0; i <= (N - M); i++) {
			// Check for hit
			if (p == t) {
				boolean flag = true;
				for (int j = 0; j < M; j++)
					if (txt.charAt(i + j) != pat.charAt(j)) {
						flag = false;
						break;
					}
				if (flag == true)
					System.out.print(i + " ");
			}
			// Compute ti+1 using ti
			if (i < N - M) {
				t = ((d * (t - txt.charAt(i) * h)) + txt.charAt(i + M)) % q;
				if (t < 0)
					t = t + q;
			}
		}
	}

	//////// Finding Longest Proper Prefix Suffix
	//////// Time Complexity : O(n*n*n)
	static void fillLPSNaive(String str, int lps[]) {
		for (int i = 0; i < str.length(); i++) {
			lps[i] = longPropPreSuffNaive(str, i + 1);
		}
	}

	static int longPropPreSuffNaive(String str, int n) {
		for (int len = n - 1; len > 0; len--) {
			boolean flag = true;
			for (int i = 0; i < len; i++) {
				if (str.charAt(i) != str.charAt(n - len + i)) {
					flag = false;
				}
			}
			if (flag == true)
				return len;
		}
		return 0;
	}

	static boolean isRotation(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;
		else {
			return (s1 + s1).contains(s2);
		}
	}

}
