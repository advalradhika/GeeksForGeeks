package gfg.week16.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class practiceProblems {

	public static void main(String[] args) {
		int[] arr = { 755, 400, 933, 61 };
		kthLargest(arr, 4, 4);
	}

	void buildHeap(int arr[], int n) {
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);
	}

	// Heapify function to maintain heap property.
	void heapify(int arr[], int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		if (l < n && arr[l] > arr[largest]) {
			largest = l;
		}
		if (r < n && arr[r] > arr[largest]) {
			largest = r;
		}
		if (largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			heapify(arr, n, largest);
		}
	}

	// Function to sort an array using Heap Sort.
	public void heapSort(int arr[], int n) {
		buildHeap(arr, n);
		for (int i = n - 1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 0);
		}
	}

	public static ArrayList<Integer> kLargest(int arr[], int n, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		for (int i = 0; i < k; i++) {
			p.add(arr[i]);
		}
		for (int i = k; i < n; i++) {
			if (p.peek() < arr[i]) {
				p.poll();
				p.add(arr[i]);
			}
		}
		while (!p.isEmpty()) {
			res.add(p.poll());
		}
		Collections.reverse(res);
		return res;
	}

	public static int KthLargest(int arr[], int n, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		for (int i = 0; i < k; i++) {
			p.add(arr[i]);
		}
		for (int i = k; i < n; i++) {
			if (p.peek() < arr[i]) {
				p.poll();
				p.add(arr[i]);
			}
		}
		return p.peek();
	}

	public static int KthSmallest(int arr[], int n, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		PriorityQueue<Integer> p = new PriorityQueue<Integer>(Collections.reverseOrder());
		for (int i = 0; i < k; i++) {
			p.add(arr[i]);
		}
		for (int i = k; i < n; i++) {
			if (p.peek() < arr[i]) {
				p.poll();
				p.add(arr[i]);
			}
		}
		return p.peek();
	}

	public static void kthLargest(int arr[], int n, int k) {
		if (k > n) {
			for (int i = 0; i < n; i++) {
				System.out.print("-1 ");
			}
		} else {
			PriorityQueue<Integer> p = new PriorityQueue<Integer>();
			for (int i = 0; i < n; i++) {
				if (p.size() < k) {
					p.add(arr[i]);
				} else {
					if (p.peek() < arr[i]) {
						p.poll();
						p.add(arr[i]);
					}
				}
				if (p.size() < k) {
					System.out.print("-1 ");
				} else {
					System.out.print(p.peek() + " ");
				}
			}
		}

	}

	void kMostFrequent(int arr[], int n, int k) {
		PriorityQueue<Pair> p = new PriorityQueue<Pair>(Collections.reverseOrder());
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], map.get(arr[i]) + 1);
			}
		}
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			if (p.size() < k) {
				p.add(new Pair(e.getValue(), e.getKey()));
			} else {
				int fre = p.peek().freq;
				if (fre < e.getValue()) {
					p.poll();
					p.add(new Pair(e.getValue(), e.getKey()));
				}
			}
		}
		int res = 0;
		while (!p.isEmpty()) {
			res += p.poll().freq;
		}
		System.out.println(res);
	}

	long minCost(long arr[], int n) {
		if (arr.length == 1)
			return arr[0];
		PriorityQueue<Long> p = new PriorityQueue<Long>();
		for (int i = 0; i < n; i++) {
			p.add(arr[i]);
		}
		long res = 0;
		while (p.size() > 2) {
			long a = p.poll();
			long b = p.poll();
			p.add(a + b);
			res += a + b;
		}
		return res + p.poll() + p.poll();
	}

	ArrayList<Integer> nearlySorted(int arr[], int num, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		for (int i = 0; i <= k && i < num; i++) {
			p.add(arr[i]);
		}
		for (int i = k + 1; i < num; i++) {
			res.add(p.poll());
			p.add(arr[i]);
		}
		while (!p.isEmpty()) {
			res.add(p.poll());
		}
		return res;
	}

	public static ArrayList<Integer> mergeKArrays(int[][] arrays, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		PriorityQueue<Triplet> p = new PriorityQueue<Triplet>();
		for (int i = 0; i < k; i++) {
			if (arrays[i].length > 0) {
				p.add(new Triplet(arrays[i][0], i, 0));
			}
		}
		while (!p.isEmpty()) {
			Triplet t = p.poll();
			res.add(t.ele);
			int row = t.row;
			int col = t.col;
			if (col < arrays[row].length - 1) {
				Triplet in = new Triplet(arrays[row][col + 1], row, col + 1);
				p.add(in);
			}
		}
		while (!p.isEmpty()) {
			res.add(p.poll().ele);
		}
		return res;
	}

	static boolean rearrangeCharacters(String str) {
		String newStr = "";
		int n = str.length();
		PriorityQueue<PairUtil> p = new PriorityQueue<PairUtil>(Collections.reverseOrder());
		int[] count = new int[26];
		for (int i = 0; i < n; i++)
			count[str.charAt(i) - 'a']++;
		for (char c = 'a'; c <= 'z'; c++) {
			int val = c - 'a';
			if (count[val] > 0)
				p.add(new PairUtil(count[val], c));
		}
		PairUtil Prev = new PairUtil(-1, '#');
		while (!p.isEmpty()) {
			PairUtil pair = p.poll();
			newStr += pair.value;
			if (Prev.freq > 0) {
				p.add(Prev);
			}
			pair.freq--;
			Prev = pair;
		}
		if (n == newStr.length())
			return true;
		else
			return false;
	}

}

