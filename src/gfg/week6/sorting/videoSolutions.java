package gfg.week6.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
//import javafx.util.Pair;
import java.util.Vector;

public class videoSolutions {
	public static void main(String[] args) {
		// Integer array
		// Integer[] arr = { 5, 20, 10, 3, 12 };

		// Sorting arrays by passing
		// arr and MyCmp object
		// Arrays.sort(arr, new MyCmp());MyCmp
		int[] A = { 1, 1, 1, 1, 1 };
		// Displaying the sorted array
		// int[] res = threeTypeElements(A);
		// segregateNumbers(A);
		int[] arr = { 1, 2, 3, 4, 5 };
		int[] brr = { 1, 2, 3 };
		union(arr, brr, 5, 3);
		// System.out.println(countInversion(arr, 0, 4));
		// System.out.println(Arrays.toString(arr));
	}

	public static void insertionSort(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;

		}
	}

	public static void mergeArray(int[] arr, int[] brr, int m, int n) {
		int i = 0, j = 0;
		while (i < m && j < n) {
			if (arr[i] < brr[j]) {
				System.out.print(arr[i++] + " ");
			} else {
				System.out.print(brr[j++] + " ");
			}
		}
		while (i < m) {
			System.out.print(arr[i++] + " ");
		}
		while (j < n) {
			System.out.print(brr[j++] + " ");
		}
	}

	public static void merge(int arr[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		int L[] = new int[n1];
		int R[] = new int[n2];
		for (int i = 0; i < n1; i++)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; j++)
			R[j] = arr[m + 1 + j];
		int i = 0, j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k++] = L[i++];
			} else {
				arr[k++] = R[j++];
			}
		}
		while (i < n1) {
			arr[k++] = L[i++];
		}
		while (j < n2) {
			arr[k++] = R[j++];
		}
	}

	public static void mergeSort(int arr[], int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);
			merge(arr, l, m, r);
		}
	}

	public static void intersectionOfArrays(int[] arr, int[] brr, int m, int n) {
		int i = 0, j = 0;
		while (i < m && j < n) {
			if (i > 0 && arr[i - 1] == arr[i]) {
				i++;
				continue;
			}
			if (arr[i] < brr[j]) {
				i++;
			} else if (arr[i] > brr[j]) {
				j++;
			} else {
				System.out.println(arr[i] + " ");
				i++;
				j++;
			}
		}
	}

	public static void union(int[] arr, int[] brr, int m, int n) {
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
				System.out.println(arr[i]);
				i++;
			} else if (arr[i] > brr[j]) {
				System.out.println(brr[j]);
				j++;
			} else {
				System.out.println(arr[i]);
				i++;
				j++;
			}
		}
		while (i < m) {
			if (i == 0 || arr[i - 1] != arr[i]) {
				System.out.println(arr[i]);
			}
			i++;
		}
		while (j < n) {
			if (j == 0 || brr[j - 1] != brr[j]) {
				System.out.println(brr[j]);
			}
			j++;
		}
	}

	public static int countInversion(int[] arr, int l, int r) {
		int res = 0;
		System.out.println("In");
		while (l < r) {
			int m = (l + r) / 2;
			res += countInversion(arr, l, m);
			res += countInversion(arr, m + 1, r);
			res += countAndMerge(arr, l, m, r);
		}
		System.out.println("Out");
		return res;
	}

	public static int countAndMerge(int[] arr, int l, int m, int r) {
		int count = 0;
		int n1 = m - l + 1;
		int n2 = r - m;
		int L[] = new int[n1];
		int R[] = new int[n2];
		for (int i = 0; i < n1; i++)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; j++)
			R[j] = arr[m + 1 + j];
		int i = 0, j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k++] = L[i++];
			} else {
				arr[k++] = R[j++];
				count += n1 - i;
			}
		}
		while (i < n1) {
			arr[k++] = L[i++];
		}
		while (j < n2) {
			arr[k++] = R[j++];
		}
		return count;
	}

	public static void NaivePartition(int[] arr, int l, int h, int p) {
		int[] temp = new int[h - l + 1];
		int index = 0;
		for (int i = l; i <= h; i++) {
			if (arr[i] <= arr[p]) {
				temp[index++] = arr[i];
			}
		}
		for (int i = l; i <= h; i++) {
			if (arr[i] > arr[p]) {
				temp[index++] = arr[i];
			}
		}
		for (int i = l; i <= h; i++) {
			if (arr[i] <= arr[p]) {
				arr[i] = temp[i];
			}
		}
	}

	////////////// Lomuto Partition considers last element as pivot;
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

