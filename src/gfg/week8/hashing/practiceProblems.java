package gfg.week8.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class practiceProblems {

	public static void main(String[] args) {
		// int[] arr = {-45 -30 -14 -6 -24 -28 15 -42 -34 32 8 -26 -13 12 -26 -50 -14 2
		// 49 29 0 18 21 23 -19 31 -20 -17 44}
		// int[] res = linearProbing(467, arr, 4);
		int A[] = { 1, 0, 0, 1, 0, 1, 1 };
		System.out.println(countSubarrWithEqualZeroAndOne(A, 7));
	}

	public static ArrayList<ArrayList<Integer>> separateChaining(int arr[], int n, int hashSize) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int idx;
		for (int i = 0; i < hashSize; i++) {
			res.add(new ArrayList<Integer>());

		}
		for (int i = 0; i < n; i++) {
			idx = arr[i] % hashSize;
			res.get(idx).add(arr[i]);
		}
		return res;
	}

	static int[] linearProbing(int hash_size, int arr[], int N) {
		int[] res = new int[hash_size];
		int size = 0;
		Arrays.fill(res, -1);
		int j;
		for (int i = 0; i < N; i++) {
			if (size == hash_size)
				return res;
			j = arr[i] % hash_size;
			while (res[j] != -1 && res[j] != arr[i]) {
				j = (j + 1) % hash_size;
			}
			if (res[j] != arr[i]) {
				res[j] = arr[i];
				size++;
			}
		}
		return res;
	}

	static int[] linearProbingh(int hash_size, int arr[], int N) {
		int[] res = new int[hash_size];
		int size = 0;
		Arrays.fill(res, -1);
		int j;
		for (int i = 0; i < N; i++) {
			if (size == hash_size)
				return res;
			j = arr[i] % hash_size;
			while (res[j] != -1) {
				j = (j + 1) % hash_size;
			}
			res[j] = arr[i];
			size++;
		}
		return res;
	}

	static <K> long countNonRepeated(int arr[], int n) {
		long count = 0;
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			if (hm.containsKey(arr[i])) {
				hm.put(arr[i], hm.get(arr[i]) + 1);
			} else {
				hm.put(arr[i], 1);
			}
		}
		for (Map.Entry<Integer, Integer> e : hm.entrySet()) {
			if (e.getValue() == 1)
				count++;
		}
		return count;
	}

	static ArrayList<Integer> printNonRepeated(int arr[], int n) {
		LinkedHashMap<Integer, Integer> hm = new LinkedHashMap<Integer, Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			if (hm.containsKey(arr[i])) {
				hm.put(arr[i], hm.get(arr[i]) + 1);
			} else {
				hm.put(arr[i], 1);
			}
		}
		for (Map.Entry<Integer, Integer> e : hm.entrySet()) {
			if (e.getValue() == 1)
				res.add(e.getKey());
		}
		return res;
	}

	public static int firstRepeated(int[] arr, int n) {
		LinkedHashMap<Integer, Integer> hm = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			if (hm.containsKey(arr[i])) {
				hm.put(arr[i], hm.get(arr[i]) + 1);
			} else {
				hm.put(arr[i], 1);
			}
		}
		for (Map.Entry<Integer, Integer> e : hm.entrySet()) {
			if (e.getValue() > 1) {
				for (int i = 0; i < n; i++) {
					if (arr[i] == e.getKey())
						return i + 1;
				}
			}
		}
		return -1;
	}

	public static int NumberofElementsInIntersection(int a[], int b[], int n, int m) {
		HashSet<Integer> hm = new HashSet<Integer>();
		int count = 0;
		for (int i = 0; i < n; i++) {
			hm.add(a[i]);
		}
		for (int i = 0; i < m; i++) {
			if (hm.contains(b[i])) {
				count++;
				hm.remove(b[i]);
			}
		}
		return count;
	}

	public static int doUnion(int a[], int n, int b[], int m) {
		HashSet<Integer> hm = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {
			hm.add(a[i]);
		}
		for (int i = 0; i < m; i++) {
			hm.add(b[i]);
		}
		return hm.size();
	}

	public static boolean check(long arr[], long brr[], int n) {
		HashMap<Long, Integer> ha = new HashMap<Long, Integer>();
		for (int i = 0; i < n; i++) {
			if (ha.containsKey(arr[i])) {
				ha.put(arr[i], ha.get(arr[i]) + 1);
			} else {
				ha.put(arr[i], 1);
			}
		}
		for (int i = 0; i < n; i++) {
			if (ha.containsKey(brr[i])) {
				ha.put(arr[i], ha.get(arr[i]) - 1);
			} else {
				return false;
			}
		}
		return true;
	}

	static boolean findsum(int arr[], int n) {
		int presum = 0;
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

	static int countSubarrWithEqualZeroAndOne(int arr[], int N) {
		for (int i = 0; i < N; i++) {
			if (arr[i] == 0)
				arr[i] = -1;
		}
		LinkedHashMap<Integer, Integer> h = new LinkedHashMap<Integer, Integer>();
		int presum = 0, res = 0;
		for (int i = 0; i < N; i++) {
			presum += arr[i];
			if (presum == 0) {
				res++;
			}
			if (h.containsKey(presum)) {
				res += h.get(presum);
			}
			Integer count = h.get(presum);
			if (count == null)
				h.put(presum, 1);
			else
				h.put(presum, count + 1);
		}
		return res;
	}

	public static int LongestSubArrayWithGivenSum(int[] arr, int sum) {
		int n = arr.length, presum = 0, res = 0;
		HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			presum += arr[i];
			if (presum == sum) {
				res++;
			}
			if (!h.containsKey(presum)) {
				h.put(presum, i);
			}
			if (h.containsKey(presum - sum)) {
				// res = Math.max(res, i - h.get(presum - sum));
				// res++;
			}
		}
		return res;
	}

}
