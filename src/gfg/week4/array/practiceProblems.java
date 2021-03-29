package gfg.week4.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class practiceProblems {

	public void insertAtEnd(int arr[], int sizeOfArray, int element) {
		arr[sizeOfArray - 1] = element;
	}

	public void insertAtIndex(int arr[], int sizeOfArray, int index, int element) {
		for (int i = sizeOfArray - 2; i >= index; i--) {
			arr[i + 1] = arr[i];
		}
		arr[index] = element;
	}

	public int median(int A[], int N) {
		Arrays.sort(A);
		if (A.length % 2 != 0) {
			return A[N / 2];
		} else {
			return (int) ((A[N / 2] + A[N / 2 - 1]) / 2);
		}
	}

	public int mean(int A[], int N) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += A[i];
		}
		return (int) (sum / N);
	}

	public int majorityWins(int arr[], int n, int x, int y) {
		int count_x = 0;// counter to count frequency of x
		int count_y = 0;// counter to count frequency of y
		for (int i = 0; i < n; i++) {
			if (arr[i] == x)
				count_x++;
			else if (arr[i] == y)
				count_y++;
		}
		return count_x == count_y ? Math.min(x, y) : (count_x > count_y ? x : y);
	}

	public static ArrayList<Integer> largestAndSecondLargest(int sizeOfArray, int arr[]) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int maxindex = 0, secondmaxindex = -1;
		for (int i = 1; i < sizeOfArray; i++) {
			if (arr[maxindex] < arr[i]) {
				secondmaxindex = maxindex;
				maxindex = i;
			} else if ((arr[i] != arr[maxindex]) && (secondmaxindex == -1 || arr[secondmaxindex] < arr[i])) {
				secondmaxindex = i;
			}
		}
		res.add(arr[maxindex]);
		if (secondmaxindex == -1)
			res.add(-1);
		else
			res.add(arr[secondmaxindex]);
		return res;
	}

	public static void maximumAdjacent(int sizeOfArray, int arr[]) {
		for (int i = 0; i < sizeOfArray - 1; i++) {
			System.out.println(Math.max(arr[i], arr[i + 1]) + " ");
		}
	}

	public static ArrayList<Integer> reverseInGroups(ArrayList<Integer> mv, int n, int k) {
		if (n == 0)
			return null;
		for (int i = 0; i < n; i += k) {
			int l = i;
			int r = Math.min(i + k - 1, n - 1);
			while (l < r) {
				int temp = mv.get(l);
				mv.set(l, mv.get(r));
				mv.set(r, temp);
				l++;
				r--;
			}
		}
		return mv;
	}

	public static void rotateArr(int arr[], int d, int n) {
		int start, end, temp;
		start = 0;
		end = d - 1;
		while (start < end) {
			temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
		start = d;
		end = n - 1;
		while (start < end) {
			temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
		start = 0;
		end = n - 1;
		while (start < end) {
			temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static int minAdjDiff(int arr[], int n) {
		int curr = Math.abs(arr[n - 1] - arr[0]);
		for (int i = 1; i < n; i++) {
			curr = Math.min(curr, Math.abs(arr[i] - arr[i - 1]));
		}
		return curr;
	}

	public static int maxOccured(int L[], int R[], int n, int maxx) {
		int[] arr = new int[1000000];
		int maxidx = -1;
		for (int i = 0; i < n; i++) {
			arr[L[i]]++;
			arr[R[i] + 1]--;
		}
		int currmax = arr[0], res = -1;
		for (int i = 1; i < maxx; i++) {
			arr[i] += arr[i - 1];
			if (currmax < arr[i]) {
				currmax = arr[i];
				res = i;
			}
		}
		return res;
	}

	public static void convertToWave(int arr[], int n) {
		Arrays.sort(arr);
		int temp;
		for (int i = 0; i < n; i += 2) {
			if (i < n - 1) {
				temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}
		}
	}

	// Solution for frequencycount
	/*
	 * 1) Subtract 1 from every element so that the elements become in range from 0
	 * to n-1 for (int j =0; j < n; j++) arr[j] = arr[j]-1;
	 * 
	 * 2) Use every element arr[i] as index and add 'n' to element present at
	 * arr[i]%n to keep track of count of occurrences of arr[i] for (int i=0; i < n;
	 * i++) arr[arr[i]%n] = arr[arr[i]%n] + n;
	 * 
	 * 3) To print counts, simply print the number of times n was added at index
	 * corresponding to every element for (int i =0; i < n; i++) print
	 * "(i + 1) -> arr[i] "
	 */
	public static void frequencycount(int arr[], int n) {
		for (int j = 0; j < n; j++)
			arr[j] = arr[j] - 1;

		// storing the frequency of elements using mathematical formula
		for (int i = 0; i < n; i++)
			arr[arr[i] % n] = arr[arr[i] % n] + n;

		// taking out frequency of each element
		for (int i = 0; i < n; i++)
			arr[i] = (arr[i] / n);
	}

	public static int equilibriumPoint(long arr[], int n) {
		if (n == 1)
			return 1;
		int currres = 0;
		long lsum = 0, rsum = 0;
		for (int i = 0; i < n; i++) {
			rsum += arr[i];
		}
		for (int i = 0; i < n; i++) {
			if (lsum == rsum - arr[i])
				return currres + 1;
			else {
				lsum += arr[i];
				rsum -= arr[i];
				currres++;
			}
		}
		return -1;
	}

	public static ArrayList<Integer> leaders(int arr[], int n) {
		//// Adds Multiple occurences
		ArrayList<Integer> res = new ArrayList<Integer>();
		int currleader = arr[n - 1];
		res.add(currleader);
		for (int i = n - 2; i >= 0; i--) {
			if (arr[i] >= currleader) {
				res.add(arr[i]);
				currleader = arr[i];
			}
		}
		Collections.reverse(res);
		return res;
	}

	public static int missingNumber(int arr[], int size) {
		int countneg = segregateNumbers(arr, size);
		for (int i = 0; i < size - countneg; i++) {
			arr[i] = arr[countneg + i];
		}
		return findMissing(arr, size - countneg);
	}

	public static int segregateNumbers(int[] arr, int size) {
		int j = 0, temp;
		for (int i = 0; i < size; i++) {
			if (arr[i] <= 0) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j++;
			}
		}
		return j;
	}

	public static int findMissing(int[] arr, int size) {
		int x;
		for (int i = 0; i < size; i++) {
			x = Math.abs(arr[i]);
			if (x - 1 < size && arr[x - 1] > 0) {
				arr[x - 1] *= -1;
			}
		}
		for (int i = 0; i < size; i++) {
			if (arr[i] > 0)
				return i + 1;
		}
		return size + 1;
	}

	public static void rearrange(int arr[], int n) {

	}

	public static void arrange(long arr[], int n) {

	}

	public static int maxIndexDiff(int arr[], int n) {
		int i, j, maxDiff;
		int[] min = new int[n];
		int[] max = new int[n];
		min[0] = arr[0];
		max[n - 1] = arr[n - 1];
		for (i = 1; i < n; i++) {
			min[i] = Math.min(min[i - 1], arr[i]);
		}
		for (i = n - 2; i >= 0; i--) {
			max[i] = Math.max(max[i + 1], arr[i]);
		}
		i = 0;
		j = 0;
		maxDiff = -1;
		while (j < n && i < n) {
			if (min[i] <= max[j]) {
				maxDiff = Math.max(maxDiff, j - i);
				j = j + 1;
			} else {
				i = i + 1;
			}
		}
		return maxDiff;
	}

	public static int trappingWater(int arr[], int n) {
		int[] lmax = new int[n], rmax = new int[n];
		lmax[0] = arr[0];
		rmax[n - 1] = arr[n - 1];
		for (int i = 1; i < n; i++) {
			lmax[i] = Math.max(lmax[i - 1], arr[i]);
		}
		for (int i = n - 2; i >= 0; i--) {
			rmax[i] = Math.max(rmax[i + 1], arr[i]);
		}
		int total = 0;
		for (int i = 1; i < n - 1; i++) {
			total += (Math.min(lmax[i], rmax[i]) - arr[i]);
		}
		return total;
	}

	public static void stockBuySell(int price[], int n) {
		StringBuffer str = new StringBuffer();
		if (n == 1) {
			return;
		}
		ArrayList<Interval> sol = new ArrayList<Interval>();
		int i = 0, cnt = 0;
		while (i < n - 1) {
			// Find Local Minima. Note that the limit is (n-2) as we are
			// comparing present element to the next element.
			while ((i < n - 1) && (price[i + 1] <= price[i])) {
				i++;
			}
			// If we reached the end, break as no further solution possible
			if (i == n - 1) {
				break;
			}
			Interval e = new Interval();
			e.buy = i++;

			// Find Local Maxima. Note that the limit is (n-1) as we are
			// comparing to previous element
			while ((i < n) && (price[i] >= price[i - 1]))
				i++;

			// Store the index of maxima
			e.sell = i - 1;
			sol.add(e);
			// Increment count of buy/sell pairs
			cnt++;
		}
		if (cnt == 0) {
			str.append("No Profit");
		} else {
			for (int j = 0; j < sol.size(); j++) {
				str.append("(" + sol.get(j).buy + " " + sol.get(j).sell + ") ");
			}
		}
		System.out.print(str);
	}

	public static boolean checkRotatedAndSorted(int arr[], int num) {
		int inccount = 0, deccount = 0;
	//	int minidx = 0;
		for (int i = 1; i < num; i++) {
			if (arr[i] < arr[i - 1]) {
				deccount++;
			} else {
				inccount++;
			}
		}
		if ((inccount == 1 && arr[num - 1] > arr[0]) || (deccount == 1) && arr[num - 1] < arr[0]) {
			return true;
		} else {
			return false;
		}
	}

	public static int maxSubarraySum(int arr[], int n) {
		int maxsum = arr[0];
		int currsum = arr[0];
		for (int i = 1; i < n; i++) {
			currsum = Math.max(currsum + arr[i], arr[i]);
			maxsum = Math.max(maxsum, currsum);
		}
		return maxsum;
	}

	public static int maxEvenOdd(int arr[], int n) {
		int maxcount = 1, currcount = 1;
		int currmod = arr[0] % 2;
		for (int i = 1; i < n; i++) {
			if (arr[i] % 2 != currmod) {
				currcount++;
			} else {
				currcount = 1;
			}
			currmod = arr[i] % 2;
			maxcount = Math.max(maxcount, currcount);
		}
		return maxcount;
	}

	public static int circularSubarraySum(int a[], int n) {
		int maxLinear = maxSubarraySum(a, n);
		int totalSum = 0, maxCircular;
		if (maxLinear < 0) {
			return maxLinear;
		}
		for (int i = 0; i < n; i++) {
			totalSum += a[i];
			a[i] = -a[i];
		}
		maxCircular = totalSum + maxSubarraySum(a, n);
		return Math.max(maxCircular, maxLinear);
	}
}

class Interval {
	int buy;
	int sell;
}
