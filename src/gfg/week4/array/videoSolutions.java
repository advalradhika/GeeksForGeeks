package gfg.week4.array;

public class videoSolutions {

	public static boolean checkSortedArray(int[] a) {
		if (a.length == 0)
			return true;
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i + 1] < a[i]) {
				return false;
			}
		}
		return true;
	}

	public static void reverseArray(int[] arr) {
		int high = arr.length - 1;
		int temp;
		for (int low = 0; low < high;) {
			temp = arr[low];
			arr[low] = arr[high];
			arr[high] = temp;
			low++;
			high--;
		}
	}

	public static int RemoveDuplicatesFromSortedArray(int[] arr, int n) {
		int res = 1;
		for (int i = 1; i < n; i++) {
			if (arr[res - 1] != arr[i]) {
				arr[res] = arr[i];
				res++;
			}
		}
		return res;
	}

	public static void leftRotateOne(int[] a, int n) {
		int temp = a[0];
		for (int i = 1; i < n; i++) {
			a[i - 1] = a[i];
		}
		a[n - 1] = temp;
	}

	public static void leftRotateByD(int[] a, int n, int d) {
		/// Copy d elements to auxilary array and then shift the elements
		int[] temp = new int[d];
		for (int i = 0; i < d; i++) {
			temp[i] = a[i];
		}
		for (int i = d; i < n; i++) {
			a[i - d] = a[i];
		}
		for (int i = 0; i < d; i++) {
			a[n - d + i] = temp[i];
		}
	}

	public static void Leader(int[] a, int n) {
		int curr_lead = a[n - 1];
		System.out.println(curr_lead);
		for (int i = n - 2; i >= 0; i--) {
			if (curr_lead < a[i]) {
				curr_lead = a[i];
				System.out.println(curr_lead);
			}
		}
	}

	public static int maxDifference(int[] arr, int n) {
		int res = arr[1] - arr[0];
		int minValue = arr[0];
		for (int j = 1; j < n; j++) {
			res = Math.max(res, arr[j] - minValue);
			minValue = Math.min(minValue, arr[j]);
		}
		return res;
	}

	public static void findFrequencies(int[] a, int n) {
		int counter = 1, i = 1;
		while (i < n) {
			while (i < n && a[i] == a[i - 1]) {
				counter++;
				i++;
			}
			System.out.println(a[i - 1] + " " + counter);
			counter = 1;
			i++;
		}
	}

	public static int stockBuySell(int[] price, int start, int end) {
		if (end <= start)
			return 0;
		int profit = 0;
		for (int i = start; i < end; i++) {
			for (int j = i + 1; j <= end; j++) {
				if (price[j] > price[i]) {
					int currprof = price[j] - price[i] + stockBuySell(price, start, i - 1)
							+ stockBuySell(price, j + 1, end);
					profit = Math.max(profit, currprof);
				}
			}
		}
		return profit;
	}

	public static int stockBuySell2(int[] price) {
		int profit = 0;
		for (int i = 1; i < price.length; i++) {
			if (price[i] > price[i - 1]) {
				profit += (price[i] - price[i - 1]);
			}
		}
		return profit;
	}

	public static int largestElement(int[] arr, int n) {
		int index = 0;
		for (int i = 1; i < n; i++) {
			if (arr[i] > arr[index]) {
				index = i;
			}
		}
		return index;
	}

	public static int secondLargest(int[] arr, int n) {
		int largest = 0, seclargest = -1;
		for (int i = 1; i < n; i++) {
			if (arr[i] > arr[largest]) {
				seclargest = largest;
				largest = i;
			} else if (((seclargest == -1) || (arr[i] > arr[seclargest])) && (arr[i] != arr[largest])) {
				seclargest = i;
			}
		}
		return seclargest;
	}

	public static void moveZeroes(int[] arr, int n) {
		int count = 0, temp;
		for (int i = 0; i < n; i++) {
			if (arr[i] != 0) {
				if (i != count) {
					temp = arr[i];
					arr[i] = arr[count];
					arr[count] = temp;
				}
				count++;
			}
		}
	}

	public static int getWater(int[] arr) {
		int n = arr.length;
		int res = 0;
		int[] lmax = new int[n], rmax = new int[n];
		lmax[0] = arr[0];
		for (int i = 1; i < n; i++) {
			lmax[i] = Math.max(arr[i], lmax[i - 1]);
		}
		rmax[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			rmax[i] = Math.max(arr[i], rmax[i + 1]);
		}
		for (int i = 1; i < n - 1; i++) {
			res = res + Math.min(lmax[i], rmax[i]) - arr[i];
		}
		return res;
	}

	public static int maxConsecutiveOnes(int[] arr) {
		//////// Given array consists of 0 and 1
		int curr = 0, res = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			if (arr[i] == 0) {
				curr = 0;
			} else {
				curr++;
				res = Math.max(curr, res);
			}
		}
		return res;
	}

	public static int maxSumSubarray(int[] arr) {
		int n = arr.length;
		int res = arr[0], curr = arr[0];
		for (int i = 1; i < n; i++) {
			curr = Math.max(arr[i], curr + arr[i]);
			res = Math.max(curr, res);
		}
		return res;
	}

	public static int maxEvenOdd(int[] arr) {
		int n = arr.length;
		int res = 1, currrem = arr[0] % 2, currcount = 1;
		for (int i = 1; i < n; i++) {
			if (arr[i] % 2 != currrem) {
				currcount++;
				res = Math.max(currcount, res);
			} else {
				currcount = 1;
			}
			currrem = arr[i] % 2;
		}
		return res;
	}

	public static int maxCircularSum(int[] arr) {
		int maxNormal = maxSumSubarray(arr);
		if (maxNormal < 0)
			return maxNormal;
		int n = arr.length;
		int sumArray = 0;
		for (int i = 1; i < n; i++) {
			sumArray += arr[i];
			arr[i] = -arr[i];
		}
		int maxCircular = sumArray + maxSumSubarray(arr);
		return Math.max(maxCircular, maxNormal);
	}

	public static int majorityElement(int[] arr) {
		int n = arr.length;
		int res = 0, count = 1;
		for (int i = 1; i < n; i++) {
			if (arr[i] == arr[res]) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				count = 1;
				res = i;
			}
		}
		count = 0;
		for (int i = 0; i < n; i++) {
			if (arr[res] == arr[i]) {
				count++;
			}
		}
		if (count <= n / 2) {
			res = -1;
		}
		return res;
	}

	public static void minimumFlips(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			if (arr[i] != arr[i - 1]) {
				if (arr[i] != arr[0])
					System.out.print("From " + i + " to ");
				else
					System.out.println(i - 1);
			}
		}
		if (arr[n - 1] != arr[0])
			System.out.println(n - 1);
	}

	public static int maxSum(int arr[], int n, int k) {
		int curr_sum = 0;
		for (int i = 0; i < k; i++)
			curr_sum += arr[i];
		int max_sum = curr_sum;
		for (int i = k; i < n; i++) {
			curr_sum += (arr[i] - arr[i - k]);
			max_sum = Math.max(max_sum, curr_sum);
		}
		return max_sum;
	}

	public static boolean isSubarraySum(int[] arr, int sum) {
		///// Array must contain only non-negative integers
		int n = arr.length;
		int currsum = arr[0], s = 0;
		for (int e = 1; e < n; e++) {
			while (currsum > sum && s < e - 1) {
				currsum -= arr[s];
				s++;
			}
			if (currsum == sum) {
				return true;
			}
			if (e < n) {
				currsum += arr[e];
			}
		}
		return (currsum == sum);
	}

	public static int[] prefixSumArray(int[] arr) {
		int n = arr.length;
		int[] prefixsum = new int[n];
		prefixsum[0] = arr[0];
		for (int i = 1; i < n; i++) {
			prefixsum[i] = prefixsum[i - 1] + arr[i];
		}
		return prefixsum;
	}

	public static int getSum(int[] prefixsum, int l, int r) {
		if (l != 0)
			return prefixsum[r] - prefixsum[l - 1];
		else
			return prefixsum[r];
	}

	public static boolean isEquilibriam(int[] arr) {
		int n = arr.length;
		int lsum = 0, rsum = 0;
		for (int i = 0; i < n; i++) {
			rsum += arr[i];
		}
		for (int i = 0; i < n; i++) {
			if (lsum == rsum - arr[i]) {
				return true;
			}
			lsum += arr[i];
			rsum -= arr[i];
		}
		return false;
	}

	public static int maxOcc(int L[], int R[], int n) {
		int arr[] = new int[1000];
		for (int i = 0; i < n; i++) {
			arr[L[i]]++;
			arr[R[i] + 1]--;
		}
		int maxm = arr[0], res = 0;
		for (int i = 1; i < 1000; i++) {
			arr[i] += arr[i - 1];
			if (maxm < arr[i]) {
				maxm = arr[i];
				res = i;
			}
		}
		return res;
	}
}
