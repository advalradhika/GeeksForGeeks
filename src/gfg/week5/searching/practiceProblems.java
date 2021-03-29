package gfg.week5.searching;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class practiceProblems {

	static int search(int arr[], int N, int X) {
		for (int i = 0; i < N; i++) {
			if (arr[i] == X)
				return i;
		}
		return -1;
	}

	static int searchInSorted(int arr[], int N, int K) {
		int low = 0, high = N - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == K)
				return mid;
			else if (arr[mid] > K)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}

	public static int countOnes(int arr[], int N) {
		int low = 0, high = N - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == 0) {
				high = mid - 1;
			} else if (arr[mid] == 1) {
				if (mid == N - 1 || arr[mid + 1] == 0) {
					return mid + 1;
				} else {
					low = mid + 1;
				}
			}
		}
		return 0;
	}

	public static long floorSqrt(long x) {
		long ans = -1;
		long low = 1, high = x / 2;
		while (low <= high) {
			long mid = (low + high) / 2;
			long sqrt = mid * mid;
			if (sqrt == x) {
				return mid;
			} else if (sqrt > x) {
				high = mid - 1;
			} else {
				ans = mid;
				low = mid + 1;
			}
		}
		return ans;
	}

	public static int majorityElement(int arr[], int n) {
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
		return arr[res];
	}

	public static int leftIndex(int N, int arr[], int X) {
		int low = 0, high = N - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] > X) {
				high = mid - 1;
			} else if (arr[mid] < X) {
				low = mid + 1;
			} else {
				if (mid == 0 || arr[mid - 1] != arr[mid]) {
					return mid;
				} else {
					high = mid - 1;
				}
			}
		}
		return -1;
	}

	public int peakElement(int[] a, int n) {
		int low = 0, high = n - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if ((mid == n - 1 || a[mid] >= a[mid + 1]) && (mid == 0 || a[mid] >= a[mid - 1])) {
				return mid;
			} else if (mid > 0 && a[mid - 1] >= a[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	public static int findFloor(long arr[], int left, int right, long x) {
		int curridx = -1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (arr[mid] > x) {
				right = mid - 1;
			} else if (arr[mid] == x) {
				return mid;
			} else {
				curridx = mid;
				left = mid + 1;
			}
		}
		return curridx;
	}

	public static long minNumber(int arr[], long low, long high) {
		if (high < low)
			return arr[0];
		if (high == low)
			return arr[(int) low];
		long mid = low + (high - low) / 2;
		// check if mid+1 element is the minimum number
		if (mid < high && arr[(int) mid + 1] < arr[(int) mid])
			return arr[(int) mid + 1];
		// check if mid element is the minimum element
		if (mid > low && arr[(int) mid] < arr[(int) mid - 1])
			return arr[(int) mid];
		// recurse for left half
		if (arr[(int) high] > arr[(int) mid])
			return minNumber(arr, low, mid - 1);
		// recurse for right half
		return minNumber(arr, mid + 1, high);
	}

	public Pair twoRepeated(int arr[], int N) {
		Pair res = new Pair();
		res.first = 0;
		res.second = 0;
		boolean[] isVisited = new boolean[N + 1];
		Arrays.fill(isVisited, false);
		for (int i = 0; i < arr.length; i++) {
			if (isVisited[arr[i]] == true) {
				if (res.first == 0)
					res.first = arr[i];
				else
					res.second = arr[i];
			}
			isVisited[arr[i]] = true;
		}
		return res;
	}

	public static int maxStep(int A[], int N) {
		int max = 0;
		int curr = 0;
		for (int i = 0; i < N - 1; i++) {
			if (A[i + 1] > A[i]) {
				curr++;
			} else {
				curr = 0;
			}
			max = Math.max(curr, max);
		}
		return max;
	}

	public static int maxWater(int height[], int n) {
		if (n == 2)
			return 0;
		int left = 0, right = n - 1;
		int max = Math.min(height[left], height[right]) * (n - 2);
		while (left < right) {
			if (height[left] < height[right])
				left++;
			else
				right--;
			max = Math.max(Math.min(height[left], height[right]) * (right - left - 1), max);
		}
		return max;
	}

	public static Point findRepeating(Integer arr[], int n) {
		if (n == 0)
			return new Point(0, 0);
		Point p = new Point();
		int low = 0, high = n - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] >= mid + arr[0])
				low = mid + 1;
			else
				high = mid;
		}
		p.x = arr[low];
		p.y = arr.length - (arr[arr.length - 1] - arr[0]);
		return p;
	}

	public static ArrayList<Integer> subarraySum(int n, int sum, int[] arr) {
		ArrayList<Integer> res = new ArrayList<>();
		int curr_sum = arr[0], start = 0, i;
		for (i = 1; i <= n; i++) {
			while (curr_sum > sum && start < i - 1) {
				curr_sum = curr_sum - arr[start];
				start++;
			}
			if (curr_sum == sum) {
				int p = i - 1;
				res.add(start + 1);
				res.add(p + 1);
				return res;
			}
			if (i < n)
				curr_sum = curr_sum + arr[i];
		}
		res.add(-1);
		return res;
	}

	public static int countOccurence(int[] arr, int n, int k) {
		int count = 0;
		int x = n / k;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		for (Integer key : map.keySet()) {
			if (map.get(key) > x) {
				count++;
			}
		}
		return count;
	}

	/*
	 * public static int findPages(int[] a, int n, int m) { // Your code here }
	 */

	public static int findMedian(int a[], int n, int b[], int m) {
		int minidx = 0, maxidx = n;
		int i = 0, j = 0, median = 0;
		while (minidx <= maxidx) {
			i = (minidx + maxidx) / 2;
			j = ((m + n + 1) / 2) - i;
			if (i < n && j > 0 && b[j - 1] > a[i]) {
				minidx = i + 1;
			} else if (i > 0 && j < m && b[j] < a[i - 1]) {
				maxidx = i - 1;
			} else {
				if (i == 0)
					median = b[j - 1];
				else if (j == 0)
					median = a[i - 1];
				else
					median = Math.max(a[i - 1], b[j - 1]);
				break;
			}
		}
		if ((n + m) % 2 == 1)
			return median;
		if (i == n)
			return (median + b[j]) / 2;
		if (j == m)
			return (median + a[i]) / 2;

		return (median + Math.min(a[i], b[j])) / 2;
	}
}

class Pair {
	public int first;
	public int second;
}