class PairUtil implements Comparable<PairUtil> {
	int freq;
	char value;

	PairUtil(int freq, char value) {
		this.freq = freq;
		this.value = value;
	}

	@Override
	public int compareTo(PairUtil t) {
		if (this.freq < t.freq)
			return -1;
		else if (this.freq > t.freq)
			return 1;
		else
			return 0;
	}
}

class Triplet implements Comparable<Triplet> {
	int ele;
	int row;
	int col;

	Triplet(int ele, int row, int col) {
		this.ele = ele;
		this.row = row;
		this.col = col;
	}

	@Override
	public int compareTo(Triplet t) {
		if (this.ele <= t.ele)
			return -1;
		else
			return 1;
	}
}

class Pair implements Comparable<Pair> {
	int freq;
	int value;

	Pair(int freq, int value) {
		this.freq = freq;
		this.value = value;
	}

	@Override
	public int compareTo(Pair t) {
		if (this.freq > t.freq)
			return -1;
		else
			return 1;
	}
}

class MinHeap {
	int[] harr;
	int capacity;
	int heap_size;

	MinHeap(int cap) {
		heap_size = 0;
		capacity = cap;
		harr = new int[cap];
	}

	int parent(int i) {
		return (i - 1) / 2;
	}

	int left(int i) {
		return (2 * i + 1);
	}

	int right(int i) {
		return (2 * i + 2);
	}

	// Function to extract minimum value in heap and then to store
	// next minimum value at first index.
	int extractMin() {
		if (heap_size <= 0)
			return -1;
		else if (heap_size == 1) {
			heap_size--;
			return harr[0];
		} else {
			int root = harr[0];
			harr[0] = harr[heap_size - 1];
			harr[heap_size - 1] = 0;
			heap_size--;
			MinHeapify(0);
			return root;
		}
	}

	// Function to insert a value in Heap.
	void insertKey(int k) {
		heap_size++;
		harr[heap_size - 1] = k;
		int i = heap_size - 1;
		while (i != 0 && harr[parent(i)] > harr[i]) {
			int temp = harr[i];
			harr[i] = harr[parent(i)];
			harr[parent(i)] = temp;
			i = parent(i);
		}
	}

	// Function to delete a key at ith index.
	void deleteKey(int i) {
		if (i < heap_size) {
			decreaseKey(i, Integer.MIN_VALUE);
			extractMin();
		}
	}

	// Function to change value at ith index and store that value at first index.
	void decreaseKey(int i, int new_val) {
		harr[i] = new_val;
		while (i != 0 && harr[parent(i)] > harr[i]) {
			int temp = harr[i];
			harr[i] = harr[parent(i)];
			harr[parent(i)] = temp;
			i = parent(i);
		}
	}

	/*
	 * You may call below MinHeapify function in above codes. Please do not delete
	 * this code if you are not writing your own MinHeapify
	 */
	void MinHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < heap_size && harr[l] < harr[i])
			smallest = l;
		if (r < heap_size && harr[r] < harr[smallest])
			smallest = r;
		if (smallest != i) {
			int temp = harr[i];
			harr[i] = harr[smallest];
			harr[smallest] = temp;
			MinHeapify(smallest);
		}
	}
}
