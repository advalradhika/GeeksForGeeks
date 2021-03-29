package gfg.week6.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class practiceProblems {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 3, 4 };
		ArrayList<Integer> r = new ArrayList<Integer>();
		r.add(1);
		r.add(2);
		r.add(3);
		r.add(3);
		r.add(4);
		int[] brr = { 2, 4, 6, 7, 8 };
		int[] x = { 10, 5, 3, 9, 2 };
		char[] y = { 'g', 'c', 'a', 'b', 'a', 'e', 'c' };
		//sortABS(x, 5, 7);
		// for (int i = 0; i < arr.length; i++) {
		// System.out.println(arr[i]);
		// }
	}

	public static void bubble(int arr[], int i, int n) {
		int temp;
		for (int j = 0; j < n - i - 1; j++) {
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
	}

	public static void insert(int arr[], int i) {
		for (int j = 1; j < i; j++) {
			int key = arr[j];
			int k = j - 1;
			while (k >= 0 && arr[k] > key) {
				arr[k + 1] = arr[k];
				k--;
			}
			arr[k + 1] = key;
		}
	}

	static int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = low - 1, temp;
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				i++;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return (i + 1);
	}

	public static void binSort(int arr[], int n) {
		if (n == 1)
			return;
		int i = -1, j = n, temp;
		while (true) {
			do {
				i++;
			} while (arr[i] == 0 && i < n - 1);
			do {
				j--;
			} while (arr[j] == 1 && j > 0);
			if (i >= j)
				return;
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	static long inversionCount(long arr[], long N) {
		return countInversion(arr, 0, arr.length - 1);
	}

	public static long countInversion(long[] arr, long l, long r) {
		long res = 0;
		if (l < r) {
			long m = (l + r) / 2;
			res += countInversion(arr, l, m);
			res += countInversion(arr, m + 1, r);
			res += countAndMerge(arr, l, m, r);
		}
		return res;
	}

	public static long countAndMerge(long[] arr, long l, long m, long r) {
		long count = 0;
		long n1 = m - l + 1;
		long n2 = r - m;
		long L[] = new long[(int) n1];
		long R[] = new long[(int) n2];
		for (long i = 0; i < n1; i++)
			L[(int) i] = arr[(int) (l + i)];
		for (long j = 0; j < n2; j++)
			R[(int) j] = arr[(int) (m + 1 + j)];
		long i = 0, j = 0;
		long k = l;
		while (i < n1 && j < n2) {
			if (L[(int) i] <= R[(int) j]) {
				arr[(int) k++] = L[(int) i++];
			} else {
				arr[(int) k++] = R[(int) j++];
				count += n1 - i;
			}
		}
		while (i < n1) {
			arr[(int) k++] = L[(int) i++];
		}
		while (j < n2) {
			arr[(int) k++] = R[(int) j++];
		}
		return count;
	}

	public static ArrayList<Integer> findUnion(int arr[], int brr[], int m, int n) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int i = 0, j = 0;
		while (i < m && j < n) {
			if (i > 0 && arr[i - 1] == arr[i]) {
				i++;
				continue;
			}
			if (j > 0 && brr[j - 1] == brr[j]) {
				j++;
				continue;
			}
			if (arr[i] < brr[j]) {
				res.add(arr[i]);
				i++;
			} else if (arr[i] > brr[j]) {
				res.add(brr[j]);
				j++;
			} else {
				res.add(arr[i]);
				i++;
				j++;
			}
		}
		while (i < m) {
			if (i == 0 || arr[i - 1] != arr[i]) {
				res.add(arr[i]);
			}
			i++;
		}
		while (j < n) {
			if (j == 0 || brr[j - 1] != brr[j]) {
				res.add(brr[j]);
			}
			j++;
		}
		return res;
	}

	public static ArrayList<Integer> printIntersection(int arr1[], int arr2[], int n, int m) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int i = 0, j = 0;
		while (i < n && j < m) {
			if (i > 0 && arr1[i - 1] == arr1[i]) {
				i++;
				continue;
			}
			if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr1[i] > arr2[j]) {
				j++;
			} else {
				res.add(arr1[i]);
				i++;
				j++;
			}
		}
		if (res.size() == 0)
			res.add(-1);
		return res;
	}

	public static int findNumberOfTriangles(int arr[], int n) {
		int count = 0;
		Arrays.sort(arr);
		for (int i = 0; i < n - 2; ++i) {
			int k = i + 2;
			for (int j = i + 1; j < n; ++j) {
				while (k < n && arr[k] < arr[i] + arr[j]) {
					++k;
				}
				if (k > j)
					count += k - j - 1;
			}
		}
		return count;
	}

	static void CountTriangles(int[] A) {
		int n = A.length;

		Arrays.sort(A);

		int count = 0;

		for (int i = n - 1; i >= 1; i--) {
			int l = 0, r = i - 1;
			while (l < r) {
				if (A[l] + A[r] > A[i]) {

					// If it is possible with a[l], a[r]
					// and a[i] then it is also possible
					// with a[l+1]..a[r-1], a[r] and a[i]
					count += r - l;

					// checking for more possible solutions
					r--;
				} else // if not possible check for
				// higher values of arr[l]
				{
					l++;
				}
			}
		}
		System.out.print("No of possible solutions: " + count);
	}

	public static boolean findTriplets(int arr[], int n) {
		Arrays.sort(arr);
		if (arr[0] >= 0)
			return false;
		for (int i = 0; i < n; i++) {
			int j = i + 1, k = n - 1;
			while (j < k) {
				if (arr[j] + arr[k] == -arr[i]) {
					return true;
				}
				if (arr[j] + arr[k] > -arr[i]) {
					k--;
				} else {
					j++;
				}
			}
		}
		return false;
	}

	public static ArrayList<Integer> threeWayPartition(ArrayList<Integer> A, int a, int b) {
		int n = A.size();
		int i = 0, j = 0, k = n - 1, temp, curr;
		while (j <= k) {
			curr = A.get(j);
			if (curr < a) {
				temp = A.get(i);
				A.set(i, curr);
				A.set(j, temp);
				i++;
				j++;
			} else if (curr >= a && curr <= b) {
				j++;
			} else {
				temp = A.get(k);
				A.set(k, curr);
				A.set(j, temp);
				k--;
			}
		}
		return A;
	}

	public static char[] countSort(char seq[]) {
		int[] alph = new int[26];
		int n = seq.length;
		for (int i = 0; i < n; i++) {
			alph[(int) (seq[i]) - 97]++;
		}
		for (int i = 1; i < alph.length; i++) {
			alph[i] += alph[i - 1];
		}
		char[] output = new char[n];
		for (int i = n - 1; i >= 0; i--) {
			int idx = alph[((int) (seq[i])) - 97];
			output[idx - 1] = seq[i];
			alph[((int) (seq[i])) - 97]--;
		}
		return output;
	}

	public static void segragate012(int arr[], int N) {
		int i = 0, j = 0, k = N - 1, temp, curr;
		while (j <= k) {
			curr = arr[j];
			if (curr == 0) {
				temp = arr[i];
				arr[i] = curr;
				arr[j] = temp;
				i++;
				j++;
			} else if (curr == 1) {
				j++;
			} else {
				temp = arr[k];
				arr[k] = curr;
				arr[j] = temp;
				k--;
			}
		}
	}

	/*
	 * static void sortABS(int arr[], int n, int k) { // Pair[] res = new Pair[n];
	 * /* for (int i = 0; i < n; i++) { System.out.println(" i : " + i + " value : "
	 * + arr[i]); res[i].key = arr[i]; res[i].diff = Math.abs(arr[i] - k); }
	 */
	/*
	 * Pair[] res = new Pair[2]; res[0] = new Pair(6, 1); res[1] = new Pair(5, 2);
	 * Arrays.sort(res); for (int i = 0; i < res.length; i++) {
	 * System.out.println("key : " + res[i].key + " Diff : " + res[i].diff); } }
	 */

	public static int find3Numbers(int arr[], int N, int X) {
		Arrays.sort(arr);
		if (arr[0] > X - 2)
			return 1;
		for (int i = 0; i < N; i++) {
			int j = i + 1, k = N - 1;
			while (j < k) {
				if (arr[j] + arr[k] == -arr[i] + X) {
					return 0;
				}
				if (arr[j] + arr[k] > -arr[i] + X) {
					k--;
				} else {
					j++;
				}
			}
		}
		return 1;
	}

	public static int kthSmallest(int arr[], int n, int k) {
		int l = 0, r = n - 1;
		while (l <= r) {
			int p = LomutoPartition(arr, l, r);
			if (p == k - 1)
				return p;
			else if (p > k - 1) {
				r = p - 1;
			} else {
				l = p + 1;
			}
		}
		return -1;
	}

	public static int LomutoPartition(int[] arr, int l, int h) {
		int pivot = arr[h];
		int i = l - 1;
		int temp;
		for (int j = l; j < h; j++) {
			if (arr[j] < pivot) {
				i++;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[i + 1];
		arr[i + 1] = arr[h];
		arr[h] = temp;
		return (i + 1);
	}

	public static long countPairs(int x[], int y[], int n, int m) {
		return 0;
	}

	/*
	 * public static class Pair implements Comparator<Pair> { int diff; int key;
	 * 
	 * public Pair(int key, int diff) { this.key = key; this.diff = diff; }
	 * 
	 * @Override public int compare(Pair a, Pair b) { return a.diff - b.diff; } }
	 */

	/*
	 * public static ArrayList<Integer> merge3sorted(int A[], int B[], int C[]) {
	 * int l = A.length; int m = B.length; int n = C.length; int i = 0, j = 0; int k
	 * = 0; ArrayList<Integer> res = new ArrayList<Integer>(); while (i < l && j < m
	 * && k < n) { if (A[i] < B[j] && A[i] < C[k]) { res.add(A[i]); i++; } else if
	 * (B[j] < A[i] && B[j] < C[k]) { res.add(B[j]); j++;
	 * 
	 * } else if (C[k] < A[i] && C[k] < B[j]) { res.add(C[k]); k++;
	 * 
	 * } } while (i < n1)
	 * 
	 * { arr[k++] = L[i++]; } while (j < n2) { arr[k++] = R[j++]; }
	 * 
	 * }
	 */

}
