package gfg.week5.searching;

import java.util.HashSet;

public class videoSolutions {

	public static int bSearchIterative(int[] arr, int x) {
		int n = arr.length;
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = (int) Math.floor((low + high) / 2);
			if (arr[mid] == x)
				return x;
			else if (arr[mid] > x)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}

	public static int bSearchRecursive(int[] arr, int low, int high, int x) {
		if (low > high) {
			return -1;
		} else {
			int mid = (int) Math.floor((low + high) / 2);
			if (arr[mid] == x)
				return x;
			else if (arr[mid] > x)
				return bSearchRecursive(arr, low, mid - 1, x);
			else
				return bSearchRecursive(arr, mid + 1, high, x);
		}
	}

	public static int firstOccurence(int[] arr, int x) {
		int n = arr.length;
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = (int) Math.floor((low + high) / 2);
			if (arr[mid] > x)
				high = mid - 1;
			else if (arr[mid] < x)
				low = mid + 1;
			else {
				if (mid == 0 || arr[mid - 1] != arr[mid])
					return mid;
				else
					high = mid - 1;
			}
		}
		return -1;
	}

	public static int lastOccurence(int[] arr, int x) {
		int n = arr.length;
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = (int) Math.floor((low + high) / 2);
			if (arr[mid] > x)
				high = mid - 1;
			else if (arr[mid] < x)
				low = mid + 1;
			else {
				if (mid == n - 1 || arr[mid + 1] != arr[mid])
					return mid;
				else
					low = mid + 1;
			}
		}
		return -1;
	}

	public static int countOccurence(int[] arr, int x) {
		int firstOccurence = firstOccurence(arr, x);
		if (firstOccurence != -1) {
			return lastOccurence(arr, x) - firstOccurence + 1;
		}
		return 0;
	}

	public static int countOnesInBinary(int[] arr) {
		///////////// Applicable for Array containing zeros and ones
		int firstOccurence = firstOccurence(arr, 1);
		if (firstOccurence != -1)
			return arr.length - firstOccurence;
		else
			return 0;
	}

	public static int floorSquareRoot(int num) {
		int low = 1, high = num, ans = -1;
		while (low <= high) {
			int mid = (high + low) / 2;
			int mSquare = mid * mid;
			if (mSquare == num) {
				return mid;
			} else if (mSquare > num) {
				high = mid - 1;
			} else {
				low = mid + 1;
				ans = mid;
			}
		}
		return ans;
	}

	static int bSearch(int arr[], int low, int high, int x) {
		if (low > high)
			return -1;
		int mid = (low + high) / 2;
		if (arr[mid] == x)
			return mid;
		else if (arr[mid] > x)
			return bSearch(arr, low, mid - 1, x);
		else
			return bSearch(arr, mid + 1, high, x);
	}

	public static int searchInInfiniteArray(int[] arr, int num) {
		if (arr[0] == num)
			return 0;
		int i = 1;
		while (arr[i] < num) {
			i = i * 2;
		}
		if (arr[i] == num)
			return i;
		return bSearch(arr, i / 2 + 1, i - 1, num);
	}

	public static int searchInSortedRotatedArray(int[] arr, int x) {
		int n = arr.length;
		int low = 0, high = n - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == x) {
				return mid;
			}
			if (arr[low] < arr[mid]) {
				if (x >= arr[low] && x < arr[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				if (x > arr[mid] && x <= arr[high]) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}
		return -1;
	}

	public static int getPeak(int arr[]) {
		int n = arr.length;
		int low = 0, high = n - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if ((mid == 0 || arr[mid - 1] <= arr[mid]) && (mid == n - 1 || arr[mid + 1] <= arr[mid]))
				return mid;
			if (mid > 0 && arr[mid - 1] >= arr[mid])
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}

	public static boolean isPair(int[] arr, int sum) {
		//// Sorted Array
		int n = arr.length;
		int left = 0, right = n - 1;
		while (left < right) {
			if ((arr[left] + arr[right]) == sum)
				return true;
			else if ((arr[left] + arr[right]) > sum)
				right--;
			else
				left++;
		}
		return false;
	}

	public static boolean printpairs(int arr[], int sum) {
		//// Unsorted Array
		HashSet<Integer> s = new HashSet<Integer>();
		for (int i = 0; i < arr.length; ++i) {
			int temp = sum - arr[i];
			if (s.contains(temp)) {
				System.out.println("Pair with given sum " + sum + " is (" + arr[i] + ", " + temp + ")");
				return true;
			}
			s.add(arr[i]);
		}
		return false;
	}

	public static boolean find3Numbers(int A[], int arr_size, int sum) {
		int l, r;
		/* Sort the elements */
		quickSort(A, 0, arr_size - 1);
		/*
		 * Now fix the first element one by one and find the other two elements
		 */
		for (int i = 0; i < arr_size - 2; i++) {
			// To find the other two elements, start two index variables
			// from two corners of the array and move them toward each
			// other
			l = i + 1; // index of the first element in the remaining elements
			r = arr_size - 1; // index of the last element
			while (l < r) {
				if (A[i] + A[l] + A[r] == sum) {
					System.out.print("Triplet is " + A[i] + ", " + A[l] + ", " + A[r]);
					return true;
				} else if (A[i] + A[l] + A[r] < sum)
					l++;

				else // A[i] + A[l] + A[r] > sum
					r--;
			}
		}
		// If we reach here, then no triplet was found
		return false;
	}

	public static int partition(int A[], int si, int ei) {
		int x = A[ei];
		int i = (si - 1);
		int j;
		for (j = si; j <= ei - 1; j++) {
			if (A[j] <= x) {
				i++;
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		int temp = A[i + 1];
		A[i + 1] = A[ei];
		A[ei] = temp;
		return (i + 1);
	}

	public static void quickSort(int A[], int si, int ei) {
		int pi;
		/* Partitioning index */
		if (si < ei) {
			pi = partition(A, si, ei);
			quickSort(A, si, pi - 1);
			quickSort(A, pi + 1, ei);
		}
	}

	static int findMajority(int arr[], int n) {
		int res = 0, count = 1;
		for (int i = 1; i < n; i++) {
			if (arr[res] == arr[i])
				count++;
			else
				count--;
			if (count == 0) {
				res = i;
				count = 1;
			}
		}
		count = 0;
		for (int i = 0; i < n; i++)
			if (arr[res] == arr[i])
				count++;
		if (count <= n / 2)
			res = -1;
		return res;
	}

	public static double getMed(int a1[], int a2[], int n1, int n2) {
		int begin1 = 0, end1 = n1;
		while (begin1 < end1) {
			int i1 = (begin1 + end1) / 2;
			int i2 = ((n1 + n2 + 1) / 2) - i1;
			int min1 = (i1 == n1) ? Integer.MAX_VALUE : a1[i1];
			int max1 = (i1 == 0) ? Integer.MIN_VALUE : a1[i1 - 1];
			int min2 = (i2 == n2) ? Integer.MAX_VALUE : a2[i2];
			int max2 = (i1 == 0) ? Integer.MIN_VALUE : a1[i2 - 1];
			if (max1 <= min2 && max2 <= min1) {
				if ((n1 + n2) % 2 == 0)
					return ((double) Math.max(max1, max2) + Math.min(min1, min2)) / 2;
				else
					return (double) Math.max(max1, max2);
			} else if (max1 > min2)
				end1 = i1 - 1;
			else
				begin1 = i1 + 1;
		}
		return -1;
	}

	public static int repeat(int arr[], int n) {
		///////// Takes O(n) time and space
		boolean visit[] = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (visit[arr[i]])
				return arr[i];
			visit[arr[i]] = true;
		}
		return -1;
	}

	public static int repeatEfficient(int arr[], int n) {
		int slow = arr[0], fast = arr[0];
		do {
			slow = arr[slow];
			fast = arr[arr[fast]];
		} while (slow != fast);
		slow = arr[0];
		while (slow != fast) {
			slow = arr[slow];
			fast = arr[fast];
		}
		return slow;
	}

}