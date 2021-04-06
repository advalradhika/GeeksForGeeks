package gfg.week16.heap;

import java.util.*;

public class videoSolutions {

	public static void main(String[] args) {
		int[] arr = { 6, 4, 9, 2, 8, 1 };
		int[] a = { 10, 15, 7, 3, 4 };
		// System.out.println(maxPurchase(a, 10));
		kClosest(a, 8, 2);
		// kSortedArray(a, 3);
		// sortK(a, 6, 3);
		// MaxHeapUtil h = new MaxHeapUtil(arr.length);
		// h.heapSort(arr);
		// for (int i = 0; i < arr.length; i++)
		// System.out.println(arr[i]);
	}

	static void kSortedArray(int[] arr, int k) {
		int n = arr.length;
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		for (int i = 0; i < k + 1; i++) {
			p.add(arr[i]);
		}
		int index = 0;
		for (int i = k + 1; i < n; i++) {
			arr[index++] = p.poll();
			p.add(arr[i]);
		}
		while (!p.isEmpty()) {
			arr[index++] = p.poll();
		}
	}

	static int maxPurchase(int[] arr, int cost) {
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		for (int i = 0; i < arr.length; i++) {
			p.add(arr[i]);
		}
		int res = 0, currCost = 0;
		while (currCost < cost) {
			if (currCost + p.peek() <= cost) {
				res++;
				currCost += p.poll();
			} else {
				break;
			}
		}
		return res;
	}

	static void kLargest(int[] arr, int k) {
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		for (int i = 0; i < k; i++) {
			p.add(arr[i]);
		}
		for (int i = k; i < arr.length; i++) {
			if (p.peek() < arr[i]) {
				p.poll();
				p.add(arr[i]);
			}
		}
		while (!p.isEmpty()) {
			System.out.print(p.poll() + " ");
		}
	}

	static void kClosest(int[] arr, int key, int k) {
		PriorityQueue<diffPair> p = new PriorityQueue<diffPair>();
		for (int i = 0; i < k; i++) {
			p.add(new diffPair(arr[i], Math.abs(arr[i] - key)));
		}
		for (int i = k; i < arr.length; i++) {
			if (p.peek().diff > Math.abs(arr[i]) - key) {
				p.poll();
				p.add(new diffPair(arr[i], Math.abs(arr[i] - key)));
			}
		}
		while (!p.isEmpty()) {
			System.out.print(p.poll().ele + " ");
		}
	}

	static List<Integer> mergeArr(ArrayList<ArrayList<Integer>> arr) {
		List<Integer> res = new ArrayList<Integer>();
		PriorityQueue<TripletUtil> pq = new PriorityQueue<TripletUtil>();
		for (int i = 0; i < arr.size(); i++)
			pq.add(new TripletUtil(arr.get(i).get(0), i, 0));
		while (!pq.isEmpty()) {
			TripletUtil curr = pq.poll();
			res.add(curr.val);
			int ap = curr.aPos;
			int vp = curr.vPos;
			if (vp + 1 < arr.get(ap).size()) {
				pq.add(new TripletUtil(arr.get(ap).get(vp + 1), ap, vp + 1));
			}
		}
		return res;
	}

	static void findMedian(int[] arr) {
		PriorityQueue<Integer> g = new PriorityQueue<Integer>();
		PriorityQueue<Integer> s = new PriorityQueue<Integer>(Collections.reverseOrder());
		s.add(arr[0]);
		System.out.println(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			int x = arr[i];
			if (s.size() > g.size()) {
				if (s.peek() > x) {
					g.add(s.poll());
				} else {
					g.add(x);
				}
				System.out.println((double) ((s.peek() + g.peek()) / 2));
			} else {
				if (x <= s.peek()) {
					s.add(x);
				} else {
					g.add(x);
					s.add(g.poll());
				}
				System.out.println(s.peek());
			}
		}
	}

}

class diffPair implements Comparable<diffPair> {
	Integer ele;
	Integer diff;

	diffPair(int ele, int diff) {
		this.ele = ele;
		this.diff = diff;
	}

	@Override
	public int compareTo(diffPair x) {
		if (this.diff < x.diff)
			return 1;
		else if (this.diff > x.diff)
			return -1;
		return (x.diff).compareTo(this.diff);
	}
}

class TripletUtil implements Comparable<TripletUtil> {
	int val, aPos, vPos;