//////////////Lomuto Partition considers first element as pivot;
	public static int HoarePartition(int[] arr, int l, int h) {
		int pivot = arr[l];
		int temp;
		int i = l - 1, j = h + 1;
		while (true) {
			do {
				i++;
			} while (arr[i] < pivot);
			do {
				j--;
			} while (arr[j] > pivot);
			if (i >= j)
				return j;
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	public static void quickSortLomuto(int[] arr, int l, int h) {
		if (l < h) {
			int p = LomutoPartition(arr, l, h);
			quickSortLomuto(arr, l, p - 1);
			quickSortLomuto(arr, p + 1, h);
		}
	}

	public static void quickSortHoare(int[] arr, int l, int h) {
		if (l < h) {
			int p = HoarePartition(arr, l, h);
			quickSortLomuto(arr, l, p);
			quickSortLomuto(arr, p + 1, h);
		}
	}

	////////// Returns index
	public static int KthSmallest(int[] arr, int k) {
		int n = arr.length;
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

	public static int minDiff(int[] arr, int m) {
		int n = arr.length;
		if (m > n)
			return -1;
		Arrays.sort(arr);
		int diff = arr[m - 1] - arr[0];
		for (int i = 1; i + m - 1 < n; i++) {
			diff = Math.min(diff, arr[i + m - 1] - arr[i]);
		}
		return diff;
	}

	public static void segregateNumbers(int[] arr) {
		int n = arr.length;
		int i = -1, j = n;
		while (true) {
			do {
				i++;
			} while (arr[i] < 0);
			do {
				j--;
			} while (arr[i] > 0);
			if (i >= j)
				return;
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	/////////// Given array contains 0,1,2 as elements
	public static int[] threeTypeElements(int[] arr) {
		int n = arr.length;
		int temp;
		int low = 0, mid = 0, high = n - 1;
		while (mid <= high) {
			switch (arr[mid]) {
			case 0:

				temp = arr[low];
				arr[low] = arr[mid];
				arr[mid] = temp;
				low++;
				mid++;
				break;

			case 1:

				mid++;
				break;

			case 2:

				temp = arr[high];
				arr[high] = arr[mid];
				arr[mid] = temp;
				high--;
				break;

			}
		}
		return arr;
	}

	public static void mergeOverlappingIntervals(Interval[] arr) {
		int n = arr.length;
		// Interval[] pair = new Interval[n];
		// Arrays.sort(arr, new MyCmp());
		int res = 0;
		for (int i = 1; i < n; i++) {
			if (arr[res].end >= arr[i].start) {
				arr[res].end = Math.min(arr[res].end, arr[i].end);
				arr[res].start = Math.max(arr[res].start, arr[i].start);
			} else {
				res++;
				arr[res] = arr[i];
			}
		}
		for (int i = 0; i <= res; i++) {
			//// Print intervals
		}
	}

	public static int maxGuests(int[] arr, int[] dep) {
		int n = arr.length;
		int i = 1, j = 0, curr = 1, res = 1;
		while (i < n && j < n) {
			if (arr[i] <= dep[j]) {
				curr++;
				i++;
			} else {
				curr--;
				j++;
			}
			res = Math.max(res, curr);
		}
		return res;
	}

	public static void cycleSortDistinctElements(int[] arr) {
		int n = arr.length;
		int temp;
		for (int cs = 0; cs < n - 1; cs++) {
			int item = arr[cs];
			int pos = cs;
			for (int i = cs + 1; i < n; i++) {
				if (arr[i] < item) {
					pos++;
				}
			}
			temp = arr[pos];
			arr[pos] = item;
			item = temp;
			while (pos != cs) {
				pos = cs;
				for (int i = cs + 1; i < n; i++) {
					if (arr[i] < item) {
						pos++;
					}
				}
				temp = arr[pos];
				arr[pos] = item;
				item = temp;
			}

		}

	}

	public static void countingSort(int[] arr, int k) {
		int n = arr.length;
		int[] count = new int[k];
		for (int i = 0; i < n; i++) {
			count[arr[i]]++;
		}
		for (int i = 1; i < k; i++) {
			count[i] += count[i - 1];
		}
		int[] output = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			output[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}
		for (int i = 0; i < n; i++) {
			arr[i] = output[i];
		}
	}

	public static void radixsort(int arr[], int n) {
		int m = getMax(arr, n);
		for (int exp = 1; m / exp > 0; exp *= 10)
			countSort(arr, n, exp);
	}

	static int getMax(int arr[], int n) {
		int mx = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > mx)
				mx = arr[i];
		return mx;
	}

	static void countSort(int arr[], int n, int exp) {
		int output[] = new int[n]; // output array
		int i;
		int count[] = new int[10];
		Arrays.fill(count, 0);
		for (i = 0; i < n; i++)
			count[(arr[i] / exp) % 10]++;
		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];
		for (i = n - 1; i >= 0; i--) {
			output[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}
		for (i = 0; i < n; i++)
			arr[i] = output[i];
	}

	static void bucketSort(float arr[], int n) {
		if (n <= 0)
			return;

		// 1) Create n empty buckets
		@SuppressWarnings("unchecked")
		Vector<Float>[] buckets = new Vector[n];

		for (int i = 0; i < n; i++) {
			buckets[i] = new Vector<Float>();
		}

		// 2) Put array elements in different buckets
		for (int i = 0; i < n; i++) {
			float idx = arr[i] * n;
			buckets[(int) idx].add(arr[i]);
		}

		// 3) Sort individual buckets
		for (int i = 0; i < n; i++) {
			Collections.sort(buckets[i]);
		}

		// 4) Concatenate all buckets into arr[]
		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < buckets[i].size(); j++) {
				arr[index++] = buckets[i].get(j);
			}
		}
	}

	class MyCmp implements Comparator<Integer> {
		// Sorts the Integers
		public int compare(Integer a, Integer b) {
			return a % 2 - b % 2;
		}

	}

	class Interval {

		int start;
		int end;

	}
}