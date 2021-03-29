package gfg.week3.recursion;

import java.util.ArrayList;
import java.util.Collections;

public class practiceProblems {
	static int counter = 2;

	public void printNos(int N) {
		if (N == 0)
			return;
		printNos(N - 1);
		System.out.println(N);
	}

	public static int sumOfDigits(int n) {
		if (n < 10)
			return n;
		return n % 10 + sumOfDigits(n / 10);
	}

	public static int countDigits(int n) {
		if (n < 10)
			return 1;
		return 1 + countDigits(n / 10);
	}

	public static int digitalRoot(int n) {
		if (n < 10)
			return n;
		int x = n % 10 + sumOfDigits(n / 10);
		return x < 10 ? x : digitalRoot(x);
	}

	static long fibonacci(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static int RecursivePower(int n, int p) {
		if (p == 1)
			return n;
		if (p == 0)
			return 1;
		if (p % 2 == 0)
			return RecursivePower(n, p / 2) * RecursivePower(n, p / 2);
		return RecursivePower(n, (p - 1) / 2) * RecursivePower(n, (p - 1) / 2) * n;
	}

	public long toh(int N, int from, int to, int aux) {
		long moves = 0L;
		if (N >= 1) {
			// recursive call to move top disk from "from" to aux in current call
			moves += toh(N - 1, from, aux, to);
			System.out.println("move disk " + N + " from rod " + from + " to rod " + to);

			// increment moves
			moves++;

			// recursive call to move top disk from aux to "to" in current call
			moves += toh(N - 1, aux, to, from);
		}
		return moves;
	}

	public int myjosephus(int n, int k) {//// Index starting with 0
		if (n == 1)
			return 0;
		return (myjosephus(n - 1, k) + k) % n;
	}

	public int josephus(int n, int k) {/////// Index starting with 1
		return myjosephus(n, k) + 1;
	}

	public static boolean isLucky(int n) {
		int position = n;
		if (counter > n)
			return true;
		if (n % counter == 0)
			return false;
		position = position - position / counter;
		counter++;
		return isLucky(position);
	}

	public long modfun(long n, long R) {
		// Base cases
		if (n == 0)
			return 0;
		if (R == 0) // power zero mean answer is 1
			return 1;

		// If R is even
		long y;
		if (R % 2 == 0) {
			y = modfun(n, R / 2); // finding r/2 power as power is even then storing answer in y and---
			y = (y * y) % 1000000007; // ---if power is even like 2^4 we can simply do (2^2)*(2^2)
		}

		// If R is odd
		else {
			y = n % 1000000007;
			y = (y * modfun(n, R - 1) % 1000000007) % 1000000007; // --- reduce the power by 1 to make it even. The
																	// reducing power
			// by one can be done if we take one n out. Like 2^3 can be written as 2*(2^2)
		}

		return ((y + 1000000007) % 1000000007); // finally return the answer (y+mod)%mod = (y%mod+mod%mod)%mod =
												// (y%mod)%mod
	}

	long power(int N, int R) {
		return modfun(N, R) % 1000000007;
	}

	static ArrayList<String> ans = new ArrayList<>();

	public static ArrayList<String> powerSet(String s) {
		ans = new ArrayList<String>();
		powerSetUtil(s, ans, 0, "");
		return ans;
	}

	// str : Stores input string
	// curr : Stores current subset
	// index : Index in current subset, curr
	static void powerSetUtil(String str, ArrayList<String> v, int index, String curr) {
		int n = str.length();
		if (index == n) {
			v.add(curr);
			return;
		}
		powerSetUtil(str, v, index + 1, curr);
		powerSetUtil(str, v, index + 1, curr + str.charAt(index));
	}

	// String array to store keypad characters
	static String hash[] = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	static ArrayList<String> possibleWords(int arr[], int N) {
		String str = "";
		for (int i = 0; i < N; i++)
			str += arr[i];
		ArrayList<String> res = possibleWordsUtil(str);
		Collections.sort(res); // arrange all possible strings lexicographically
		return res;

	}

	static ArrayList<String> possibleWordsUtil(String str) {
		// If str is empty
		if (str.length() == 0) {
			ArrayList<String> baseRes = new ArrayList<>();
			baseRes.add("");

			// Return an Arraylist containing
			// empty string
			return baseRes;
		}

		// First character of str
		char ch = str.charAt(0);

		// Rest of the characters of str
		String restStr = str.substring(1);

		// get all the combination
		ArrayList<String> prevRes = possibleWordsUtil(restStr);
		ArrayList<String> Res = new ArrayList<>();

		String code = hash[ch - '0'];

		for (String val : prevRes) {

			for (int i = 0; i < code.length(); i++) {
				Res.add(code.charAt(i) + val);
			}
		}
		return Res;
	}

}
