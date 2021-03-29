package gfg.week2.bitmagic;

public class videoSolutions {
	public String checkKthBitLeftShift(int number, int k) {
		// Time Complexity : O(log n)
		if ((number & (1 << (k - 1))) != 0) {
			return "SET";
		} else {
			return "NOTSET";
		}
	}

	public String checkKthBitRightShift(int number, int k) {
		// Time Complexity : O(log n)
		if (((number >> k - 1) & 1) == 1) {
			return "SET";
		} else {
			return "NOTSET";
		}
	}

	public int countSetBitsNaive(int number) {
		///////// Time Complexity : O(Total Bits in n)
		int count = 0;
		while (number > 0) {
			if (number % 2 != 0)////// ((number & 1)==1)
			{
				count++;
			}
			number /= 2;/// Removes the last bit
			///// Alternative number = number >> 1;
		}
		return count;
	}

	public int countSetBitsBrianKerningamAlgo(int number) {
///////// Time Complexity : O(Set Bits in n)
		////// Subtracting 1 from num converts 0 to 1 and 1 to 0 after last set bit
		////// which means 10000010000 minus 1 = 10000001111
		int res = 0;
		while (number > 0) {
			number = (number & (number - 1));
			res++;
		}
		return res;
	}

	public boolean powerOfTwo(int number) {
		return ((number != 0) && (number & (number - 1)) == 0);
	}

	public int findOddOccuringNumber(int number[]) {
		int result = 0;
		for (int i = 0; i < number.length; i++) {
			result = result ^ number[i];
		}
		return result;
	}

	public int findMissingNumber(int number[]) {
		int result = 0;
		for (int i = 0; i < number.length; i++) {
			result = result ^ number[i] ^ i;
		}
		return (result ^ number.length);

	}
}
