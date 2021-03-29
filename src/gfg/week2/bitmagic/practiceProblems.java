package gfg.week2.bitmagic;

public class practiceProblems {

	public static int getFirstSetBitPos(int n) {
		if (n == 0)
			return 0;
		return (int) (Math.log(n & -n) / Math.log(2.0)) + 1;

		// doing AND of n and -n as n and -n will have only one bit similar so and will
		// turn all other
		// bits into zero. The result has its log2 taken to find the position and we add
		// 1 to get
		// count from 1 instead of zero
	}

	public static int posOfRightMostDiffBit(int m, int n) {
		if (m == n)
			return -1;
		int XOR = m ^ n;
		return (int) (Math.log(XOR & -XOR) / Math.log(2.0)) + 1;
	}

	public static int countSetBits(int n) {

		n++;
		int powerOf2 = 2;
		int cnt = n / 2;
		while (powerOf2 <= n) {
			int totalPairs = n / powerOf2;
			cnt += (totalPairs / 2) * powerOf2;
			cnt += (totalPairs % 2 == 1) ? (n % powerOf2) : 0;
			powerOf2 <<= 1;
		}
		return cnt;
	}

	public static int countBitsFlip(int a, int b) {
		////// Count set bits in XOR
		int number = a ^ b;
		int res = 0;
		while (number > 0) {
			number = (number & (number - 1));
			res++;
		}
		return res;
	}

	public static boolean isSparse(int n) {
		if ((n & (n >> 1)) >= 1)
			return false;
		return true;
	}

	public static char xor(char a, char b) {
		return a == b ? '0' : '1';
	}

	public static char flipbit(char c) {
		return c == '0' ? '1' : '0';
	}

	public static int maxConsecutiveOnes(int x) {
		int c = 0, max = 0;
		while (x > 0) {
			if ((x & 1) == 1) {
				c++;
				max = Math.max(c, max);
			} else {
				c = 0;
			}
			x = x >> 1;
		}
		return max;
	}

	int greyConverter(int n) {
		return n ^ (n >> 1);
	}

	public static int grayConverter(int n) {
		String binary = Integer.toBinaryString(n);
		String gray = "";
		gray += binary.charAt(0);
		for (int i = 1; i < binary.length(); i++) {
			gray += xor(binary.charAt(i), binary.charAt(i - 1));
		}
		return Integer.parseInt(gray, 2);
		/// Converts Binary to decimal
	}
	/*
	 * String binary = "";
	 * 
	 * // MSB of binary code is same // as gray code binary += gray.charAt(0);
	 * 
	 * // Compute remaining bits for (int i = 1; i < gray.length(); i++) { // If
	 * current bit is 0, // concatenate previous bit if (gray.charAt(i) == '0')
	 * binary += binary.charAt(i - 1);
	 * 
	 * // Else, concatenate invert of // previous bit else binary +=
	 * flip(binary.charAt(i - 1)); }
	 * 
	 */

	public static int grayToBinary(int n) {
		int b = 0;
		for (n = n; n > 0; n = n >> 1)
			b ^= n;

		return b;
		/*
		 * String gray = Integer.toBinaryString(n); String binary = ""; binary +=
		 * gray.charAt(0); for (int i = 1; i < gray.length(); i++) { if (gray.charAt(i)
		 * == '0') { binary += binary.charAt(i - 1); } else { binary +=
		 * flipbit(binary.charAt(i - 1)); } } return Integer.parseInt(binary);
		 */
	}

	public static boolean isPowerofTwo(long n) {
		return ((n != 0) && (n & (n - 1)) == 0);

	}

	public static int swapBits(int n) {
		int res = 0;
		int a, b, i = -1;
		while (n > 0) {
			a = n % 2;
			n /= 2;
			b = n % 2;
			n /= 2;
			res += (b * Math.pow(2, ++i) + a * Math.pow(2, ++i));
		}
		return res;
	}
}
