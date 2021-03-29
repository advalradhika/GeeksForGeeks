package gfg.week8.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class videoSolutions {

	public static void main(String[] args) {
	}

	public static int countDistinct(int[] arr) {
		HashSet<Integer> ele = new HashSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			ele.add(arr[i]);
		}
		return ele.size();
	}

	public static HashMap<Integer, Integer> countFrequency(int[] arr) {
		HashMap<Integer, Integer> ele = new HashMap<Integer, Integer>();
		// LinkedHashMap<Integer, Integer> hm = new LinkedHashMap<Integer,
		// Integer>();///////// For maintaining the order
		for (int i = 0; i < arr.length; i++) {
			if (ele.containsKey(arr[i])) {
				ele.put(arr[i], ele.get(arr[i]) + 1);
			} else {
				ele.put(arr[i], 1);
			}
		}
		return ele;
	}

	public static int[] findIntersection(int[] arr, int[] brr) {
		int a = arr.length;
		int b = brr.length;
		int n = Math.max(a, b);
		int[] res = new int[n];
		int j = 0;
		HashSet<Integer> ele = new HashSet<Integer>();
		for (int i = 0; i < a; i++) {
			ele.add(arr[i]);
		}
		for (int i = 0; i < b; i++) {
			if (ele.contains(brr[i])) {
				res[j] = brr[i];
				j++;
			}
		}
		return res;
	}

	public static HashSet<Integer> findUnion(int[] arr, int[] brr) {
		int a = arr.length;
		int b = brr.length;
		// int n = Math.max(a, b);
		// int[] res = new int[n];
		// int j = 0;
		HashSet<Integer> ele = new HashSet<Integer>();
		for (int i = 0; i < a; i++) {
			ele.add(arr[i]);
		}
		for (int i = 0; i < b; i++) {
			ele.add(brr[i]);
		}
		return ele;
	}

	public static boolean checkSumPair(int[] arr, int n, int sum) {
		HashSet<Integer> ele = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {
			if (ele.contains(sum - arr[i]))
				return true;
			ele.add(arr[i]);
		}
		return false;
	}

	public static boolean subArrayWithZeroSum(int[] arr) {
		int n = arr.length, presum = 0;
		HashSet<Integer> h = new HashSet<Integer>();
		h.add(0);
		for (int i = 0; i < n; i++) {
			presum += arr[i];
			if (h.contains(presum) == true)
				return true;
			h.add(presum);
		}
		return false;
	}

	public static boolean subArrayWithGivenSum(int[] arr, int sum) {
		int n = arr.length, presum = 0;
		HashSet<Integer> h = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {
			if (presum == sum)
				return true;
			presum += arr[i];
			if (h.contains(presum - sum) == true)
				return true;

			h.add(presum);
		}
		return false;
	}

	public static int LongestSubArrayWithGivenSum(int[] arr, int sum) {
		int n = arr.length, presum = 0, res = 0;
		////// Store sum as key and first index as value
		HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			presum += arr[i];
			if (presum == sum) {
				res = i + 1;
			}
			if (!h.containsKey(presum)) {
				h.put(presum, i);
			}
			if (h.containsKey(presum - sum)) {
				res = Math.max(res, i - h.get(presum - sum));
			}
		}
		return res;
	}

	public static int LongestSubArrayWithSameZeroOne(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0)
				arr[i] = -1;
		}
		return LongestSubArrayWithGivenSum(arr, 0);
	}
}