	TripletUtil(int v, int ap, int vp) {
		val = v;
		aPos = ap;
		vPos = vp;
	}

	public int compareTo(TripletUtil t) {
		if (val <= t.val)
			return -1;
		else
			return 1;
	}
}

class MinHeapUtil {
	int[] arr;
	int size;
	int cap;

	MinHeapUtil(int c) {
		arr = new int[c];
		cap = c;
		size = 0;
	}

	int left(int i) {
		return 2 * i + 1;
	}

	int right(int i) {
		return 2 * i + 2;
	}

	int parent(int i) {
		return (i - 1) / 2;
	}

	void minHeapify(int i) {
		int smallest = i;
		int left = left(i);
		int right = right(i);
		if (left < size && arr[left] < arr[i]) {
			smallest = left;
		}
		if (right < size && arr[right] < arr[i]) {
			smallest = right;
		}
		if (smallest != i) {
			int temp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = temp;
			minHeapify(smallest);
		}
	}

	void insert(int x) {
		if (size == cap) {
			return;
		}
		arr[size] = x;
		size++;
		int i = size - 1;
		while (i != 0 && arr[parent(i)] > arr[i]) {
			int temp = arr[i];
			arr[i] = arr[parent(i)];
			arr[parent(i)] = temp;
			i = parent(i);
		}
	}

	int extractMin() {
		if (size == 0) {
			return Integer.MAX_VALUE;
		} else if (size == 1) {
			size--;
			return arr[0];
		} else {
			int min = arr[0];
			arr[0] = arr[size - 1];
			size--;
			minHeapify(0);
			return min;
		}
	}

	void decreaseKey(int i, int newKey) {
		arr[i] = newKey;
		while (i != 0 && arr[parent(i)] > arr[i]) {
			int temp = arr[i];
			arr[i] = arr[parent(i)];
			arr[parent(i)] = temp;
			i = parent(i);
		}
	}

	void delete(int i) {
		decreaseKey(i, Integer.MIN_VALUE);
		extractMin();
	}

	void buildMinHeap(int[] arr) {
		for (int i = (size - 2) / 2; i >= 0; i--) {
			minHeapify(i);
		}
	}

}

class MaxHeapUtil {
	int[] arr;
	int size;
	int cap;

	MaxHeapUtil(int c) {
		arr = new int[c];
		cap = c;
		size = 0;
	}

	int left(int i) {
		return 2 * i + 1;
	}

	int right(int i) {
		return 2 * i + 2;
	}

	int parent(int i) {
		return (i - 1) / 2;
	}

	void maxHeapify(int i, int[] arr, int n) {
		int largest = i;
		int left = left(i);
		int right = right(i);
		if (left < size && arr[left] > arr[i]) {
			largest = left;
		}
		if (right < size && arr[right] > arr[i]) {
			largest = right;
		}
		if (largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			maxHeapify(largest, arr, n);
		}
	}

	void insert(int x) {
		if (size == cap) {
			return;
		}
		arr[size] = x;
		size++;
		int i = size - 1;
		while (i != 0 && arr[parent(i)] > arr[i]) {
			int temp = arr[i];
			arr[i] = arr[parent(i)];
			arr[parent(i)] = temp;
			i = parent(i);
		}
	}

	int extractMax() {
		if (size == 0) {
			return Integer.MAX_VALUE;
		} else if (size == 1) {
			size--;
			return arr[0];
		} else {
			int min = arr[0];
			arr[0] = arr[size - 1];
			size--;
			maxHeapify(0, arr, arr.length);
			return min;
		}
	}

	void decreaseKey(int i, int newKey) {
		arr[i] = newKey;
		while (i != 0 && arr[parent(i)] > arr[i]) {
			int temp = arr[i];
			arr[i] = arr[parent(i)];
			arr[parent(i)] = temp;
			i = parent(i);
		}
	}

	void delete(int i) {
		decreaseKey(i, Integer.MIN_VALUE);
		extractMax();
	}

	void buildMaxHeap(int[] arr) {
		for (int i = (arr.length - 2) / 2; i >= 0; i--) {
			maxHeapify(i, arr, arr.length);
		}
	}

	void heapSort(int[] arr) {
		buildMaxHeap(arr);
		for (int i = arr.length - 1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			maxHeapify(0, arr, i);
		}
	}

}